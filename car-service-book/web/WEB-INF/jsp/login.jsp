<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:html message="${message}" title="Вход">
    <h3>Авторизация</h3>
    <form method="post" action="/login.html">
        <label>login</label><br>
        <input required="" type="text" id="login" name="login"/><br><br>
        <label>password</label><br>
        <input required="" type="password" name="password"/><br><br>
        <input type="submit" value="Вход"/>
    </form>
    <h3>Регистрация</h3>
	<a href="/register.html">зарегистрироваться</a>
</u:html>