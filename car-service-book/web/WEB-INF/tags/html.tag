<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="message" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en_US'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="props.page-content" />
<HTML lang="${language}">
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE><fmt:message key="tag.title" /> - ${title}</TITLE>
	<c:if test="${not empty message}">
		<p><fmt:message key="${message}" /></p>
	</c:if>
</HEAD>
<BODY>
<form>
	<select id="language" name="language" onchange="submit()">
		<option value="en_US" ${language == 'en_US' ? 'selected' : ''}>English</option>
		<option value="ru_RU" ${language == 'ru_RU' ? 'selected' : ''}>Русский</option>
	</select>
</form>
<u:menu/>
<DIV id="page">
<jsp:doBody/>
</DIV>
</BODY>
</HTML>