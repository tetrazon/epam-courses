<%@tag language="java" pageEncoding="UTF-8"%>
<%--<%@ attribute name="key" required="true" type="java.lang.String"%>
<%@ attribute name="buttonValue" required="false" type="java.lang.String"%>--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<%--<c:if test="${buttonValue!= null}">
    <fmt:message key="${key}" var="${buttonValue}" />
</c:if>--%>
<%--<fmt:message key="${key}" />--%>
<jsp:doBody/>
