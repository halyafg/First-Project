<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 17:27
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
    <h1> Add flat in ${house.name}</h1>
    <%--@elvariable id="flat" type=""--%>
    <form:form commandName="flat" action="/flat/add/${house.id}" >
        <form:label path="flatNumber" cssClass="title" >Flat's number</form:label>
        <form:input path="flatNumber" class="textbox"/>
        <form:label path="floor" cssClass="title">Floor</form:label>
        <form:input path="floor" class="textbox"/>
        <form:label path="romsNumber" cssClass="title">Number of rooms</form:label>
        <form:input path="romsNumber" class="textbox"/>
        <form:label path="projectSize" cssClass="title">Projected size</form:label>
        <form:input path="projectSize" class="textbox"/>
        <form:label path="realSize" cssClass="title">Real size</form:label>
        <form:input path="realSize" class="textbox"/>
        <form:label path="description" cssClass="title">Description</form:label>
        <form:input path="description" class="textbox"/>

        <input type="submit" class="button" value="Add" />
    </form:form>
</div>
</body>
</html>
