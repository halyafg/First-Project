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
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<div class="container">
    <h1> Add flat in ${house.name}</h1>
    <%--@elvariable id="flat" type=""--%>
    <form:form commandName="flat" action="/flat/add/${house.id}" >
        <form:label cssClass="title"  path="flatNumber" >Flat's number</form:label>
        <form:input path="flatNumber" class="textbox"/>
        <form:label cssClass="title"  path="floor" >Floor</form:label>
        <form:input path="floor" class="textbox"/>
        <form:label cssClass="title"  path="romsNumber" >Number of rooms</form:label>
        <form:input path="romsNumber" class="textbox"/>
        <form:label cssClass="title"  path="projectSize" >Projected size</form:label>
        <form:input path="projectSize" class="textbox"/>
        <form:label cssClass="title"  path="realSize" >Real size</form:label>
        <form:input path="realSize" class="textbox"/>
        <form:label cssClass="title"  path="description" >Description</form:label>
        <form:input path="description" class="textbox"/>

        <input type="submit" class="button" value="Add" />
    </form:form>
</div>
</body>
</html>
