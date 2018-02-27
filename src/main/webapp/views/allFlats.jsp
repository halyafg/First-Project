<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
<h1>All Flats in ${house.name}</h1>
    <my:menu/>
    <ul>
        <li>
            <sec:authorize access="hasRole('ROLE_ADMIN')"><p><a class="bot" href="/flat/add/${house.id}">Add flat! </a> </p><br></sec:authorize><br>
        </li>
    </ul>

    <table>
        <thead>
        <tr id="tabl">

            <th>Flat's number</th>
            <th>Floor</th>
            <th>Rooms</th>
            <th>Project size</th>
            <th>Real size</th>
            <th>Status</th>
            <th>Description</th>
            <th>Customer</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${flats}" var = "fl">
            <tr>
                <td class="flatNumber"><strong>${fl.flatNumber}</strong></td>
                <td>${fl.floor}</td>
                <td>${fl.romsNumber}</td>
                <td>${fl.projectSize}</td>
                <td>${fl.realSize}</td>
                <td>${fl.status}</td>
                <td>${fl.description}</td>
                <td><a href="/customer/inf/${house.id}/${fl.getCustomer().getId()}"> ${fl.customer.getSurname()} ${fl.customer.getName()} ${fl.customer.getLastname()}</a> </td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/flat/editpage/${fl.id}">Edit flat! </a> <br></sec:authorize></td>
                <td><a href="/flat/delete/${house.id},${fl.id}">Delete flat! </a> </td>


            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
