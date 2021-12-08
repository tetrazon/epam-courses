<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<c:if test="${not empty carRecord}">
    <c:set var="category" value="${carRecord.category}"/>
    <c:set var="description" value="${carRecord.description}"/>
    <c:set var="kmInterval" value="${carRecord.kmInterval}"/>
    <c:set var="monthsInterval" value="${carRecord.monthsInterval}"/>
    <c:set var="recordDate" value="${carRecord.recordDate}"/>
    <c:set var="isPeriodic" value="${carRecord.isPeriodic}"/>
    <c:if test="${carRecord.isPeriodic == true}">
        <c:set var="isPeriodic" value="да"/>
    </c:if>
    <c:if test="${carRecord.isPeriodic == false}">
        <c:set var="isPeriodic" value="нет"/>
    </c:if>
    <c:if test="${carRecord.isTender == true}">
        <c:set var="isTender" value="да"/>
    </c:if>
    <c:if test="${carRecord.isTender == false}">
        <c:set var="isTender" value="нет"/>
    </c:if>

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
        <INPUT type="number" id="monthsInterval" name="monthsInterval" value="${monthsInterval}">
        <LABEL for="description">описание:</LABEL>
        <INPUT type="text" id="description" name="description" value="${description}">
        <LABEL for="recordDate">дата:</LABEL>
        <INPUT type="date" id="recordDate" name="recordDate" value="${recordDate}"><br>

        <label for="category">категория:</label>
        <select name="category" id="category">
            <option selected>${category}</option>
            <option value="Двигательная система">Двигательная система</option>
            <option value="Система торможения">Система торможения</option>
            <option value="Топливная система">Топливная система</option>
            <option value="Система охлаждения">Система охлаждения</option>
            <option value="Система управления">Система управления</option>
            <option value="Трансмиссия">Трансмиссия</option>
            <option value="Электрическая система">Электрическая система</option>
            <option value="Система подвески">Система подвески</option>
            <option value="Система безопасности">Система безопасности</option>
            <option value="Салон">Салон</option>
            <option value="Кузов">Кузов</option>
            <option value="Другие системы">Другие системы</option>
        </select>

        <label for="isPeriodic">периодическая операция:</label>
        <select name="isPeriodic" id="isPeriodic">
            <option selected>${isPeriodic}</option>
            <option value="true">да</option>
            <option value="false">нет</option>
        </select>

        <label for="isTender">актуально для выполнения:</label>
        <select name="isTender" id="isTender">
            <option selected>${isPeriodic}</option>
            <option value="true">да</option>
            <option value="false">нет</option>
        </select>

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