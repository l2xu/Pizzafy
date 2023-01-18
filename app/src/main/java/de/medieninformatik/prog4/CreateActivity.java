package de.medieninformatik.prog4;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Objects;

import de.medieninformatik.prog4.Model.CheeseModel;
import de.medieninformatik.prog4.Model.GroundModel;
import de.medieninformatik.prog4.Model.PizzaModel;
import de.medieninformatik.prog4.Model.SauceModel;
import de.medieninformatik.prog4.Model.SizeModel;
import de.medieninformatik.prog4.Model.ToppingModel;

/**
 * Die Klasse Create Activity ist für die View beim erstellen der Pizza zuständig.
 * Hier kann die eingene Pizza nach belieben zusammengestellt werden.
 * Alle Zutaten werden hier erstellt.
 *
 * Future: Die Zutaten könnten über ein backend dynamisch eingeladen werden, anstelle von hardcoded Werten.
 * */
public class CreateActivity extends AppCompatActivity {


    private final String TAG = "#CreateActivity";


    //Variablen
    private RadioGroup ground_radioGroup;
    private RadioButton ground_radioBtn;

    private RadioGroup sauce_radioGroup;
    private RadioButton sauce_radioBtn;

    private RadioGroup cheese_radioGroup;
    private RadioButton cheese_radioBtn;

    private RadioGroup size_radioGroup;
    private RadioButton size_radioBtn;

    private TableLayout tableLayout;

    private TextView pizza_totalPrice;
    private TextView pizza_totalCal;
    private  ToggleButton toggle_toppings_pineapple;
    private  ToggleButton toggle_toppings_mushrooms;
    private  ToggleButton toggle_toppings_broccoli;
    private  ToggleButton toggle_toppings_tomatoes;
    private  ToggleButton toggle_toppings_tuna;
    private  ToggleButton toggle_toppings_onions;
    private  ToggleButton toggle_toppings_garlic;
    private  ToggleButton toggle_toppings_salami;


    private GroundModel ground_normal;
    private GroundModel ground_thin;
    private GroundModel ground_thick;
    private GroundModel ground_crusty;

    private SauceModel sauce_tomatoes;
    private SauceModel sauce_hollandaise;
    private SauceModel sauce_herb;
    private SauceModel sauce_none;

    private ToppingModel topping_pineapple;
    private ToppingModel topping_mushrooms;
    private ToppingModel topping_broccoli;
    private ToppingModel topping_tomatoes;
    private ToppingModel topping_tuna;
    private ToppingModel topping_onions;
    private ToppingModel topping_garlic;
    private ToppingModel topping_salami;

    private CheeseModel cheese_gouda;
    private CheeseModel cheese_mozzarella;
    private CheeseModel cheese_vegan;
    private CheeseModel cheese_none;

    private SizeModel size_16;
    private SizeModel size_24;
    private SizeModel size_40;

    PizzaModel pizza;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_create);

        //Zusweisung der Varibalen
        pizza_totalPrice = findViewById(R.id.text_priceTotal);
        pizza_totalCal = findViewById(R.id.text_calTotal);

        Button btn_info = findViewById(R.id.btn_info);
        Button btn_order = findViewById(R.id.btn_order);
        Button btn_back = findViewById(R.id.btn_back);

        ground_radioGroup = findViewById(R.id.radioGroup_ground);
        sauce_radioGroup = findViewById(R.id.radioGroup_sauce);
        cheese_radioGroup = findViewById(R.id.radioGroup_cheese);
        size_radioGroup = findViewById(R.id.radioGroup_size);

        tableLayout = findViewById(R.id.tableLayout_toppings);

        toggle_toppings_pineapple = findViewById(R.id.toggle_toppings_pineapple);
        toggle_toppings_mushrooms = findViewById(R.id.toggle_toppings_mushrooms);
        toggle_toppings_broccoli = findViewById(R.id.toggle_toppings_broccoli);
        toggle_toppings_tomatoes = findViewById(R.id.toggle_toppings_tomatoes);
        toggle_toppings_tuna = findViewById(R.id.toggle_toppings_tuna);
        toggle_toppings_onions = findViewById(R.id.toggle_toppings_onions);
        toggle_toppings_garlic = findViewById(R.id.toggle_toppings_garlic);
        toggle_toppings_salami = findViewById(R.id.toggle_toppings_salami);

        //Erstellen der Zutaten
        createIngredients();

        //Pizza laden
        checkForDeliverdPizza();

        //Auswahl auf Basis der geladenen Pizza initialisieren
        initView();

        //Changelistene für Auswahlgruppen festlegen
        addChangeListenerToGround();
        addChangeListenerToSauce();
        addChangeListenerToCheese();
        addChnageListenerToSize();
        addChangeListenerToToppings();

        //OnClickListener für weitere Views/Activities
        btn_order.setOnClickListener(view -> switchToOrderActivity());
        btn_info.setOnClickListener(view -> switchToInfoActivity());
        btn_back.setOnClickListener(view -> switchToMainActivity());

        //Pizzapreis und Kalorien updaten
        updatePizza();
    }



    /** Diese Methode erstellt die ganzen Zutaten mit denen eine Pizza belegt/asugestattet werden kann.
    ** In Zukunft könnte man diesen Porzess durch dynamisches Laden von Zutaten erweitern.*/
    private void createIngredients(){
        ground_normal = new GroundModel("normal", 2, 70);
        ground_thin = new GroundModel("thin", 1, 50);
        ground_thick = new GroundModel("thick", 3, 80);
        ground_crusty = new GroundModel("crusty", 3, 60);

        sauce_tomatoes = new SauceModel("tomatoes",1,60);
        sauce_hollandaise = new SauceModel("hollandaise",2,80);
        sauce_herb = new SauceModel("herbs",1,40);
        sauce_none = new SauceModel("none",0,0);

        topping_pineapple = new ToppingModel("pineapple", 2,20);
        topping_mushrooms = new ToppingModel("mushrooms", 1,20);
        topping_broccoli = new ToppingModel("broccoli", 1,15);
        topping_tomatoes = new ToppingModel("tomatoes", 2,5);
        topping_tuna = new ToppingModel("tuna", 3,50);
        topping_onions = new ToppingModel("onions", 1,20);
        topping_garlic = new ToppingModel("garlic", 1,15);
        topping_salami = new ToppingModel("salami", 3,70);

        cheese_gouda = new CheeseModel("gouda", 2,120);
        cheese_mozzarella = new CheeseModel("mozzarella", 2,100);
        cheese_vegan = new CheeseModel("vegan", 4,80);
        cheese_none = new CheeseModel("none", 0,0);

        size_16 = new SizeModel("16cm", 1,1);
        size_24 = new SizeModel("24cm", 2,2);
        size_40 = new SizeModel("40cm", 3,3);

    }


    /**
     * Die Methode schaut, ob eine Pizza reingeladen wurde, oder ob eine neue erstellt werden muss.
     * Je nachdem was eintrifft, wir die Pizza hier initialisiert.
     * */
    private void checkForDeliverdPizza() {
        Intent intent = getIntent();
        //Es gibt eine Pizza zum laden (Der Nutzer lädt eine Pizza um sie zu bearbeiten/erneut zu bestellen)
        if(intent.hasExtra("pizza")){
            Gson gson = new Gson();
            String pizzaString = getIntent().getStringExtra("pizza");
             pizza = gson.fromJson(pizzaString,PizzaModel.class);
        //Eine neue "base" Pizza wird erstellt.
        }else{
            pizza = new PizzaModel(ground_normal,sauce_tomatoes, new ArrayList<>(),cheese_gouda, size_24);
        }
    }

    /**
     * Die Methode setzt die Viewcomponenten so, dass sie mit der vorher initialisierten Pizza übereinstimmen.
     * */
    private void initView() {

        //GROUND
        Log.d(TAG,"PIZZA BODEN IST: " +pizza.getGround().getGround_name());

        switch (pizza.getGround().getGround_name()){
            case "normal":
                ground_radioGroup.check(R.id.radio_ground_normal);
                break;
            case "thin":
                ground_radioGroup.check(R.id.radio_ground_thin);
                break;
            case "thick":
                ground_radioGroup.check(R.id.radio_ground_thick);
                break;
            case "crusty":
                ground_radioGroup.check(R.id.radio_ground_crusty);
                break;
            default:
                break;
        }

        Log.d(TAG,"PIZZA SAUCE IST: " +pizza.getSauce().getSauce_name());

        //SAUCE
        switch (pizza.getSauce().getSauce_name()){
            case "tomatoes":
                sauce_radioGroup.check(R.id.radio_sauce_tomatoes);
                break;
            case "hollandaise":
                sauce_radioGroup.check(R.id.radio_sauce_hollandaise);
                break;
            case "herbs":
                sauce_radioGroup.check(R.id.radio_sauce_herb);
                break;
            case "none":
                sauce_radioGroup.check(R.id.radio_sauce_none);
                break;
        }

        //TOPPINGS
       ArrayList<ToppingModel> pizzaModelArrayList = pizza.getToppings();
        for (ToppingModel topping: pizzaModelArrayList) {
            switch (topping.getTopping_name()){
                case "pineapple":
                    toggle_toppings_pineapple.setChecked(true);
                    break;
                case "mushrooms":
                    toggle_toppings_mushrooms.setChecked(true);
                    break;
                case "broccoli":
                    toggle_toppings_broccoli.setChecked(true);
                    break;
                case "tomatoes":
                    toggle_toppings_tomatoes.setChecked(true);
                    break;
                case "tuna" :
                    toggle_toppings_tuna.setChecked(true);
                    break;
                case "onions":
                    toggle_toppings_onions.setChecked(true);
                    break;
                case "garlic":
                    toggle_toppings_garlic.setChecked(true);
                    break;
                case "salami":
                    toggle_toppings_salami.setChecked(true);
                    break;
                default:
                    Log.d(TAG,"No toppings found");

            }
        }

        Log.d(TAG,"Toppings: " );
        for (ToppingModel topping: pizza.getToppings()) {
            Log.d(TAG, topping.getTopping_name() );
        }

        //CHEESE
        Log.d(TAG,"PIZZA CHEESE IST: " +pizza.getCheese().getCheese_name() );

        switch (pizza.getCheese().getCheese_name()){
            case "gouda":
                cheese_radioGroup.check(R.id.radio_cheese_gouda);
                break;
            case "mozzarella":
                cheese_radioGroup.check(R.id.radio_cheese_mozzarella);
                break;
            case "vegan":
                cheese_radioGroup.check(R.id.radio_cheese_vegan);
                break;
            case "none":
                cheese_radioGroup.check(R.id.radio_cheese_none);
                break;
            default:
                Log.d(TAG,"Couldn't find the right cheese");
                break;
        }

        //SIZE
        Log.d(TAG,"PIZZA SIZE IST: " +pizza.getSize().getSize_name() );
        switch (pizza.getSize().getSize_name()){
            case "16cm":
                size_radioGroup.check(R.id.radio_size_16);
                break;
            case "24cm":
                size_radioGroup.check(R.id.radio_size_24);
                break;
            case "40cm":
                size_radioGroup.check(R.id.radio_size_40);
                break;
            default:
                Log.d(TAG,"Couldn't find the right size");
                break;
        }

        //Repopulating the Toppings-list
        ArrayList<ToppingModel> backupToppingsList = new ArrayList<>(pizza.getToppings());
        pizza.getToppings().clear();

        for (ToppingModel topping: backupToppingsList) {
            switch (topping.getTopping_name()){
                case "pineapple":
                    pizza.addTopping(topping_pineapple);
                    break;
                case "mushrooms":
                    pizza.addTopping(topping_mushrooms);
                    break;
                case "broccoli":
                    pizza.addTopping(topping_broccoli);
                    break;
                case "tomatoes":
                    pizza.addTopping(topping_tomatoes);
                    break;
                case "tuna":
                    pizza.addTopping(topping_tuna);
                    break;
                case "onions":
                    pizza.addTopping(topping_onions);
                    break;
                case "garlic":
                    pizza.addTopping(topping_garlic);
                    break;
                case "salami":
                    pizza.addTopping(topping_salami);
                    break;
                default:
                    Log.d(TAG,"Something went wrong while repopulating the topping list");
                    break;
            }
        }
    }


    /**
     * Die Methode fügt einen Changelistene zu allen Radiobuttons aus der Radiobutton-group hinzu.
     * */
    private  void addChangeListenerToGround(){

        ground_radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            ground_radioBtn = findViewById(i);
            switch (getResources().getResourceEntryName(ground_radioBtn.getId())){
                case "radio_ground_normal":
                    pizza.setGround(ground_normal);
                    break;
                case "radio_ground_thin":
                    pizza.setGround(ground_thin);
                    break;
                case "radio_ground_thick":
                    pizza.setGround(ground_thick);
                    break;
                case "radio_ground_crusty":
                    pizza.setGround(ground_crusty);
                    break;
            }
            updatePizza();


        });
    }

    /**
     * Die Methode fügt einen Changelistener zu allen Radiobuttons aus der Radiobutton-group hinzu.
     * */
    private  void addChangeListenerToSauce(){
        sauce_radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            sauce_radioBtn = findViewById(i);
            switch (getResources().getResourceEntryName(sauce_radioBtn.getId())){
                case "radio_sauce_tomatoes":
                    pizza.setSauce(sauce_tomatoes);
                    break;
                case "radio_sauce_hollandaise":
                    pizza.setSauce(sauce_hollandaise);
                    break;
                case "radio_sauce_herb":
                    pizza.setSauce(sauce_herb);
                    break;
                case "radio_sauce_none":
                    pizza.setSauce(sauce_none);
                    break;
            }
            updatePizza();


        });
    }


    /**
     * Die Methode fügt einen Changelistener zu allen Elementen der Tabelle hinzu.
     * */
    private void addChangeListenerToToppings(){
        for (int x = 0; x<tableLayout.getChildCount(); x++){
            View child = tableLayout.getChildAt(x);
            if(child instanceof TableRow) {
                TableRow row = (TableRow) child;
                for (int y = 0; y<row.getChildCount(); y++){
                    View view = row.getChildAt(y);
                    if(view instanceof ToggleButton){
                        ToggleButton toggle_btn = (ToggleButton) view;
                        toggle_btn.setOnCheckedChangeListener((compoundButton, b) -> {
                            switch (getResources().getResourceEntryName(toggle_btn.getId())){
                                case "toggle_toppings_pineapple":
                                    pizza.changeTopping(topping_pineapple);
                                    break;
                                case "toggle_toppings_mushrooms":
                                    pizza.changeTopping(topping_mushrooms);
                                    break;
                                case "toggle_toppings_broccoli":
                                    pizza.changeTopping(topping_broccoli);
                                    break;
                                case "toggle_toppings_tomatoes":
                                    pizza.changeTopping(topping_tomatoes);
                                    break;
                                case "toggle_toppings_tuna":
                                    pizza.changeTopping(topping_tuna);
                                    break;
                                case "toggle_toppings_onions":
                                    pizza.changeTopping(topping_onions);
                                    break;
                                case "toggle_toppings_garlic":
                                    pizza.changeTopping(topping_garlic);
                                    break;
                                case "toggle_toppings_salami":
                                    pizza.changeTopping(topping_salami);
                                    break;
                            }
                            updatePizza();
                        });
                    }
                }
            }
        }

    }


    /**
     * Die Methode fügt einen Changelistener zu allen Radiobuttons aus der Radiobutton-group hinzu.
     * */
    private  void addChangeListenerToCheese(){

        RadioGroup cheese_radioGroup = findViewById(R.id.radioGroup_cheese);

        cheese_radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
            cheese_radioBtn = findViewById(i);

            switch (getResources().getResourceEntryName(cheese_radioBtn.getId())){
                case "radio_cheese_gouda":
                    pizza.setCheese(cheese_gouda);
                    break;
                case "radio_cheese_mozzarella":
                    pizza.setCheese(cheese_mozzarella);
                    break;
                case "radio_cheese_vegan":
                    pizza.setCheese(cheese_vegan);
                    break;
                case "radio_cheese_none":
                    pizza.setCheese(cheese_none);
                    break;
                default:
                    break;
            }
            updatePizza();
        });
    }


    /**
     * Die Methode fügt einen Changelistener zu allen Radiobuttons aus der Radiobutton-group hinzu.
     * */
    private void addChnageListenerToSize(){
            size_radioGroup = findViewById(R.id.radioGroup_size);
            size_radioGroup.setOnCheckedChangeListener((radioGroup, i) -> {
                size_radioBtn = findViewById(i);

                switch (getResources().getResourceEntryName(size_radioBtn.getId())){
                    case "radio_size_16":
                        pizza.setSize(size_16);
                        break;
                    case "radio_size_24":
                        pizza.setSize(size_24);
                        break;
                    case "radio_size_40":
                        pizza.setSize(size_40);
                        break;
                }
                updatePizza();
            });
    }



    /**
     * Die Methode updaten den Preis und die Kalorienanzahl der Pizza.
     * Die Methode wird nach jedem Change aufgerufen, sodass der Preis und die Kalorienanzahl direkt aktualisiert werden.
     * */
    private void updatePizza(){
        int newPrice =  pizza.calcPrize();
        pizza_totalPrice.setText("$" + newPrice);

        int newCal = pizza.calcCal();
        pizza_totalCal.setText(newCal + "cal");
    }


    /**
     * Die Methode wechselt die Activity zur Order-Activity.
     * Hierbei wird die Pizza serialisiert, um als extra in die nächste Activity übertragen zu werden.
     * */
    private void switchToOrderActivity(){
        Gson gson = new Gson();
        String gsonString = gson.toJson(pizza);
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("pizza",gsonString);
        startActivity(intent);
        finish();
    }

    /**
     * Die Methode wechselt die Activity zur Info-Activity
     * Hierbei wird die Pizza serialisiert, um als extra in die nächste Activity übertragen zu werden.
     * */
    private void switchToInfoActivity(){
        Gson gson = new Gson();
        String gsonString = gson.toJson(pizza);
        Intent intent = new Intent(this, InfoActivity.class);
        intent.putExtra("pizza",gsonString);
        startActivity(intent);
    }

    /**
     * Die Methode wechselt die Activity zur Main-Activity
     * */
    private void switchToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}



