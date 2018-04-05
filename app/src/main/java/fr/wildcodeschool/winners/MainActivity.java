package fr.wildcodeschool.winners;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
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

public class MainActivity extends AppCompatActivity {
    private final static String API_KEY = "remplace cette valeur par ta clé API";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //String url = "https://api.openweathermap.org/data/2.5/weather?q=Toulouse&appid=" + API_KEY;
        // Crée une file d'attente pour les requêtes vers l'API
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // TODO : URL de la requête vers l'API
        String url = "https://cdn.rawgit.com/akabab/superhero-api/0.2.0/api/all.json";





        // Création de la requête vers l'API, ajout des écouteurs pour les réponses et erreurs possibles
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                                                       TextView tvname = findViewById(R.id.textView_name);
                            TextView tvattaque = findViewById(R.id.textView_str);
                            TextView tvSpd = findViewById(R.id.textView_spd);
                            TextView tvvie = findViewById(R.id.textView_vie);
                            ImageView profil =findViewById(R.id.imageView_superheros);

                            JSONObject name10= (JSONObject) response.get(10);
                            String names10 = name10.getString("name");
                            tvname.setText(names10);


                            JSONObject stat = name10.getJSONObject("powerstats");
                            int attaque =stat.getInt("strength");
                            tvattaque.setText(String.valueOf(attaque));

                            int speed =stat.getInt("speed");
                            tvSpd.setText(String.valueOf(speed));

                            int vie = stat.getInt("durability");
                            tvvie.setText(String.valueOf(vie));

                            JSONObject img = name10.getJSONObject("images");
                            String imgprofil =img.getString("sm");
                            Glide.with(MainActivity.this).load(imgprofil).into(profil);



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
    }
}
