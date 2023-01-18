package de.medieninformatik.prog4;



import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

/**
 * Die MainActivity Klasse ist der Startpunkt der App.
 * Von hieraus kann der Nutzer eine neue Pizza erstellen oder seine gespeicherten Pizzen aubrufen.
 * */
public class MainActivity extends AppCompatActivity {

    //Variablen
    Button btn_createNewPizza = null;
    Button btn_savedPizzas = null;
    private final String TAG = "#MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btn_createNewPizza = findViewById(R.id.btn_createNew);
        btn_createNewPizza.setOnClickListener(view -> openCreateActivity());

        btn_savedPizzas = findViewById(R.id.btn_savedPizzas);
        btn_savedPizzas.setOnClickListener(view -> openSavedPizzasActivity());
        Log.d(TAG,"MainActivity Started");
    }

    /**
     * Die methode öffnet die CreateActivity, in der die Pizza konfiguriert werden kann.
     * */
    private void openCreateActivity(){
        Intent intent = new Intent(this, CreateActivity.class);
        startActivity(intent);
    }

    /**
     * Die methode öffnet die SavedPizzasActivity, in der die Pizza gespeicherten Pizzen eingesehen, gelöscht und bearbeitet werden können.
     * */
    private void openSavedPizzasActivity(){
        Intent intent = new Intent(this, SavedPizzasActivity.class);
        startActivity(intent);
    }
}