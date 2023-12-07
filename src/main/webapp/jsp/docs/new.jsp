<%--
  Created by IntelliJ IDEA.
  User: Tufa
  Date: 05.12.2023
  Time: 3:01
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <style>
        body {
            background: linear-gradient(108deg, #2CA231 0%, #AEEB00 100%);
            text-align: center;
        }

        .main {
            display: inline-block;
            width:200px;
            padding:20px;
        }

        input[type=text] {
            padding: 10px;
        }

        input[type=text] {
            padding:10px;
            margin:10px 0; // add top and bottom margin
        }

        table {
            display: inline-block;
        }

    </style>
    <title>Add document</title>
</head>
<body>
    <form method="post" action="${pageContext.request.contextPath}/docs" class="main">
        <label for="patient">Patient</label>
        <input name="patient" type="text" id="patient">
        <br/>

        <label for="view">View document</label>
        <input name="view" type="text" id="view">
        <br>

        <label for="organization">Organization</label>
        <input name="organization" type="text" id="organization">
        <br>

        <label for="description">Description</label>
        <input name="description" type="text" id="description">
        <br>

        <input type="submit" value="Add">
    </form>
</body>
</html>
