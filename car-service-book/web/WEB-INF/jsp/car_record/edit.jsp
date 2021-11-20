<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:if test="${not empty carRecord}">
<%--    <c:set var="category" value="${carRecord.category}"/>--%>
    <c:set var="description" value="${carRecord.description}"/>
    <c:set var="kmInterval" value="${carRecord.kmInterval}"/>
    <c:set var="recordDate" value="${carRecord.recordDate}"/>
</c:if>
<u:html title="TO" message="${message}">
    <H2>Авто</H2>
    <c:url value="/car_record/save.html" var="carRecordSaveUrl"/>
    <FORM action="${carRecordSaveUrl}" method="post">
        <c:if test="${not empty carRecord}">
            <INPUT type="hidden" name="id" value="${carRecord.id}">
        </c:if>

        <LABEL for="carId">ID авто:</LABEL>
        <INPUT type="number" id="carId" name="carId" value="${carId}">
        <LABEL for="kmInterval">интервал, км:</LABEL>
        <INPUT type="number" id="kmInterval" name="kmInterval" value="${kmInterval}">
        <LABEL for="monthsInterval">интервал, мес:</LABEL>
        <INPUT type="text" id="monthsInterval" name="monthsInterval" value="${monthsInterval}">
        <LABEL for="description">описание:</LABEL>
        <INPUT type="text" id="description" name="description" value="${description}">
        <LABEL for="recordDate">дата:</LABEL>
        <INPUT type="date" id="recordDate" name="recordDate" value="${recordDate}"><br>

        <p>категория:</p>
        <input type="radio" id="engine" name="category" value="Двигательная система">
        <label for="engine">Двигательная система</label><br>
        <input type="radio" id="brake" name="category" value="Система торможения">
        <label for="brake">Система торможения</label><br>
        <input type="radio" id="coolant" name="category" value="Система охлаждения">
        <label for="coolant">Система охлаждения</label><br>


        <p>периодическая операция:</p>
        <input type="radio" id="false" name="isPeriodic" value="false">
        <label for="false">false</label><br>
        <input type="radio" id="true" name="isPeriodic" value="true">
        <label for="true">true</label><br>

        <p>актуально для выполнения:</p>
        <input type="radio" id="false" name="isTender" value="false">
        <label for="false">false</label><br>
        <input type="radio" id="true" name="isTender" value="true">
        <label for="true">true</label><br>

        <BUTTON type="submit">Сохранить</BUTTON>
        <BUTTON type="reset">Сбросить</BUTTON>
    </FORM>
    <c:if test="${not empty carRecord}">
        <c:url value="/car_record/delete.html" var="carRecordDeleteUrl"/>
        <FORM action="${carRecordDeleteUrl}" method="post">
            <INPUT type="hidden" name="carRecordId" value="${carRecord.id}">
            <BUTTON type="submit" >Удалить</BUTTON>
        </FORM>
    </c:if>
</u:html>