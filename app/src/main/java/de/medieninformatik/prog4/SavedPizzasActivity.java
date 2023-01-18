package de.medieninformatik.prog4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.List;
import java.util.Objects;

import de.medieninformatik.prog4.Database.PizzaDatabase;
import de.medieninformatik.prog4.Model.PizzaModel;

/**
 * Die Klasse ist für die gespeicherten Pizzen verantwortlich.
 * Hier kann der Nutzer alle gespeicherten Pizzen einsehen, diese löschen oder erneut auswählen um sie zu bearbeiten bzw. erneut zu bestellen.
 * In der Liste sieht der Nutzer den Namen der Pizza, den Gesamtpreis und die Gesamtkalorienanzahl.
 * */
public class SavedPizzasActivity extends AppCompatActivity {


    //Variablen
    private List<PizzaModel> pizzaList;
    private PizzaAdapter adapter;
    private PizzaDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_pizzas);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btn_back = findViewById(R.id.btn_savedPizza_back);
        btn_back.setOnClickListener(view -> goToMainActivity());

        //Zugriff auf Datenbank
        db = Room.databaseBuilder(getApplicationContext(),
                PizzaDatabase.class, "pizza-database").allowMainThreadQueries().build();

        ListView listView = findViewById(R.id.list_savedPizzas);

        //Laden aller gespeicherten Pizzen aus der Datenbank
        pizzaList = db.pizzaModelDao().getAll();

        //Setzen des Adapters für die Listview
        adapter = new PizzaAdapter(this, pizzaList);

        //Setzen des OnClickListeners für jedes Item aus der Liste
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            PizzaModel pizzaModel = (PizzaModel) adapterView.getItemAtPosition(i);
            //Laden der Pizza
            loadSavedPizza(pizzaModel);
        });

        //Setzen des OnLongClickListener für jedes Item aus der Liste.
        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {

            //Alert Dialog wird erzeugt: Nutzer wird gefragt, ob der die Pizza wirklich löschen möchte.
            AlertDialog.Builder builder = new AlertDialog.Builder(SavedPizzasActivity.this);
            builder.setMessage("Do you want to delete this pizza?")
                    .setCancelable(true)
                    .setPositiveButton("YES", (dialog, id) -> {
                        PizzaModel pizzaModel = (PizzaModel) adapterView.getItemAtPosition(i);
                        //löscht die Pizza
                        db.pizzaModelDao().delete(pizzaModel);
                        refreshList();
                    })
            .setNegativeButton("Cancel",((dialogInterface, i1) -> dialogInterface.dismiss()));
            AlertDialog alert = builder.create();
            alert.show();
            return true;
        });
        listView.setAdapter(adapter);
    }

    /**
     * Die Methode navigiert den Nutzer zurück zur MainActivity
     * */
    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Die Methode aktualisiert die Liste
     * */
    private void refreshList() {
        List<PizzaModel> updatedList = db.pizzaModelDao().getAll();
        pizzaList.clear();
        pizzaList.addAll(updatedList);
        adapter.notifyDataSetChanged();
    }

    /**
     * Die Methode lädt die gespeicherte Pizza in die CreateActivity, damit diese dort konfiguriert, bzw. erneut bestellt werden kann.
     * */
    private void loadSavedPizza(PizzaModel pizza){
        Gson gson = new Gson();
        String pizzaString = gson.toJson(pizza);
        Intent intent = new Intent(this, CreateActivity.class);
        intent.putExtra("pizza",pizzaString);
        startActivity(intent);
    }
}