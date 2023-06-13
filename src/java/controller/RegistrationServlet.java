/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myDAO.AccountDAO;
import myDTO.Account;

/**
 *
 * @author ADMIN
 */
public class RegistrationServlet extends HttpServlet {

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
        try{
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("loginUser");
            if(acc == null){
                response.sendRedirect("register.jsp");
            }
            else{
                if(acc.getRole() == 0){
                    response.sendRedirect("personalPage.jsp");
                }
                else response.sendRedirect("adminIndex.jsp");
            } 
        }catch(IOException e){
            
        }
        //processRequest(request, response);
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
        try{
            String email = request.getParameter("txtemail");
            String name = request.getParameter("txtfullname");
            String pwd = request.getParameter("txtpassword");
            String phone = request.getParameter("txtphone");
            Account acc = AccountDAO.getAccountInfoByEmail(email);
            if(acc != null){
                request.setAttribute("msg_error", "The account already exists in the system! Please use this email to login.");
                request.setAttribute("email", email);
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
            else{
                if(phone.matches(".*[a-zA-Z].*")){
                    request.setAttribute("txtemail", email);
                    request.setAttribute("txtfullname", name);
                    request.setAttribute("txtphone", phone);
                    request.setAttribute("msg_error", "The phone is invalid");
                    request.getRequestDispatcher("register.jsp").forward(request, response);
                }
                else{
                    int status = 1;
                    int role = 0;
                    boolean check = AccountDAO.insertAccount(email, pwd, name, phone, status, role);
                    if(check){
                        request.setAttribute("MSG_SUCCESS", "You have successfully registered an account!");
                        request.getRequestDispatcher("register.jsp").forward(request, response);
                    }
                }
                
            }
        }catch(IOException | SQLException | ServletException e){
            
        }
        
        
        //processRequest(request, response);
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
