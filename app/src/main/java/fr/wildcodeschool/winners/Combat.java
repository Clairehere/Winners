package fr.wildcodeschool.winners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Combat extends AppCompatActivity {
    private Model player1;
    private Model player2;
    private Model player3;
    private  Model player4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combat);
        ImageView un=findViewById(R.id.imageView);
        ImageView deux = findViewById(R.id.imageView5);
        ImageView trois = findViewById(R.id.imageView6);
        ImageView quatre = findViewById(R.id.imageView3);
        ImageView cinq = findViewById(R.id.imageView2);
        ImageView six = findViewById(R.id.imageView4);

        Model troisEnUnValue = getIntent().getExtras().getParcelable("troisEnUn");
        Glide.with(Combat.this).load(troisEnUnValue.getImage()).into(un);
        Glide.with(Combat.this).load(troisEnUnValue.getImage()).into(deux);




    }


}
