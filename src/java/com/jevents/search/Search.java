package com.jevents.search;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jevents.ActivityDBAO;
import com.jevents.model.Activity;
import com.jevents.model.Category;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;

public class Search extends HttpServlet {

    private ActivityDBAO activityDB;

    @Override
    public void init() throws ServletException {
        System.out.println("init");
        activityDB = (ActivityDBAO) getServletContext().getAttribute("activityDB");
        if (activityDB == null) {
            throw new UnavailableException("Couldn't get database.");
        }
    }

    @Override
    public void destroy() {
        activityDB = null;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userid") != null) {
            doPost(request, response);
        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("</head>");
            out.println("<body >");
            out.println("<p>Please login to use the search function. </p>");
            out.println("</body>");
            out.println("</html>");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("userid") != null) {
            System.out.println("doGet");
            ArrayList<Category> categoryList = activityDB.getCategory();
            request.setAttribute("categroyList", categoryList);
            System.out.println("doPost");
            String keyword = request.getParameter("keyword");
            String category = request.getParameter("category");
            String location = request.getParameter("location");
            String description = request.getParameter("description");
            System.out.println("=================Searching Detail==================");
            System.out.println("keyword: " + keyword);
            System.out.println("category: " + category);
            System.out.println("location: " + location);
            System.out.println("description: " + description);
            System.out.println("keyword: " + keyword);
            System.out.println("==============Searching Detail END=================");
            ArrayList<Activity> activitiesList = activityDB.getActivitiesByFilter(keyword, category, location, description);
            request.setAttribute("resultlist", activitiesList);
            System.out.println("search result : " + activitiesList.size());

            request.setAttribute("ActivityActive", false);
            RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
            rd.forward(request, response);

        } else {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("</head>");
            out.println("<body >");
            out.println("<p>Please login to use the search function. </p>");
            out.println("</body>");
            out.println("</html>");
        }

    }
}
