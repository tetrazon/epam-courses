<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:url value="/car_record/offer.html" var="offerPrice"/>
    <c:if test="${not empty carList}">
        <h2><fmt:message key="menu.tender" /></h2>
        <table>
            <tr><th><fmt:message key="car.model" /> </th><th><fmt:message key="car.year" /> </th></tr>
            <c:forEach items="${carList}" var="car">
                <tr>
                    <td>${car.model} </td>
                    <td>${car.year} </td>
                </tr>
                <c:if test="${not empty car.carRecords}">
                    <h2><fmt:message key="car.record.modelOffer" /></h2>
                    <table>
                        <tr><th><fmt:message key="car.record.category" /> </th>
                            <th><fmt:message key="car.description" /> </th>
                            <th><fmt:message key="car.record.date" /> </th>
                        </tr>
                        <c:forEach items="${car.carRecords}" var="carRecord">
                            <tr>
                                <td>${carRecord.category} </td>
                                <td>${carRecord.description} </td>
                                <td>${carRecord.recordDate} </td>
                                <td>
                                    <FORM id="form-${carRecord.id}" action="${offerPrice}" method="post">
                                        <INPUT type="hidden" name="carRecordId" value="${carRecord.id}">
                                        <LABEL for="price"><fmt:message key="car.record.price" /> </LABEL>
                                        <INPUT type="number" id="price" name="price" required="required" step="0.01">
                                        <BUTTON type="submit"><fmt:message key="car.record.offerPrice" /></BUTTON>
                                    </FORM>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </c:forEach>
        </table>
    </c:if>
</u:html>

