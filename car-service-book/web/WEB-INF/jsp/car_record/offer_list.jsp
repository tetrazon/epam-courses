<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:url value="/offer/accept.html" var="acceptOfferUrl"/>
    <c:if test="${empty offerList}">
        <h2><fmt:message key="car.record.noOffers" /></h2>
    </c:if>
    <c:if test="${not empty offerList}">
        <h2><fmt:message key="car.record.offerList" /> ${carModel}</h2>
        <table>
            <tr>
                <th><fmt:message key="car.description" /> </th>
                <th><fmt:message key="message.price" />цена </th>
                <th><fmt:message key="car.record.masterName" /> </th>
                <th><fmt:message key="car.record.masterContact" /> </th>
            </tr>
            <c:forEach items="${offerList}" var="offer">

                <tr>
                    <td>${offer.carRecord.description} </td>
                    <td>${offer.price} </td>
                    <td>${offer.master.name} </td>
                    <td>${offer.master.mobilePhone} </td>
                    <td>
                        <FORM id="form-${carRecord.id}" action="${acceptOfferUrl}" method="post">
                            <INPUT type="hidden" name="carRecordId" value="${offer.carRecord.id}">
                            <INPUT type="hidden" name="masterId" value="${offer.master.id}">
                            <BUTTON type="submit"><fmt:message key="car.record.choose" /></BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

