<%--
  Created by IntelliJ IDEA.
  User: Айвен
  Date: 29.08.2021
  Time: 21:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>XML parser</title>
  </head>
  <body>
  <h1>XML parser</h1>
  <form action="parser" method="post">
    file name:
    <input name="fileName" type="text"><br><br>
    <label>Parse type</label><br>
    <input type="radio" id="dom" name="parseType" value="DOM">
    <label for="dom">DOM</label><br>
    <input type="radio" id="sax" name="parseType" value="SAX">
    <label for="sax">SAX</label><br>
    <div>
      <button type="submit">Submit</button>
    </div>
  </form>
  </body>
</html>
