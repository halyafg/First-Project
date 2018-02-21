<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-Mar-17
  Time: 12:52
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
    <%--@elvariable id="editedPayment" type=""--%>
    <form:form commandName="editedPayment" action="/payment/edit/${payment.id}" >
        <form:label cssClass="title"  path="data" >Date of payment</form:label>
        <form:input path="data" class="textbox" value="${payment.data}"/>
        <form:label cssClass="title"  path="amountGRN" >Amount, GRN</form:label>
        <form:input path="amountGRN" class="textbox" value="${payment.amountGRN}"/>
        <form:label cssClass="title"  path="quoteUSA" >Quote, USA</form:label>
        <form:input path="quoteUSA" class="textbox" value="${payment.quoteUSA}"/>
        <input type="submit" class="button" value="Edit" />
    </form:form>
</div>
</body>
</html>
