<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 18-Mar-17
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
    <link rel="stylesheet" href="/resources/css/style_table.css">
</head>
<body>
<div class="page">
    <h2>Buy parking for ${customer.surname} ${customer.name} ${customer.lastname}</h2>
    <table>
        <thead>
        <tr class="tabl">
            <th>This customer already has parkings:</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customer_sParkings}" var = "cp">
            <tr>
                <td>${cp.number}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="container">
        <form action="/parking/buy" method="post">
            <input name="houseId" type="text" value="${houseId}" hidden>
            <input name="customerId" type="text" value="${customer.id}" hidden>

            <label for="parkingId">Parking's number to buy: </label>
            <select name="parkingId" id="parkingId"  class="textbox">
                <option value="-1">Free parkings:</option>
                <c:forEach items="${freeParkings}" var = "fp"><option value="${fp.id}">${fp.number}</option></c:forEach>
            </select>

            <button type="submit"  class="button">Buy!</button>
        </form>
    </div>


</div>

</body>
</html>
