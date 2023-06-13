<%-- 
    Document   : manageCategory
    Created on : Mar 4, 2023, 10:43:35 AM
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
        <div>
        <c:set var="list" value="${sessionScope.listCategories}"/>
            <div>
                <c:choose>
                    <c:when test="${list != null}">
                        <c:set var="msg" value="${requestScope.msg_success}"/>
                            <c:if test="${msg != null}">
                                <p style="color: red">${msg}</p>
                            </c:if>
                        <table class="order">
                            <tr>
                                <th>Category ID</th>
                                <th>Category Name</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach var="cate" items="${list}">
                                <form action="KeyKeeper" method="post">
                                    <tr>
                                        <td><input type="hidden" value="${cate.key}" name="cateid"/>${cate.key}</td>
                                        <td><input type="text" value="${cate.value}" name="catename"/></td>
                                        <td><input type="submit" name="action" value="updateCategory"/></td>
                                    </tr>
                                </form>
                            </c:forEach>
                        </table>
                    </c:when>
                </c:choose>
            </div>
        </div>
            <form action="KeyKeeper" method="post">
                <input type="submit" value="Add New Category" name="action"/>
            </form>
    </body>
</html>
