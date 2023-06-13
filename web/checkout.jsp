<%-- 
    Document   : index
    Created on : Feb 7, 2023, 2:23:22 PM
    Author     : ADMIN
--%>

<%@page import="controller.MyConstant"%>
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
            <nav>
                <ul>
                    <li><img src="images/logo.jpg" class="product"></li>
                    <li><a href="personalPage.jsp">Personal page</a></li>
                    <li><a href="KeyKeeper?action=viewCart">View Cart</a></li>
                    <li><a href="plantlist1.jsp">Shopping</a></li>
                    <li><a href="KeyKeeper?action=logout">Logout</a></li>
                    <li>
                        <form action="KeyKeeper" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null)?"":request.getParameter("txtsearch")%>"/>
                             <select name="searchby">
                                 <option value="byname">By Name</option>
                                 <option value="bycate">By Category</option>
                             </select>
                            <input type="submit" value="<%=  MyConstant.FIND1 %>" name="action" />
                        </form>
                    </li>
                </ul>    
            </nav>
        </header>
                        <img width="100%" src="images/footer.jpg">
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
