package com.jevents.controller;

import java.io.IOException;
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
 * Servlet implementation class AddPosts
 */
@WebServlet("/AddPostsServlet")
public class AddPostsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPostsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");  
		try{	
			String content= request.getParameter("content");
			HttpSession session = request.getSession();
			int userId=0;

			if(session.getAttribute("userid").toString()!=null){
				userId=Integer.parseInt(session.getAttribute("userid").toString());
			}
			int AcId=Integer.parseInt(request.getParameter("AcId").toString());
			MySQLAccessPost mysql=new MySQLAccessPost (AcId);
			mysql.Connect();
			request.setAttribute("ori", "Post");
			request.setAttribute("Id", AcId);
			if(mysql.createPost(userId, content)){
				mysql.Close();
				request.setAttribute("res", "1");
//				request.getRequestDispatcher("./GetPost?ActivityId="+AcId).forward(request, response);
			}else{
				mysql.Close();
				request.setAttribute("res", "0");
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
