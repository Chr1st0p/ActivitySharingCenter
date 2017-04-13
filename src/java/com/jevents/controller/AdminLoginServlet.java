/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents.controller;

import com.jevents.Log;
import com.jevents.MySQLAccess;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FENG0
 */
public class AdminLoginServlet extends HttpServlet {

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
        response.sendRedirect("./login.jsp");
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
        String login_email = request.getParameter("login_email");
        String login_password = request.getParameter("login_password");

        try {
            MySQLAccess mysql = new MySQLAccess();
            mysql.Connect();
            if (mysql.checkEmail(login_email)) {
                int userId = mysql.Login(login_email, login_password);
                int roleId = mysql.getUserGroup(userId);
                if (userId != -1) {
                    mysql.setLast_login(userId);
                    if (roleId == 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("userid", userId);
                        session.setAttribute("roleid", roleId);
                        response.sendRedirect("./Dashboard");
                    } else {
                        request.setAttribute("result", "failed");
                        request.setAttribute("error", "email");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("result", "failed");
                    request.setAttribute("error", "password");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("result", "failed");
                request.setAttribute("error", "email");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            mysql.Close();
        } catch (Exception ex) {
            Log.out("Login", ex);
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
