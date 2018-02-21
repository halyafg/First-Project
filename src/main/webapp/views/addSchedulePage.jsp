<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 18-Feb-18
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url  value="/resources/css/style_form.css" var="cssPath" htmlEscape="true"/>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="${cssPath}">
</head>
<body>
    <div class="container">
    <h1>Add payment schedule</h1>
        <%--@elvariable id="schedule" type=""--%>
        <form:form commandName="schedule" action="/schedule/add/${houseId}" >
            <select class="textbox"  name="customerId" >
                <option value="0">Виберіть покупця:</option>
                <c:forEach items="${customers}" var = "c"><option value=${c.id}>${c.surname} ${c.name} ${c.lastname}</option></c:forEach>
            </select>
            <form:label cssClass="title"  path="date" >Дата оплати: </form:label>
            <form:input path="date" class="textbox"/>
            <form:label cssClass="title"  path="amountUSA" >Amount, USA</form:label>
            <form:input path="amountUSA" class="textbox"/>
            <input type="submit" class="button" value="Add" />
        </form:form>

    </div> <%--container--%>
</body>
</html>
