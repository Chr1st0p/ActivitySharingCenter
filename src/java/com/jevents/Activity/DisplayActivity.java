package com.jevents.Activity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevents.ActivityDBAO;
import com.jevents.model.Activity;

/**
 * Servlet implementation class DisplayActivity
 */
@WebServlet(name = "DisplayActivity", urlPatterns = "/DisplayActivity")
public class DisplayActivity extends HttpServlet {

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
    public DisplayActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        List<Activity> c = activityDB.getActivities();
        request.getSession().setAttribute("UserId", 5);

        int UserId = Integer.parseInt(request.getSession().getAttribute("userid").toString());
        c = modifyActivityList(c, UserId);
        request.setAttribute("activityList", c);
        request.setAttribute("ActivityActive", true);

        RequestDispatcher rd = request.getRequestDispatcher("displayActivity.jsp");
        rd.forward(request, response);
    }

    /**
     * @param response
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

    private List<Activity> modifyActivityList(List<Activity> c, int UserID) {
        List<Activity> result = new ArrayList<Activity>();
        for (Activity a : c) {
            if (a.getCreatorID() == UserID) {
                if (a.getStatus() == 0) {
                    a.setName("[Awaiting Approval]" + a.getName());
                }
                if (a.getStatus() == -1) {
                    a.setName("[Rejected]" + a.getName());
                }
                if (a.getStatus() == 2) {
                    a.setName("[Completed]" + a.getName());
                }
                result.add(a);

            } else {
                if (a.getStatus() == 2) {
                    a.setName("[Completed]" + a.getName());
                    result.add(a);

                }
                if (a.getStatus() == 1) {
                    result.add(a);
                }

            }
        }
        return result;
    }

}
