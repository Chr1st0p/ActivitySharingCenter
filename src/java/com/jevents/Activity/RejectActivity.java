package com.jevents.Activity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevents.ActivityDBAO;

/**
 * Servlet implementation class RejectActivity
 */
@WebServlet("/RejectActivity")
public class RejectActivity extends HttpServlet {

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
    public RejectActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        activityDB.rejectActivity(request.getParameter("id"));
        request.setAttribute("message", "You have REJECTED the acticity");
        request.getRequestDispatcher("./result.jsp").forward(request, response);
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
