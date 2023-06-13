<%-- 
    Document   : manageOrder
    Created on : Mar 2, 2023, 9:46:19 PM
    Author     : ADMIN
--%>
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
        
        <form action="KeyKeeper" method="post">
            From <input type="date" name="from" value="<%= (request.getParameter("from") == null)?"":request.getParameter("from")%>"/> 
            To <input type="date" name="to" value="<%= (request.getParameter("to") == null)?"":request.getParameter("to")%>"/>
            <input type="submit" value="search" name="action"/>
        </form>
            
            <form action="KeyKeeper" method="post">
                <input type="number" placeholder="Please enter account ID" 
                       name="accID" value="<%= (request.getParameter("accID") == null)?"":request.getParameter("accID")%>"/>
                <input type="submit" value="searching" name="action"/>
            </form>
        
            <div>
            <c:set var="list" value="${requestScope.orderList1}"/>
            <div>
                <c:choose>
                    <c:when test="${list == null}">
                        <c:set var="msg" value="${requestScope.msg_error}"/>
                        <c:if test="${msg != null}">
                            <p style="color: red">${msg}</p>
                        </c:if>
                    </c:when>
                        <c:otherwise>
                            <table class="order">
                                <tr>
                                    <th>Order ID</th>
                                    <th>Order Date</th>
                                    <th>Ship Date</th>
                                    <th>Status</th>
                                    <th>Account ID</th>
                                </tr>
                                <c:forEach var="ord" items="${list}">
                                    <tr>
                                        <td><c:out value="${ord.getOrderID()}"></c:out></td>
                                        <td><c:out value="${ord.getOrderDate()}"></c:out></td>
                                        <td>
                                        <c:choose>
                                            <c:when test="${ord.getShipDate() != null}">
                                                <c:out value="${ord.getShipDate()}"></c:out>
                                            </c:when>
                                        <c:otherwise>null</c:otherwise>
                                        </c:choose>
                                        </td>
                                        <td>
                                        <c:choose>
                                            <c:when test="${ord.getStatus() eq 1}">Completed</c:when>
                                            <c:when test="${ord.getStatus() eq 2}">Processing</c:when>
                                            <c:otherwise>Canceled</c:otherwise>
                                        </c:choose>
                                        </td>
                                        <td><c:out value="${ord.getAccID()}"></c:out></td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </c:otherwise>
                </c:choose>
            </div>
        </div>

    </body>
</html>
