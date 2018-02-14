<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-Apr-17
  Time: 12:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="va" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <h1>All Customers in ${house.name}</h1>

    <my:menu/>


    <div><table>
        <thead>
        <tr>

            <th>Name</th>
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

                <td><sec:authorize access="hasRole('ROLE_ADMIN')"><c:forEach items="${cust.flatList}" var="cpl">${cpl.flatNumber}(${cpl.house.name}),</c:forEach><br></sec:authorize></td>
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
