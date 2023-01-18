package de.medieninformatik.prog4.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import de.medieninformatik.prog4.Model.PizzaModel;
/**
 * Die Klasse stellt die Datenbank da.
 * Hier werden die einzelnen Entities mit dem DAO zu einer DB zusammengesetzt.
 * */
@Database(entities = {PizzaModel.class}, version = 1)
@TypeConverters({ToppingsTypeConverter.class})
public abstract class PizzaDatabase extends RoomDatabase {
    public abstract PizzaModelDao pizzaModelDao();
}
