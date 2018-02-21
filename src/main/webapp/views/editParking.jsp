<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 11-Mar-17
  Time: 22:59
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
    <%--@elvariable id="editedParking" type=""--%>
    <form:form commandName="editedParking" action="/parking/edit/${parking.id}" >
        <form:label cssClass="title"  path="number" >Parking's number:</form:label>
        <form:input path="number" class="textbox" value="${parking.number}"/>
        <form:hidden path="status" value="${parking.status}" />
        <input type="submit" class="button" value="Edit" />
    </form:form>
</div>
</body>
</html>
