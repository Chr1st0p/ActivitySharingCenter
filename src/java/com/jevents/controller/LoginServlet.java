package com.jevents.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.jevents.Log;
import com.jevents.MySQLAccess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FENG0
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/Login"})
public class LoginServlet extends HttpServlet {

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
        response.sendRedirect("./index.jsp");
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
                    HttpSession session = request.getSession();
                    session.setAttribute("userid", userId);
                    session.setAttribute("roleid", roleId);
                    mysql.setLast_login(userId);
                    if (roleId == 0) {
                        addCookie(response, "jid", login_email, 360000);
                        addCookie(response, "password", login_password, 360000);
                        response.sendRedirect("./Dashboard");    
                    } else {
                        addCookie(response, "jid", login_email, 360000);
                        addCookie(response, "password", login_password, 360000);
                        response.sendRedirect("./Search");
                    }
                } else {
                    request.setAttribute("result", "failed");
                    request.setAttribute("error", "password");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("result", "failed");
                request.setAttribute("error", "email");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
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
        return "LoginServlet";
    }// </editor-fold>

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }
}
