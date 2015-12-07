package ru.javawebinar.topjava.web;

/**
 * Created by NotePad on 06.12.2015.
 */
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserMealService;
import ru.javawebinar.topjava.service.UserMealServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);
    private static String INSERT_OR_EDIT = "/addOrUpdateMeal.jsp";
    private static String LIST_USER_MEAL = "/mealList.jsp";
    private UserMealService userMealService = new UserMealServiceImpl();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");

        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            LOG.debug("deleting meal to mealList");
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
            userMealService.deleteUserMeal(dateTime);
            forward = LIST_USER_MEAL;
            request.setAttribute("mealList", userMealService.getAllUserMealsWithExceed());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
            UserMeal userMeal = userMealService.getUserMealByDate(dateTime);
            request.setAttribute("userMeal", userMeal);
        } else if (action.equalsIgnoreCase("insert")){
            forward = INSERT_OR_EDIT;
        } else {
            forward = LIST_USER_MEAL;
            request.setAttribute("mealList", userMealService.getAllUserMealsWithExceed());
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("date"));
        UserMeal userMeal = new UserMeal(
                dateTime,
                new String(request.getParameter("description")),
                Integer.parseInt(request.getParameter("calories")));
        String userid = request.getParameter("userid");
        if(dateTime == null){
            LOG.debug("adding meal to mealList");
            userMealService.addUserMeal(userMeal);
        }
        else {
            LOG.debug("editing meal to mealList");
            userMealService.updateUserMeal(dateTime, userMeal);
        }
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER_MEAL);
        request.setAttribute("mealList", userMealService.getAllUserMealsWithExceed());
        view.forward(request, response);
    }

}


