<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-Mar-17
  Time: 12:21
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
    <link rel="stylesheet" href="${cssPath}">
</head>
<body>
    <h1>${customer.surname} ${customer.name}:</h1>

    <div class="container">

        <%--@elvariable id="payment" type=""--%>
        <form:form commandName="payment" action="/payment/add/${houseId}/${customer.id}" >
            <form:label cssClass="title"  path="data" >Date of payment</form:label>
            <form:input path="data" class="textbox"/>
            <form:label cssClass="title"  path="amountGRN" >Amount, GRN</form:label>
            <form:input path="amountGRN" class="textbox"/>
            <input type="submit" class="button" value="Add" />
        </form:form>
    </div>



</body>
</html>
