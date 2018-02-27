<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 09-Mar-17
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">

    <%--@elvariable id="flat" type=""--%>
    <form:form commandName="flat" action="/flat/edit" >
        <form:hidden path="id" value="${flat.id}"/>
        <form:label path="flatNumber" cssClass="title" >Flat's number</form:label>
        <form:input path="flatNumber" class="textbox" value="${flat.flatNumber}"/>
        <form:label path="floor" cssClass="title">Floor</form:label>
        <form:input path="floor" class="textbox" value="${flat.floor}"/>
        <form:label path="romsNumber" cssClass="title">Number of rooms</form:label>
        <form:input path="romsNumber" class="textbox" value="${flat.romsNumber}"/>
        <form:label path="projectSize" cssClass="title">Projected size</form:label>
        <form:input path="projectSize" class="textbox"  value="${flat.projectSize}"/>
        <form:label path="realSize" cssClass="title">Real size</form:label>
        <form:input path="realSize" class="textbox" value="${flat.realSize}"/>
        <form:hidden path="status" value="${flat.status}"/>
        <form:label path="description" cssClass="title">Description</form:label>
        <form:input path="description" class="textbox" value="${flat.description}"/>
        <input type="submit" class="button" value="Add" />
    </form:form>
</div>
</body>
</html>
