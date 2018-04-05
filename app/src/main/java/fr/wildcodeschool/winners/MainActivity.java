package fr.wildcodeschool.winners;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
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

        profil =findViewById(R.id.imageView_superheros);
        final Intent intent = new Intent(MainActivity.this, Combat.class);

        //String url = "https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=" + API_KEY;
        // Crée une file d'attente pour les requêtes vers l'API
        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        // TODO : URL de la requête vers l'API
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";

        final ListView listView =findViewById(R.id.listVieww);
        final ArrayList<Model> test = new ArrayList<>();
        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            //djfhehf
                            TextView tvname = findViewById(R.id.textView_name);
                            TextView tvattaque = findViewById(R.id.textView_str);
                            TextView tvSpd = findViewById(R.id.textView_spd);
                            TextView tvvie = findViewById(R.id.textView_vie);
                            profil =findViewById(R.id.imageView_superheros);
                            for( int g=0; g<response.length();g++) {
                                JSONObject name10 = (JSONObject) response.get(g);
                                String names10 = name10.getString("name");
                                tvname.setText(names10);


                                JSONObject stat = name10.getJSONObject("powerstats");
                                int attaque = stat.getInt("strength");
                                tvattaque.setText(String.valueOf(attaque));

                                int speed = stat.getInt("speed");
                                tvSpd.setText(String.valueOf(speed));

                                int vie = stat.getInt("durability");
                                tvvie.setText(String.valueOf(vie));

                                JSONObject img = name10.getJSONObject("images");
                                String imgprofil = img.getString("sm");
                                Glide.with(MainActivity.this).load(imgprofil).into(profil);


                                test.add(new Model(names10, vie, speed, attaque,imgprofil));

                                Adapter adapter = new Adapter(MainActivity.this, test);
                                listView.setAdapter(adapter);


                                Model troisEnUn = new Model(names10, vie, speed, attaque,imgprofil);
                                intent.putExtra("troisEnUn", troisEnUn);



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
