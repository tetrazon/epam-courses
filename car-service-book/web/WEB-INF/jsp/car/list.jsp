<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:url value="/car/edit.html" var="carEditUrl"/>
    <c:url value="/car_record/list.html" var="carRecordUrl"/>
    <c:url value="/car_record/offer_list.html" var="carRecordOfferListUrl"/>
    <c:url value="/car_record/history.html" var="carRecordHistoryUrl"/>

    <c:if test="${not empty carList}">
            <h2><fmt:message key="car.garage" /></h2>
        <table>
            <tr><th><fmt:message key="car.model" /> </th><th><fmt:message key="car.year" /> </th><th><fmt:message key="car.mileage" /></th></tr>
            <c:forEach items="${carList}" var="car">
                <tr>
                    <td>${car.model} </td>
                    <td>${car.year} </td>
                    <td>${car.mileage}</td>
                    <td>
                        <FORM id="form-${car.id}" action="${carEditUrl}" method="post">
                            <INPUT type="hidden" name="carId" value="${car.id}">
                            <BUTTON type="submit"><fmt:message key="car.change" /></BUTTON>
                        </FORM>
                    </td>
                    <td>
                        <FORM id="form-${car.id}" action="${carRecordUrl}" method="post">
                            <INPUT type="hidden" name="carId" value="${car.id}">
                            <BUTTON type="submit"><fmt:message key="car.service" /></BUTTON>
                        </FORM>
                    </td>
                    <td>
                        <FORM id="form-${car.id}" action="${carRecordHistoryUrl}" method="post">
                            <INPUT type="hidden" name="carId" value="${car.id}">
                            <BUTTON type="submit"><fmt:message key="car.service.history" /></BUTTON>
                        </FORM>
                    </td>
                    <td>
                        <FORM id="form-${car.id}" action="${carRecordOfferListUrl}" method="post">
                            <INPUT type="hidden" name="carId" value="${car.id}">
                            <BUTTON type="submit"><fmt:message key="car.tender" /></BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <FORM action="${carEditUrl}" method="post">
        <BUTTON type="submit"><fmt:message key="car.add" /></BUTTON>
    </FORM>
</u:html>

