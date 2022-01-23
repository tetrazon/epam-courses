<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<u:html title="${title}" message="${message}">
    <c:url value="/user/ban.html" var="banUserUrl"/>

    <c:if test="${not empty userList}">
            <h2><fmt:message key="menu.admin" /></h2>
        <table>
            <tr>
                <th><fmt:message key="user.id" /> </th>
                <th><fmt:message key="user.role" /> </th>
                <th><fmt:message key="user.name" /> </th>
                <th><fmt:message key="user.surname" /> </th>
                <th><fmt:message key="user.email" /> </th>
                <th><fmt:message key="user.phone" /> </th>
                <th><fmt:message key="user.district" /> </th>
                <th><fmt:message key="user.isBanned" /> </th>
            </tr>
            <fmt:message key="message.yes" var="yes" />
            <fmt:message key="message.no" var="no" />
            <c:forEach items="${userList}" var="user">
                <c:if test="${user.isBanned == true}">
                    <c:set var="isBanned" value="${yes}"/>
                </c:if>
                <c:if test="${user.isBanned == false}">
                    <c:set var="isBanned" value="${no}"/>
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
                            <BUTTON type="submit"><fmt:message key="user.ban" /></BUTTON>
                        </FORM>
                        <FORM id="form-${user.id}" action="${banUserUrl}" method="post">
                            <INPUT type="hidden" name="userId" value="${user.id}">
                            <INPUT type="hidden" name="isBanned" value="false">
                            <BUTTON type="submit"><fmt:message key="user.unban" /></BUTTON>
                        </FORM>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</u:html>

