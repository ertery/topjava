package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user=:user"),
        @NamedQuery(name = UserMeal.GET, query = "SELECT m FROM UserMeal m WHERE m.id=?1 AND m.user=?2"),
        @NamedQuery(name = UserMeal.ALL_SORTED, query = "SELECT m FROM UserMeal m WHERE m.user=:user ORDER BY m.dateTime DESC"),
//       @NamedQuery(name = UserMeal.BETWEEN, query = "SELECT m FROM UserMeal m WHERE m.user=?1 AND m.dateTime BETWEEN ?2 AND ?3 ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserMeal.BETWEEN, query = "SELECT m FROM UserMeal m WHERE m.user=:user AND m.dateTime>=:startDate AND m.dateTime<=:endDate ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserMeal.UPDATE, query = "UPDATE UserMeal m SET m.dateTime=:dateTime, m.description=:description, m.calories=:calories"+
                " where m.id=:id AND m.user=:user")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = "date_time", name = "meals_unique_user_datetime_idx")})
public class UserMeal extends BaseEntity {
    public static final String DELETE = "UserMeal.delete";
    public static final String ALL_SORTED = "UserMeal.getAllSorted";
    public static final String GET = "UserMeal.get";
    public static final String BETWEEN = "UserMeal.getBetween";
    public static final String UPDATE = "UserMeal.update";

    @NotNull
    @Column(name = "date_time", nullable = false)
    protected LocalDateTime dateTime;

    @Column(name = "description")
    protected String description;

    @Column(name = "calories")
    @Digits(fraction = 0, integer = 4)
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public UserMeal() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}