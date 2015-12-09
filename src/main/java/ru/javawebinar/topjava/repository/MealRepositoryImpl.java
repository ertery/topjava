package ru.javawebinar.topjava.repository;

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
 * Created by NotePad on 08.12.2015.
 */
public class MealRepositoryImpl implements MealRepository {
    private Map<Integer, UserMeal> mealMap;
    private int id;

    public MealRepositoryImpl() {
        this.mealMap = new HashMap<>();
        this.id = 0;
    }

    @Override
    public List<UserMealWithExceed> getAll() {
        if (mealMap.isEmpty()){return new ArrayList<>();}
        return UserMealsUtil.getFilteredMealsWithExceeded(new ArrayList<>(mealMap.values()), LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public UserMeal save(UserMeal userMeal) {
        if (userMeal.getId() == 0){
            userMeal.setId(++id);
        }
        return mealMap.put(userMeal.getId(), userMeal);
    }

    @Override
    public UserMeal delete(int id) {
        return mealMap.remove(id);
    }

    @Override
    public UserMeal get(int id) {
        return mealMap.get(id);
    }

}
