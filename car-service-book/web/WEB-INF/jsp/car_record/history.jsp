<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:if test="${not empty carRecordList}">
        <h2>история обслуживания ${carModel}</h2>
        <table>
            <tr>
                <th>Описание </th>
                <th>дата выполнения</th>
                <th>цена</th>
                <th>контакт мастера</th>
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

