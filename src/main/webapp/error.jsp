<%@ page import="java.util.List" %>
<html>
<head>
    <title>新增会员失败</title>
</head>
<body>
<h1>新增会员失败</h1>
<%
        List<String> errors = (List<String>) request.getAttribute("errors");
%>
<ul style='color: rgb(255, 0, 0);'>
        <% for (String error : errors) { %>
        <li><%= error %></li>
        <% } %>
</ul>
</body>
</html>