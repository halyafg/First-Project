<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 31-Mar-17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/image.css">
</head>
<body>
<div class="page">
<h1> Основні характеристики квартири № ${flat.flatnumber}</h1>
    <table>
        <tr>
            <th class="head">Flat's number:</th>
            <td>${flat.flatnumber}</td>
        </tr>
        <tr>
            <th class="head">Floor:</th>
            <td>${flat.floor}</td>
        </tr>
        <tr>
            <th class="head">Number of rooms:</th>
            <td>${flat.romsNumber}</td>
        </tr>
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

    <img width="100%" src="/resources/images/floor.jpg" alt = "floor"><br><br>

</div>
</body>
</html>
