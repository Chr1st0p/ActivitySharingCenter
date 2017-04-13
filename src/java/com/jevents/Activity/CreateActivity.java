package com.jevents.Activity;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevents.ActivityDBAO;
import com.jevents.utils.GeoCoding;
import com.jevents.utils.Location;
import com.jevents.model.Activity;
import com.jevents.model.Category;

/**
 * Servlet implementation class CreateActivity
 */
@WebServlet("/CreateActivity")
public class CreateActivity extends HttpServlet {

    private ActivityDBAO activityDB;

    private static final long serialVersionUID = 1L;

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
    public CreateActivity() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
        List<Category> c = activityDB.getCategory();
        request.setAttribute("categoryList", c);

        RequestDispatcher rd = request.getRequestDispatcher("createActivity.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
//        request.getSession().setAttribute("UserId", 5);
        response.setContentType("text/html");
        request.getAttribute("name");
        String name = (String) request.getParameter("name");
        String description = request.getParameter("description");
        int creatorID = Integer.parseInt(request.getSession().getAttribute("userid").toString());

        Timestamp time = Timestamp.valueOf(request.getParameter("time"));

        int category = Integer.valueOf(request.getParameter("categoryID"));
        String location = request.getParameter("location");
        Location loc = GeoCoding.GetLatLng(location);

        Activity ac = new Activity(name, description, creatorID, time, category, (float) loc.getLat(), (float) loc.getLng(), location);
        activityDB.createActivity(ac);
        
        response.sendRedirect("DisplayActivity");
    }

}
