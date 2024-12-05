<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Class List</title>
</head>
<body>
<h1>Class List</h1>
<a href="class?action=create">Add New Class</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="class" items="${classes}">
        <tr>
            <td>${class.id}</td>
            <td>${class.name}</td>
            <td>
                <a href="class?action=edit&id=${class.id}">Edit</a>
                <a href="class?action=delete&id=${class.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>