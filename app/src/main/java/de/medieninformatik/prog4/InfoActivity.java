package de.medieninformatik.prog4;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.Objects;

import de.medieninformatik.prog4.Model.PizzaModel;
import de.medieninformatik.prog4.Model.ToppingModel;

/**
 * Die Klasse InfoActivity ist für das darstellen der Infoview beim erstellen der Pizza verantwortlich.
 * Hier kann der Nutzer alle Information zu den einezlenen Zutaten seiner Pizza einsehen (Preis und Kalorien)
 * */
public class InfoActivity extends AppCompatActivity {

    private final String TAG = "#InfoActivity";

    //Variablen
    TableLayout tableLayout_info;
    Button back_btn;

    TextView totalPrice;
    TextView totalCal;

    PizzaModel myPizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Laden der Pizza aus vorheriger Activity
        Gson gson = new Gson();
        String gsonString = getIntent().getStringExtra("pizza");
        myPizza = gson.fromJson(gsonString,PizzaModel.class);


        //Initialisierung der Variablen und setzen der Events
        back_btn = findViewById(R.id.btn_infoBack);
        back_btn.setOnClickListener(view -> finish());

        totalCal = findViewById(R.id.text_infoTotalCal);
        totalPrice = findViewById(R.id.text_infoTotalPrice);

        totalCal.setText(myPizza.calcCal() + "cal");
        totalPrice.setText("$"+ myPizza.calcPrize());

        //Aufsetzen der Tabelle mit den Daten der geladenen Pizza
        populateTable();

    }

    /**
     * Die Methode fügt die Daten der Pizza in die Tabelle ein.
     * Hierfür wird für jede Kategorie eine neue Zeile angelegt.
     * */
    private void populateTable(){
        tableLayout_info = findViewById(R.id.tableInfo);

        //Ground
        TableRow ground_row_info = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)ground_row_info.findViewById(R.id.attrib_name)).setText("Ground");
        ((TextView)ground_row_info.findViewById(R.id.attrib_name)).setTypeface(null, Typeface.BOLD_ITALIC);
        ((TextView)ground_row_info.findViewById(R.id.attrib_name)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        tableLayout_info.addView(ground_row_info);

        TableRow ground_row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)ground_row.findViewById(R.id.attrib_name)).setText(myPizza.getGround().getGround_name());
        ((TextView)ground_row.findViewById(R.id.attrib_price)).setText("$" + myPizza.getGround().getGround_price());
        ((TextView)ground_row.findViewById(R.id.attrib_cal)).setText(myPizza.getGround().getGround_cal() + "cal");
        tableLayout_info.addView(ground_row);


        //Sauce
        TableRow sauce_row_info = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)sauce_row_info.findViewById(R.id.attrib_name)).setText("Sauce");
        ((TextView)sauce_row_info.findViewById(R.id.attrib_name)).setTypeface(null, Typeface.BOLD_ITALIC);
        ((TextView)sauce_row_info.findViewById(R.id.attrib_name)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        tableLayout_info.addView(sauce_row_info);


        TableRow sauce_row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)sauce_row.findViewById(R.id.attrib_name)).setText(myPizza.getSauce().getSauce_name());
        ((TextView)sauce_row.findViewById(R.id.attrib_price)).setText("$" + myPizza.getSauce().getSauce_price());
        ((TextView)sauce_row.findViewById(R.id.attrib_cal)).setText(myPizza.getSauce().getSauce_cal() + "cal");
        tableLayout_info.addView(sauce_row);

        //Toppings
        TableRow toppings_row_info = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)toppings_row_info.findViewById(R.id.attrib_name)).setText("Toppings");
        ((TextView)toppings_row_info.findViewById(R.id.attrib_name)).setTypeface(null, Typeface.BOLD_ITALIC);

        ((TextView)toppings_row_info.findViewById(R.id.attrib_name)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tableLayout_info.addView(toppings_row_info);

        for (ToppingModel toppingModel: myPizza.getToppings()) {
            TableRow topping_row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
            ((TextView)topping_row.findViewById(R.id.attrib_name)).setText(toppingModel.getTopping_name());
            ((TextView)topping_row.findViewById(R.id.attrib_price)).setText("$" + toppingModel.getTopping_price());
            ((TextView)topping_row.findViewById(R.id.attrib_cal)).setText(toppingModel.getTopping_cal() + "cal");
            tableLayout_info.addView(topping_row);
        }

        //Cheese
        TableRow cheese_row_info = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)cheese_row_info.findViewById(R.id.attrib_name)).setText("Cheese");
        ((TextView)cheese_row_info.findViewById(R.id.attrib_name)).setTypeface(null, Typeface.BOLD_ITALIC);

        ((TextView)cheese_row_info.findViewById(R.id.attrib_name)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        tableLayout_info.addView(cheese_row_info);

        TableRow cheese_row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)cheese_row.findViewById(R.id.attrib_name)).setText(myPizza.getCheese().getCheese_name());
        ((TextView)cheese_row.findViewById(R.id.attrib_price)).setText("$" + myPizza.getCheese().getCheese_price());
        ((TextView)cheese_row.findViewById(R.id.attrib_cal)).setText(myPizza.getCheese().getCheese_cal() + "cal");
        tableLayout_info.addView(cheese_row);

        //Size
        TableRow size_row_info = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)size_row_info.findViewById(R.id.attrib_name)).setText("Size");
        ((TextView)size_row_info.findViewById(R.id.attrib_name)).setTypeface(null, Typeface.BOLD_ITALIC);

        ((TextView)size_row_info.findViewById(R.id.attrib_name)).setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);

        tableLayout_info.addView(size_row_info);


        TableRow size_row = (TableRow) LayoutInflater.from(this).inflate(R.layout.attrib_row, null);
        ((TextView)size_row.findViewById(R.id.attrib_name)).setText(myPizza.getSize().getSize_name());
        ((TextView)size_row.findViewById(R.id.attrib_price)).setText("$" + myPizza.getSize().getSize_price());
        ((TextView)size_row.findViewById(R.id.attrib_cal)).setText(myPizza.getSize().getSize_cal() + "cal");
        tableLayout_info.addView(size_row);
    }


}