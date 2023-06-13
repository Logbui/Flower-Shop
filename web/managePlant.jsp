<%-- 
    Document   : managePlant
    Created on : Mar 1, 2023, 11:13:59 AM
    Author     : ADMIN
--%>
<%@page import="controller.MyConstant"%>
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
            <form action="KeyKeeper" method="post">
                <input type="text" name="txtsearch" value="<%= (request.getParameter("txtsearch") == null)?"":request.getParameter("txtsearch")%>">
                <select name="searchby">
                    <option value="byname">By Name</option>
                    <option value="bycate">By Category</option>
                </select>
                <input type="submit" value="Search" name="action" />
            </form>
        </div>
            <c:set var="list" value="${requestScope.plantList}"/>
            
            <div>
                <c:choose>
                    <c:when test="${list == null}">
                        <c:set var="msg" value="${requestScope.msg_error}"/>
                            <c:if test="${msg != null}">
                                <p style="color: red">${msg}</p>
                            </c:if>
                    </c:when>
                    <c:otherwise>
                        <c:set var="msg" value="${requestScope.msg_success}"/>
                            <c:if test="${msg != null}">
                                <p style="color: red">${msg}</p>
                            </c:if>
                        <table class="order">
                            <tr>
                                <th>ID</th>
                                <th>Plant Name</th>
                                <th>Image</th>
                                <th>Price</th>
                                <th>Description</th>
                                <th>Status</th>
                                <th>Category ID</th>
                                <th>Category Name</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="plant" items="${list}">
                                <tr>
                                    <td>
                                        <c:out value="${plant.getId()}"></c:out>
                                    </td>
                                    <td><c:out value="${plant.getName()}"></c:out></td>
                                    <td><img src="${plant.getImgpath()}" class="plantimg"></td>
                                    <td><c:out value="${plant.getPrice()}"></c:out></td>
                                    <td><c:out value="${plant.getDescription()}"></c:out></td>
                                    <td>
                                    <c:choose>
                                        <c:when test="${plant.getStatus() eq 1}">Available</c:when>
                                        <c:otherwise>Out of stock</c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td><c:out value="${plant.getCateid()}"></c:out>
                                    </td>
                                    <td><c:out value="${plant.getCatename()}"></c:out></td>
                                    <td>
                                        <a href="KeyKeeper?action=updatePlant&pid=${plant.getId()}">Update</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </table>
                    </c:otherwise>
                </c:choose>
            </div>
            <div>
                <form action="KeyKeeper" method="post">
                    <input type="submit" value="AddNewPlant" name="action"/>
                </form>
            </div>
    </body>
</html>
