<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 07-Apr-17
  Time: 12:40
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
    <h1>Add house page</h1>

    <%--@elvariable id="house" type=""--%>
    <form:form commandName="house" action="/house/add" >
        <table>
            <tr>
                <td>House's name:</td>
                <td><form:input   class="textbox" path="name"/></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" class="textbox"/></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description" class="textbox"/></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="button" value="Add house" />
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>
