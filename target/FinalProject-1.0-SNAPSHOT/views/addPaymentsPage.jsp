<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 24-Mar-17
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Add payments</h1>

<div class="container">
    <form action="/payment/add" method="post">

        <input   name="houseId"  value="${houseId}" hidden>

        <select class="textbox"  name="customer_id" >
            <option value="0">Виберіть покупця:</option>
            <c:forEach items="${customers}" var = "c"><option value=${c.id}>${c.surname} ${c.name} ${c.lastname}</option></c:forEach>
        </select>

        <label for="data">Дата оплати: </label>
        <input class="textbox"  name="data" id="data" type="text">
        <label for="amount">Сума, грн: </label>
        <input class="textbox"  name="amount" id="amount" type="text">

        <button class="button" type="submit" >Add!</button>
    </form>
</div>




    <%--<form action="/payment/add" method="post">

        <label for="customer">Customer: </label>

        &lt;%&ndash;<input  name ="customer_id" id="customer" type="text"><br><br>&ndash;%&gt;
        <select name = "customer"  id="customer"><option></option>
            <c:forEach  items="${customers}" var="c"><option >${c.surname} ${c.name} ${lastname}</option> </c:forEach>

        </select>
        <br><br>

        <label for="data">Дата оплати: </label>
        <input name="data" id="data" type="text"><br><br>
        <label for="amount">Сума грн: </label>
        <input name="amount" id="amount" type="text"><br><br>

        <button type="submit">Add!</button>
    </form>--%>

</body>
</html>
