package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by NotePad on 06.12.2015.
 */
public interface UserMealService {
    List<UserMealWithExceed> getAllUserMealsWithExceed();
    UserMeal addUserMeal(UserMeal userMeal);
    UserMeal deleteUserMeal(LocalDateTime dateTime);
    UserMeal getUserMealByDate(LocalDateTime dateTime);
    UserMeal updateUserMeal(LocalDateTime dateTime, UserMeal userMeal);
}
