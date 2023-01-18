package de.medieninformatik.prog4.Model;


/**
 * Die Klasse hält alle Information für die Größenauswahl der Pizza
 */
public class SizeModel {

    String size_name;
    int size_price;
    int size_cal;

    public SizeModel(String size_name, int size_price, int size_cal) {
        this.size_name = size_name;
        this.size_price = size_price;
        this.size_cal = size_cal;
    }

    //Getter and Setter
    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }

    public int getSize_price() {
        return size_price;
    }

    public void setSize_price(int size_price) {
        this.size_price = size_price;
    }

    public int getSize_cal() {
        return size_cal;
    }

    public void setSize_cal(int size_cal) {
        this.size_cal = size_cal;
    }



}
