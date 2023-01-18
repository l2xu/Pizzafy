package de.medieninformatik.prog4.Model;


/**
 * Die Klasse hält alle Information für die Belagsauwahl der Pizza
 */
public class ToppingModel{

    String topping_name;
    int topping_price;
    int topping_cal;

    public ToppingModel(String topping_name, int topping_price, int topping_cal) {
        this.topping_name = topping_name;
        this.topping_price = topping_price;
        this.topping_cal = topping_cal;
    }

    //Getter and Setter
    public String getTopping_name() {
        return topping_name;
    }

    public void setTopping_name(String topping_name) {
        this.topping_name = topping_name;
    }

    public int getTopping_price() {
        return topping_price;
    }

    public void setTopping_price(int topping_price) {
        this.topping_price = topping_price;
    }

    public int getTopping_cal() {
        return topping_cal;
    }

    public void setTopping_cal(int topping_cal) {
        this.topping_cal = topping_cal;
    }


}
