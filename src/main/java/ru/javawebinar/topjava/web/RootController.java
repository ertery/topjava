package ru.javawebinar.topjava.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.TimeUtil;
import ru.javawebinar.topjava.web.meal.UserMealRestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@Controller
public class RootController {
    @Autowired
    private UserService userService;
    /*@Autowired
    private UserMealService mealService;*/
    @Autowired
    private UserMealRestController mealController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "index";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String userList(Model model) {
        model.addAttribute("userList", userService.getAll());
        return "userList";
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String setUser(HttpServletRequest request) {
        int userId = Integer.valueOf(request.getParameter("userId"));
        LoggedUser.setId(userId);
        return "redirect:meals";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.POST)//Исправить. бред скорее всего
    public String mealList(HttpServletRequest request, Model model) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            String id = request.getParameter("id");
            UserMeal userMeal = new UserMeal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
            if (userMeal.isNew()) {
                //LOG.info("Create {}", userMeal);
                mealController.create(userMeal);
            } else {
                //LOG.info("Update {}", userMeal);
                mealController.update(userMeal);
            }
            return "redirect:meals";
        } else if (action.equals("filter")) {
            LocalDate startDate = TimeUtil.parseLocalDate(resetParam("startDate", request), TimeUtil.MIN_DATE);
            LocalDate endDate = TimeUtil.parseLocalDate(resetParam("endDate", request), TimeUtil.MAX_DATE);
            LocalTime startTime = TimeUtil.parseLocalTime(resetParam("startTime", request), LocalTime.MIN);
            LocalTime endTime = TimeUtil.parseLocalTime(resetParam("endTime", request), LocalTime.MAX);
            model.addAttribute("mealList", mealController.getBetween(startDate, startTime, endDate, endTime));
            return "mealList";
        }
        return "mealList";
    }

    @RequestMapping(value = "/meals", method = RequestMethod.GET)//Исправить. бред скорее всего
    public String setMeal(HttpServletRequest request, Model model) {
        String action = request.getParameter("action");

        if (action == null) {
            //LOG.info("getAll");
            request.setAttribute("mealList", mealController.getAll());
            return "mealList";
        } else if (action.equals("delete")) {
            int id = getId(request);
            //LOG.info("Delete {}", id);
            mealController.delete(id);
            return "redirect:meals";
        } else {
            final UserMeal meal = action.equals("create") ?
                    new UserMeal(LocalDateTime.now(), "", 1000) :
                    mealController.get(getId(request));
            model.addAttribute("meal", meal);
            return "mealEdit";
        }
    }

    private String resetParam(String param, HttpServletRequest request) {
        String value = request.getParameter(param);
        request.setAttribute(param, value);
        return value;
    }
    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
