<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Add customer:</h1>
<div class="container">

    <form action="/customer/add" method="post">
        <input name="houseId"  value="${houseId}" hidden>
        <input name="cust_name" id="name" class="textbox" type="text" placeholder="Name*" required>
        <input name="cust_surname" id="surname" class="textbox" type="text" placeholder="Second name*" required>
        <input name="cust_lastname" id="lastname" class="textbox" type="text" placeholder="Last name*" required>
        <input name="cust_phone" id="phone" class="textbox" class="textbox" type="text" placeholder="Phone:">
        <input name="cust_email" id="email" class="textbox" type="email" placeholder="Email:" required>
        <input name="cust_password" id="password" class="textbox" type="password" placeholder="Password*: " required>
        <input name="pas_ser" id="pasportSerija" class="textbox" type="text" placeholder="Pasport's serija:">
        <input name="pas_number" id="pasportNumber" class="textbox" type="text" placeholder="Pasport's number:">
        <input name="pas_vidan" id="pasportVidan"class="textbox" type="text" placeholder="Pasport kim vydan: ">
        <input name="pas_data" id="pasportdata" class="textbox"  type="text" placeholder="Pasport data vidachi: ">

        <button class="button" type="submit">Add!</button>
    </form>

</div>

</body>
</html>
