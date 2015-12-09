package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.util.List;

/**
 * Created by NotePad on 08.12.2015.
 */
public interface MealRepository {
    List<UserMealWithExceed> getAll();
    UserMeal save(UserMeal userMeal); //add or update
    UserMeal delete(int id);
    UserMeal get(int id);

}
