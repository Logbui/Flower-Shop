<%-- 
    Document   : orderAgain
    Created on : Feb 27, 2023, 4:19:03 PM
    Author     : ADMIN
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css" />
    </head>
    <body>
        <section>
            <h3>List Products</h3>
            <table class="shopping">
                    <tr>
                        <th scope="col">PRODUCT ID</th>
                        <th scope="col">IMAGE</th>
                        <th scope="col">NAME</th>
                        <th scope="col">PRICE</th>
                        <th scope="col">QUANTITY</th>
                        <th scope="col">TOTAL PRICE</th>
                    </tr>
                    <c:set var="total" scope="request" value="${0}"/>
                    <c:forEach items="${subCarts}" var="C">
                        <tr>
                            <td>
                                <c:url var="linkPlant" value="GetPlantServlet">
                                    <c:param name="pid" value="${C.value.plant.id}"></c:param>
                                </c:url>
                                <a href="${linkPlant}">${C.value.plant.id}</a>
                                <input type="hidden" name="productId" value="${C.value.plant.id}">
                            </td>
                            <td>
                                <a href="${linkPlant}">
                                    <img src="${C.value.plant.imgpath}" width="100%">
                                </a>
                            </td>
                            <td>${C.value.plant.name}</td>
                            <td>$${C.value.plant.price}</td>
                            <td>${C.value.quantity}</td>
                            <td>$${C.value.plant.price * C.value.quantity}</td>
                            <c:set var="total" scope="request" value="${total + C.value.plant.price * C.value.quantity}"/>
                        </tr>
                    </c:forEach>

            </table>
                    <form action="KeyKeeper" method="post">
                        <input type="submit" name="action" value="save"/>
                    </form>
                    <h3>Total amount: $${not empty totalMoney ? totalMoney : total}</h3>
                    
        </section>
    </body>
</html>
