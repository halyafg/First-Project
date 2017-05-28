<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 23:25
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
    <form action="/pantry/add" method="post">
        <input name="houseId"  type="text" value=${houseId} hidden>
        <label for="number">Pantry's number: </label>
        <input name="pant_number" id="number" type="text" class="textbox">
        <label for="floor">Floor: </label>
        <input name="pant_floor" id="floor" type="text" class="textbox">
        <label for="p_size">Project size: </label>
        <input name="p_size" id="p_size" type="text" class="textbox" value="0">
        <label for="r_size">Real size: </label>
        <input name="r_size" id="r_size" type="text" class="textbox" value="0">
        <label for="description">Description: </label>
        <input name="pant_description" id="description" type="text" class="textbox">

        <button type="submit" class="button">Add!</button>
    </form>
</div>
</body>
</html>
