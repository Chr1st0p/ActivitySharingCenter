/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jevents.controller;

import com.jevents.ActivityDBAO;
import com.jevents.MySQLAccessPost;
import com.jevents.model.Activity;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FENG0
 */
public class AdminActivityServlet extends HttpServlet {

    private ActivityDBAO activityDB;

    @Override
    public void init() throws ServletException {

        activityDB = (ActivityDBAO) getServletContext()
                .getAttribute("activityDB");
        if (activityDB == null) {
            throw new UnavailableException("Couldn't get database.");
        }
    }

    @Override
    public void destroy() {
        activityDB = null;
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

        int UserID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int isAdmin = Integer.parseInt(request.getSession().getAttribute("roleid").toString());

        String activityID = request.getParameter("id");
        Activity ac;
        ac = activityDB.getActivityByID(activityID);
        System.err.println("Activity ID" + activityID);
        System.err.println("com.jevents.Activity.ViewActivity.doGet() Act" + ac.getDescription());
        boolean isParticipant = activityDB.checkParticipant(UserID, Integer.valueOf(activityID));
        System.err.println("Is participant" + isParticipant);
        ac.setParticipant(isParticipant);

        request.setAttribute("activity", ac);
        request.setAttribute("isAdmin", isAdmin);
        //dante part
        MySQLAccessPost mysql = new MySQLAccessPost(Integer.parseInt(activityID));
        mysql.Connect();
        ArrayList<Integer> IdSet = mysql.getPostId(Integer.parseInt(activityID));
        HashMap<Integer, Integer> PostUserId = mysql.getPostUserId(Integer.parseInt(activityID));
        HashMap<Integer, String> PostContent = mysql.getPostContent(Integer.parseInt(activityID));
        HashMap<Integer, Timestamp> PostTime = mysql.getPostSendTime(Integer.parseInt(activityID));
        HashMap<Integer, String> Nickname = new HashMap();
        for (int Id : IdSet) {
            Nickname.put(Id, mysql.getNickname(PostUserId.get(Id)));
        }
        
        request.setAttribute("IdSet", IdSet);
        request.setAttribute("PostUserId", PostUserId);
        request.setAttribute("PostContent", PostContent);
        request.setAttribute("PostTime", PostTime);
        request.setAttribute("Nickname", Nickname);
        request.setAttribute("ActivityId", activityID);
//		request.getRequestDispatcher("/posts.jsp").forward(request, response);
        mysql.Close();

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/adminActivity.jsp");
        rd.forward(request, response);
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
