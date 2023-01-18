package de.medieninformatik.prog4.Database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import de.medieninformatik.prog4.Model.ToppingModel;

/**
 * Der Typeconverter ist dafür da, die Liste von Toppings in der Datenbank im Pizzaibject zu speichern.
 * Hierfür wir die Liste serialisiert.
 * */
public class ToppingsTypeConverter {

    @TypeConverter
    public String fromToppingsList(ArrayList<ToppingModel> toppings) {
        if (toppings == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ToppingModel>>() {}.getType();
        return gson.toJson(toppings, type);
    }

    @TypeConverter
    public ArrayList<ToppingModel> toToppingsList(String toppingsString) {
        if (toppingsString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<ToppingModel>>() {}.getType();
        return gson.fromJson(toppingsString, type);
    }
}
