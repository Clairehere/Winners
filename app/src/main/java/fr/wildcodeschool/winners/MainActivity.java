package fr.wildcodeschool.winners;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
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
    private static int SPLASH_TIME_OUT=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //Button button = findViewById(R.id.bu)


        final Intent intent = new Intent(MainActivity.this, Combat.class);
        final Intent intent2= new Intent(MainActivity.this, Combat.class);

        //API
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";

        //recup API dans GridView
        final GridView listView =findViewById(R.id.gridView);
        final ArrayList<Model> test = new ArrayList<>();

        // Recup API
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            TextView tvname = findViewById(R.id.textView_nameG);
                            TextView tvattaque = findViewById(R.id.textView_strG);
                            TextView tvSpd = findViewById(R.id.textView_spdG);
                            TextView tvvie = findViewById(R.id.textView_vieG);
                            ImageView profil =findViewById(R.id.imageView_superherosG);
                            for( int g=0; g<response.length();g++) {
                                JSONObject name10 = (JSONObject) response.get(g);
                                final String names10 = name10.getString("name");
                                tvname.setText(names10);

                                JSONObject stat = name10.getJSONObject("powerstats");
                                final int attaque = stat.getInt("strength");
                                tvattaque.setText(String.valueOf(attaque));
                                final int speed = stat.getInt("speed");
                                tvSpd.setText(String.valueOf(speed));
                                final int vie = stat.getInt("durability");
                                tvvie.setText(String.valueOf(vie));

                                JSONObject img = name10.getJSONObject("images");
                                final String imgprofil = img.getString("sm");
                                Glide.with(MainActivity.this).load(imgprofil).into(profil);


                                test.add(new Model(names10, vie, speed, attaque,imgprofil));

                                Adapter adapter = new Adapter(MainActivity.this, test);
                                listView.setAdapter(adapter);

                                //Envoit Intent
                                final Model troisEnUn = new Model(names10, vie, speed, attaque,imgprofil);
                                intent.putExtra("troisEnUn", troisEnUn);


                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        Model un = new Model(names10, vie, speed, attaque,imgprofil);
                                        Model test2 = test.get(position);
                                        intent2.putExtra("data", test2);
                                        intent2.putExtra("data2", un);
                                        //anim image
                                        ImageView zoom = findViewById(R.id.imageView_superherosG);
                                        Animation zoomAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom);
                                        zoom.startAnimation(zoomAnimation);

                                        //anim nom
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

                                       // 3 heros images
                                        ImageButton button1 = findViewById(R.id.imageButton);
                                        button1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final ImageView hero1 = findViewById(R.id.imageView_photohero1);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero1);

                                                //anim image
                                                ObjectAnimator.ofFloat(hero1,"translationX",0,20).setDuration(200).start();
                                                ObjectAnimator.ofFloat(hero1,"translationY",0,20).setDuration(200).start();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ObjectAnimator.ofFloat(hero1,"translationX",20,0).setDuration(200).start();
                                                        ObjectAnimator.ofFloat(hero1,"translationY",20,0).setDuration(200).start();
                                                    }
                                                }, SPLASH_TIME_OUT);



                                                //intent2.putExtra("un", un);

                                            }
                                        });

                                        ImageButton button2 = findViewById(R.id.imageButton3);
                                        button2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final ImageView hero2 = findViewById(R.id.imageView_photohero2);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero2);

                                                //anim image
                                                ObjectAnimator.ofFloat(hero2,"translationX",0,20).setDuration(200).start();
                                                ObjectAnimator.ofFloat(hero2,"translationY",0,20).setDuration(200).start();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ObjectAnimator.ofFloat(hero2,"translationX",20,0).setDuration(200).start();
                                                        ObjectAnimator.ofFloat(hero2,"translationY",20,0).setDuration(200).start();
                                                    }
                                                }, SPLASH_TIME_OUT);

                                            }
                                        });

                                        ImageButton button3 = findViewById(R.id.imageButton2);
                                        button3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                final ImageView hero3 = findViewById(R.id.imageViewphotohero3);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero3);

                                                //anim image
                                                ObjectAnimator.ofFloat(hero3,"translationX",0,20).setDuration(200).start();
                                                ObjectAnimator.ofFloat(hero3,"translationY",0,20).setDuration(200).start();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ObjectAnimator.ofFloat(hero3,"translationX",20,0).setDuration(200).start();
                                                        ObjectAnimator.ofFloat(hero3,"translationY",20,0).setDuration(200).start();
                                                    }
                                                }, SPLASH_TIME_OUT);
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
                startActivity(intent2);
            }

        });




    }


}
