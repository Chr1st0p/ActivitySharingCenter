package com.jevents.controller;

import com.jevents.Log;
import com.jevents.MySQLAccess;
import com.jevents.utils.XMPP;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FENG0
 */
public class SignupServlet extends HttpServlet {

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
        String reg_email = request.getParameter("reg_email");
        String reg_password = request.getParameter("reg_password");
        String reg_fullname = request.getParameter("reg_fullname");
        String reg_phone = request.getParameter("reg_phone");
        try {
            MySQLAccess mysql = new MySQLAccess();
            mysql.Connect();
            if (!mysql.checkEmail(reg_email)) {
                mysql.Signup(reg_email, reg_password, reg_fullname, reg_phone);
                mysql.Close();
                int userId = mysql.Login(reg_email, reg_password);
                HttpSession session = request.getSession();
                session.setAttribute("userid", userId);
                addCookie(response, "jid", reg_fullname+"@www.serverathome.com", 360000);
                addCookie(response, "password", "password", 360000);
                response.sendRedirect("./Profile");

            } else {
                request.setAttribute("result", "failed");
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Log.out("Signup", ex);
        }
        String resultsRegist = "";
        String resultsLogin = "";
//        String resultsBOSH = "";
        try {
            resultsRegist = XMPP.Regist(reg_fullname, reg_password, reg_email);
            resultsLogin = XMPP.Login(reg_fullname, reg_password);
//            resultsBOSH = XMPP.BOSHLogin(reg_fullname, reg_password);
        } catch (Exception e) {
            System.out.println("regist XMPP exception");
            e.printStackTrace();
        }
        if (resultsRegist.equals("ERROR")) {
            System.err.println("Regist XMPP Error.");
        } else {
            System.out.println("Regist XMPP Success.");
        }
        if (resultsLogin.equals("ERROR")) {
            System.err.println("Login XMPP Error.");
        } else {
            System.out.println("Login XMPP Success.");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "SignupServlet";
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
