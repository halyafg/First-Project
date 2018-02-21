<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div class="page">
    <my:menu/>
<h2>Information about ${customer.surname} ${customer.name}</h2>
<ul>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/customer/editpage/${house.id}/${customer.id}" class="bot person">Edit! </a> </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/customer/delete/${house.id}/${customer.id}" class="bot person">Delete! </a> </sec:authorize><br>
</ul>

    <table>
        <tr>
            <th class="head">Name:</th>
            <td>${customer.name}</td>
        </tr>
        <tr>
            <th class="head">Second name:</th>
            <td>${customer.surname}</td>
        </tr>
        <tr>
            <th class="head">Last name:</th>
            <td>${customer.lastname}</td>
        </tr>
        <tr>
            <th class="head">Phone:</th>
            <td>${customer.phone}</td>
        </tr>
        <tr>
            <th class="head" >Email:</th>
            <td>${customer.email}</td>
        </tr>
        <tr>
            <th class="head">Pasword:</th>
            <td>${customer.password}</td>
        </tr>
        <tr>
            <th class="head">Pasport-serija:</th>
            <td>${customer.pasportSeria}</td>
        </tr>
        <tr>
            <th class="head">Pasport-number:</th>
            <td>${customer.pasportNumber}</td>
        </tr>
        <tr>
            <th class="head">Pasport-vidanui:</th>
            <td>${customer.pasportKimVidan}</td>
        </tr>
        <tr>
            <th class="head">Pasport-data:</th>
            <td>${customer.pasportData}</td>
        </tr>
        <tr>
            <th class="head">Flats:</th>
            <td><c:forEach items="${customer.flatList}" var="cf"> ${cf.flatNumber}, </c:forEach></td>
        </tr>
        <tr>
            <th class="head">Pantries:</th>
            <td><c:forEach items="${customer.pantryList}" var="cpa">${cpa.number}, </c:forEach></td>
        </tr>
        <tr>
            <th class="head">Parkings:</th>
            <td><c:forEach items="${customer.parkingList}" var="cp"> ${cp.number}, </c:forEach></td>
        </tr>


    </table>



    <br>


    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/schedl_paym/${house.id}/${customer.id}" class="bot person">Schedule and Payments</a> </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/schedule/addpage/${house.id}/${customer.id}" class="bot person">Add Schedule! </a> </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/payment/addpage/${house.id}/${customer.id}" class="bot person">Add Payment! </a> <br></sec:authorize>
    <br><br>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/flat/buypage/${house.id}/${customer.id}" class="bot person">Buy flat! </a> </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/flat/takepage/${house.id}/${customer.id}" class="bot person">Take away flat! </a> </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/pantry/buyPantryPage/${house.id}/${customer.id}" class="bot  person logout">Buy pantry! </a>  </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/pantry/takePage/${house.id}/${customer.id}" class="bot person">Take away pantry! </a>  </sec:authorize>

    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/parking/buyPage/${house.id}/${customer.id}" class="bot person logout">Buy parking! </a>  </sec:authorize>
    <sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/parking/takePage /${house.id}/${customer.id}" class="bot person">Take parking! </a>  </sec:authorize>
    <br><br><br><br>
</div>
</body>
</html>
