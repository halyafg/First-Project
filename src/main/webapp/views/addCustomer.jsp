<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 19:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
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
        <input name="name" id="name" class="textbox" type="text" placeholder="Name*" required>
        <input name="surname" id="surname" class="textbox" type="text" placeholder="Second name*" required>
        <input name="lastname" id="lastname" class="textbox" type="text" placeholder="Last name*" required>
        <input name="phone" id="phone" class="textbox" class="textbox" type="text" placeholder="Phone*:">
        <input name="email" id="email" class="textbox" type="email" placeholder="Email*:" required>
        <input name="password" id="password" class="textbox" type="password" placeholder="Password*: " required>
        <input name="pasportSeria" id="pasportSerija" class="textbox" type="text" placeholder="Pasport's serija:">
        <input name="pasportNumber" id="pasportNumber" class="textbox" type="text" placeholder="Pasport's number:">
        <input name="pasportKimVidan" id="pasportVidan"class="textbox" type="text" placeholder="Pasport kim vydan: ">
        <input name="pasportData" id="pasportdata" class="textbox"  type="text" placeholder="Pasport data vidachi: ">

        <button class="button" type="submit">Add!</button>
    </form>

</div>

</body>
</html>
