<%-- 
    Document   : orderDetail
    Created on : Feb 15, 2023, 5:45:06 PM
    Author     : ADMIN
--%>

<%@page import="myDAO.OrderDetailDAO"%>
<%@page import="myDTO.Order"%>
<%@page import="myDTO.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="myDAO.OrderDAO"%>
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
        <%
            Account ac = (Account) session.getAttribute("loginUser");
            if(ac == null){
        %>
        <p><font color='red'> This order has no products! </font></p>
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
            String orderid = request.getParameter("orderid");
            if(orderid != null){
               int orderID = Integer.parseInt(orderid.trim());
               ArrayList<OrderDetail> li = OrderDetailDAO.getOrderDetail(orderID);
        %>
        <h1 style="color: olivedrab">Here are the product(s) of your order with ID <%= orderID %></h1>
        <%
                if(li != null && !li.isEmpty()){
                    int money = 0;
                    for (OrderDetail detail : li) { %>
                    <table class="order">
                            <tr>
                                <td>Order ID</td>
                                <td>Plant ID</td>
                                <td>Plant Name</td>
                                <td>Image</td>
                                <td>Price</td>
                                <td>Quantity</td>
                            </tr>
                            <tr>
                                <td><%= detail.getOrderId() %></td>
                                <td><%= detail.getPlantId() %></td>
                                <td><%= detail.getPlantName() %></td>
                                <td><img src="<%= detail.getImgPath() %>" class="plantimg"/></td>
                                <td>$<%= detail.getPrice() %></td>
                                <td><%= detail.getQuantity() %></td>
                                <% money = money + detail.getQuantity() * detail.getPrice(); %>
                            </tr>
                        </table>
            <%}%>
            <h3>Total money: <span style="color: red"> $<%= money %> </span></h3>
            <%  }
                else{
            %>
                        <p>You don't have any order</p>
            <%
                    }
                }
            %><% } %>
    </section>
    
    </body>
</html>
