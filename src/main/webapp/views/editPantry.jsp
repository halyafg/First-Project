<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <form action="/pantry/edit" method="post">
        <input name="houseId" type="text" value="${houseId}" hidden >
        <input name="pant_id" type="text" value="${pantry.id}" hidden >

        <label for="number">Flat's number: </label>
        <input name="pant_number" id="number" type="text" class="textbox" value="${pantry.number}">
        <label for="floor">Floor: </label>
        <input name="pant_floor" id="floor" type="text" class="textbox" value="${pantry.floor}">
        <label for="p_size">Project size: </label>
        <input name="pant_p_size" id="p_size" type="text" class="textbox" value="${pantry.projectSize}">
        <label for="r_size">Real size: </label>
        <input name="pant_r_size" id="r_size" type="text" class="textbox" value="${pantry.realSize}">

        <input name="pant_status" id="status" type="text" class="textbox" value="${pantry.status}" hidden>

        <label for="description">Description: </label>
        <input name="pant_description" id="description" type="text" class="textbox" value="${pantry.description}">


        <button type="submit" class="button">Edit!</button>
    </form>
</div>
</body>
</html>
