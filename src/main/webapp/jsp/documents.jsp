<%--
  Created by IntelliJ IDEA.
  User: Tufa
  Date: 28.11.2023
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Document Description</title>
</head>
<body>
<div>
    <table>
        <tr>
            <th>Номер документа</th>
            <th>Описание</th>
        </tr>
        <c:forEach items="${documentFromServer}" var="doc">
            <tr>${doc.id}</tr>
            <tr>${doc.description}</tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
