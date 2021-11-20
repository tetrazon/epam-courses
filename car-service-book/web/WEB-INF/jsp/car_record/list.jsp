<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:url value="/car_record/edit.html" var="carRecordEditUrl"/>
    <c:if test="${not empty carRecordList}">
        <h2>Список плановых ТО для ${carModel}</h2>
        <table>
            <tr><th>Категория </th>
                <th>Описание </th>
                <th>интервал, км </th>
                <th>интервал, мес </th>
                <th>периодическая операция </th>
                <th>дата </th>
            </tr>
            <c:forEach items="${carRecordList}" var="carRecord">
                <tr>
                    <td>${carRecord.category} </td>
                    <td>${carRecord.description} </td>
                    <td>${carRecord.kmInterval} </td>
                    <td>${carRecord.monthInterval} </td>
                    <td>${carRecord.isPeriodic} </td>
                    <td>${carRecord.recordDate} </td>
                    <td>
                        <FORM id="form-${carRecord.id}" action="${carRecordEditUrl}" method="post">
                            <INPUT type="hidden" name="carRecordId" value="${carRecord.id}">
                            <INPUT type="hidden" name="carId" value="${carId}">
                            <BUTTON type="submit">Изменить</BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <FORM action="${carRecordEditUrl}" method="post">
        <BUTTON type="submit">Добавить запись</BUTTON>
    </FORM>
</u:html>

