package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryUserMealRepositoryImpl implements UserMealRepository {
    private static final LoggerWrapper LOG = LoggerWrapper.get(InMemoryUserMealRepositoryImpl.class);
    private Map<Integer, UserMeal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserMealsUtil.MEAL_LIST_1.forEach(um -> save(1, um));
        UserMealsUtil.MEAL_LIST_2.forEach(um -> save(2, um));
    }

    @Override
    public UserMeal save(int userId, UserMeal userMeal) {
        LOG.info("save " + userMeal);
        if (userMeal.isNew()) {
            userMeal.setId(counter.incrementAndGet());
            userMeal.setUserId(userId);
        }
        else{
            userMeal.setUserId(userId);
        }
        return repository.put(userMeal.getId(), userMeal);
    }

    @Override
    public boolean delete(int userId, int mealId) {
        LOG.info("delete " + mealId);
        if (repository.containsKey(mealId)&& repository.get(mealId).getUserId() == userId){
            repository.remove(mealId);
            return true;
        }
        return false;
    }

    @Override
    public UserMeal get(int userId, int id) {
        LOG.info("get " + id);
        if (repository.get(id) != null && repository.get(id).getUserId() == userId) {
            return repository.get(id);
        }
        else{
            return null;
        }
    }

    @Override
    public Collection<UserMeal> getAll(int userId) {
        LOG.info("getAll " + userId);
        return repository.values()
                .stream()
                .filter(userMeal -> userMeal.getUserId().equals(userId))
                .sorted((um1, um2) -> um2.getDateTime().compareTo(um1.getDateTime()))
                .collect(Collectors.toList());

    }

}

