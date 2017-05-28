<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Edit schedule page</h1>
<div class="container">
    <form action="/schedule/edit" method="post">
        <input name="id" type="text" value="${schedule.id}" hidden >

        <label for="data">Дата оплати: </label>
        <input name="data" id="data" type="text" class="textbox" value="${schedule.date}">

        <label for="amount">Сума оплати, $: </label>
        <input name="amount" id="amount" type="text" class="textbox" value="${schedule.amount_$}">

        <button class="button" type="submit">Edit!</button>
    </form>
</div>
</body>
</html>
