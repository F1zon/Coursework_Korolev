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
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,300;1,100&display=swap');
        body {
            background: linear-gradient(108deg, #2CA231 0%, #AEEB00 100%);
            text-align: center;
            font-family: Montserrat, sans-serif;
        }

        .main {
            width: 98%;
            height: 99%;
            top: 20px;
            display: inline-block;
            text-align: center;
            flex-shrink: 0;
            border-radius: 24px;
            background: rgba(255, 255, 255, 0.91);
        }

        .table {
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-collapse: collapse;
            padding: 30px;
            margin-top: 60px;
        }
        .table thead {
            font-weight: bold;
            padding: 5px;
            background: #37a10d;
            border: 1px solid #dddddd;
            color: white;
        }
        .table tbody {
            border: 1px solid #dddddd;
            padding: 5px;
        }
        .table tr {
            border: 1px solid black;
        }

        .addNew {
            width: 207px;
            height: 61px;
            flex-shrink: 0;

            border-radius: 100px;
            border: none;
            background: linear-gradient(95deg, #2CA231 0%, #AEEB00 100%);
            margin-top: 50px;
            margin-left: 1000px;

            color: #FFF;
            font-size: 24px;
            font-style: normal;
            font-weight: 400;
            line-height: normal;
        }

    </style>
    <title>Document Description</title>
</head>
<body>
<div>
    <div class="main">
        <button class="addNew" onclick="location.href='/docs/new'">Добавить</button>

        <table class="table">
            <thead>
                <tr>
                    <th>Номер</th>
                    <th>Вид документа</th>
                    <th>Организация</th>
                    <th>Дата</th>
                    <th>Описание</th>
                    <th>Пациент</th>
                    <th>Статус</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${allDocs}" var="docs">
                    <tr>
                        <th>${docs.id}</th>
                        <th>${docs.view}</th>
                        <th>${docs.organization}</th>
                        <th>${docs.date}</th>
                        <th>${docs.description}</th>
                        <th>${docs.patient}</th>
                        <th>${docs.state}</th>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </div>
</div>
</body>
</html>
