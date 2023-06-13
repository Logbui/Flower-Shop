<%-- 
    Document   : viewPlant
    Created on : Feb 22, 2023, 10:07:23 AM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/mycss.css" />
    </head>
    <body>
        <c:set var="plantObj" value="${requestScope.plantObj}"/>
        <table class="table-b1">
            <tr>
                <td class="side-left" rowspan="1">
                    <div class="card-img">
                        <img src="${plantObj.getImgpath()}"/>
                    </div>
                </td>
                <td class="side-right">
                    <ul>
                        <li>ID: <span>${plantObj.getId()}</span></li>
                        <li>Product Name: <span>${plantObj.getName()}</span></li>
                        <li>Price: <span>${plantObj.getPrice()}</span></li>
                        <li>Description: <span>${plantObj.getDescription()}</span></li>
                        <li>Status:
                            <span>
                                <c:choose>
                                    <c:when test="${plantObj.getStatus() eq 1}">Available</c:when>
                                    <c:otherwise>Out of stock</c:otherwise>
                                </c:choose>
                            </span>
                        </li>
                        <li>Cate ID: <span>${plantObj.getCateid()}</span></li>
                        <li>Category: <span>${plantObj.getCatename()}</span></li>
                    </ul>
                </td>
            </tr>
        </table>
    </body>
</html>
