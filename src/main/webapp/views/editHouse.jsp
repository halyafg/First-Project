<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 23-May-17
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Edit house</h1>
<div class="container">
    <form action="/house/edit" method="post">

        <input name="houseId" type="text" value="${house.id}" hidden >

        <label for="name">House's name: </label>
        <input name="house_name" id="name" type="text" class="textbox" value="${house.name}">
        <label for="address">Address:</label>
        <input name="house_address" id="address" type="text" class="textbox" value="${house.address}">
        <label for="description">Description:  </label>
        <input name="house_description" id="description" type="text" class="textbox" value="${house.description}">

        <button type="submit" class="button">Edit!</button>
    </form>
</div>

</body>
</html>
