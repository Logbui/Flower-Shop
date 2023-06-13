/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myDAO.OrderDAO;

/**
 *
 * @author ADMIN
 */
public class SaveShoppingCartServlet extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession();
            if(session != null){
                String name = (String) session.getAttribute("name");
                String email = (String) session.getAttribute("email");
                HashMap<String,Integer> cart  = (HashMap<String,Integer>) session.getAttribute("cart");
                if(cart != null && !cart.isEmpty()){
                    if(name == null || name.equals("")){//not login
                        request.setAttribute("WARNING", "You must log in to finish shopping");
                        request.getRequestDispatcher("viewCart1.jsp").forward(request, response);
                    }
                    else{
                        boolean result = OrderDAO.insertOrder(email, cart);
                        if(result){//đã login
                            session.setAttribute("cart", null);
                            request.setAttribute("WARNING", "Save your cart successfully!");
                            request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                        }
                    }
                }
                else{
                    if(name == null){
                        request.setAttribute("WARNING", "Your order must have at least one item!");
                        request.getRequestDispatcher("viewCart1.jsp").forward(request, response);
                    }
                    else{
                        request.setAttribute("WARNING", "Your order must have at least one item!");
                        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                    }
                    
                }
            }
            else {
                response.sendRedirect("index.jsp");
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
            Logger.getLogger(SaveShoppingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SaveShoppingCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
