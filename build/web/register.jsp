<%-- 
    Document   : register
    Created on : Feb 7, 2023, 4:45:53 PM
    Author     : ADMIN
--%>

<%@page import="controller.MyConstant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <header>
            <%@include file="index.jsp" %>
        </header>
        <section>
            <h1>REGISTRATION</h1> 
            <form action="KeyKeeper" method="post">
                <table>
                    <tr>
                        <td>Email: </td>
                        <td><input type="text" name="txtemail" required="" 
                                   value="<%= (request.getAttribute("txtemail") == null) ? "" : request.getAttribute("txtemail")%>" 
                                   pattern="^(\\w)+@(a-zA-Z]+([.](\\w)+){1,2}"></td>
                    </tr>
                    <tr>
                        <td>Full name: </td>
                        <td><input type="text" name="txtfullname" required=""
                                   value="<%= (request.getAttribute("txtfullname") == null) ? "" : request.getAttribute("txtfullname")%>"></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="txtpassword" required="" minlength="1" maxlength="16"></td>
                    </tr>
                    <tr>
                        <td>Phone: </td>
                        <td><input type="text" name="txtphone"
                                   value="<%= (request.getAttribute("txtphone") == null) ? "" : request.getAttribute("txtphone")%>"/>
                        </td>
                    </tr>
                    <tr>
                        <td><span style="font-weight: bold">Already have an account? Click <a href="login.jsp"> here </a> to log in!</span></td>
                    </tr>
                    <tr>
                    <%
                        String msg = (String) request.getAttribute("msg_error");
                        String msg1 = (String) request.getAttribute("MSG_SUCCESS");
                        if(msg != null){
                    %>
                        <p style="color: red"><%= msg %></p>
                    <%
                        }
                        if(msg1 != null){
                    %>
                    <p style="color: red"><%= msg1 %></p>
                    <%
                    }
                    %>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value= "<%=  MyConstant.REGISTER %>" name="action"></td>
                    </tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        
    </body>
</html>
