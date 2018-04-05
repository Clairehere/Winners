package fr.wildcodeschool.winners;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wilder on 05/04/18.
 */

public class Adapter extends ArrayAdapter<Model> {
    public Adapter(Context context, ArrayList<Model> trip) {
        super(context, 0, trip);
    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Model trip = (Model) getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_trip, parent, false);

        }
        TextView name= convertView.findViewById(R.id.textView_test);
        name.setText(trip.getName());

        TextView life= convertView.findViewById(R.id.textView_vie);
        life.setText(String.valueOf(trip.getLife()));

        TextView speed =convertView.findViewById(R.id.textView_vitesse);
        speed.setText(String.valueOf(trip.getSpeed()));

        TextView attaque = convertView.findViewById(R.id.textView_attaque);
        attaque.setText(String.valueOf(trip.getAttaque()));

        /*
        ImageView imgProfil = convertView.findViewById(R.id.imageView_profil);
        Drawable drawable= ContextCompat.getDrawable((getContext(),trip.getImage());
        imgProfil.setImageDrawable(drawable);
        */

        ImageView img =convertView.findViewById(R.id.imageView_profil);
        Glide.with(getContext()).load(trip.getImage()).into(img);






        return convertView;
    }
}
