package fr.wildcodeschool.winners;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

public class Combat extends AppCompatActivity {
    private ImageView gif1,gif2, gif3, gif4, gif5, gif6;
    /*private Model player1;
    private Model player2;
    private Model player3;
    private Model player4;
    private Model player5;
    private Model player6;

    public Combat(Model player1, Model player2, Model player3, Model player4, Model player5, Model player6) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;
        this.player4 = player4;
        this.player5 = player5;
        this.player6 = player6;
    }*/






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);

        TextView unH = findViewById(R.id.textView_hero1_player1);
        TextView deuxH = findViewById(R.id.textView_hero2_player1);
        TextView troisH = findViewById(R.id.textView_hero3_player1);

        TextView unA = findViewById(R.id.textView_hero1_player2);
        TextView deuxA = findViewById(R.id.textView_hero2_player2);
        TextView troisA = findViewById(R.id.textView_hero3_player2);



        //Model troisEnUnValue = getIntent().getExtras().getParcelable("troisEnUn");

//        Glide.with(Combat.this).load(troisEnUnValue.getImage()).into(un);
      //  TextView life = findViewById(R.id.textView_name2);
    //    life.setText(String.valueOf(troisEnUnValue.getLife()));

        //Model unH = getIntent().getExtras().getParcelable("un");
        //ImageView unnnn =findViewById(R.id.imageView4);
       // Glide.with(Combat.this).load(unH.getImage()).into(unnnn);

        //TextView unHer =findViewById(R.id.textView_name1);
       // unHer.setText(String.valueOf(unH.getLife()));

        //Intent intent= getIntent();
        //Model name1= getIntent().getParcelableExtra("data");
        //TextView name = findViewById(R.id.textView_name1);
        //name.setText(name1.getName());

       gif1 = (ImageView) findViewById(R.id.imageView_gif_player1);
        gif3 = (ImageView) findViewById(R.id.imageView_gif_player2);

        //https://i.pinimg.com/originals/03/bc/f8/03bcf88e49e97cff1834b2a9136bede7.jpg

        Glide.with(Combat.this).load("http://www.icone-gif.com/gif/super-heros/4-fantastique/drdoom-airattack.gif").into(gif1);
        Glide.with(Combat.this).load("https://media.giphy.com/media/qBt26PtiWjHAk/giphy.gif").into(gif3);

    }


}
