<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 21-Mar-17
  Time: 23:59
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
    <h1>Buy flat for ${customer.surname} ${customer.name} ${customer.lastname}</h1>





    <table>
        <tr>
            <th class="head">This customer already has flats:</th>
            <td><c:forEach items="${customer_sFlats}" var = "cp">${cp.flatNumber}, </c:forEach></td>
        </tr>
    </table>



    <div class="container">
        <form action="/flat/buy" method="post">
            <input name="customerId" type="text" value="${customer.id}" hidden>
            <input name="houseId" type="text" value="${houseId}" hidden>

            <label for="number">Flat's number to buy: </label>
            <%--<input name="number" id="number" type="text"><br><br>--%>

            <select name="flatId" id="number"  class="textbox">
                <option value="-9999">Виберіть вільну квартиру:</option>
                <c:forEach items="${freeFlats}" var = "p"><option value="${p.id}">${p.flatNumber}</option></c:forEach>
            </select>

            <button class="button"  type="submit">Buy!</button>
        </form>
    </div>


</div>
</body>
</html>
