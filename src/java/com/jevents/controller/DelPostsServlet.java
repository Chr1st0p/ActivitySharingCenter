package com.jevents.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevents.Log;
import com.jevents.MySQLAccessPost;

/**
 * Servlet implementation class DelPostsServlet
 */
@WebServlet("/DelPostsServlet")
public class DelPostsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        response.setContentType("text/html;charset=UTF-8");
        // Allocate a output writer to write the response message into the network socket   
        PrintWriter out = response.getWriter();

        try {
            int Id = Integer.parseInt(request.getParameter("Id").toString());
            System.out.println("Delete Post Servlet Do get Id " + Id);
            HttpSession session = request.getSession();
            int activityId;
            activityId = Integer.parseInt(session.getAttribute("ActivityId").toString());

            MySQLAccessPost mysql = new MySQLAccessPost(activityId);
            mysql.Connect();
            Log.out("DelPost", " heartbeat");
            mysql.deletePost(Id);
            Log.out("DelPost", request.getHeader("REFERER"));
            String url = request.getHeader("REFERER").split("/")[request.getHeader("REFERER").split("/").length - 1];

            //request.getRequestDispatcher("/"+url).forward(request, response);
            request.getRequestDispatcher("./GetPostServlet?ActivityId=" + activityId).forward(request, response);
            mysql.Close();
        } catch (Exception ex) {
            Log.out("DelPost", ex);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
