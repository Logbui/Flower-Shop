<%-- 
    Document   : login
    Created on : Feb 7, 2023, 11:01:25 AM
    Author     : ADMIN
--%>

<%@page import="controller.MyConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <header>
            <%@include file="index.jsp" %>
        </header>
        <section>
            <h1>LOGIN</h1>
            <form action="KeyKeeper" method="post">
                <table>
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" name="txtemail" required=""
                                   value="<%= (request.getAttribute("txtemail") == null) ? "" : request.getAttribute("txtemail")%>" 
                                pattern="^(\\w)+@(a-zA-Z]+([.](\\w)+){1,2}"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="txtpassword" required=""
                                   value="<%= (request.getAttribute("txtpassword") == null) ? "" : request.getAttribute("txtpassword")%>"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="<%=  MyConstant.LOGIN %>" name="action"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="checkbox" value="savelogin" name="action">Remember me</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bold">Don't have account? <a href="register.jsp">Sign up</a> here</td>
                    </tr>
                </table>
            </form>
        </section>
        <%
            String msg = (String) request.getAttribute("msg_error");
            if(msg != null){
        %>
        <p style="color: red"><%= msg %></p>
        <%
            }
        %>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
