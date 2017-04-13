package com.jevents.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jevents.Log;
import com.jevents.MySQLAccessPost;
import com.jevents.MySQLAccessReply;

/**
 * Servlet implementation class DelReplyServlet
 */
@WebServlet("/DelReplyServlet")
public class DelReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");  
		// Allocate a output writer to write the response message into the network socket   
		PrintWriter out = response.getWriter();    
		
		try{	
			int Id= Integer.parseInt( request.getParameter("Id").toString());
			HttpSession session = request.getSession();
			int activityId=Integer.parseInt(session.getAttribute("ActivityId").toString());
			int PostId=Integer.parseInt(session.getAttribute("PostId").toString());
			MySQLAccessReply mysql=new MySQLAccessReply(activityId);
			mysql.Connect();
			Log.out("Delreply",  Id);
			mysql.deleteReply(Id);
			String url=request.getHeader("REFERER").split("/")[request.getHeader("REFERER").split("/").length-1];
//			request.getRequestDispatcher("/"+url).forward(request, response);
			request.getRequestDispatcher("./GetReply?PostId=" + PostId).forward(request, response);
			mysql.Close();
		}catch (Exception ex) {
            Log.out("DelPost", ex);
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
