<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 14-Mar-17
  Time: 0:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_table.css">
</head>
<body>
<div class="page">
    <h1>${customer.surname} ${customer.name} ${customer.lastname}</h1>

    <br><br><br><br>
    <div class="c1">
        <h2>Графік виплат</h2>
        <table class="table_schedulle">
            <thead>
            <tr class="tabl">
                <th>Дата оплати</th>
                <th>Потрібно сплатити, $_USA</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customerSchedules}" var = "s">
                <tr>
                    <td>${s.date}</td>
                    <td class="money">${s.amount_$}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="c2">
        <h2>Таблиця здійснених виплат</h2>
        <table>
            <thead>
            <tr class="tabl">

                <th>Дата оплати</th>
                <th>Оплачено, грн</th>
                <th>Курс грн/дол</th>
                <th>Оплачено, $_USA</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customerPayments}" var = "p">
                <tr>
                    <td>${p.data}</td>
                    <td class="money">${p.amount_grn}</td>
                    <td class="money">${p.quote_$}</td>
                    <td class="money">${p.amount_$}</td>
                </tr>
            </c:forEach>
            <tr>
                <th>Разом:</th>
                <th></th>
                <th></th>
                <th class="money">${amountUSA}</th>
            </tr>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
