<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 21:32
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
    <%--@elvariable id="editedPantry" type=""--%>
    <form:form commandName="editedPantry" action="/pantry/edit/${pantry.id}" >
        <form:label cssClass="title"  path="number" >Pantry's number:</form:label>
        <form:input path="number" class="textbox" value="${pantry.number}"/>
        <form:label cssClass="title"  path="floor" >Floor:</form:label>
        <form:input path="floor" class="textbox" value="${pantry.floor}"/>
        <form:label cssClass="title"  path="projectSize" >Project size: </form:label>
        <form:input path="projectSize" class="textbox" value="${pantry.projectSize}"/>
        <form:label cssClass="title"  path="realSize" >Real size: </form:label>
        <form:input path="realSize" class="textbox" value="${pantry.realSize}"/>
        <form:hidden path="status" value="${pantry.status}" />
        <input type="submit" class="button" value="Edit" />
    </form:form>
</div>
</body>
</html>
