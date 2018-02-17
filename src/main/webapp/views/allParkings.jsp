<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 22:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div class="page">
    <h1>All Parkings in ${house.name}</h1>
    <my:menu/>
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
