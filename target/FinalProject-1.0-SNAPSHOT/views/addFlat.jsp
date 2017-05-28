<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1> Add flat in ${house.name}</h1>
<div class="container">
    <form action="/flat/add" method="post">
        <input name="houseId"  type="text" value="${house.id}" hidden>
        <label for="number">Flat's number: </label>
        <input name="fl_name" id="number" type="text" class="textbox" required/>
        <label for="floor">Floor: </label>
        <input name="fl_floor" id="floor" type="text" class="textbox" required/>
        <label for="rooms">Rooms: </label>
        <input name="fl_rooms" id="rooms" type="text" class="textbox" required />
        <label for="p_size" >Project size: </label>
        <input name="fl_p_size" id="p_size" type="text" class="textbox" value="0.0">
        <label for="r_size">Real size: </label>
        <input name="fl_r_size" id="r_size" type="text" class="textbox" value="0.0">


        <label for="description">Description: </label>
        <input name="fl_description" id="description" type="text" class="textbox"><br><br>

        <button type="submit" class="button">Add!</button>
    </form>
</div>
</body>
</html>
