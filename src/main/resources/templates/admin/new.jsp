<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns:th="http://thymeleaf.org">
<head>
    <title>New User</title>
</head>
<body>
<form th:action="@{/admin}" th:method="post" th:object="${newUser}">
    <label for="name">Enter name: </label>
    <input type="text" th:field="*{name}" id="name"/>
    <br/>
    <label for="pass">Enter password: </label>
    <input type="text" th:field="*{password}" id="pass"/>
    <br/>
    <label for="admin"  >ROLE_ADMIN</label>
    <input type="checkbox" id="admin" name="admin" th:value="ROLE_ADMIN"/>
    <br/>
    <input type="submit" value="Создать"/>
</form>
<br/>
<a href="/logout">Logout</a>
</body>
</html>
