package fr.wildcodeschool.winners;

import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

public class Combat extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 100;
    public Model model1, model2, model3, model4, model5, model6;
    private ImageView gif4, gif5, gif6, gif7, gif8, gif9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        // les gifs des combattants
        gif4 = findViewById(R.id.imageView_hero1_player);
        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/marvel/marvel002.gif").into(gif4);
        gif5 = findViewById(R.id.imageView_hero1_player2);
        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/alien-vs-predator/3.gif").into(gif5);
        gif6 = findViewById(R.id.imageView_hero2_player1);
        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/marvel/marvel006.gif").into(gif6);
        gif7 = findViewById(R.id.imageView_hero2_player2);
        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/alien-vs-predator/3.gif").into(gif7);
        gif8 = findViewById(R.id.imageView_hero3_player1);
        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/marvel/marvel008.gif").into(gif8);
        gif9 = findViewById(R.id.imageView_hero3_player2);
        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/alien-vs-predator/3.gif").into(gif9);


        // Localisation des text Name
        TextView unH = findViewById(R.id.textView_hero1_player1);
        TextView deuxH = findViewById(R.id.textView_hero2_player1);
        TextView troisH = findViewById(R.id.textView_hero3_player1);
        TextView unA = findViewById(R.id.textView_hero1_player2);
        TextView deuxA = findViewById(R.id.textView_hero2_player2);
        TextView troisA = findViewById(R.id.textView_hero3_player2);


        //pour les imageview de haut de page
        final ImageView ivH1 = findViewById(R.id.iv_hero1_player1);
        ImageView ivH2 = findViewById(R.id.iv_hero2_player1);
        ImageView ivH3 = findViewById(R.id.iv_hero3_player1);
        final ImageView ivA1 = findViewById(R.id.iv_hero1_player2);
        ImageView ivA2 = findViewById(R.id.iv_hero2_player2);
        ImageView ivA3 = findViewById(R.id.iv_hero3_player2);

        final TextView a = findViewById(R.id.textView_a);
        final TextView b = findViewById(R.id.textView_b);
        final TextView c = findViewById(R.id.textView_c);
        final TextView d = findViewById(R.id.textView_d);
        final TextView e = findViewById(R.id.textView_e);
        final TextView f = findViewById(R.id.textView_f);

        //pour les progressbar
        //  ProgressBar prbarH1 = findViewById(R.id.progressBar_hero1_player1);
        //ProgressBar prbarH2 = findViewById(R.id.progressBar_hero2_player1);
        //ProgressBar prbarH3 = findViewById(R.id.progressBar_hero3_player1);
        //  ProgressBar prbarA1 = findViewById(R.id.progressBar_hero1_player2);
        // ProgressBar prbarA2 = findViewById(R.id.progressBar_hero2_player2);
        // ProgressBar prbarA3 = findViewById(R.id.progressBar_hero3_player2);


        // parcelables recuperers
        model1 = getIntent().getParcelableExtra("hero1");
        model2 = getIntent().getParcelableExtra("hero2");
        model3 = getIntent().getParcelableExtra("hero3");
        model4 = getIntent().getParcelableExtra("hero4");
        model5 = getIntent().getParcelableExtra("hero5");
        model6 = getIntent().getParcelableExtra("hero6");

        //Changements des noms
        unH.setText(model1.getName());
        Glide.with(Combat.this).load(model1.getImage()).into(ivH1);
        deuxH.setText(model2.getName());
        Glide.with(Combat.this).load(model2.getImage()).into(ivH2);
        troisH.setText(model3.getName());
        Glide.with(Combat.this).load(model3.getImage()).into(ivH3);
        unA.setText(model4.getName());
        Glide.with(Combat.this).load(model4.getImage()).into(ivA1);
        deuxA.setText(model5.getName());
        Glide.with(Combat.this).load(model5.getImage()).into(ivA2);
        troisA.setText(model6.getName());
        Glide.with(Combat.this).load(model6.getImage()).into(ivA3);


        a.setText(String.valueOf(model1.getLife()));
        b.setText(String.valueOf(model2.getLife()));
        c.setText(String.valueOf(model3.getLife()));
        d.setText(String.valueOf(model4.getLife()));
        e.setText(String.valueOf(model5.getLife()));
        f.setText(String.valueOf(model6.getLife()));


        Button button = findViewById(R.id.button_attaquer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                while (model1.getLife() > 0 && model4.getLife() > 0) {
                    //anim combat joueur 1
                    ObjectAnimator.ofFloat(gif4, "translationX", 0, 750).setDuration(400).start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimator.ofFloat(gif4, "translationX", 750, 0).setDuration(400).start();
                        }
                    }, SPLASH_TIME_OUT);

                    //Attaque du joueur 1
                    int newlife = model4.getLife() - model1.getAttaque();
                    model4.setLife(newlife);
                    d.setText(String.valueOf(newlife));

                    //si adv joueur 2 meurt :Tombe
                    if (model4.getLife() <= 0) {

                        Glide.with(Combat.this).load(R.drawable.tombe).into(gif5);
                        gif5.getLayoutParams().width = 130;
                        gif5.getLayoutParams().width = 130;
                    }


                    //sinon joueur 2 attaque
                    if (model4.getLife() > 0 && model1.getLife() > 0) {

                        int newlife2 = model1.getLife() - model4.getAttaque();
                        model1.setLife(newlife2);
                        a.setText(String.valueOf(newlife2));


                        //si joueur1 meurt: tombe
                        if (model1.getLife() <= 0) {

                            Glide.with(Combat.this).load(R.drawable.tombe).into(gif4);
                            gif4.getLayoutParams().width = 130;
                            gif4.getLayoutParams().width = 130;
                        }


                    } else {
                        Toast.makeText(Combat.this, "Votre joueur a tué  votre ennemi", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        Button bt2 = findViewById(R.id.button_attaquer5);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (model5.getLife() > 0 && model2.getLife() > 0) {
                    //anim attaque joueur 1 heros 2
                    ObjectAnimator.ofFloat(gif6, "translationX", 0, 350).setDuration(500).start();
                    // ObjectAnimator.ofFloat(gif4,"translationY",0,600).setDuration(800).start();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimator.ofFloat(gif6, "translationX", 350, 0).setDuration(500).start();
                            //    ObjectAnimator.ofFloat(gif4,"translationY",20,0).setDuration(200).start();
                        }
                    }, SPLASH_TIME_OUT);

                    //attaque joueur 1 heros 2
                    int newlife = model5.getLife() - model2.getAttaque();
                    model5.setLife(newlife);
                    e.setText(String.valueOf(newlife));
                    //si joueur 2 heros 2 meurt: tombe
                    if (model5.getLife() <= 0) {

                        Glide.with(Combat.this).load(R.drawable.tombe).into(gif7);
                        gif7.getLayoutParams().width = 130;
                        gif7.getLayoutParams().width = 130;
                    }


                    //sinon joueur 2 attaque
                    if (model5.getLife() > 0 && model2.getLife() > 0) {

                        int newlife2 = model2.getLife() - model5.getAttaque();
                        model2.setLife(newlife2);
                        b.setText(String.valueOf(newlife2));

                        //si joueur 1 meurt: tombe
                        if (model2.getLife() <= 0) {

                            Glide.with(Combat.this).load(R.drawable.tombe).into(gif6);
                            gif6.getLayoutParams().width = 130;
                            gif6.getLayoutParams().width = 130;
                        }


                    } else {
                        Toast.makeText(Combat.this, "Votre joueur a tué  votre ennemi", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        Button bt3 = findViewById(R.id.button_attaquer3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                while (model6.getLife() > 0 && model3.getLife() > 0) {

                    // anim attaque joueur 1 heros 3
                    ObjectAnimator.ofFloat(gif8, "translationX", 0, 750).setDuration(500).start();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            ObjectAnimator.ofFloat(gif8, "translationX", 750, 0).setDuration(500).start();
                        }
                    }, SPLASH_TIME_OUT);

                    //attaque joueur 1 heros 3
                    int newlife = model6.getLife() - model3.getAttaque();
                    model6.setLife(newlife);
                    f.setText(String.valueOf(newlife));

                    // si joueur 2 heros 3 meurt = tombe
                    if (model6.getLife() <= 0) {
                        Glide.with(Combat.this).load(R.drawable.tombe).into(gif9);
                        gif9.getLayoutParams().width = 130;
                        gif9.getLayoutParams().width = 130;
                    }

                    //sinon joueur 2 attaque
                    if (model6.getLife() > 0 && model3.getLife() > 0) {

                        int newlife2 = model3.getLife() - model6.getAttaque();
                        model3.setLife(newlife2);
                        c.setText(String.valueOf(newlife2));

                        //si joueur 1 attaqué meurt : tombe
                        if (model3.getLife() <= 0) {

                            Glide.with(Combat.this).load(R.drawable.tombe).into(gif8);
                            gif8.getLayoutParams().width = 130;
                            gif8.getLayoutParams().width = 130;
                        }


                    } else {
                        Toast.makeText(Combat.this, "Votre joueur a tué  votre ennemi", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }


}
