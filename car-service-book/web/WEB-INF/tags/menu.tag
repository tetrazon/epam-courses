<%@tag language="java" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<DIV id="header">
	<H1>Сервисная книга авто</H1>
	<c:if test="${not empty authorizedUser}">
		<UL class="right">
			<c:forEach items="${menu}" var="item">
				<c:url value="${item.url}" var="itemUrl"/>
				<LI class="item"><A href="${itemUrl}">${item.name}</A></LI>
			</c:forEach>
			<c:url value="/profile/edit.html" var="profileEditUrl"/>
			<LI><A href="${profileEditUrl}">профиль</A></LI>
			<c:url value="/logout.html" var="logoutUrl"/>
			<LI><A href="${logoutUrl}">выход</A></LI>
			<%--<LI class="item">
				<OL class="drop">
					<c:url value="/profile/edit.html" var="profileEditUrl"/>
					<LI><A href="${profileEditUrl}">профиль</A></LI>
					<c:url value="/logout.html" var="logoutUrl"/>
					<LI><A href="${logoutUrl}">выход</A></LI>
				</OL>
			</LI>--%>
		</UL>
	</c:if>
</DIV>
