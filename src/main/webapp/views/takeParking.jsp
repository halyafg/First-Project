<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 20-Mar-17
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="page">
    <h2>Take away parking from ${customer.surname} ${customer.name} ${customer.lastname}</h2>

    <div class="container">
        <form action="/parking/take" method="post">
            <input name="houseId" value="${houseId}" hidden>
            <input name="customerId"  value="${customer.id}" hidden>

            <label for="parkingId">Parking's number to take away from customer: </label>
            <select name="parkingId" id="parkingId"  class="textbox" >
                <option value="-1">Make a choice:</option>
                <c:forEach items="${customer_sParkings}" var = "p"><option value="${p.id}">${p.number}</option></c:forEach>
            </select>

            <button type="submit" class="button">Take away!</button>
        </form>
    </div>
</div>
</body>
</html>
