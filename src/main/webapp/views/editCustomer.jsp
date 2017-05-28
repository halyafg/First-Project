<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 10-Mar-17
  Time: 1:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Edit customer page</h1>
<div class="container">
    <form action="/customer/edit" method="post">
        <input name="cust_id" value="${cust.id}" hidden>
        <input name="houseId" value="${houseId}" hidden>

        <label for="name">Name </label>
        <input name="cust_name" id="name" class="textbox" type="text" value="${cust.name}">
        <label for="surname">Second name: </label>
        <input name="cust_surname" id="surname" class="textbox" type="text" value="${cust.surname}">
        <label for="lastname">Last name: </label>
        <input name="cust_lastname" id="lastname" class="textbox" type="text" value="${cust.lastname}">
        <label for="phone">Phone: </label>
        <input name="cust_phone" id="phone" class="textbox" type="text" value="${cust.phone}">
        <label for="email">Email: </label>
        <input name="cust_email" id="email" class="textbox" type="email" value="${cust.email}">
        <label for="password">Password*: </label>
        <input name="cust_password" id="password" class="textbox" type="password" value="${cust.password}">

        <label for="pasportSerija">Pasport's serija: </label>
        <input name="pas_ser" id="pasportSerija" class="textbox" type="text" value="${cust. pasportSeria}">
        <label for="pasportNumber">Pasport's number: </label>
        <input name="pas_number" id="pasportNumber" class="textbox" type="text" value="${cust.pasportNumber}">
        <label for="pasportVidan">Pasport kim vydan: </label>
        <input name="pas_vidan" id="pasportVidan" class="textbox" type="text" value="${cust.pasportKimVidan}">
        <label for="pasportdata">Pasport data vidachi: </label>
        <input name="pas_data" id="pasportdata" class="textbox"  type="text" value="${cust.pasportData}">

        <button type="submit" class="button">Edit!</button>
    </form>

</div>


</body>
</html>
