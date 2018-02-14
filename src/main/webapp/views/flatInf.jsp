<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 31-Mar-17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url  value="/resources/css/style_table.css" var="url_1" htmlEscape="true"/>
<spring:url  value="/resources/css/menu.css" var="url_2" htmlEscape="true"/>
<spring:url  value="/resources/css/style.css" var="url_3" htmlEscape="true"/>
<spring:url  value="/resources/images/floor.jpg" var="url_4" htmlEscape="true"/>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="${url_1}">
    <link rel="stylesheet" href="${url_2}">
    <link rel="stylesheet" href="${url_3}">
</head>
<body>
<div class="page">
<my:menu/>
<h1> Основні характеристики квартири № ${flat.flatNumber} in ${flat.house.name}</h1>
    <table>
        <tr>
            <th class="head">Flat's number:</th>
            <td>${flat.flatNumber}</td>
        </tr>
        <tr>
            <th class="head">Floor:</th>
            <td>${flat.floor}</td>
        </tr>
        <tr>
            <th class="head">Number of rooms:</th>
            <td>${flat.romsNumber}</td>
        </tr>
        <tr>
            <th class="head">Project size:</th>
            <td>${flat.projectSize}</td>
        </tr>
        <tr>
            <th class="head" >Real size:</th>
            <td>${flat.realSize}</td>
        <tr>
            <th class="head">Description:</th>
            <td>${flat.description}</td>
        </tr>

    </table>
    <br>

    <c:forEach items="${flat.imageList}" var="img">
        <img class="img" src="${img.imagePath}"><br><br>
    </c:forEach>

    <img width="100%" src="${url_4}" alt = "floor"><br>
</div>
</body>
</html>