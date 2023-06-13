<%-- 
    Document   : updatePlant
    Created on : Mar 1, 2023, 5:26:43 PM
    Author     : ADMIN
--%>
<%@page import="myDTO.Plant"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"/>
        <div>
            <% 
               String action = request.getParameter("action");
               if(action != null){
                   switch(action){
                       case "UpdatePlant":
            %>
            <h1>Update plant information</h1>
            <c:set var="PlantObj" value="${requestScope.PlantObj}"/>
            <form action="KeyKeeper" method="get">
                <table class="order">
                    <tr>
                        <th>ID</th>
                        <th>Plant Name</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Category Name</th>
                    </tr>
                    <tr>    
                        <td><input type="hidden" value="${PlantObj.getId()}" name="pid"/>${PlantObj.getId()}</td>
                        <td><input type="text" name="name" required=""
                                   value="${PlantObj.getName()}"/>
                        </td>
                        <td><input type="text" name="imgPath" required=""
                                   value="${PlantObj.getImgpath()}"/>
                        </td>
                        <td><input type="number" name="price" required="" value="${PlantObj.getPrice()}" min="0" max="999" pattern="^[1-9]\d*$"/></td>
                        <td><input type="text" name="description" required=""
                                   value="${PlantObj.getDescription()}"/>
                        </td>
                        <td>
                            <select name="status" required="">
                                <option ${PlantObj.status eq 1 ? "selected" : ""} value="1">Available</option>
                                <option ${PlantObj.status eq 0 ? "selected" : ""} value="0">Out of stock</option>
                            </select>
                        </td>
                        <td>
                            <select name="cateId" required="">
                                <c:forEach items="${sessionScope.listCategories}" var="list">
                                    <option value="${list.key}">${list.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <input type="submit" value="saveNewInfo" name="action" class="submitorder">
                    </tr>
                </table>
            </form>
            <%
                    break;
                    case "AddNewPlant":
            %>
            <h1>New plant information</h1>
            <form action="KeyKeeper" method="post">
                <table class="order">
                    <tr>
                        <th>Plant Name</th>
                        <th>Image</th>
                        <th>Price</th>
                        <th>Description</th>
                        <th>Status</th>
                        <th>Category ID</th>
                    </tr>
                    <tr>
                        <td><input type="text" name="name" required="" 
                           value="<%= (request.getAttribute("name") == null) ? "" : request.getAttribute("name")%>"/>
                        </td>
                        <td><input type="text" name="imgPath" required="" 
                                   value="<%= (request.getAttribute("imgPath") == null) ? "" : request.getAttribute("imgPath")%>"/>
                        </td>
                        <td><input type="number" name="price" required="" min="0" max="999" pattern="^[1-9]\d*$"/>
                        </td>
                        <td><input type="text" name="description" required="" 
                                   value="<%= (request.getAttribute("description") == null) ? "" : request.getAttribute("description")%>"/>
                        </td>
                        <td>
                            <select name="status" required="">
                                <option ${PlantObj.status eq 1 ? "selected" : ""} value="1">Available</option>
                                <option ${PlantObj.status eq 0 ? "selected" : ""} value="0">Out of stock</option>
                            </select>
                        </td>
                        <td>
                            <select name="cateId" required="">
                                <c:forEach items="${sessionScope.listCategories}" var="list">
                                    <option value="${list.key}">${list.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr><input type="submit" value="createPlant" name="action"/></tr>
                </table>
            </form>
            <%
                break;
                   }
               }
            %>
        </div>
    </body>
</html>
