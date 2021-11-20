<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:url value="/car/edit.html" var="carEditUrl"/>
    <c:url value="/car_record/list.html" var="carRecordUrl"/>
    <c:if test="${not empty carList}">
            <h2>Гараж</h2>
        <table>
            <tr><th>Модель </th><th>год </th><th>пробег </th></tr>
            <c:forEach items="${carList}" var="car">
                <tr>
                    <td>${car.model} </td>
                    <td>${car.year} </td>
                    <td>${car.mileage}</td>
                    <td>
                        <FORM id="form-${car.id}" action="${carEditUrl}" method="post">
                            <INPUT type="hidden" name="carId" value="${car.id}">
                            <BUTTON type="submit">Изменить</BUTTON>
                        </FORM>
                    </td>
                    <td>
                        <FORM id="form-${car.id}" action="${carRecordUrl}" method="post">
                            <INPUT type="hidden" name="carId" value="${car.id}">
                            <BUTTON type="submit">Cписок ТО</BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <FORM action="${carEditUrl}" method="post">
        <BUTTON type="submit">Добавить авто</BUTTON>
    </FORM>
</u:html>

