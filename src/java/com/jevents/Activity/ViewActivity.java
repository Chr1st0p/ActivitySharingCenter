package com.jevents.Activity;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevents.ActivityDBAO;
import com.jevents.Log;
import com.jevents.MySQLAccessPost;
import com.jevents.model.Activity;

/**
 * Servlet implementation class ViewActivity
 */
@WebServlet(name = "ViewActivity", urlPatterns = "/ViewActivity")
public class ViewActivity extends HttpServlet {

    private static final long serialVersionUID = 1L;
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

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        //Delete when publish

        int UserID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        int isAdmin = Integer.parseInt(request.getSession().getAttribute("roleid").toString());
        request.getSession().setAttribute("UserId", 5);
  
        String activityID = request.getParameter("id");
        Activity ac;
        ac = activityDB.getActivityByID(activityID);
        System.err.println("Activity ID"+activityID);
        System.err.println("com.jevents.Activity.ViewActivity.doGet() Act"+ac.getDescription());
        boolean isParticipant = activityDB.checkParticipant(UserID, Integer.valueOf(activityID));
        System.err.println("Is participant"+isParticipant);
        ac.setParticipant(isParticipant);
        request.getSession().setAttribute("ActivityId", activityID);
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
        IdSet.stream().forEach((Id) -> {
            Nickname.put(Id, mysql.getNickname(PostUserId.get(Id)));
        });

        request.setAttribute("IdSet", IdSet);
        request.setAttribute("PostUserId", PostUserId);
        request.setAttribute("PostContent", PostContent);
        request.setAttribute("PostTime", PostTime);
        request.setAttribute("Nickname", Nickname);
        request.setAttribute("ActivityId", activityID);
//		request.getRequestDispatcher("/posts.jsp").forward(request, response);
        mysql.Close();
        request.setAttribute("ActivityActive", false);

        RequestDispatcher rd = request.getRequestDispatcher("viewActivity.jsp");
        rd.forward(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
