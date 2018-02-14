<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 02-Mar-17
  Time: 1:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/menu.css">
    <link rel="stylesheet" href="/resources/css/style_table.css">
    <link rel="stylesheet" href="/resources/css/style.css">
</head>
<body>
    <div class="page">
        <h1>Welcome to DreamHouse!</h1><br>
        <p class="authentic">Вітаємо, <sec:authentication property="name"/></p>
        <my:menu/>

        <%--<div class="navbar">--%>
            <%--<ul>--%>
                <%--<li><a class="bot activ" href="#">Головна</a></li>--%>
                <%--<li><sec:authorize access="isAnonymous()"><a class="bot" href="/loginpage">Увійти</a></sec:authorize></li>--%>

                <%--<li><sec:authorize access="hasRole('ROLE_USER')"><a class="bot" href="/cabinet">Моя сторінка </a></sec:authorize></li>--%>
                <%--<li><sec:authorize access="hasRole('ROLE_ADMIN')"><a class="bot" href="/houses/addpage">AddHouse </a> </sec:authorize></li>--%>

                <%--<li><sec:authorize access="isAuthenticated()"><a class="bot logout" href="/logout">Вийти   </a></sec:authorize></li><br><br><br>--%>
                <%--<c:forEach items="${houses}" var="h">--%>
                    <%--<li><a class="bot house" href="/house/page/${h.id}">${h.name} <br>${h.address} </a> </li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <ul>
            <c:forEach items="${houses}" var="h">
                <li><a class="bot house" href="/house/page/${h.id}">${h.name} <br>${h.address} </a> </li>
            </c:forEach>
        </ul>

    </div>

</body>
</html>