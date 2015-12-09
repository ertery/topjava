package ru.javawebinar.topjava.web;

/**
 * Created by NotePad on 06.12.2015.
 */
import ru.javawebinar.topjava.LoggerWrapper;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MealServlet extends HttpServlet {
    private static final LoggerWrapper LOG = LoggerWrapper.get(MealServlet.class);
    private static String INSERT_OR_EDIT = "/addOrUpdateMeal.jsp";
    private static String LIST_USER_MEAL = "/mealList.jsp";
    private MealService mealService;

    public void init(){

        mealService = new MealServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageName;
        switch (request.getParameter("action")) {
            case "delete" :
                LOG.debug("deleting meal from mealList");

                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    mealService.delete(id);
                }
                catch (Exception e){
                    LOG.warn("Exception of deleting", e);
                }
                pageName = LIST_USER_MEAL;
                request.setAttribute("mealList", mealService.getAll());
                break;
            case "edit" :
                LOG.debug("editing meal");

                pageName = INSERT_OR_EDIT;
                try {
                    int id = Integer.parseInt(request.getParameter("id"));
                    UserMeal userMeal = mealService.get(id);
                    request.setAttribute("userMeal", userMeal);
                }
                catch (Exception e){
                    LOG.warn("Exception of editing", e);
                }
                break;
            case "insert" :
                pageName = INSERT_OR_EDIT;
                break;
            default :
                pageName = LIST_USER_MEAL;
                request.setAttribute("mealList", mealService.getAll());
        }
        request.getRequestDispatcher(pageName).forward(request, response);
        //response.sendRedirect(pageName);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String dateTime = request.getParameter("dateTime");
        String description = request.getParameter("description");
        String calories = request.getParameter("calories");
        UserMeal userMeal;

        if (dateTime != null && description != null && calories != null) {
            try {
                userMeal = new UserMeal(
                        LocalDateTime.parse(dateTime),
                        description,
                        Integer.parseInt(calories));

            String id = request.getParameter("id");
            if (id == null){
                LOG.debug("adding meal to mealList");
                mealService.save(userMeal);
            }
            else{
                LOG.debug("updating meal in mealList");
                userMeal.setId(Integer.parseInt(id));
                mealService.save(userMeal);
            }
            }
            catch (Exception e){
                LOG.error("Exception", e);
            }
        }
        //request.setAttribute("mealList", mealService.getAll());
       // request.getRequestDispatcher(LIST_USER_MEAL).forward(request, response);
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER_MEAL);
        request.setAttribute("mealList", mealService.getAll());
        view.forward(request, response);
    }

}


