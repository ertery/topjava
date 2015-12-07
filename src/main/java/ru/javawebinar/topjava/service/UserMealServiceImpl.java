package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by NotePad on 06.12.2015.
 */
public class UserMealServiceImpl implements UserMealService {
    private Map<LocalDateTime, UserMeal> mealMap = new HashMap<>();
    {
        UserMeal um0 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        UserMeal um1 = new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 3000);
        mealMap.put(um0.getDateTime(), um0);
        mealMap.put(um1.getDateTime(), um1);
    }

    @Override
    public List<UserMealWithExceed> getAllUserMealsWithExceed() {
        return UserMealsUtil.getFilteredMealsWithExceeded(new ArrayList<>(mealMap.values()), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public UserMeal addUserMeal(UserMeal userMeal) {
        return mealMap.put(userMeal.getDateTime(), userMeal);
    }

    @Override
    public UserMeal deleteUserMeal(LocalDateTime dateTime) {
        return mealMap.remove(dateTime);
    }

    @Override
    public UserMeal getUserMealByDate(LocalDateTime dateTime) {
        return mealMap.get(dateTime);
    }

    @Override
    public UserMeal updateUserMeal(LocalDateTime dateTime, UserMeal userMeal) {
        return mealMap.put(dateTime, userMeal);
    }
}
