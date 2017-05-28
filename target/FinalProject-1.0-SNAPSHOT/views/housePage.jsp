<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 07-Apr-17
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%--<link rel="stylesheet" href="/resources/css/style.css">--%>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
</head>
<body>
<div class="page">
    <h2>This is ${house.name}</h2>
    <div class="navbar">
        <ul>
            <li><a class="bot activ" href="/">Головна</a></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/customers/all/inhouse/${house.id}">Покупці </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/flats/all/${house.id}">Квартири </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/pantries/all/${house.id}">Комірки </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/parkings/all/${house.id}">Паркомісця </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/schedules/all/${house.id}">Графіки виплат </a> </sec:authorize></li>
            <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/payments/all/${house.id}">Фактична оплата </a> </sec:authorize></li>
            <li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Вийти   </a></sec:authorize></li>
        </ul>
    </div>

    <table class="table_home">
        <tr>
            <th rowspan="3">Вільні <br>квартири:</th>
            <th>1-кім</th>
            <td ><c:forEach items="${flats1}" var="f1">
                <a href="/flat/inf/${f1.id}" >${f1.flatnumber},
                </a></c:forEach>
            </td>

        </tr>
        <tr>
            <th>2-кім</th>
            <td ><c:forEach items="${flats2}" var="f2">
                <a href="/flat/inf/${f2.flatnumber}" >${f2.flatnumber},
                </a></c:forEach>
            </td>
        </tr>
        <tr>
            <th>3-кім</th>
            <td ><c:forEach items="${flats3}" var="f3">
                <a href="/flat/inf/${f3.flatnumber}" >${f3.flatnumber},
                </a></c:forEach>
            </td>
        </tr>
        <tr>
            <th colspan="2" class="head">Вільні комірки:</th>
            <td><c:forEach items="${freePantries}" var="fp">${fp.number}, </c:forEach></td>
        </tr>
        <tr>
            <th colspan="2" class="head">Вільні паркомісця:</th>
            <td><c:forEach items="${freeParkings}" var="fp">${fp.number},</c:forEach></td>
        </tr>


    </table>
    <ul>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/house/editpage/${house.id}">Edit </a> </sec:authorize></li>
        <li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/house/delete/${house.id}">Delete this house </a> </sec:authorize></li>
    </ul>

</div>
</body>
</html>
