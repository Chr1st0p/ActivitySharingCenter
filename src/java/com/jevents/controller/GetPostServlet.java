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
 * Servlet implementation class GetPostServlet
 */
@WebServlet("/GetPostServlet")
public class GetPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
        if( request.getParameter("ActivityId")==null){
        	
        }
		
		
		response.setContentType("text/html;charset=UTF-8");  
		// Allocate a output writer to write the response message into the network socket   
		PrintWriter out = response.getWriter();    
		
		try{	
			int activityId= Integer.parseInt( request.getParameter("ActivityId"));
			HttpSession session = request.getSession();
			session.setAttribute("ActivityId", activityId);
			MySQLAccessPost mysql=new MySQLAccessPost(activityId);
			mysql.Connect();
			ArrayList<Integer> IdSet= mysql.getPostId(activityId);
			HashMap<Integer, Integer> PostUserId= mysql.getPostUserId(activityId);
			HashMap<Integer, String> PostContent= mysql.getPostContent(activityId);
			HashMap<Integer, Timestamp> PostTime= mysql.getPostSendTime(activityId);
			HashMap<Integer, String>  Nickname=new HashMap();
			for(int Id: IdSet){
				Nickname.put(Id, mysql.getNickname(PostUserId.get(Id)));
			}
			
			request.setAttribute("IdSet", IdSet);
			request.setAttribute("PostUserId", PostUserId);
			request.setAttribute("PostContent", PostContent);
			request.setAttribute("PostTime", PostTime);
			request.setAttribute("Nickname", Nickname);
			request.setAttribute("ActivityId", activityId);
			request.getRequestDispatcher("/posts.jsp").forward(request, response);
			mysql.Close();
		}catch (Exception ex) {
            Log.out("GetPost", ex);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
