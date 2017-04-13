package com.jevents.Activity;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevents.ActivityDBAO;

/**
 * Servlet implementation class RateActivity
 */
@WebServlet("/RateActivity")
public class RateActivity extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ActivityDBAO activityDB;

    public void init() throws ServletException {

        activityDB = (ActivityDBAO) getServletContext()
                .getAttribute("activityDB");
        if (activityDB == null) {
            throw new UnavailableException("Couldn't get database.");
        }
    }

    public void destroy() {
        activityDB = null;
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RateActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//		response.sendRedirect(request.getContextPath() + "/ViewActivity?id="+request.getParameter("id"));
//		if(checkRating(userID,))
        int UserID = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        if (activityDB.checkRating(UserID, request.getParameter("id"))) {
            response.getWriter().append("You have rated already");

        } else {
            request.setAttribute("activity_id", request.getParameter("id"));

            RequestDispatcher rd = request.getRequestDispatcher("rateActivity.jsp");
            rd.forward(request, response);

        }

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        int UserID = (int) request.getSession().getAttribute("UserId");

        activityDB.rating(UserID, request.getParameter("activityID"), request.getParameter("star"));

        response.sendRedirect(request.getContextPath() + "/ViewActivity?id=" + request.getParameter("activityID"));

    }

}
