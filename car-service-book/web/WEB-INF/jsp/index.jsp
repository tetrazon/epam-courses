<%--
  Created by IntelliJ IDEA.
  User: Айвен
  Date: 31.10.2021
  Time: 10:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<html>
<head>
    <title>car service book</title>
</head>
<body>
<h1>welcome to car service book</h1>
<h3>"Регистрация"</h3>
<form method="post" action="/register.html">
    <label>Name</label><br>
    <input required="" name="name"/><br><br>
    <label>Surname</label><br>
    <input required="" name="surname" /><br><br>
    <label>логин</label><br>
    <input required="" name="login" /><br><br>
    <label>email</label><br>
    <input required="" type="email" name="email" /><br><br>
    <label>password</label><br>
    <input required="" type="password" name="password" /><br><br>
    <label>мобильный</label><br>
    <input required="" name="mobilePhone" /><br><br>
    <label>Роль</label><br>
    <input type="radio" id="user" name="role" value=1>
    <label for="user">пользователь</label><br>
    <input type="radio" id="master" name="role" value=2>
    <label for="master">мастер</label><br>
    <label>Область</label><br>
    <input type="radio" id="brest" name="district" value=1>
    <label for="brest">гомельская</label><br>
    <input type="radio" id="gomel" name="district" value=2>
    <label for="gomel">гомельская</label><br>
    <div>
        <button type="submit">register</button>
    </div>
</form>
<h3>Already registered? log in:</h3>
<a href="/login.html">log in</a>
</body>
</html>
