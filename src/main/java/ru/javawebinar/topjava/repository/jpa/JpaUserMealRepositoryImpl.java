package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        User ref = em.getReference(User.class, userId);
        if (userMeal.isNew()) {
            userMeal.setUser(ref);
            em.persist(userMeal);

        } else {
            if (em.createNamedQuery(UserMeal.UPDATE)
                    .setParameter("dateTime", userMeal.getDateTime())
                    .setParameter("description", userMeal.getDescription())
                    .setParameter("calories", userMeal.getCalories())
                    .setParameter("id", userMeal.getId())
                    .setParameter("user", ref)
                    .executeUpdate() == 0){
                return null;
            }
        }
        return userMeal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("user", em.getReference(User.class, userId))
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        /*List<UserMeal> results = em.createNamedQuery(UserMeal.GET, UserMeal.class)
                        .setParameter(1, id)
                        .setParameter(2, em.getReference(User.class, userId))
                        .getResultList();
        if (results.size() == 1) return results.get(0);
        else return null;*/

        try {
            return em.createNamedQuery(UserMeal.GET, UserMeal.class)
                    .setParameter(1, id)
                    .setParameter(2, em.getReference(User.class, userId))
                    .getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return em.createNamedQuery(UserMeal.ALL_SORTED, UserMeal.class)
                .setParameter("user", em.getReference(User.class, userId))
                .getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return em.createNamedQuery(UserMeal.BETWEEN, UserMeal.class)
                .setParameter("user", em.getReference(User.class, userId))
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}