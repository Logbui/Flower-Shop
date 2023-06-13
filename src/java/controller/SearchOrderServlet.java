/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myDAO.OrderDAO;
import myDTO.Account;
import myDTO.Order;

/**
 *
 * @author ADMIN
 */
public class SearchOrderServlet extends HttpServlet {

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
            HttpSession session = request.getSession(true);
            Account acc = (Account) session.getAttribute("loginUser");
            String from = request.getParameter("from");
            String to = request.getParameter("to");
            String action = request.getParameter("action");
            if(action != null){
                switch(action){
                    case "searchOrder"://này của user
                        ArrayList<Order> orderList = OrderDAO.getOrdersByPeriodTime(acc.getAccID(), from, to);
                        if(orderList != null && orderList.size() > 0){
                            request.setAttribute("orderList", orderList);
                            request.setAttribute("from", from);
                            request.setAttribute("to", to);
                            request.getRequestDispatcher("searchOrderByDate.jsp").forward(request, response);
                        }
                        else{
                            request.setAttribute("from", from);
                            request.setAttribute("to", to);
                            request.setAttribute("msg_error", "You don't have any order from " + from + " to " + to);
                            request.getRequestDispatcher("searchOrderByDate.jsp").forward(request, response);
                        }
                        break;
                    case "search"://này của admin
                        ArrayList<Order> orderList1 = OrderDAO.getOrdersByPeriodTime(from, to);
                        if(orderList1 != null && orderList1.size() > 0){
                            request.setAttribute("orderList1", orderList1);
                            request.setAttribute("from", from);
                            request.setAttribute("to", to);
                            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
                        }
                        else{
                            request.setAttribute("from", from);
                            request.setAttribute("to", to);
                            request.setAttribute("msg_error", "You don't have any order from " + from + " to " + to);
                            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
                        }
                        break;
                    case "searching":
                        int accID = Integer.parseInt(request.getParameter("accID"));
                        ArrayList<Order> orderList2 = OrderDAO.getOrdersByAccID(accID);
                        if(orderList2 != null && orderList2.size() > 0){
                            request.setAttribute("orderList1", orderList2);
                            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
                        }
                        else{
                            request.setAttribute("msg_error", "This account ID don't have any orders");
                            request.getRequestDispatcher("manageOrder.jsp").forward(request, response);
                        }
                }
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
            Logger.getLogger(SearchOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SearchOrderServlet.class.getName()).log(Level.SEVERE, null, ex);
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
