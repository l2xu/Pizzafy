package de.medieninformatik.prog4.Model;

/**
 * Die Klasse hält alle Information für die Bodenauswahl der Pizza
 */
public class GroundModel{

    String ground_name;
    int ground_price;
    int ground_cal;

    public GroundModel(String ground_name, int ground_price, int ground_cal) {
        this.ground_name = ground_name;
        this.ground_price = ground_price;
        this.ground_cal = ground_cal;
    }

    //Getter and Setter
    public String getGround_name() {
        return ground_name;
    }

    public void setGround_name(String ground_name) {
        this.ground_name = ground_name;
    }

    public int getGround_price() {
        return ground_price;
    }

    public void setGround_price(int ground_price) {
        this.ground_price = ground_price;
    }

    public int getGround_cal() {
        return ground_cal;
    }

    public void setGround_cal(int ground_cal) {
        this.ground_cal = ground_cal;
    }



}
