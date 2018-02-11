<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-Mar-17
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>${customer.surname} ${customer.name}:</h1>
    <div class="container">

    <form action="/payment/add" method="post">
        <input name="customerId" type="text" value="${customer.id}" hidden >
        <input name="houseId" type="text" value="${houseId}" hidden >

        <label for="data">Дата оплати: </label>
        <input class="textbox" name="data" id="data" type="text" value="null"><br><br>
        <label for="amount">Сума грн: </label>
        <input class="textbox" name="amount" id="amount" type="text" value="0"><br><br>

        <button class="button" type="submit">Add!</button>
    </form>
    </div>



</body>
</html>
