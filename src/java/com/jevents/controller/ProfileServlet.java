/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents.controller;

import com.jevents.Log;
import com.jevents.MySQLAccess;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author FENG0
 */
@WebServlet(name = "ProfileServlet", urlPatterns = {"/Profile"})
public class ProfileServlet extends HttpServlet {

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
        Log.out("Profile", "GET");
        request.setAttribute("result", "none");
        setProfile_Farword(request, response);
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
        Log.out("Profile", "POST");    
        String nickname = request.getParameter("Nickname");
        String gender = request.getParameter("Gender");
        String dob = request.getParameter("DOB");
        String nationality = request.getParameter("Nationality");
        String address = request.getParameter("Address");
        
//        Log.out("Profile", nickname);
//        Log.out("Profile", gender);
//        Log.out("Profile", dob);
//        Log.out("Profile", nationality);
        MySQLAccess mysql = new MySQLAccess();
        mysql.Connect();
        HttpSession session = request.getSession();
        int UserId = (int) session.getAttribute("userid");
        if (!mysql.checkNickname(UserId, nickname))
        {
            mysql.updateProfile(UserId, nickname, Integer.parseInt(gender), dob, nationality, address);
            mysql.Close();
            request.setAttribute("result", "Successful!");
            request.setAttribute("msg", "none");
        }
        else {
            request.setAttribute("result", "Failed!");
            request.setAttribute("msg", "nickname");
        }
        setProfile_Farword(request, response);
    }
    
    public void setProfile_Farword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        MySQLAccess mysql = new MySQLAccess();
        mysql.Connect();
        HttpSession session = request.getSession();
        int UserId = (int) session.getAttribute("userid");
        String nickname = mysql.getNickname(UserId);
        String gender = mysql.getGender(UserId);
        if (gender.length() == 0)
            gender = "0";
        String dob = mysql.getDOB(UserId);
        String nationality = mysql.getNationality(UserId);
        String address = mysql.getAddress(UserId);
        mysql.Close();
        request.setAttribute("nickname", nickname);
        request.setAttribute("gender", gender);
        request.setAttribute("dob", dob);
        request.setAttribute("nationality", nationality);
        request.setAttribute("address", address);
        request.setAttribute("PageTitle", "Profile");
        request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "ProfileServlet";
    }

}
