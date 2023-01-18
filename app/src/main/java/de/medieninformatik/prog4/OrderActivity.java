package de.medieninformatik.prog4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.gson.Gson;

import java.util.Objects;

import de.medieninformatik.prog4.Database.PizzaDatabase;
import de.medieninformatik.prog4.Model.PizzaModel;


/**
 * Die OrderActivity Klasse ist für den Bestellprozess und das Speichern der Pizza in der Datenbank verantwortlich.
 * Der Nutzer kann die Pizza hier speichern und/oder zum Hautpmenü zurückkehren.
*/
public class OrderActivity extends AppCompatActivity {


    //Variablen
    private final String TAG = "#OrderActivity";
    private PizzaDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Objects.requireNonNull(getSupportActionBar()).hide();

        //Aufrufen der Datenbank



        //Laden der konfigurierten Pizza
        Gson gson = new Gson();
        String gsonString = getIntent().getStringExtra("pizza");
        PizzaModel myPizza = gson.fromJson(gsonString,PizzaModel.class);


        Button btn_mainMenu = findViewById(R.id.btn_mainMenu);
        Button btn_savePizza = findViewById(R.id.btn_savePizza);

        //Speichern der Pizza.
        //Ein Dialog zum eingeben des Namens wird geöffnet.
        btn_savePizza.setOnClickListener(view -> {
            db = Room.databaseBuilder(getApplicationContext(),
                    PizzaDatabase.class, "pizza-database").allowMainThreadQueries().build();
            AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
            builder.setTitle("Name your Pizza");

             final EditText input = new EditText(OrderActivity.this);
             input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
             builder.setView(input);

             builder.setPositiveButton("Save", (dialog, which) -> {
                 //Abfrage, ob Nutzer etwas eingebenen hat
                 if(input.getText().toString().length() <= 0 ){
                     toastMessage("You have to give your pizza a longer name");

                     //Abfrage, ob Name der Pizza schon vorhanden ist
                 }else if(db.pizzaModelDao().alreadyExits(input.getText().toString())){
                     toastMessage("You have to give your pizza a different name");

                 }else{
                     savePizzaToDB(myPizza,input.getText().toString());
                     toastMessage("Pizza saved successfully");
                 }
             });
             builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
             builder.show();
       });

       btn_mainMenu.setOnClickListener(view -> goBackToMainMenu());
    }



    /**
     * Die Methode lässte den Nutzer zurück in Hautpmenü navigieren
     * */
    private void goBackToMainMenu(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Die Methode erzeugt Toast, um den Nutzer zu informieren.
     * */
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Die Methode speichert die eben konfigurierte Pizza unter dem Namen den der Nutzer zuvor eingeben hat.
     * Anschließend wird der Nutzer ins Hautpmenü navigiert.
     * */
    private void savePizzaToDB(PizzaModel pizza, String name){
        pizza.setName(name);
        PizzaDatabase db = Room.databaseBuilder(getApplicationContext(),
                PizzaDatabase.class, "pizza-database").allowMainThreadQueries().build();

        db.pizzaModelDao().insertAll(pizza);
        goBackToMainMenu();
    }
}