<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<u:html title="${title}" message="${message}">
    <c:url value="/user/ban.html" var="banUserUrl"/>

    <c:if test="${not empty userList}">
            <h2>Администрирование</h2>
        <table>
            <tr><th>ID </th><th>Роль </th><th>имя </th><th>фамилия </th><th>email </th><th>телефон </th><th>область </th><th>забанен </th></tr>
            <c:forEach items="${userList}" var="user">
                <c:if test="${user.isBanned == true}">
                    <c:set var="isBanned" value="да"/>
                </c:if>
                <c:if test="${user.isBanned == false}">
                    <c:set var="isBanned" value="нет"/>
                </c:if>
                <tr>
                    <td>${user.id} </td>
                    <td>${user.role} </td>
                    <td>${user.name} </td>
                    <td>${user.surname} </td>
                    <td>${user.email} </td>
                    <td>${user.mobilePhone} </td>
                    <td>${user.district} </td>
                    <td>${isBanned}</td>
                    <td>
                        <FORM id="form-${user.id}" action="${banUserUrl}" method="post">
                            <INPUT type="hidden" name="userId" value="${user.id}">
                            <INPUT type="hidden" name="isBanned" value="true">
                            <BUTTON type="submit">забанить</BUTTON>
                        </FORM>
                        <FORM id="form-${user.id}" action="${banUserUrl}" method="post">
                            <INPUT type="hidden" name="userId" value="${user.id}">
                            <INPUT type="hidden" name="isBanned" value="false">
                            <BUTTON type="submit">разбанить</BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

