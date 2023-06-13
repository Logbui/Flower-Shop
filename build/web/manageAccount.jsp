<%-- 
    Document   : manageAccount
    Created on : Feb 22, 2023, 5:06:26 PM
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
            <input type="text" name="txtSearch" placeholder="Please enter name" class="formsearch"
                   value="<%= (request.getParameter("txtSearch") == null)?"":request.getParameter("txtSearch")%>">
            <input type="submit" value="searchAccount" name="action"> 
        </form>
        <c:set var="list" value="${requestScope.accountList}"/>
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
                            <th>ID</th>
                            <th>Email</th>
                            <th>Full name</th>
                            <th>Status</th>
                            <th>Phone</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach var="acc" items="${list}">
                            <tr>
                                <td><c:out value="${acc.getAccID()}"></c:out></td>
                                <td><c:out value="${acc.getEmail()}"></c:out></td>
                                <td><c:out value="${acc.getFullname()}"></c:out></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${acc.getStatus() eq 1}">Active</c:when>
                                        <c:otherwise>Inactive</c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${acc.getPhone()}"></c:out></td>
                                <td>
                                <c:choose>
                                    <c:when test="${acc.getRole() eq 1}">Admin</c:when>
                                    <c:otherwise>User</c:otherwise>
                                </c:choose>
                                </td>
                                <td>
                                    <c:if test="${acc.getRole() eq 0}">
                                        <c:url var="mylink" value="KeyKeeper">
                                            <c:param name="email" value="${acc.getEmail()}"></c:param>
                                            <c:param name="status" value="${acc.getStatus()}"></c:param>
                                            <c:param name="action" value="updateStatusAccount"></c:param>  
                                        </c:url>
                                        <a href="${mylink}">Block/UnBlock</a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table> 
                </c:otherwise>
        </c:choose>         
    </body>
</html>
