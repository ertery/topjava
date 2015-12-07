<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add/Update Meal list</title>
</head>
<body>
<h2>Add/Update Meal list</h2>
<form method="POST">
    <table border="1">
        <tbody>
            <tr><td>date:</td><td><input type="datetime-local" id="dateTime" name="date"
            <c:if test="${not empty userMeal}"> value="${userMeal.dateTime}"</c:if>/></td></tr>

            <tr><td>description:</td><td><input type="text" id="description" name="description"
            <c:if test="${not empty userMeal}"> value="${userMeal.description}"</c:if>/></td></tr>
            <tr><td>calories:</td><td><input type="number" id="calories" name="calories"

            <c:if test="${not empty userMeal}"> value="${userMeal.calories}"</c:if>/></td></tr>
            <tr><td><input type="submit" name="Ok" value="ok" /></td></tr>
        </tbody>
    </table>
</form>

</body>
</html>
