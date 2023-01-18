package de.medieninformatik.prog4.Model;

/**
 * Die Klasse hält alle Information für die Käseauswahl der Pizza
 */
public class CheeseModel {
    String cheese_name;
    int cheese_price;
    int cheese_cal;

    public CheeseModel(String cheese_name, int cheese_price, int cheese_cal) {
        this.cheese_name = cheese_name;
        this.cheese_price = cheese_price;
        this.cheese_cal = cheese_cal;
    }

    //Getter and Setter
    public String getCheese_name() {
        return cheese_name;
    }

    public void setCheese_name(String cheese_name) {
        this.cheese_name = cheese_name;
    }

    public int getCheese_price() {
        return cheese_price;
    }

    public void setCheese_price(int cheese_price) {
        this.cheese_price = cheese_price;
    }

    public int getCheese_cal() {
        return cheese_cal;
    }

    public void setCheese_cal(int cheese_cal) {
        this.cheese_cal = cheese_cal;
    }


}
