<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:useBean id="mealList" type="java.util.List<ru.javawebinar.topjava.model.UserMealWithExceed>" scope="request"/>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2 >Meal list</h2>

<p><a href="meal?action=insert">Add meal</a></p>
<table border="1" width="80%">
    <thead align="center">
    <tr>
        <th width="20%" hidden>Id</th>
        <th width="20%">Date</th>
        <th width="20%">Description</th>
        <th width="20%">Calories</th>
        <th width="20%" colspan=2>Action</th>
    </tr>
    </thead>
    <tbody align="center">

    <c:forEach items="${mealList}" var="meal">
        <c:choose>
            <c:when test="${meal.exceed eq 'true'}">
                <tr style="color: red">
            </c:when>
            <c:otherwise>
                <tr style="color: green">
            </c:otherwise>
        </c:choose>

        <td hidden>${meal.id}</td>
        <td>${meal.parsed}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>

        <td><a href="meal?action=edit&id=${meal.id}">Update</a></td>
        <td><a href="meal?action=delete&id=${meal.id}">Delete</a></td>

        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
