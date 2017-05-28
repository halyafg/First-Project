<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 23:43
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
    <form action="/schedule/add" method="post">
        <input name="customer_id" type="text" value="${customer_id}" hidden>
        <input name="houseId" type="text" value="${houseId}" hidden>
        <label for="data">Дата оплати: </label>
        <input name="data" id="data" class="textbox" type="text"><br><br>
        <label for="amount">Сума, $_USA: </label>
        <input name="amount" id="amount" class="textbox" type="text"><br><br>

        <button class="button" type="submit">Add!</button>
    </form>
</div>
</body>
</html>
