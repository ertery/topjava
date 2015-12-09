package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
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
public class MealServiceImpl implements MealService {
    private MealRepository mealRepository = new MealRepositoryImpl();

    @Override
    public List<UserMealWithExceed> getAll() {
        return mealRepository.getAll();
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        return mealRepository.save(userMeal);
    }

    @Override
    public UserMeal delete(int id) {
        return mealRepository.delete(id);
    }

    @Override
    public UserMeal get(int id) {
        return mealRepository.get(id);
    }
}
