<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:url value="/car_record/done.html" var="markDone"/>
    <h2><fmt:message key="car.record.approved" /></h2>
    <c:if test="${empty offerList}">
        <fmt:message key="car.record.no" />

            </c:if>
    <table>
    <c:if test="${not empty offerList}">
        <table>
            <tr>
                <th><fmt:message key="car.model" /></th>
                <th><fmt:message key="car.description" /></th>
                <th><fmt:message key="car.price" /></th>
                <th><fmt:message key="car.client" /></th>
                <th><fmt:message key="car.client.phone" /></th>
            </tr>
            <c:forEach items="${offerList}" var="offer">
                <tr>
                    <td>${offer.carRecord.car.model} </td>
                    <td>${offer.carRecord.description} </td>
                    <td>${offer.price} </td>
                    <td>${offer.carRecord.car.user.name} </td>
                    <td>${offer.carRecord.car.user.mobilePhone} </td>

                    <td>
                        <FORM id="form-${offer.carRecord.id}" action="${markDone}" method="post">
                            <INPUT type="hidden" name="carRecordId" value="${offer.carRecord.id}">
                            <INPUT type="hidden" name="price" value="${offer.price}">
                            <BUTTON type="submit"><fmt:message key="car.record.done" /></BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

