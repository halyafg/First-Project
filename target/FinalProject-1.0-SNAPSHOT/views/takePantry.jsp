<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 21-Mar-17
  Time: 0:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="page">
    <h2>Take away pantry from ${customer.surname} ${customer.name} ${customer.lastname}</h2>


    <table>
        <thead>
        <tr class="tabl">
            <th>This customer already have pantries:</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customer_sPantries}" var = "cp">
            <tr>
                <td>${cp.number}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table><br>

    <div class="container">
    <form action="/pantry/take" method="post">
        <input name="houseId" value="${houseId}" hidden>
        <input name="customerId"  value="${customer.id}" hidden>

        <label for="pantryId">Pantry's number to take away from customer: </label>
        <select name="pantryId" id="pantryId"  class="textbox" >
            <option value="-1">Make a choice:</option>
            <c:forEach items="${customer_sPantries}" var = "p"><option value="${p.id}">${p.number}</option></c:forEach>
        </select>

        <button type="submit" class="button">Take away!</button>
    </form>
    </div>
</div>
</body>
</html>
