package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by NotePad on 06.12.2015.
 */
public interface MealService {
    List<UserMealWithExceed> getAll();
    UserMeal save(UserMeal userMeal); //add or update
    UserMeal delete(int id);
    UserMeal get(int id);
}
