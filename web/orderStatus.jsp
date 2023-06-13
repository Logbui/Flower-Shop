<%-- 
    Document   : orderStatus
    Created on : Feb 19, 2023, 10:27:47 PM
    Author     : ADMIN
--%>

<%@page import="myDAO.OrderDAO"%>
<%@page import="myDTO.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="myDTO.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <%
            Account ac = (Account) session.getAttribute("loginUser");
            if(ac == null){
        %>
        <p><font color='red'> You must login to view personal </font></p>
        <%}else{
        %>
        <header>
            <nav>
                <ul>
                    <li><a href="index.jsp">Home</a></li>
                    <i class="fa fa-arrow"></i>
                    <li><a href="personalPage.jsp">View all orders</a></li>
                    <i class="fa fa-arrow"></i>
                </ul>
            </nav>
        </header>
        <section>
            <%
                String orderstatus = request.getParameter("Orderstatus");
                String[] status = {"", "completed", "processing", "canceled"};
                if(orderstatus != null){
                    int orderStatus = Integer.parseInt(orderstatus.trim());
                    ArrayList<Order> list = OrderDAO.getOrdersByStatus(orderStatus, ac.getAccID());
                    if(list != null && list.size() > 0){
            %>
            <h2>Here are all the <span style="color: red; font-weight: 700"><%= status[orderStatus] %></span>  orders: </h2>
                <table class="order">
                    <tr>
                        <td>Order ID</td>
                        <td>Create Date</td>
                        <td>Order Detail</td>
                    </tr>
            <%
                for (Order o : list) {
                    if(o.getStatus() == orderStatus){
            %>
                <tr>
                    <td><%= o.getOrderID() %></td>
                    <td><%= o.getOrderDate().toString() %></td>
                    <td>
                        <a href="KeyKeeper?action=viewOrderDetail&orderid=<%= o.getOrderID() %>">detail</a>
                    </td>
                </tr>
            <%
                    }
                }
            }
            else{
            %>
            <p style="color: red; align-content: flex-start">You don't have any <%= status[orderStatus] %> orders!</p>
            <%
            }
        }
    }
            %>
            </table>
        </section>
    </body>
</html>
