package fr.wildcodeschool.winners;

import android.content.Intent;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ImageView profil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Button button = findViewById(R.id.bu)

        profil =findViewById(R.id.imageView_superherosG);
        final Intent intent = new Intent(MainActivity.this, Combat.class);

        //String url = "https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=" + API_KEY;
        // Crée une file d'attente pour les requêtes vers l'API
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        // TODO : URL de la requête vers l'API
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";





        final GridView listView =findViewById(R.id.gridView);
                final ArrayList<Model> test = new ArrayList<>();
        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            //djfhehf
                            TextView tvname = findViewById(R.id.textView_nameG);
                            TextView tvattaque = findViewById(R.id.textView_strG);
                            TextView tvSpd = findViewById(R.id.textView_spdG);
                            TextView tvvie = findViewById(R.id.textView_vieG);
                            profil =findViewById(R.id.imageView_superherosG);
                            for( int g=0; g<response.length();g++) {
                                JSONObject name10 = (JSONObject) response.get(g);
                                final String names10 = name10.getString("name");
                                tvname.setText(names10);


                                JSONObject stat = name10.getJSONObject("powerstats");
                                int attaque = stat.getInt("strength");
                                tvattaque.setText(String.valueOf(attaque));

                                final int speed = stat.getInt("speed");
                                tvSpd.setText(String.valueOf(speed));

                                int vie = stat.getInt("durability");
                                tvvie.setText(String.valueOf(vie));

                                JSONObject img = name10.getJSONObject("images");
                                final String imgprofil = img.getString("sm");
                                Glide.with(MainActivity.this).load(imgprofil).into(profil);


                                test.add(new Model(names10, vie, speed, attaque,imgprofil));

                                Adapter adapter = new Adapter(MainActivity.this, test);
                                listView.setAdapter(adapter);


                                Model troisEnUn = new Model(names10, vie, speed, attaque,imgprofil);
                                intent.putExtra("troisEnUn", troisEnUn);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        ImageView zoom = findViewById(R.id.imageView_superherosG);
                                        Animation zoomAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom);
                                        zoom.startAnimation(zoomAnimation);

                                        TextView zoom1= findViewById(R.id.textView_nameG);
                                        Animation zoomAnimation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom);
                                        zoom1.startAnimation(zoomAnimation1);

                                        final Model currentMonster = test.get(position);
                                        TextView nameG =findViewById(R.id.textView_nameG);
                                        nameG.setText(currentMonster.getName());

                                        final ImageView imgG =findViewById(R.id.imageView_superherosG);
                                        Glide.with(MainActivity.this).load(currentMonster.getImage()).into(imgG);

                                       TextView lifeG = findViewById(R.id.textView_vieG);
                                       lifeG.setText(String.valueOf(currentMonster.getLife()));

                                       TextView forceG = findViewById(R.id.textView_strG);
                                       forceG.setText(String.valueOf(currentMonster.getAttaque()));

                                       TextView speedG = findViewById(R.id.textView_spdG);
                                       speedG.setText(String.valueOf(currentMonster.getSpeed()));

                                       // 3 heros
                                        ImageButton button1 = findViewById(R.id.imageButton);
                                        button1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ImageView hero1 = findViewById(R.id.imageView_photohero1);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero1);

                                                Intent intentHeros1 = new Intent(MainActivity.this, Combat.class);

                                                String names = currentMonster.getName().toString();
                                               intentHeros1.putExtra("name1", names);

                                            }
                                        });

                                        ImageButton button2 = findViewById(R.id.imageButton3);
                                        button2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ImageView hero2 = findViewById(R.id.imageView_photohero2);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero2);

                                            }
                                        });

                                        ImageButton button3 = findViewById(R.id.imageButton2);
                                        button3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                ImageView hero3 = findViewById(R.id.imageViewphotohero3);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero3);
                                            }
                                        });








                                    }
                                });

                            }


                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }


                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Afficher l'erreur
                        Log.d("VOLLEY_ERROR", "onErrorResponse: " + error.getMessage());
                    }
                }
        );

        // On ajoute la requête à la file d'attente
        requestQueue.add(jsonArrayRequest);



        Button button =findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }

        });

    }


}
