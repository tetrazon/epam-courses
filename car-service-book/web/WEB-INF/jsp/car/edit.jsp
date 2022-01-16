<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:if test="${not empty car}">
    <c:set var="model" value="${car.model}"/>
    <c:set var="mileage" value="${car.mileage}"/>
    <c:set var="year" value="${car.year}"/>
</c:if>
<u:html title="Авто" message="${message}">
    <H2>Авто</H2>
    <c:url value="/car/save.html" var="carSaveUrl"/>
    <FORM action="${carSaveUrl}" method="post">
        <c:if test="${not empty car}">
            <INPUT type="hidden" name="id" value="${car.id}">
        </c:if>
        <LABEL for="model">модель:</LABEL>
        <INPUT type="text" id="model" required="" minlength="2" maxlength="30" name="model" value="${model}">
        <LABEL for="mileage">пробег (км):</LABEL>
        <INPUT type="number" required="required" step="0.5" id="mileage" name="mileage" value="${mileage}">
        <LABEL for="year">год выпуска:</LABEL>
        <INPUT type="number" required="required" min="1900"  max="2022" id="year" name="year" value="${year}">
        <BUTTON type="submit">Сохранить</BUTTON>
        <BUTTON type="reset">Сбросить</BUTTON>
    </FORM>
    <c:if test="${not empty car}">
        <c:url value="/car/delete.html" var="carDeleteUrl"/>
        <FORM action="${carDeleteUrl}" method="post">
            <INPUT type="hidden" name="carId" value="${car.id}">
            <BUTTON type="submit" >Удалить</BUTTON>
        </FORM>
    </c:if>
</u:html>