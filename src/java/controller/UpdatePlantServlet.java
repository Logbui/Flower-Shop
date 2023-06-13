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
import myDAO.PlantDAO;
import myDTO.Plant;

/**
 *
 * @author ADMIN
 */
public class UpdatePlantServlet extends HttpServlet {

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
            String action = request.getParameter("action");
            if(action != null){
                String name = request.getParameter("name");
                String imgPath = request.getParameter("imgPath");
                int price = Integer.parseInt(request.getParameter("price"));
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateId = Integer.parseInt(request.getParameter("cateId"));
                switch(action){
                    case "saveNewInfo":
                        int pid = Integer.parseInt(request.getParameter("pid"));
                        boolean check1 = PlantDAO.updatePlantInfo(pid, name, imgPath, price, description, status, cateId);
                        if (check1) {
                            ArrayList<Plant> list = PlantDAO.getAllPlants();
                            request.setAttribute("msg_success", "You have successfully updated the plant information!");
                            request.setAttribute("plantList", list);
                            request.getRequestDispatcher("managePlant.jsp").forward(request, response);
                        } else {
                            request.setAttribute("msg_error", "You have failed to update plant information!");
                        }
                        break;
                    case "createPlant":
                        boolean check2 = PlantDAO.insertNewPlant(name, imgPath, price, description, status, cateId);
                        if (check2) {
                            ArrayList<Plant> list1 = PlantDAO.getAllPlants();
                            request.setAttribute("msg_success", "You have successfully created new plant!");
                            request.setAttribute("plantList", list1);
                            request.getRequestDispatcher("managePlant.jsp").forward(request, response);
                        } else {
                            request.setAttribute("msg_error", "You have failed to create new plant!");
                        }
                        break;
                    }
                }
            else{
                request.setAttribute("MSG_ERROR", "Oops, something went wrong! Try later!");
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
            Logger.getLogger(UpdatePlantServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdatePlantServlet.class.getName()).log(Level.SEVERE, null, ex);
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
