<%-- 
    Document   : updateCategory
    Created on : Mar 5, 2023, 10:30:48 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <h1>New Category Information</h1>
        <form action="KeyKeeper" method="post">
            <table class="order">
                <tr>
                    <th>Category Name</th>
                </tr>
                <tr>
                    <td><input type="text" name="catename" required=""
                               value="<%= (request.getAttribute("catename") == null) ? "" : request.getAttribute("catename")%>"/>
                    </td>
                </tr>
                <tr><input type="submit" value="Create category" name="action"/></tr>
            </table>
        </form>
    </body>
</html>
