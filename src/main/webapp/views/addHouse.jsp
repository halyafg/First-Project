<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 07-Apr-17
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <h1>Add house page</h1>
    <form action="/house/add" method="post">
        <label for="name">House's name </label>
        <input name="name" id="name" type="text" class="textbox"><br><br>
        <label for="address">Address: </label>
        <input name="address" id="address" type="text" class="textbox"><br><br>
        <label for="description">Description: </label>
        <input name="description" id="description" type="text" class="textbox"><br><br>

        <button type="submit" class="button">Add!</button>
    </form>
</div>
</body>
</html>
