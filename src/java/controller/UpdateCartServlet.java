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

/**
 *
 * @author ADMIN
 */
public class UpdateCartServlet extends HttpServlet {

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
            String pid = request.getParameter("pid");
            String newQuantity = request.getParameter("quantity");
            HttpSession session = request.getSession();
            if(session != null){
                HashMap<String,Integer> cart = (HashMap<String,Integer>) session.getAttribute("cart");
                if(cart != null){
                    boolean found = cart.containsKey(pid);
                    if(found){
                        String action = request.getParameter("action");
                        if(action != null){
                            switch(action){
                                case "updateCart"://cách này là cách cho quantity xuống 0 thì xóa lun trong giỏ hàng
                                    if(newQuantity.equalsIgnoreCase("0")){
                                        cart.remove(pid);
                                        session.setAttribute("cart", cart);
                                        request.setAttribute("msg_success", "Update cart successfully!");
                                        request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                                    }
                                    else{
                                    System.out.println(newQuantity);
                                    cart.put(pid, Integer.parseInt(newQuantity));
                                    session.setAttribute("cart", cart);
                                    request.setAttribute("msg_success", "Update cart successfully!");
                                    request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                                    }
                                    break;
                                case "delete":
                                    cart.remove(pid);
                                    session.setAttribute("cart", cart);
                                    request.setAttribute("msg_success", "Delete successfully!");
                                    request.getRequestDispatcher("viewCart.jsp").forward(request, response);
                                    break;
                                case "UpdateCart":
                                    if(newQuantity.equalsIgnoreCase("0")){
                                        cart.remove(pid);
                                        session.setAttribute("cart", cart);
                                        request.setAttribute("msg_success", "Update cart successfully!");
                                        request.getRequestDispatcher("viewCart1.jsp").forward(request, response);
                                    }
                                    else{
                                    System.out.println(newQuantity);
                                    cart.put(pid, Integer.parseInt(newQuantity));
                                    session.setAttribute("cart", cart);
                                    request.setAttribute("msg_success", "Update cart successfully!");
                                    request.getRequestDispatcher("viewCart1.jsp").forward(request, response);
                                    }
                                    break;
                                case "Delete":
                                    cart.remove(pid);
                                    session.setAttribute("cart", cart);
                                    request.setAttribute("msg_success", "Delete successfully!");
                                    request.getRequestDispatcher("viewCart1.jsp").forward(request, response);
                                    break;
                            }
                        }
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
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateCartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
