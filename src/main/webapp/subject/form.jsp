<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subject Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<h1>Subject Form</h1>
<form action="subject" method="post">
    <input type="hidden" name="id" value="${subject != null ? subject.id : ''}">
    <label>Name:</label>
    <input type="text" name="name" value="${subject != null ? subject.name : ''}"><br>
    <label>Code:</label>
    <input type="text" name="code" value="${subject != null ? subject.code : ''}"><br>
    <label>Hours:</label>
    <input type="number" name="hour" value="${subject != null ? subject.hour : ''}"><br>
    <button type="submit">Save</button>
</form>
</body>
</html>