<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 06-Mar-17
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1>Registration page</h1>

<form action="" method="POST">
    <label for="name">Name*: </label>
    <input name="name" id="name" type="text"><br>
    <label for="surname">Second name*: </label>
    <input name="surname" id="surname" type="text"><br>
    <label for="lastname">Last name*</label>
    <input name="lastname" id="lastname" type="text"><br>
    <label for="phone">Phone*: </label>
    <input name="phone" id="phone" type="text"><br>
    <label for="email">Email*: </label>
    <input name="email" id="email" type="email"><br>
    <label for="password">Passwod*: </label>
    <input name="password" id="password" type="password"><br>

    <button type="submit" >Registrate!</button>
</form>
</body>
</html>
