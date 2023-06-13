<%-- 
    Document   : header_loginedAdmin
    Created on : Feb 22, 2023, 4:23:14 PM
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
        <header>
            <ul>
                <li><a href="KeyKeeper?action=manageAccounts">Manage Accounts</a></li>
                <li><a href="KeyKeeper?action=manageOrder">Manage Orders</a></li>
                <li><a href="KeyKeeper?action=managePlant">Manage Plants</a></li>
                <li><a href="KeyKeeper?action=manageCategory">Manage categories</a></li>
                <li>Welcome ${sessionScope.name} |  <a href="KeyKeeper?action=logout">Logout</a></li>
            </ul>
        </header>
   

    </body>
    
</html>
