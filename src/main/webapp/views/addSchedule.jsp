<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url  value="/resources/css/style_form.css" var="cssPath" htmlEscape="true"/>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="${cssPath}">
</head>
<body>
<div class="container">
    <%--@elvariable id="schedule" type=""--%>
    <form:form commandName="schedule" action="/schedule/add/${houseId}/${customer.id}" >
        <form:label cssClass="title"  path="date" >Дата оплати: </form:label>
        <form:input path="date" class="textbox"/>
        <form:label cssClass="title"  path="amountUSA" >Amount, USA</form:label>
        <form:input path="amountUSA" class="textbox"/>
        <input type="submit" class="button" value="Add" />
    </form:form>
</div>
</body>
</html>
