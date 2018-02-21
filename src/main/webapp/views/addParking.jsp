<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url  value="/resources/css/style_form.css" var="cssPath" htmlEscape="true"/>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/${cssPath}">
</head>
<body>
<div class="container">
    <h1> Add parking place in ${house.name}</h1>
    <%--@elvariable id="parking" type=""--%>
    <form:form commandName="parking" action="/parking/add/${house.id}" >
        <form:label cssClass="title"  path="number" >Parking's number</form:label>
        <form:input path="number" class="textbox"/>
        <input type="submit" class="button" value="Add" />
    </form:form>
</div>
</body>
</html>
