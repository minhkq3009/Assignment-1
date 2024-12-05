<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Subject List</title>
</head>
<body>
<h1>Subject List</h1>
<a href="subject?action=create">Add New Subject</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Code</th>
        <th>Hours</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="subject" items="${subjects}">
        <tr>
            <td>${subject.id}</td>
            <td>${subject.name}</td>
            <td>${subject.code}</td>
            <td>${subject.hour}</td>
            <td>
                <a href="subject?action=edit&id=${subject.id}">Edit</a>
                <a href="subject?action=delete&id=${subject.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>