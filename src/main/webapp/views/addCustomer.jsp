<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 05-Mar-17
  Time: 19:49
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
    <h1>Add customer:</h1>
    <div class="container">

        <%--@elvariable id="customer" type=""--%>
        <form:form commandName="customer" action="/customer/add/${houseId}" >
            <table>
                <tr>
                    <td>Name* :</td>
                    <td><form:input path="name" class="textbox" request="true"/></td>
                </tr>
                <tr>
                    <td>Surnsme*:</td>
                    <td><form:input path="surname" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Lastname*:</td>
                    <td><form:input path="lastname" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Phone*:</td>
                    <td><form:input path="phone" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Email*:</td>
                    <td><form:input path="email" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Password*:</td>
                    <td><form:password path="password" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Passport's seriya:</td>
                    <td><form:input path="pasportSeria" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Passport's number:</td>
                    <td><form:input path="pasportNumber" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Pasport kim vydan::</td>
                    <td><form:input path="pasportKimVidan" class="textbox"/></td>
                </tr>
                <tr>
                    <td>Passport's data vidachi:</td>
                    <td><form:input path="pasportData" class="textbox"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" class="button" value="Add" />
                    </td>
                </tr>
            </table>
        </form:form>

    </div>

</body>
</html>
