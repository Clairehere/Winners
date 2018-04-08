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
import android.view.animation.AlphaAnimation;
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
    private static int SPLASH_TIME_OUT = 100;
    private Model hero1, hero2, hero3, hero4, hero5, hero6 = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Animation anim = new AlphaAnimation(0.0f, 1.0f);
        //parametre de la frequence clignotement
        anim.setDuration(700);
        //temps qu'il reste invisible
        anim.setStartOffset(800);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        final TextView choisisHeros = findViewById(R.id.textview_choose);
        final ImageView croix1 = findViewById(R.id.imageButton);
        final ImageView croix2 = findViewById(R.id.imageButton3);
        final ImageView croix3 = findViewById(R.id.imageButton2);
        final Button button = findViewById(R.id.button2);


        final ImageView icone1 = findViewById(R.id.imageView_hero1);
        final ImageView icone2 = findViewById(R.id.imageView_hero2);
        final ImageView icone3 = findViewById(R.id.imageView_hero3);


        final Intent intent2 = new Intent(MainActivity.this, Combat.class);


        //API
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";

        //recup API dans GridView
        final GridView listView = findViewById(R.id.gridView);
        final ArrayList<Model> test = new ArrayList<>();

        // Recup API
        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            final TextView tvname = findViewById(R.id.textView_nameG);
                            final TextView tvattaque = findViewById(R.id.textView_strG);
                            final TextView tvSpd = findViewById(R.id.textView_spdG);
                            final TextView tvvie = findViewById(R.id.textView_vieG);
                            final ImageView profil = findViewById(R.id.imageView_superherosG);
                            for (int g = 0; g < response.length(); g++) {
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


                                test.add(new Model(names10, vie, speed, attaque, imgprofil));

                                Adapter adapter = new Adapter(MainActivity.this, test);
                                listView.setAdapter(adapter);

                                //Envoit Intent
                                // final Model troisEnUn = new Model(names10, vie, speed, attaque, imgprofil);
                                // intent.putExtra("troisEnUn", troisEnUn);

                                //Action lorsqu'on click sur la selection d'unHeros
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        //test
                                        // Model un = new Model(names10, vie, speed, attaque, imgprofil);
                                        //  Model test2 = test.get(position);
                                        // intent2.putExtra("data", test2);
                                        icone1.setVisibility(View.VISIBLE);
                                        icone2.setVisibility(View.VISIBLE);
                                        icone3.setVisibility(View.VISIBLE);
                                        croix1.setVisibility(View.VISIBLE);
                                        tvvie.setVisibility(View.VISIBLE);
                                        tvattaque.setVisibility(View.VISIBLE);
                                        tvSpd.setVisibility(View.VISIBLE);
                                        tvname.setVisibility(View.VISIBLE);
                                        profil.setVisibility(View.VISIBLE);
                                        croix1.setVisibility(View.VISIBLE);


                                        //anim image
                                        final ImageView zoom = findViewById(R.id.imageView_superherosG);
                                        //Animation zoomAnimation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom);
                                        //zoom.startAnimation(zoomAnimation);

                                        //anim nom
                                        TextView zoom1 = findViewById(R.id.textView_nameG);
                                        final Animation zoomAnimation1 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom);
                                        zoom1.startAnimation(zoomAnimation1);

                                        ObjectAnimator.ofFloat(zoom, "translationY", 0, 300).setDuration(900).start();


                                        //Initialisation parcelable
                                        final Model currentMonster = test.get(position);
                                        final Model currentMonsterdeux = test.get(position + 50);

                                        //changement stats et des petites images
                                        TextView nameG = findViewById(R.id.textView_nameG);
                                        nameG.setText(currentMonster.getName());
                                        final ImageView imgG = findViewById(R.id.imageView_superherosG);
                                        imgG.setVisibility(view.VISIBLE);
                                        Glide.with(MainActivity.this).load(currentMonster.getImage()).into(imgG);
                                        TextView lifeG = findViewById(R.id.textView_vieG);
                                        lifeG.setText(String.valueOf(currentMonster.getLife()));
                                        TextView forceG = findViewById(R.id.textView_strG);
                                        forceG.setText(String.valueOf(currentMonster.getAttaque()));
                                        TextView speedG = findViewById(R.id.textView_spdG);
                                        speedG.setText(String.valueOf(currentMonster.getSpeed()));

                                        // Actions sur les 3petits heros
                                        ImageButton button1 = findViewById(R.id.imageButton);
                                        button1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //recup données parcelable pour heros 1et 4
                                                hero1 = currentMonster;
                                                hero4 = currentMonsterdeux;

                                                //chgment texte et anim
                                                choisisHeros.setText("Plus que 2");
                                                croix2.setVisibility(View.VISIBLE);

                                                final ImageView hero1 = findViewById(R.id.imageView_photohero1);
                                                hero1.setVisibility(View.VISIBLE);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero1);

                                                ObjectAnimator.ofFloat(choisisHeros, "translationX", 20, 0).setDuration(100).start();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ObjectAnimator.ofFloat(choisisHeros, "translationX", 20, 0).setDuration(100).start();
                                                    }
                                                }, SPLASH_TIME_OUT);

                                            }
                                        });

                                        //2eme heros
                                        ImageButton button2 = findViewById(R.id.imageButton3);
                                        button2.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //recuperation données heros 2 et 5
                                                hero2 = currentMonster;
                                                hero5 = currentMonsterdeux;

                                                //anim et chement texte
                                                croix3.setVisibility(View.VISIBLE);
                                                choisisHeros.setText("Plus qu'1");
                                                final ImageView hero2 = findViewById(R.id.imageView_photohero2);
                                                hero2.setVisibility(View.VISIBLE);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero2);

                                                ObjectAnimator.ofFloat(choisisHeros, "translationX", 70, 0).setDuration(200).start();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ObjectAnimator.ofFloat(choisisHeros, "translationX", 70, 0).setDuration(200).start();
                                                    }
                                                }, SPLASH_TIME_OUT);

                                            }
                                        });

                                        //heros 3
                                        ImageButton button3 = findViewById(R.id.imageButton2);
                                        button3.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                //recup données heros 3 et 6
                                                hero3 = currentMonster;
                                                hero6 = currentMonsterdeux;

                                                //anim
                                                choisisHeros.setText("C'est parti !!!");
                                                final ImageView hero3 = findViewById(R.id.imageViewphotohero3);
                                                hero3.setVisibility(View.VISIBLE);
                                                Glide.with(MainActivity.this).load(currentMonster.getImage()).into(hero3);

                                                ObjectAnimator.ofFloat(choisisHeros, "translationX", 70, 0).setDuration(400).start();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        ObjectAnimator.ofFloat(choisisHeros, "translationX", 70, 0).setDuration(400).start();
                                                    }
                                                }, SPLASH_TIME_OUT);

                                                button.setVisibility(View.VISIBLE);
                                                button.startAnimation(zoomAnimation1);
                                            }
                                        });


                                    }
                                });

                            }


                        } catch (JSONException e) {
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


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hero1 != null && hero2 != null && hero3 != null) {
                    intent2.putExtra("hero1", hero1);
                    intent2.putExtra("hero2", hero2);
                    intent2.putExtra("hero3", hero3);
                    intent2.putExtra("hero4", hero4);
                    intent2.putExtra("hero5", hero5);
                    intent2.putExtra("hero6", hero6);
                    startActivity(intent2);
                }

            }

        });


    }


}
