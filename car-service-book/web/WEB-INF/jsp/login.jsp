<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false"%>
<html>
<head>
	<title>"Вход в систему"</title>
</head>
<body>
<h3>"Вход в систему"</h3>
<form method="post" action="/login.html">
	<label>login</label><br>
	<input required="" type="text" id="login" name="login" /><br><br>
	<label>password</label><br>
	<input required="" type="password" name="password" /><br><br>
	<input type="submit" value="Вход" />
</form>
</body>
</html>