<%-- 
    Document   : updateUserInfo
    Created on : Feb 20, 2023, 5:36:27 PM
    Author     : ADMIN
--%>

<%@page import="myDTO.Account"%>
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
                    <li><a href="personalPage.jsp">Personal page</a></li>
                    <i class="fa fa-arrow"></i>
                    <li><a href="#">Change profile</a></li>
                    <i class="fa fa-arrow"></i>
                </ul>
            </nav>
        </header>
        <%
            Account acc = (Account) session.getAttribute("loginUser");
            if(acc != null){
        %>
        <section>
            <h1>Welcome, <%= acc.getFullname() %>!</h1>
            <h3>Your information</h3>
            <div>
                Full name: <%= acc.getFullname() %>
            </div>
            <div>
                Phone: <%= acc.getPhone() %>
            </div>
            <div>
                <h3>Update information</h3>
                <form method="post" action="KeyKeeper">
                    <table>
                        <tr>
                            <td>Full name: </td>
                            <td><input type="text" name="txtfullname" required=""
                                value="<%= (request.getAttribute("txtfullname") == null) ? "" : request.getAttribute("txtfullname")%>"></td>
                        </tr>
                        <tr>
                        <td>Phone: </td>
                            <td><input type="text" name="txtphone" required=""
                                value="<%= (request.getAttribute("txtphone") == null) ? "" : request.getAttribute("txtphone")%>"/>
                            </td>
                        </tr>
                        <tr>
                        <%
                            String msg = (String) request.getAttribute("msg_error");
                            String msg1 = (String) request.getAttribute("msg_success");
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
                            <td><input type="submit" value="updateInfo" name="action"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </section>
        <%    
            }
        %>
    </body>
</html>
