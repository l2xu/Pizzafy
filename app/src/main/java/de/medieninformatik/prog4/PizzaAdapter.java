package de.medieninformatik.prog4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.medieninformatik.prog4.Model.PizzaModel;

/**
 * Die Klasse bildet einen custom-adapter für die Listview mit den gespeicherten Pizzen (savedPizzasActivity)
 * */
public class PizzaAdapter extends ArrayAdapter {

    public PizzaAdapter(Context context, List<PizzaModel> pizzas) {
        super(context, 0, pizzas);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Holt das aktuelle Item aus der Liste
        PizzaModel pizza = (PizzaModel) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.saved_pizza, parent, false);
        }
        // Initialisierung der Variablen aus der View
        TextView text_name = convertView.findViewById(R.id.text_savedName);
        TextView text_cal = convertView.findViewById(R.id.text_savedCal);
        TextView text_price = convertView.findViewById(R.id.text_savedPrice);


        //Einfügen der Daten
        text_name.setText(pizza.getName());
        text_cal.setText(pizza.calcCal() + "cal");
        text_price.setText("$"+pizza.calcPrize());


        return convertView;
    }
}
