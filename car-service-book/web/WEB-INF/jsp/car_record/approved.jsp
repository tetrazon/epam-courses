<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:url value="/car_record/done.html" var="markDone"/>
    <h2>Одобренные работы</h2>
    <c:if test="${empty offerList}">
        Нет одобренных работ
            </c:if>
    <table>
    <c:if test="${not empty offerList}">
        <table>
            <tr>
                <th>авто </th>
                <th>Описание </th>
                <th>цена </th>
                <th>имя клиента </th>
                <th>номер клиента </th>
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
                            <BUTTON type="submit">отметить как выполненное: </BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

