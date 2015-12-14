package ru.javawebinar.topjava;

import ru.javawebinar.topjava.util.UserMealsUtil;

/**
 * GKislin
 * 06.03.2015.
 */
public class LoggedUser {
    private static final LoggerWrapper LOG = LoggerWrapper.get(LoggedUser.class);

    private static int logId;

    public static int id() {
        return logId;
    }

   /* public static int getLogId() {
        return logId;
    }
*/
    public static void setId(int id) {
        LoggedUser.logId = id;
    }

    public static int getCaloriesPerDay() {
        return UserMealsUtil.DEFAULT_CALORIES_PER_DAY;
    }
}
