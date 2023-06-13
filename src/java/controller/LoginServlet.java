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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import myDAO.AccountDAO;
import myDTO.Account;
import mylib.CookieUtils;

/**
 *
 * @author ADMIN
 */
public class LoginServlet extends HttpServlet {

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
            String email = request.getParameter("txtemail");
            String pwd = request.getParameter("txtpassword");
            String save = request.getParameter("savelogin");
            request.setAttribute("msg_error", "Invalid email or password! Please login again!");
            Account acc = null;
            try{
                if(email == null || email.equals("") || pwd == null || pwd.equals("")){
                    Cookie[] c = request.getCookies();
                    String token = "";
                    if(c != null){
                        for (Cookie cookie : c) {
                            if(cookie.getName().equals("selector")){
                                token = cookie.getValue();
                            }
                        }
                    }
                    if(!token.equals("")){
                        response.sendRedirect("personalPage.jsp");
                    }else request.getRequestDispatcher("login.jsp").forward(request, response);
                }
                else{
                    acc = AccountDAO.getAccount(email, pwd);
                    if(acc != null){
                        if(acc.getRole() == 1){
                            HttpSession s = request.getSession();
                            if(s != null){
                                s.setAttribute("name", acc.getFullname());
                                s.setAttribute("email", email);
                                if(save != null){
                                    String token = CookieUtils.generateNewToken();
                                    AccountDAO.updateToken(token, email);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 5);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("adminIndex.jsp");
                            }
                        }
                        else{
                            if(acc.getStatus() == 1){   
                                HttpSession s = request.getSession();
                                if(s != null){
                                    s.setAttribute("name", acc.getFullname());
                                    s.setAttribute("email", email);
                                    s.setAttribute("loginUser", acc);
                                if(save != null){
                                    String token = CookieUtils.generateNewToken();
                                    AccountDAO.updateToken(token, email);
                                    Cookie cookie = new Cookie("selector", token);
                                    cookie.setMaxAge(60 * 5);
                                    response.addCookie(cookie);
                                }
                                response.sendRedirect("personalPage.jsp");
                                }
                            }
                            else{
                                request.setAttribute("msg_error", "This account has been blocked!");
                                request.getRequestDispatcher("login.jsp").forward(request, response);
                            }
                        }
                    }
                    else
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            }catch(IOException e){
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
