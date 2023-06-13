<%-- 
    Document   : personalPage
    Created on : Feb 13, 2023, 10:18:30 PM
    Author     : ADMIN
--%>

<%@page import="myDAO.AccountDAO"%>
<%@page import="myDTO.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="myDAO.OrderDAO"%>
<%@page import="myDTO.Order"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            Cookie[] c = request.getCookies();
            boolean login = false;
            if(name == null){
                String token = "";
                for (Cookie cookie : c)
                    if(cookie.getName().equals("selector")){
                        token = cookie.getValue();
                        Account acc = AccountDAO.getAccountByToken(token);
                        if(acc != null){
                            name = acc.getFullname();
                            email = acc.getEmail();
                            login = true;    
                        }
                    }
            }
            else login = true;
            //Account ac = (Account) session.getAttribute("loginUser");
            if(!login){
        %>
        <p><font color='red'> You must login to view personal </font></p>
        <%}else{
        %>
    <header>
        <%@include file="header_loginedUser.jsp" %>
    </header>
    <section>
        <h3>Welcome <%= name %> come back </h3>
        <h3><a href="KeyKeeper?action=logout">Logout</a></h3>
        <a href="personalPage.jsp">View all orders</a>
    </section>
    <section>
        <h1>Buying history</h1>
        <%
            ArrayList<Order> list1 = (ArrayList) request.getAttribute("orderList");
            String[] status = {"", "completed", "processing", "canceled"};
            if(list1 != null && list1.size() > 0){
                for (Order o : list1) { %>
                <table class="order">
                    <tr>
                        <td>Order ID</td>
                        <td>Order Date</td>
                        <td>Ship Date</td>
                        <td>Order's status</td>
                        <td>Action</td>
                        <td>View</td>
                    </tr>
                    <tr>
                        <td><%= o.getOrderID() %></td>
                        <td><%= o.getOrderDate() %></td>
                        <td><%= o.getShipDate() %></td>
                        <td><%= status[o.getStatus()] %></td>
                        <td>
                            <% if(o.getStatus() == 2){
                            %>
                            <a href="KeyKeeper?action=updateOrder1&status=<%= o.getStatus() %>&orderID=<%= o.getOrderID() %>">Finish order</a> /
                            <a href="KeyKeeper?action=updateOrder2&status=<%= o.getStatus() %>&orderID=<%= o.getOrderID() %>">Cancel order</a>
                            <%
                                }else if(o.getStatus() == 3){
                            %>
                            <a href="KeyKeeper?action=updateOrder&status=<%= o.getStatus() %>&orderID=<%= o.getOrderID() %>">Order again</a>
                            <%
                                }
                                else{
                            %>
                            <p>None</p>
                            <%
                            }
                            %>
                        </td>
                        <td>
                            <a href="KeyKeeper?action=viewOrderDetail&orderid=<%= o.getOrderID() %>">Detail</a>
                        </td>
                    </tr>
                </table>
        <%      }
            }else{
            String msg = (String) request.getAttribute("msg_error");
            if(msg != null){
        %>
            <p style="color: red"><%= msg %></p>
        <%
            }
        %>
    </section>
        <%
            }
        }
        %>
    <footer>
        <%@include file="footer.jsp" %>
    </footer>    
    </body>
</html>
