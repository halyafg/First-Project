<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-Mar-17
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <form action="/payment/edit" method="post">
        <input name="id" type="text" value="${payment.id}" hidden >

        <label for="data">Дата оплати: </label>
        <input name="data" id="data" class="textbox" type="text" value="${payment.data}"><br><br>

        <label for="amount">Сума оплати, грн: </label>
        <input name="amount" id="amount" class="textbox" type="text" value="${payment.amountGRN}"><br><br>

        <label for="quote">Курс, грн/дол: </label>
        <input name="quote" id="quote" class="textbox" type="text" value="${payment.quoteUSA}"><br><br>

        <button type="submit" class="button">Edit!</button>
    </form>
</div>
</body>
</html>
