<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 19:55
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
<body><div class="page">
    <h1>All Customers ${house.name}</h1>

    <div class="navbar">
        <ul>
            <li><a class="bot" href="/">Головна</a></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot activ" href="/customers/all/inhouse/${house.id}">Покупці </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/flats/all/${house.id}">Квартири </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/pantries/all/${house.id}">Комірки </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/parkings/all/${house.id}">Паркомісця </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/schedules/all/${house.id}">Графіки виплат </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/payments/all/${house.id}">Фактична оплата </a> </sec:authorize></li>
            <li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Вийти   </a></sec:authorize></li>
        </ul>
    </div>


    <br>
    <ul>
        <li>
            <sec:authorize access="hasRole('ROLE_ADMIN')"><p><a class="bot" href="/customer/add/${house.id}">Add customer! </a> </p><br></sec:authorize><br>
        </li>
    </ul>


    <div><table>
        <thead>
        <tr>

            <th>Name</th>
            <th>Flats</th>
            <th>Pantries</th>
            <th>Parkings</th>
            <th>Schedule and Payment</th>
            <th>Add payment</th>
            <th>Delete customer</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${customers}" var = "cust">

                <tr>
                    <td><a href="/customer/inf/${house.id}/${cust.id}">${cust.surname} ${cust.name} ${cust.lastname}</a></td>
                    <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.flatList}" var="cpl">${cpl.flatNumber},</c:forEach><br></sec:authorize></td>
                    <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.pantryList}" var="cpanl">${cpanl.number},</c:forEach><br></sec:authorize></td>
                    <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.parkingList}" var="cpl">${cpl.number},</c:forEach><br></sec:authorize></td>
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
