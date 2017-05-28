<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-Apr-17
  Time: 12:52
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
    <h1>All Customers ${house.name}</h1>

    <div class="navbar">
        <ul>
            <li><a class="bot" href="/">Головна</a></li>
            <li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Вийти   </a></sec:authorize></li><br><br>

        </ul>
    </div>


    <div><table>
        <thead>
        <tr>

            <th>Name</th>
            <%--<th>Name</th>
            <th>Last name</th>--%>

            <th>Flats(house)</th>
            <th>Pantries(house)</th>
            <th>Parkings(house)</th>
            <th>Schedule and Payment</th>
            <th>Add payment</th>
            <th>Delete customer</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var = "cust">


            <tr>
                <td><a href="/customer/inf/0/${cust.id}">${cust.surname} ${cust.name} ${cust.lastname}</a></td>
                    <%--<td>${cust.name}</td>
                    <td>${cust.lastname}</td>--%>

                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.flatList}" var="cpl">${cpl.flatnumber}(${cpl.house.name}),</c:forEach><br></sec:authorize></td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.pantryList}" var="cpanl">${cpanl.number}(${cpanl.house.name}),</c:forEach><br></sec:authorize></td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.parkingList}" var="cpl">${cpl.number}(${cpl.house.name}),</c:forEach><br></sec:authorize></td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/schedl_paym/${cust.id}"> ***** </a> <br></sec:authorize></td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/payment/addpage/${house.id}/${cust.id}">Add payment!</a></sec:authorize></td>
                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><a href="/customer/delete/${house.id}/${cust.id}">Delete customer!</a></sec:authorize></td>

            </tr>
        </c:forEach>
        </tbody>


    </table></div>

</div>

</body>
</html>
