package fr.wildcodeschool.winners;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;

public class Combat extends AppCompatActivity {
    private ImageView gif1,gif2, gif3, gif4, gif5, gif6;

    public Model model1, model2;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);


        gif1 = (ImageView) findViewById(R.id.imageView_gif_player1);
        gif3 = (ImageView) findViewById(R.id.imageView_gif_player2);

        //https://i.pinimg.com/originals/03/bc/f8/03bcf88e49e97cff1834b2a9136bede7.jpg

        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/4-fantastique/drdoom-airattack.gif").into(gif1);
        Glide.with(Combat.this).load("https://media.giphy.com/media/qBt26PtiWjHAk/giphy.gif").into(gif3);


        //pour les name
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
        ImageView ivA1 = findViewById(R.id.iv_hero1_player2);
        ImageView ivA2 = findViewById(R.id.iv_hero2_player2);
        ImageView ivA3 = findViewById(R.id.iv_hero3_player2);

        //pour les progressbar
        ProgressBar prbarH1 = findViewById(R.id.progressBar_hero1_player1);
        ProgressBar prbarH2 = findViewById(R.id.progressBar_hero2_player1);
        ProgressBar prbarH3 = findViewById(R.id.progressBar_hero3_player1);
        ProgressBar prbarA1 = findViewById(R.id.progressBar_hero1_player2);
        ProgressBar prbarA2 = findViewById(R.id.progressBar_hero2_player2);
        ProgressBar prbarA3 = findViewById(R.id.progressBar_hero3_player2);


        Intent intent= getIntent();
         model1= getIntent().getParcelableExtra("hero1");
         model2= getIntent().getParcelableExtra("hero2");
        Model model3= getIntent().getParcelableExtra("hero3");
        final Model model4=getIntent().getParcelableExtra("hero4");
        Model model5= getIntent().getParcelableExtra("hero5");
        Model model6=getIntent().getParcelableExtra("hero6");



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

        final TextView a = findViewById(R.id.textView_a);
        final TextView b =findViewById(R.id.textView_b);
        a.setText(String.valueOf(model1.getLife()));
        b.setText(String.valueOf(model4.getLife()));


       //prbarH1.setProgress(model1.getAttaque());
        Button button =findViewById(R.id.button_attaquer);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                while (model1.getLife() >0 && model4.getLife() >0){
                    int newlife = model4.getLife()-model1.getAttaque();
                    model4.setLife(newlife);
                    b.setText(String.valueOf(newlife));
                } if (model4.getLife() >0 && model1.getLife()>0) {
                    int newlife2 = model1.getLife()-model4.getAttaque();
                    model1.setLife(newlife2);
                    a.setText(String.valueOf(newlife2));
                } else {
                    Toast.makeText(Combat.this, "Votre joueur a tué un ennemi", Toast.LENGTH_SHORT).show();
                }

                int newlife = model2.getLife()-model1.getAttaque();
                model2.setLife(newlife);
                b.setText(String.valueOf(newlife));


            }
        });







    }

    public void fight (Model player1, Model player2) {
        player2.setLife(player2.getLife()-player1.getAttaque());
    }

}
