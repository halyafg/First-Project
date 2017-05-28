<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DreamHouse</title>
    <%--<link rel="stylesheet" href="/resources/css/style.css">--%>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div class="page">
    <h1>All Parkings in ${house.name}</h1>
    <div class="navbar">
        <ul>
            <li><a class="bot " href="/">Головна</a></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/customers/all/inhouse/${house.id}">Покупці </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/flats/all/${house.id}">Квартири </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/pantries/all/${house.id}">Комірки </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot activ" href="/parkings/all/${house.id}">Паркомісця </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/schedules/all/${house.id}">Графіки виплат </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/payments/all/${house.id}">Фактична оплата </a> </sec:authorize></li>
            <li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Вийти   </a></sec:authorize></li>
        </ul>
    </div>
    <br>
    <ul>
        <li>
            <sec:authorize access="hasRole('ROLE_ADMIN')"><p><a class="bot" href="/parking/addpage/${house.id}">Add parking! </a> </p><br></sec:authorize><br>
        </li>
    </ul>


    <table>
        <thead>
        <tr id="tabl">

            <th>Number</th>
            <th>Status</th>
            <th>Customer</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${parkings}" var = "p">
            <tr>
                <td>${p.number}</td>
                <td>${p.status}</td>
                <td><a href="/customer/inf/${house.id}/${p.getCustomer().getId()}"> ${p.customer.getSurname()} ${p.customer.getName()} ${p.customer.getLastname()}</a> </td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/parking/editpage/${p.id}">Edit parking! </a> <br></sec:authorize></td>
                <td><a href="/parking/delete/${house.id}/${p.id}">Delete! </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
