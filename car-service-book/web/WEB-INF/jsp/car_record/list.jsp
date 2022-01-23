<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:url value="/car_record/edit.html" var="carRecordEditUrl"/>
    <c:if test="${not empty carRecordList}">
        <h2><fmt:message key="car.record.planned" /> ${carModel}</h2>
        <table>
            <tr><th><fmt:message key="car.record.category" /></th>
                <th><fmt:message key="car.description" /></th>
                <th><fmt:message key="car.period.km" /> </th>
                <th><fmt:message key="car.period.month" /> </th>
                <th><fmt:message key="car.record.periodic" /> </th>
                <th><fmt:message key="car.record.isActual" /> </th>
                <th><fmt:message key="car.record.date" /> </th>
            </tr>
            <fmt:message key="message.yes" var="yes" />
            <fmt:message key="message.no" var="no" />
            <c:forEach items="${carRecordList}" var="carRecord">
                <c:if test="${carRecord.isPeriodic == true}">
                    <c:set var="isPeriodic" value="${yes}"/>
                </c:if>
                <c:if test="${carRecord.isPeriodic == false}">
                    <c:set var="isPeriodic" value="${no}"/>
                </c:if>
                <c:if test="${carRecord.isTender == true}">
                    <c:set var="isTender" value="${yes}"/>
                </c:if>
                <c:if test="${carRecord.isTender == false}">
                    <c:set var="isTender" value="${no}"/>
                </c:if>
                <tr>
                    <td>${carRecord.category} </td>
                    <td>${carRecord.description} </td>
                    <td>${carRecord.kmInterval} </td>
                    <td>${carRecord.monthsInterval} </td>
                    <td>${isPeriodic} </td>
                    <td>${isTender} </td>
                    <td>${carRecord.recordDate} </td>
                    <td>
                        <FORM id="form-${carRecord.id}" action="${carRecordEditUrl}" method="post">
                            <INPUT type="hidden" name="carRecordId" value="${carRecord.id}">
                            <INPUT type="hidden" name="carId" value="${carId}">
                            <BUTTON type="submit"><fmt:message key="car.change" /></BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <FORM action="${carRecordEditUrl}" method="post">
        <INPUT type="hidden" name="carId" value="${carId}">
        <BUTTON type="submit"><fmt:message key="car.record.new" /></BUTTON>
    </FORM>
</u:html>

