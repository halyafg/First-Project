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
<spring:url  value="/resources/css/style_table.css" var="styleTable" htmlEscape="true"/>
<spring:url  value="/resources/css/menu.css" var="menuCss" htmlEscape="true"/>
<spring:url  value="/resources/css/style.css" var="styleCss" htmlEscape="true"/>
<spring:url  value="/resources/images/floor.jpg" var="imageFloor" htmlEscape="true"/>

<html>
<head>
    <my:title/>
    <%--<my:springUrl style_table="url_1" menu_css="url_2" style_css="url_3" imageFloor="url_4"/>--%>
    <link rel="stylesheet" href="${styleTable}">
    <link rel="stylesheet" href="${menuCss}">
    <link rel="stylesheet" href="${styleCss}">
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

    <img width="100%" src="${imageFloor}" alt = "floor"><br>
</div>
</body>
</html>