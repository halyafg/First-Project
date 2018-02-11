<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 20-Mar-17
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="page">
    <h2>Buy pantry for ${customer.surname} ${customer.name} ${customer.lastname}</h2>


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
    <form action="/pantry/buy" method="post">
        <input name="customerId" type="text" value="${customer.id}" hidden>
        <input name="houseId" type="text" value="${houseId}" hidden>

        <label for="pantryId">Pantry's number to buy: </label>
        <select name="pantryId" id="pantryId"  class="textbox">
            <option value="-1">Free pantries:</option>
            <c:forEach items="${freePantries}" var = "fp"><option value="${fp.id}">${fp.number}</option></c:forEach>
        </select>

        <button class="button" type="submit">Buy!</button>
    </form>
    </div>
</div>
</body>
</html>
