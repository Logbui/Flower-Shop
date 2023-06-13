<%-- 
    Document   : header_loginedUser
    Created on : Feb 13, 2023, 10:12:40 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="checkout.jsp">Home</a></li>
                <li><a href="KeyKeeper?action=updateProfile">Change profile</a></li>
                <li><a href="KeyKeeper?action=viewCompletedOrders&Orderstatus=1">Completed orders</a></li>
                <li><a href="KeyKeeper?action=viewProcessingOrders&Orderstatus=2">Processing orders</a></li>
                <li><a href="KeyKeeper?action=viewCanceledOrders&Orderstatus=3">Canceled orders</a></li>
                <li><a href="KeyKeeper?action=logout">Logout</a></li>
                <li>
                    <form action="KeyKeeper" method="post">
                        From <input type="date" name="from" value="<%= (request.getParameter("from") == null)?"":request.getParameter("from")%>"/> 
                        To <input type="date" name="to" value="<%= (request.getParameter("to") == null)?"":request.getParameter("to")%>"/>
                        <input type="submit" value="searchOrder" name="action"/>
                    </form>
                </li>
            </ul>
        </nav>
    </body>
</html>
