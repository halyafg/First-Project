<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Edit schedule page</h1>
<div class="container">

    <%--@elvariable id="schedule" type=""--%>
    <form:form commandName="schedule" action="/schedule/edit/${schedule.id}" >
        <form:label cssClass="title"  path="date" >Date of payment</form:label>
        <form:input path="date" class="textbox" value="${schedule.date}"/>
        <form:label cssClass="title"  path="amountUSA" >Amount, USA</form:label>
        <form:input path="amountUSA" class="textbox" value="${schedule.amountUSA}"/>
        <input type="submit" class="button" value="Edit" />
    </form:form>
</div>
</body>
</html>
