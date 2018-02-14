<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 22-Mar-17
  Time: 0:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="page">
    <h1>Take away flat from ${customer.surname} ${customer.name} ${customer.lastname}</h1>

    <div class="container">
    <form action="/flat/take" method="post">
        <input name="customerId" type="text" value="${customer.id}" hidden>
        <input name="houseId" type="text" value="${houseId}" hidden>


        <select name="flatId" id="number"  class="textbox">
            <option value="-1">Виберіть квартиру:</option>
            <c:forEach items="${customer_sFlats}" var = "f"><option value="${f.id}">${f.flatNumber}</option></c:forEach>
        </select>

        <button type="submit" class="button">Take away!</button>
    </form>
    </div>
</div>
</body>
</html>
