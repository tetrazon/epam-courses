<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<%@page isELIgnored="false" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u" %>
<u:html message="${message}" title="${title}">

    <h3><fmt:message key="login.authorization" /></h3>
    <form method="post" action="/login.html">
        <label><fmt:message key="login.login" /> :</label><br>
        <input required="" type="text" id="login" name="login"/><br><br>
        <label><fmt:message key="login.password" /> :</label><br>
        <input required="" type="password" name="password"/><br><br>
        <fmt:message key="login.enter" var="buttonValue" />
        <input type="submit" value="${buttonValue}"/>
    </form>
    <h3><fmt:message key="login.registration"/></h3>
	<a href="/register.html"><fmt:message key="login.register"/></a>
</u:html>