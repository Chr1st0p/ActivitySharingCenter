package com.jevents.controller;

import java.io.IOException;
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
import com.jevents.MySQLAccessReply;

/**
 * Servlet implementation class AddReplyServlet
 */
@WebServlet("/AddReplyServlet")
public class AddReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");  
		try{	
			String content= request.getParameter("content");
			HttpSession session = request.getSession();
			int userId=0;

			if(session.getAttribute("userid").toString()!=null){
				userId=Integer.parseInt(session.getAttribute("userid").toString());
			}
			int ReId=Integer.parseInt(request.getParameter("ReId").toString());
			MySQLAccessReply mysql=new MySQLAccessReply(Integer.parseInt(session.getAttribute("PostId").toString()));
			mysql.Connect();
			request.setAttribute("ori", "Reply");
			request.setAttribute("Id", session.getAttribute("PostId").toString());
			if(mysql.createReply(userId, content,ReId)){
				mysql.Close();
				
				request.setAttribute("res", "1");
//				request.getRequestDispatcher("./GetReply?PostId="+Integer.parseInt(session.getAttribute("PostId").toString())).forward(request, response);
				
			}else{
				request.setAttribute("res", "0");
				mysql.Close();
			}
		}catch (Exception ex) {
			request.setAttribute("res", "0");
            Log.out("Add Reply", ex);
        }finally{
        	request.getRequestDispatcher("./addres.jsp").forward(request, response);
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
