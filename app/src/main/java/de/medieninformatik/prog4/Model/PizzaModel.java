package de.medieninformatik.prog4.Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/**
 * Die Klasse dient als Pizzaobject, in dem alle Information der Pizza gespeichert werden.
 * Zusätzlich dient die Klass noch als Entity für die Datenbank.
 */
@Entity
public class PizzaModel {

    @ColumnInfo(name = "toppings")
    private ArrayList<ToppingModel> toppings;

    @PrimaryKey
    @NonNull
    private String name = "default_pizza";

    @Embedded public GroundModel ground;

    @Embedded public SauceModel sauce;

    @Embedded public CheeseModel cheese;

    @Embedded public SizeModel size;


    public PizzaModel(GroundModel ground, SauceModel sauce, ArrayList<ToppingModel> toppings, CheeseModel cheese,SizeModel size ) {
        this.ground = ground;
        this.sauce = sauce;
        this.cheese = cheese;
        this.size = size;
        this.toppings = toppings;
    }

    //Getter and Setter
    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public GroundModel getGround() {
        return ground;
    }

    public void setGround(GroundModel ground) {
        this.ground = ground;
    }

    public SauceModel getSauce() {
        return sauce;
    }

    public void setSauce(SauceModel sauce) {
        this.sauce = sauce;
    }


    public ArrayList<ToppingModel> getToppings() {
        return toppings;
    }

    public void setToppings(ArrayList<ToppingModel> toppings) {
        this.toppings = toppings;
    }

    public CheeseModel getCheese() {
        return cheese;
    }

    public void setCheese(CheeseModel cheese) {
        this.cheese = cheese;
    }

    public SizeModel getSize() {
        return size;
    }

    public void setSize(SizeModel size) {
        this.size = size;
    }


    /**
     * Die Methode kalkuliert den Gesamtpreis der Pizza
     * */
    public int calcPrize(){
        int totalPizzaPrice = 0;
        totalPizzaPrice += getGround().getGround_price();
        totalPizzaPrice += getSauce().getSauce_price();
        totalPizzaPrice +=getCheese().getCheese_price();


        for (int x = 0; x< toppings.size(); x++){
            totalPizzaPrice += toppings.get(x).getTopping_price();
        }

        totalPizzaPrice *= getSize().getSize_price();
        return totalPizzaPrice;
    }


    /**
     * Die Methode kalkuliert die Gesammtzahl der Kalorien auf der Pizza
     * */
    public int calcCal(){
        int totalPizzaCal = 0;
        totalPizzaCal += getGround().getGround_cal();
        totalPizzaCal += getSauce().getSauce_cal();
        totalPizzaCal += getCheese().getCheese_cal();

        for (int x = 0; x< toppings.size(); x++){
            totalPizzaCal += toppings.get(x).getTopping_cal();
        }

        totalPizzaCal *= getSize().getSize_cal();
        return totalPizzaCal;
    }


    /**
     * Die Methode ist für das ändern der Toppings zuständig.
     * Ist ein Topping schon auf der Pizza, wird es entfert, ansonsten hinzugefügt.
     * */
    public void changeTopping (ToppingModel topping) {

        if(toppings.contains(topping)){
            toppings.remove(topping);
        }else{
            toppings.add(topping);
        }
    }


    /**
     * Die Methode fügt ein Topping zur Toppingliste der Pizza hinzu.
     * (Wird in der CreateActivity verwendet)
     * */
    public void addTopping(ToppingModel topping) {
        toppings.add(topping);
    }
}
