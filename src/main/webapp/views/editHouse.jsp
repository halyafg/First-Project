<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 23-May-17
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <my:title/>
    <link rel="stylesheet" href="/resources/css/style_form.css">
</head>
<body>
<h1>Edit house</h1>
<div class="container">
<%--    <form action="/house/edit" method="post">

        <input name="houseId" type="text" value="${house.id}" hidden >

        <label for="name">House's name: </label>
        <input name="name" id="name" type="text" class="textbox" value="${house.name}">
        <label for="address">Address:</label>
        <input name="address" id="address" type="text" class="textbox" value="${house.address}">
        <label for="description">Description:  </label>
        <input name="description" id="description" type="text" class="textbox" value="${house.description}">

        <button type="submit" class="button">Edit!</button>
    </form>--%>

    <%--@elvariable id="house" type=""--%>
    <form:form commandName="house"  action="/house/edit">
        <table>
            <tr>
                <td><form:hidden path="id" class="textbox" /></td>
            </tr>
            <tr>
                <td>House's name:</td>
                <td><form:input path="name" class="textbox" /></td>
            </tr>
            <tr>
                <td>Address:</td>
                <td><form:input path="address" class="textbox" /></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><form:input path="description" class="textbox" /></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" class="button" value="Save Changes" />
                </td>
            </tr>
        </table>
    </form:form>

</div>

</body>
</html>
