<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html message="${message}" title="Регистрация">
    <c:if test="${not empty authorizedUser}">
        <c:set var="login" value="${authorizedUser.login}"/>
        <c:set var="id" value="${authorizedUser.id}"/>
        <c:set var="mobilePhone" value="${authorizedUser.mobilePhone}"/>
        <c:set var="district" value="${authorizedUser.district}"/>
        <c:set var="email" value="${authorizedUser.email}"/>
        <c:set var="name" value="${authorizedUser.name}"/>
        <c:set var="surname" value="${authorizedUser.surname}"/>

        <c:set var="title" value="изменение профиля"/>
        <c:set var="submit" value="Сохранить изменения и войти в систему"/>

    </c:if>
    <c:if test="${empty authorizedUser}">
        <c:set var="submit" value="Зарегистрироваться и войти в систему"/>

    </c:if>
    <h3>${title}</h3>
    <c:url value="/user/save.html" var="userSaveUrl"/>
    <form method="post" action="${userSaveUrl}">


        <LABEL for="password">пароль</LABEL>
        <INPUT required type="password" id="password" name="password">
        <LABEL for="name">имя</LABEL>
        <INPUT required type="text" id="name" name="name" value="${name}">
        <LABEL for="surname">фамилия</LABEL>
        <INPUT required type="text" id="surname" name="surname" value="${surname}">
            <c:if test="${empty authorizedUser}">
        <LABEL for="login">логин</LABEL>
        <INPUT required type="text" id="login" name="login">
        <label>Роль</label><br>
        <input type="radio" id="user" name="role" value=1>
        <label for="user">пользователь</label><br>
        <input type="radio" id="master" name="role" value=2>
        <label for="master">мастер</label><br>
        </c:if>
        <LABEL for="email">почта</LABEL>
        <INPUT required type="email" id="email" name="email" value="${email}">
        <label for="district">область:</label>
        <select name="district" id="district" value="${district}">
            <option value="Брест">Брест</option>
            <option value="Витебск">Витебск</option>
            <option value="Гомель">Гомель</option>
            <option value="Гродно">Гродно</option>
            <option value="Минск">Минск</option>
            <option value="Могилев">Могилев</option>
        </select>
        <LABEL for="mobilePhone">мобильный</LABEL>
        <INPUT type="text" size="13" required id="mobilePhone" name="mobilePhone" value="${mobilePhone}">
        <c:if test="${not empty authorizedUser}">
        <input type="hidden" name="id" value="${id}">
        <INPUT type="hidden" type="text" id="login" name="login"  value="${login}">
        </c:if>
        <input type="submit" value="${submit}" />
</u:html>