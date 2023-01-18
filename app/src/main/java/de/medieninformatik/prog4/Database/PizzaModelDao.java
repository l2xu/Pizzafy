package de.medieninformatik.prog4.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import de.medieninformatik.prog4.Model.PizzaModel;

/**
 * Die Klasse stellt das data access object der Datenbank zur Verfügung.
 * über diese Interface kann die Datenbank angeseteuert werden.
 * */
@Dao
public interface PizzaModelDao {

    //Gets all pizzas
    @Query("SELECT * FROM pizzamodel")
    List<PizzaModel> getAll();

    //Check if pizza exits
    @Query("SELECT EXISTS(SELECT * FROM pizzamodel WHERE name = :name)")
    boolean alreadyExits(String name);

    //Inserts a pizza in the db
    @Insert
    void insertAll(PizzaModel... pizzas);

    //Deletes a pizza from the db
    @Delete
    void delete(PizzaModel pizza);


}
