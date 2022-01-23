<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:if test="${not empty carRecordList}">
        <h2><fmt:message key="car.record.history" /> ${carModel}</h2>
        <table>
            <tr>
                <th><fmt:message key="car.description" /></th>
                <th><fmt:message key="message.datePerformed" /></th>
                <th><fmt:message key="message.price" /></th>
                <th><fmt:message key="car.record.masterContact" /></th>
            </tr>
            <c:forEach items="${carRecordList}" var="carRecord">
                <tr>
                    <td>${carRecord.description} </td>
                    <td>${carRecord.recordDate} </td>
                    <td>${carRecord.workPrice} </td>
                    <td>${carRecord.master.mobilePhone} </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

