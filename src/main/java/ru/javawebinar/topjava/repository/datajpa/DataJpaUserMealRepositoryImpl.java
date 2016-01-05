package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository{

    @Autowired
    private ProxyUserMealRepository proxyMeal;
    @Autowired
    private ProxyUserRepository proxyUser;

    @Override
    public UserMeal save(UserMeal meal, int userId) {
        meal.setUser(proxyUser.findOne(userId));
        if (meal.isNew()) {
            proxyMeal.save(meal);
            return meal;
        } else {
            return get(meal.getId(), userId) == null ? null : proxyMeal.save(meal);
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxyMeal.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxyMeal.findOne(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxyMeal.findAll(userId);
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxyMeal.findAllBetween(startDate, endDate, userId);
    }
}
