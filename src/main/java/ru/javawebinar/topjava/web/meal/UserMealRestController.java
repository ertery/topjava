package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealServiceImpl;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealServiceImpl service;

    public UserMeal save(UserMeal userMeal){
           return service.save(LoggedUser.id(), userMeal);
    }

    public void delete(int mealId) throws NotFoundException{
            service.delete(LoggedUser.id(), mealId);
    }

    public UserMeal get(int id) throws NotFoundException{
            return service.get(LoggedUser.id(), id);
    }

    public Collection<UserMealWithExceed> getAll(){
        return UserMealsUtil.getWithExceeded(service.getAll(LoggedUser.id()), UserMealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Collection<UserMealWithExceed> getFilteredMeal(LocalDate startDay, LocalDate endDay, LocalTime startTime, LocalTime endTime){
           return getAll().stream()
                    .filter(userMeal -> TimeUtil.isBetweenDay(userMeal.getDateTime().toLocalDate(), startDay, endDay))
                    .filter(userMeal1 -> TimeUtil.isBetweenTime(userMeal1.getDateTime().toLocalTime(), startTime, endTime))
                    .collect(Collectors.toList());


    }

}
