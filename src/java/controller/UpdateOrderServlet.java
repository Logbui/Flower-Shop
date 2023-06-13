/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import myDAO.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class UpdateOrderServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //HttpSession session = request.getSession();
            //Account acc = (Account) session.getAttribute("loginUser");
            String orderStatusTxt = request.getParameter("status");
            String odrID = request.getParameter("orderID");
            if(orderStatusTxt != null && !orderStatusTxt.isEmpty()){
                int orderStatus = Integer.parseInt(orderStatusTxt);
                int orderId = Integer.parseInt(odrID);
                if(orderStatus == 2){
                    String action = request.getParameter("action");
                    if(action != null){
                        switch(action){
                            case "updateOrder1"://Finish order
                                boolean check = OrderDAO.updateOrderStatus(orderId, 1);
                                if(check){
                                request.setAttribute("msg_success", "Finish ordering");
                                request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                            }
                            else{
                                request.setAttribute("msg_error", "Finish order fail!");
                                request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                            }
                                break;
                            case "updateOrder2"://cancel order
                                boolean check1 = OrderDAO.finishOrder(orderId, 3);
                                if(check1){
                                    request.setAttribute("msg_success", "Cancel order successfully!");
                                    request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                                }
                                else{
                                request.setAttribute("msg_error", "Cancel order fail!");
                                request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                            }
                                break;
                        }
                    }
                }
                else if(orderStatus == 3){
                    boolean check = OrderDAO.updateOrderStatus(orderId, 2);
                    if(check){
                        
                        request.getRequestDispatcher("OrderAgainServlet").forward(request, response);
                    }
                    else{
                        request.setAttribute("msg_error", "Order again fail!");
                        request.getRequestDispatcher("personalPage.jsp").forward(request, response);
                    }
                }
            }
            else {
                    
            }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
