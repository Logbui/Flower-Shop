<%-- 
    Document   : viewCart
    Created on : Feb 20, 2023, 10:26:11 PM
    Author     : ADMIN
--%>

<%@page import="myDAO.OrderDetailDAO"%>
<%@page import="myDTO.OrderDetail"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
                    <li><a href="home.jsp">Home</a></li>
                    <i class="fa fa-arrow"></i>
                    <li><a href="plantlist.jsp">Shopping</a></li>
                    <i class="fa fa-arrow"></i>
                </ul>
            </nav>
        </header>
        <section>
            <%
                String name = (String) session.getAttribute("name");
                if(name != null){
            %>
            <p style="font-size: 20px">Welcome <span style="font-weight: 600; color: olivedrab"><%= name %></span> !</p> 
            <%
                }
            %>
            <br/>
            <p>
                <font style="color: red"><%= (request.getAttribute("WARNING") == null)?"": (String) request.getAttribute("WARNING") %></font>
                <font style="color: red"><%= (request.getAttribute("msg_success") == null)?"": (String) request.getAttribute("msg_success") %></font>
            </p>
            <h1>Here is the information of your cart:</h1>
            <table width="100%" class="shopping">
                <tr>
                    <th>PRODUCT ID</th>
                    <th>IMAGE</th>
                    <th>PRICE</th>
                    <th>QUANTITY</th>
                    <th>ACTION</th>
                </tr>
            <%
                HashMap<String,Integer> cart = (HashMap)session.getAttribute("cart");
                int total = 0;
                if(cart != null){
                    for (String pid : cart.keySet()){
                        OrderDetail o = OrderDetailDAO.getOrderDetails(Integer.parseInt(pid));
                        int quantity = cart.get(pid);
            %>
            <form action="KeyKeeper" method="post">
                <tr>
                    <td><input type="hidden" value="<%= pid %>" name="pid"><a href="GetPlantServlet?action=viewPlant&pid=<%= pid %>"><%= pid %></a></td>
                    <td><img src="<%= o.getImgPath() %>" class="plantimg"</td>
                    <td>
                        <input type="hidden" value="<%= o.getPrice() %>" name="price"><%= o.getPrice() %>
                    </td>
                    <td>
                        <input type="number" value="<%= quantity %>" name="quantity" min="1"> <!-- cách này là bắt nhập quantity >= 1 -->
                        
                    </td> 
                    <td>
                        <input type="submit" value="UpdateCart" name="action">
                        <input type="submit" value="Delete" name="action">
                    </td>
                    <% total = total + quantity * o.getPrice(); %>
                </tr>
            </form>
            <%
                    }
                }
            else{
            %>
            <p style="color: red">Your cart is empty!</p>
            <%
            }
            %>
            </table>
            <p><span style="font-weight: 700">Total money: </span> <span style="color: red; font-weight: 600">$<%= total %> </span></p>
                <p><span style="font-weight: 700">Order date: </span> <%= (new Date()).toString()  %></p>
                <p><span style="font-weight: 700">Ship date: </span> N/A</p>
        </section>
            <section><form action="KeyKeeper" method="post">
                    <input type="submit" value="saveOrder" name="action" class="submitorder">
            </form></section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
