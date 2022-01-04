<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:url value="/offer/accept.html" var="acceptOfferUrl"/>
    <c:if test="${offerList}">
        <h2>нет предложений</h2>
    </c:if>
    <c:if test="${not empty offerList}">
        <h2>Список предложений для ${carModel}</h2>
        <table>
            <tr>
                <th>Описание </th>
                <th>цена </th>
                <th>имя мастера </th>
                <th>контакт мастера </th>
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
                            <BUTTON type="submit">выбрать предложение мастера</BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

