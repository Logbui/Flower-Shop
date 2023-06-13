/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
public class KeyKeeper extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("action");
            String url = "login.jsp";
            switch(action){
                case MyConstant.LOGIN:
                    url = "LoginServlet";
                    break;
                case MyConstant.FIND:
                    url = "FindServlet";
                    break;
                case MyConstant.FIND1:
                    url = "FindServlet";
                    break;
                case MyConstant.REGISTER:
                    url = "RegistrationServlet";
                    break;
                case MyConstant.LOGOUT:
                    url = "LogoutServlet";
                    break;
                case "viewOrderDetail":
                    url = "orderDetail.jsp";
                    break;
                case "viewCompletedOrders":
                    url="orderStatus.jsp";
                    break;
                case "viewCanceledOrders":
                    url="orderStatus.jsp";
                    break;
                case "viewProcessingOrders":
                    url="orderStatus.jsp";
                    break;
                case "addToCart":
                    url = "AddToCartServlet";
                    break;
                case "addToCart1":
                    url = "AddToCartServlet_Guest";
                    break;
                case "viewCart":
                    url = "viewCart.jsp";
                    break;
                case "viewCart1":
                    url = "viewCart1.jsp";
                    break;
                case "updateCart":
                    url = "UpdateCartServlet";
                    break;
                case "UpdateCart":
                    url = "UpdateCartServlet";
                    break;
                case "saveOrder":
                    url = "SaveShoppingCartServlet";
                    break;
                case "savelogin":
                    url = "LoginServlet";
                    break;
                case "manageAccounts":
                    url = "ManageAccountsServlet";
                    break;
                case "updateStatusAccount":
                    url = "UpdateStatusAccountServlet";
                    break;
                case "manageOrders":
                    url = "ManageOrdersServlet";
                    break;
                case "updateOrder":
                    url = "UpdateOrderServlet";
                    break;
                case "updateProfile":
                    url = "updateUserInfo.jsp";
                    break;
                case "updateInfo":
                    url = "UpdateUserServlet";
                    break;
                case "searchOrder":
                    url = "SearchOrderServlet";
                    break;
                case "updateOrder1":
                    url = "UpdateOrderServlet";
                    break;
                case "updateOrder2":
                    url = "UpdateOrderServlet";
                    break;
                case "delete":
                    url = "UpdateCartServlet";
                    break;
                case "Delete":
                    url = "UpdateCartServlet";
                    break;
                case "viewPlant":
                    url = "GetPlantServlet";
                    break;
                case "save":
                    url = "personalPage.jsp";
                    break;
                case "searchAccount":
                    url = "SearchAccountServlet";
                    break;
                case "managePlant":
                    url = "ManagePlantServlet";
                    break;
                case "updatePlantStatus":
                    url = "UpdatePlantStatusServlet";
                    break;
                case "updatePlant":
                    url = "GetPlantServlet";
                    break;
                case "AddNewPlant":
                    url = "updatePlant.jsp";
                    break;
                case "saveNewInfo":
                    url = "UpdatePlantServlet";
                    break;
                case "createPlant":
                    url = "UpdatePlantServlet";
                    break;
                case "Search":
                    url = "FindServlet";
                    break;
                case "manageOrder":
                    url = "ManageOrderServlet";
                    break;
                case "search":
                    url = "SearchOrderServlet";
                    break;
                case "searching":
                    url = "SearchOrderServlet";
                    break;
                case "UpdatePlant":
                    url = "updatePlant.jsp";
                    break;
                case "manageCategory":
                    url = "ManageCategoryServlet";
                    break;
                case "updateCategory":
                    url = "UpdateCategoryServlet";
                    break;
                case "Add New Category":
                    url = "createCategory.jsp";
                    break;
                case "Create category":
                    url = "UpdateCategoryServlet";
                    break;
            }
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
