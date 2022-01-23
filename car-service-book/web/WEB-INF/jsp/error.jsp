<%@page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<html>
<head>
	<title><fmt:message key="error" /></title>
</head>
<body>
<h3><fmt:message key="error" /></h3>
<c:choose>
	<c:when test="${not empty error}">
		<H2>${error}</H2>
	</c:when>
	<c:when test="${not empty pageContext.errorData.requestURI}">
		<H2><fmt:message key="error.requestedPage" /> ${pageContext.errorData.requestURI} <fmt:message key="error.notFound" /></H2>
	</c:when>
	<c:otherwise><fmt:message key="error.unexpectedError" /></c:otherwise>
</c:choose>
<c:url value="/index.html" var="mainUrl"/>
<A href="${mainUrl}"><fmt:message key="error.toMain" /></A>
</body>
</html>