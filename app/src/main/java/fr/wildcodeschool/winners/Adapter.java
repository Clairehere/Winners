package fr.wildcodeschool.winners;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

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
        return convertView;
    }
}
