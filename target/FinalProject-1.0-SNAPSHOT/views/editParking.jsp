<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <form action="/parking/edit" method="post">

        <input name="id" type="text" value="${parking.id}" hidden >

        <label for="number">Parking's number: </label>
        <input name="number" id="number" class="textbox" type="text" value="${parking.number}">

        <input name="status" id="status" class="textbox" type="text" value="${parking.status}" hidden>

        <button type="submit" class="button">Edit!</button>
    </form>
</div>
</body>
</html>
