<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="" %>
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
    <h1>All Schedules</h1>
    <my:menu/>

    <table>
        <thead>
        <tr id="tabl">
            <th></th>
            <th>Data</th>
            <th>Сума</th>
            <th>Customer</th>
            <th>DELETE</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${schedules}" var = "s">
            <tr>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/schedule/editpage/${s.id}">Edit schedule! </a> <br></sec:authorize></td>
                <td>${s.date}</td>
                <td>${s.amountUSA}</td>
                <td><a href="/customer/inf/${s.getCustomer().getId()}"> ${s.customer.getSurname()} ${s.customer.getName()} ${s.customer.getLastname()}</a> </td>

                <td><a href="/schedule/delete/${s.id}">Delete! </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
