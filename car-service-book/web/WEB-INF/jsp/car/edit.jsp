<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<c:if test="${not empty car}">
    <c:set var="model" value="${car.model}"/>
    <c:set var="mileage" value="${car.mileage}"/>
    <c:set var="year" value="${car.year}"/>
</c:if>
<c:set var="year" value="${car.year}"/>
<fmt:message key="car.title" var="pageTitle" />
<u:html title="${pageTitle}" message="${message}">
    <H2>"${pageTitle}"</H2>
    <c:url value="/car/save.html" var="carSaveUrl"/>
    <FORM action="${carSaveUrl}" method="post">
        <c:if test="${not empty car}">
            <INPUT type="hidden" name="id" value="${car.id}">
        </c:if>
        <LABEL for="model"><fmt:message key="car.model" /></LABEL>
        <INPUT type="text" id="model" required="" minlength="2" maxlength="30" name="model" value="${model}">
        <LABEL for="mileage"><fmt:message key="car.mileage" /></LABEL>
        <INPUT type="number" required="required" step="0.5" id="mileage" name="mileage" value="${mileage}">
        <LABEL for="year"><fmt:message key="car.year" /></LABEL>
        <INPUT type="number" required="required" min="1900"  max="2022" id="year" name="year" value="${year}">
        <BUTTON type="submit"><fmt:message key="car.save" /></BUTTON>
        <BUTTON type="reset"><fmt:message key="car.reset" /></BUTTON>
    </FORM>
    <c:if test="${not empty car}">
        <c:url value="/car/delete.html" var="carDeleteUrl"/>
        <FORM action="${carDeleteUrl}" method="post">
            <INPUT type="hidden" name="carId" value="${car.id}">
            <BUTTON type="submit" ><fmt:message key="car.delete" /></BUTTON>
        </FORM>
    </c:if>
</u:html>