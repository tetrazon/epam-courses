<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<fmt:message key="login.registration" var="registration" />
<u:html message="${message}" title="${registration}">
    <c:if test="${not empty authorizedUser}">
        <c:set var="login" value="${authorizedUser.login}"/>
        <c:set var="id" value="${authorizedUser.id}"/>
        <c:set var="mobilePhone" value="${authorizedUser.mobilePhone}"/>
        <c:set var="district" value="${authorizedUser.district}"/>
        <c:set var="email" value="${authorizedUser.email}"/>
        <c:set var="name" value="${authorizedUser.name}"/>
        <c:set var="surname" value="${authorizedUser.surname}"/>

        <fmt:message key="user.profileChange" var="title" />
        <fmt:message key="user.saveChanges" var="submit" />

    </c:if>
    <c:if test="${empty authorizedUser}">
        <fmt:message key="user.register" var="submit" />
    </c:if>
    <h3>${title}</h3>
    <c:url value="/user/save.html" var="userSaveUrl"/>
    <form method="post" action="${userSaveUrl}">

        <LABEL for="password"><fmt:message key="login.password" /> </LABEL>
        <INPUT required type="password" id="password" name="password">
        <LABEL for="name"><fmt:message key="user.name" /></LABEL>
        <INPUT required type="text" id="name" name="name" value="${name}">
        <LABEL for="surname"><fmt:message key="user.surname" /></LABEL>
        <INPUT required type="text" id="surname" name="surname" value="${surname}">
        <c:if test="${empty authorizedUser}">
        <LABEL for="login"><fmt:message key="login.login" /></LABEL>
        <INPUT required type="text" id="login" name="login">
        <label><fmt:message key="user.role" /></label><br>
        <input type="radio" id="user" name="role" value=1>
        <label for="user"><fmt:message key="user.user" /></label><br>
        <input type="radio" id="master" name="role" value=2>
        <label for="master"><fmt:message key="user.master" /></label><br>
        </c:if>
        <LABEL for="email"><fmt:message key="user.email" /></LABEL>
        <INPUT required type="email" id="email" name="email" value="${email}">
        <label for="district"><fmt:message key="user.district" /></label>
        <select name="district" id="district" value="${district}">
            <option value="Брест"><fmt:message key="district.brest" /></option>
            <option value="Витебск"><fmt:message key="district.vitebsk" /></option>
            <option value="Гомель"><fmt:message key="district.Gomel" /></option>
            <option value="Гродно"><fmt:message key="district.grodno" /></option>
            <option value="Минск"><fmt:message key="district.minsk" /></option>
            <option value="Могилев"><fmt:message key="district.mogilev" /></option>
        </select>
        <LABEL for="mobilePhone"><fmt:message key="user.phone" /></LABEL>
        <INPUT type="text" size="13" required id="mobilePhone" name="mobilePhone" value="${mobilePhone}">
        <c:if test="${not empty authorizedUser}">
        <input type="hidden" name="id" value="${id}">
        <INPUT type="hidden" type="text" id="login" name="login"  value="${login}">
        </c:if>
        <input type="submit" value="${submit}" />
</u:html>