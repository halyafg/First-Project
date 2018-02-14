<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 09-Mar-17
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <form action="/flat/edit" method="post">
        <input name="flId" type="text" value="${flat.id}" hidden >
        <input name="houseId" type="text" value="${houseId}" hidden >

        <label for="number">Flat's number: </label>
        <input name="flatNumber" id="number" type="text" class="textbox" value="${flat.flatNumber}"><br><br>
        <label for="floor">Floor: </label>
        <input name="floor" id="floor" type="text" class="textbox" value="${flat.floor}"><br><br>
        <label for="rooms">Rooms: </label>
        <input name="romsNumber" id="rooms" type="text" class="textbox" value="${flat.romsNumber}"><br><br>
        <label for="p_size">Project size: </label>
        <input name="projectSize" id="p_size" type="text" class="textbox" value="${flat.projectSize}"><br><br>
        <label for="r_size">Real size: </label>
        <input name="realSize" id="r_size" type="text" class="textbox" value="${flat.realSize}"><br><br>

        <input name="status" id="status" type="text" value="${flat.status}" hidden>

        <label for="description">Description: </label>
        <input name="description" id="description" class="textbox" type="text" value="${flat.description}"><br><br>

        <button type="submit" class="button">Edit!</button>
    </form>
</div>
</body>
</html>
