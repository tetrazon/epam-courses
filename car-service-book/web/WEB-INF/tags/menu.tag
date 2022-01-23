<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<DIV id="header">
	<H1><fmt:message key="menu.header" /></H1>
	<c:if test="${not empty authorizedUser}">
		<UL class="right">
			<c:forEach items="${menu}" var="item">
				<c:url value="${item.url}" var="itemUrl"/>
				<LI class="item"><A href="${itemUrl}"><fmt:message key="${item.name}" /></A></LI>
			</c:forEach>
			<c:url value="/register.html" var="profileEditUrl"/>
			<LI><A href="${profileEditUrl}"><fmt:message key="menu.profile" /></A></LI>
			<c:url value="/logout.html" var="logoutUrl"/>
			<LI><A href="${logoutUrl}"><fmt:message key="car.logout" /></A></LI>
		</UL>
	</c:if>
</DIV>
