<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
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
    <H2><fmt:message key="car.auto" /></H2>
    <c:url value="/car_record/save.html" var="carRecordSaveUrl"/>
    <FORM action="${carRecordSaveUrl}" method="post">
        <c:if test="${not empty carRecord}">
            <INPUT type="hidden" name="id" value="${carRecord.id}">

        </c:if>

        <INPUT type="hidden" id="carId" name="carId" value="${carId}">
        <LABEL for="kmInterval"><fmt:message key="car.period.km" /></LABEL>
        <INPUT type="number" min="100" required="required" max="200000" id="kmInterval" name="kmInterval" value="${kmInterval}">
        <LABEL for="monthsInterval"><fmt:message key="car.period.month" /></LABEL>
        <INPUT type="number" required="required" min="1" max="36" id="monthsInterval" name="monthsInterval" value="${monthsInterval}">
        <LABEL for="description"><fmt:message key="car.description" /></LABEL>
        <INPUT type="text" required="required" minlength="3" maxlength="255" id="description" name="description" value="${description}">
        <LABEL for="recordDate"><fmt:message key="car.record.date" /></LABEL>
        <INPUT type="date" required="required" min="2020-01-01" id="recordDate" name="recordDate" value="${recordDate}"><br>

        <label for="category"><fmt:message key="car.record.category" /></label>
        <select name="category" id="category" required="required">
            <option selected>${category}</option>
            <option value="Двигательная система"><fmt:message key="car.record.engine" /></option>
            <option value="Система торможения"><fmt:message key="car.record.brake" /></option>
            <option value="Топливная система"><fmt:message key="car.record.gas" /></option>
            <option value="Система охлаждения"><fmt:message key="car.record.cool" /></option>
            <option value="Система управления"><fmt:message key="car.record.control" /></option>
            <option value="Трансмиссия"><fmt:message key="car.record.transmission" /></option>
            <option value="Электрическая система"><fmt:message key="car.record.electric" /></option>
            <option value="Система подвески"><fmt:message key="car.record.suspension" /></option>
            <option value="Система безопасности"><fmt:message key="car.record.safety" /></option>
            <option value="Салон"><fmt:message key="car.record.saloon" /></option>
            <option value="Кузов"><fmt:message key="car.record.body" /></option>
            <option value="Другие системы"><fmt:message key="car.record.other" /></option>
        </select>

        <label for="isPeriodic"><fmt:message key="car.record.periodic" /></label>
        <select name="isPeriodic" id="isPeriodic" required="required">
            <option selected>${isPeriodic}</option>
            <option value="true"><fmt:message key="message.yes" /></option>
            <option value="false"><fmt:message key="message.no" /></option>
        </select>

        <label for="isTender"><fmt:message key="car.record.isActual" /></label>
        <select name="isTender" id="isTender" required="required">
            <option selected>${isPeriodic}</option>
            <option value="true"><fmt:message key="message.yes" /></option>
            <option value="false"><fmt:message key="message.no" /></option>
        </select>

        <BUTTON type="submit"><fmt:message key="car.save" /></BUTTON>
        <BUTTON type="reset"><fmt:message key="car.reset" /></BUTTON>
    </FORM>
    <c:if test="${not empty carRecord}">
        <c:url value="/car_record/delete.html" var="carRecordDeleteUrl"/>
        <FORM action="${carRecordDeleteUrl}" method="post">
            <INPUT type="hidden" name="carRecordId" value="${carRecord.id}">
            <BUTTON type="submit" ><fmt:message key="car.delete" /></BUTTON>
        </FORM>
    </c:if>
</u:html>