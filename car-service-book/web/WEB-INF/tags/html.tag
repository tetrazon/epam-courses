<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/strict.dtd">

<%@tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="title" required="true" rtexprvalue="true" type="java.lang.String"%>
<%@attribute name="message" required="false" rtexprvalue="true" type="java.lang.String"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="u"%>

<HTML>
<HEAD>
	<META http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<TITLE>Онлайн сервисная книга авто - ${title}</TITLE>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
</HEAD>
<BODY>
<u:menu/>
<DIV id="page">
<jsp:doBody/>
</DIV>
</BODY>
</HTML>