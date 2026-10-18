<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Gossip 微网志</title>
    <link rel='stylesheet' href='css/member.css' type='text/css'>
</head>
<body>
<div class='leftPanel'>
    <img src='images/14388.jpg' alt='Gossip 微网志' /><br><br>
    <%
        String username = (String) request.getSession().getAttribute("login");
    %>
    <a href='logout.do'>注销<%=username%></a>
<%--    <a href='logout.do'>注销${sessionScope.login}</a>--%>
</div>

<div class='leftPanel'>
    分享新鲜事...<br>
    信息要140字以内<br>
    <form method='post' action='message.do'>
    <textarea rows="6" cols="60" name=blabla></textarea><br>
        <button type='submit'>送出</button>
    </form>
    <p><hr><p>

<%--    <%--%>
<%--        UserService userService = (UserService)this.getServletContext().getAttribute("userService");--%>
<%--//        Map<Date,String> messages = userService.readMessage(username);--%>
<%--        List<Blah> blahs = (List<Blah>) request.getAttribute("blahs");--%>
<%--        if (blahs.isEmpty()){--%>
<%--                out.println("暂无信息");--%>
<%--        }--%>
<%--    %>--%>
<%--    <table>--%>
<%--    <%  for (Blah blah : blahs){ %>--%>
<%--        <tr>--%>
<%--        <%--%>
<%--            String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(blah.getDate());--%>
<%--        %>--%>
<%--            <td>--%>
<%--                <%=blah.getUsername()%><br>--%>
<%--                <%=blah.getTxt()%><br>--%>
<%--                <%=strDate%><br>--%>
<%--                <a href="delete.do?message=<%=blah.getDate().getTime()%>">删除</a><hr>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    <% } %>--%>
<%--    </table>--%>
    <table>
        <c:forEach var="blah" items="${requestScope.blahs}">
            <tr>
                <td>
                    ${blah.username}"<br>
                    ${blah.txt}"<br>
                    <fmt:formatDate value="${blah.date}" type="both" dateStyle="full" timeStyle="full"/><br>
                    <a href="delete.do?message=${blah.date.time}">删除</a><hr>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
