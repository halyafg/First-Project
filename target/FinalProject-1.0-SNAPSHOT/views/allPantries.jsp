<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div class="page">
    <h1>All pantries ${house.name}</h1>
    <div class="navbar">
        <ul>
            <li><a class="bot" href="/">Головна</a></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/customers/all/inhouse/${house.id}">Покупці </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/flats/all/${house.id}">Квартири </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot activ" href="/pantries/all/${house.id}">Комірки </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/parkings/all/${house.id}">Паркомісця </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/schedules/all/${house.id}">Графіки виплат </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/payments/all/${house.id}">Фактична оплата </a> </sec:authorize></li>
            <li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Вийти   </a></sec:authorize></li>
        </ul>
    </div>
    <br>
    <ul>
        <li>
            <sec:authorize access="hasRole('ROLE_ADMIN')"><p><a class="bot"  href="/pantry/add/${house.id}">Add pantry!</a> </p><br></sec:authorize><br>
        </li>
    </ul>



    <table>
        <thead>
        <tr id="tabl">

            <th>Number</th>
            <th>Floor</th>
            <th>ProjectSize</th>
            <th>Real size</th>
            <th>Status</th>
            <th>Description</th>
            <th>Customer</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pantries}" var = "pant">
            <tr>
                <td>${pant.number}</td>
                <td>${pant.floor}</td>
                <td>${pant.projectSize}</td>
                <td>${pant.realSize}</td>
                <td>${pant.status}</td>
                <td>${pant.description}</td>
                <td><a href="/customer/inf/${house.id}/${pant.getCustomer().getId()}"> ${pant.customer.getSurname()} ${pant.customer.getName()} ${pant.customer.getLastname()}</a> </td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/pantry/editpage/${house.id}/${pant.id}">Edit pantry! </a><br></sec:authorize></td>
                <td><a href="/pantry/delete/${house.id}/${pant.id}">Delete pantry! </a></td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
