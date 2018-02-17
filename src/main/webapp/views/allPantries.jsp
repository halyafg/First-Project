<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 23:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div class="page">
    <h1>All pantries in ${house.name}</h1>
    <my:menu/>
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
