<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>
        <c:if test="${empty userMeal}">Add Meal list</c:if>
        <c:if test="${not empty userMeal}">Update Meal list</c:if>
    </title>
</head>
<body>
<h2>
    <c:if test="${empty userMeal}">Add Meal list</c:if>
    <c:if test="${not empty userMeal}">Update Meal list</c:if>
</h2>
<form method="POST">
    <table border-collapse="collapse">
        <tbody>
            <tr><td>date:</td>
                <td><input type="datetime-local" id="dateTime" name="dateTime"
                           value="${userMeal.dateTime}"/></td>
            </tr>
            <tr>
                <td>description:</td>
                <td><input type="text" id="description" name="description"
                         value="${userMeal.description}"/></td>
            </tr>
            <tr>
                <td>calories:</td>
                <td><input type="number" id="calories" name="calories"
                           value="${userMeal.calories}"/></td>
            </tr>
        </tbody>
    </table>
    <input type="hidden" id="id" value="${userMeal.id}">
    <input type="submit" name="Ok" value="ok" />


</form>

</body>
</html>
