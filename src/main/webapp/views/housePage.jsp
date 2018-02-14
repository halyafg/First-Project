<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 07-Apr-17
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
        <my:title/>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/menu.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
<div class="page">
    <my:menu/>
    <h2>This is ${house.name}</h2>

    <table class="table_home">
        <tr>
            <th rowspan="3">Вільні <br>квартири:</th>
            <th>1-кім</th>
            <td ><c:forEach items="${flats1}" var="f1">
                <a href="/flat/inf/${f1.id}" >${f1.flatNumber},
                </a></c:forEach>
            </td>

        </tr>
        <tr>
            <th>2-кім</th>
            <td ><c:forEach items="${flats2}" var="f2">
                <a href="/flat/inf/${f2.id}" >${f2.flatNumber},
                </a></c:forEach>
            </td>
        </tr>
        <tr>
            <th>3-кім</th>
            <td ><c:forEach items="${flats3}" var="f3">
                <a href="/flat/inf/${f3.id}" >${f3.flatNumber},
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