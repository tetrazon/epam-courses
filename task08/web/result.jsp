<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>XML parser</title>
  </head>
  <body>
  <h1>Flowers</h1>
  <jsp:include page="WEB-INF/header.html" />
  <br>
  <table>

    <tr><th>itemName </th><th>Name </th><th>soil </th><th>multiplying </th><th>grow Date </th>
        <th>stColor </th><th>lfColor </th>><th>average size </th>
        <th>temperature </th><th>water </th>><th>light </th><th></th></tr>
    <c:forEach var="flower" items="${flowers}">

      <tr>
        <td>${flower.itemName}</td>
        <td>${flower.name}</td>
        <td>${flower.soil}</td>
        <td>${flower.multiplying}</td>
        <td>${flower.growDate}</td>
        <td>${flower.visualParams.stColor}</td>
        <td>${flower.visualParams.lfColor}</td>
        <td>${flower.visualParams.avSize}</td>
        <td>${flower.growingTips.temperature}</td>
        <td>${flower.growingTips.water}</td>
        <td>${flower.growingTips.light}</td>
        <td>
        </td>
      </tr>
    </c:forEach>

  </table>
  </body>
</html>
