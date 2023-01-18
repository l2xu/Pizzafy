package de.medieninformatik.prog4.Model;


/**
 * Die Klasse hält alle Information für die Saucenauswahl der Pizza
 */
public class SauceModel {

    String sauce_name;
    int sauce_price;
    int sauce_cal;

    public SauceModel(String sauce_name, int sauce_price, int sauce_cal) {
        this.sauce_name = sauce_name;
        this.sauce_price = sauce_price;
        this.sauce_cal = sauce_cal;
    }

    //Getter and Setter

    public String getSauce_name() {
        return sauce_name;
    }

    public void setSauce_name(String sauce_name) {
        this.sauce_name = sauce_name;
    }

    public int getSauce_price() {
        return sauce_price;
    }

    public void setSauce_price(int sauce_price) {
        this.sauce_price = sauce_price;
    }

    public int getSauce_cal() {
        return sauce_cal;
    }

    public void setSauce_cal(int sauce_cal) {
        this.sauce_cal = sauce_cal;
    }

}
