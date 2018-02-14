<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <form action="/parking/add" method="post">
        <input name="houseId" type="text" value="${houseId}" hidden >

        <label for="number">Parking's number: </label>
        <input name="number" id="number" type="text" class="textbox"  value="0"><br><br>

        <button class="button" type="submit">Add!</button>
    </form>
</div>
</body>
</html>
