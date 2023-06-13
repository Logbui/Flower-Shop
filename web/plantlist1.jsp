<%-- 
    Document   : searchplant
    Created on : Feb 8, 2023, 10:05:31 AM
    Author     : ADMIN
--%>

<%@page import="controller.MyConstant"%>
<%@page import="myDAO.PlantDAO"%>
<%@page import="myDTO.Plant"%>
<%@page import="java.util.ArrayList"%>
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
                    <li><a href="checkout.jsp">Home</a></li>
                    <i class="fa fa-arrow"></i>
                    <li><a href="personalPage.jsp">Personal page</a></li>
                    <i class="fa fa-arrow"></i>
                    <li><a href="viewCart.jsp">View Cart</a></li>
                    <i class="fa fa-arrow"></i>
                    <li>
                        <form action="KeyKeeper" method="post" class="formsearch">
                            <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null)?"":request.getParameter("txtsearch")%>"/>
                             <select name="searchby">
                                 <option value="byname">By Name</option>
                                 <option value="bycate">By Category</option>
                             </select>
                            <input type="submit" value="<%=  MyConstant.FIND %>" name="action" />
                        </form>
                    </li>
                </ul>
            </nav>
        </header>
        <section>
        <%
            //String tmp[] = {"out of stock", "available"};
            String msg2 = (String) session.getAttribute("msg_success");
            if(msg2 != null){
            %>
                <p style="color: red"><%= msg2 %></p>
            <%
            }
            ArrayList<Plant> list = PlantDAO.getAllPlants();
            if(list != null && list.size() > 0){
        %>
        <table class="order">
            <tr>
                <th>ID</th>
                <th>NAME</th>
                <th>PRICE</th>
                <th>IMAGE</th>
                <th>STATUS</th>
                <th>DETAIL</th>
                <th>ACTION</th>
            </tr>
            <%
                for (Plant pla : list) {
            %>
            <tr>
                <td ><%= pla.getId() %></td>
                <td><%= pla.getName() %></td>
                <td>$<%= pla.getPrice() %></td>
                <td><img src="<%= pla.getImgpath()%>" class="plantimg"/></td>
                <td>
                    <%
                    if(pla.getStatus() == 0){
                    %>
                    <p style="color: red;">Out of stock</p>
                    <%
                    }else{
                    %>
                    <p style="color: red;">Available</p>
                    <%
                    }
                    %>
                </td>
                <td><a href="KeyKeeper?action=viewPlant&pid=<%= pla.getId() %>">View Detail</a></td>
                <td>
                    <%
                    if(pla.getStatus() == 1){
                    %>
                    <a href="KeyKeeper?action=addToCart&pid=<%= pla.getId() %>&status=<%= pla.getStatus() %>">Add to your cart</a>
                    <%
                    }
                    %>
                </td>
            </tr>
            <%
                    }
            %>
        </table>
        <%
            }
            else
            {
                String msg = (String) request.getAttribute("msg_error");
                if(msg != null){
            %>
            <p style="color: red"><%= msg %></p>
                <%
            }
            }
        %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>
