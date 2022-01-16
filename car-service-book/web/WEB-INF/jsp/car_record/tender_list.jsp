<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:url value="/car_record/offer.html" var="offerPrice"/>
    <c:if test="${not empty carList}">
        <h2>Тендер</h2>
        <table>
            <tr><th>Модель </th><th>год </th></tr>
            <c:forEach items="${carList}" var="car">
                <tr>
                    <td>${car.model} </td>
                    <td>${car.year} </td>
                </tr>
                <c:if test="${not empty car.carRecords}">
                    <h2>Модель/список работ</h2>
                    <table>
                        <tr><th>Категория </th>
                            <th>Описание </th>
                            <th>дата </th>
                        </tr>
                        <c:forEach items="${car.carRecords}" var="carRecord">
                            <tr>
                                <td>${carRecord.category} </td>
                                <td>${carRecord.description} </td>
                                <td>${carRecord.recordDate} </td>
                                <td>
                                    <FORM id="form-${carRecord.id}" action="${offerPrice}" method="post">
                                        <INPUT type="hidden" name="carRecordId" value="${carRecord.id}">
                                        <LABEL for="price">цена за работу: </LABEL>
                                        <INPUT type="number" id="price" name="price" required="required" step="0.01">
                                        <BUTTON type="submit">предложить цену</BUTTON>
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

