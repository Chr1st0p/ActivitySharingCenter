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
import com.jevents.MySQLAccessReply;

/**
 * Servlet implementation class GetActivityServlet
 */
@WebServlet("/GetActivityServlet")
public class GetReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetReplyServlet() {
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
		// Allocate a output writer to write the response message into the network socket   
		PrintWriter out = response.getWriter();    
		
		try{	
			System.out.println("PostReplay "+ request.getParameter("PostId"));
			HttpSession session = request.getSession();
			int activityId= Integer.parseInt( session.getAttribute("ActivityId").toString());//request.getParameter("ActivityId")
			int postId= Integer.parseInt( request.getParameter("PostId"));
			
			session.getAttribute("ActivityId");
			session.setAttribute("PostId", postId);
			MySQLAccessPost mysqlP=new MySQLAccessPost(activityId);
			mysqlP.Connect();
			//int IdSetP= mysqlP.getPostId_1R(postId);
			int PostUserId= mysqlP.getPostUserId1R(postId);
			String PostContent= mysqlP.getPostContent1R(postId);
			Timestamp PostTime= mysqlP.getPostSendTime1R(postId);
			String NicknameP= mysqlP.getNickname(PostUserId);
			mysqlP.Close();
			
			MySQLAccessReply mysqlR=new MySQLAccessReply(postId);
			mysqlR.Connect();
			ArrayList<Integer> IdSet= mysqlR.getReplayId(postId);
			HashMap<Integer, Integer> ReplyUserId= mysqlR.getReplayUserId(postId);
			HashMap<Integer, String> ReplyContent= mysqlR.getReplayContent(postId);
			HashMap<Integer, Timestamp> ReplyTime= mysqlR.getReplaySendTime(postId);
			HashMap<Integer, Integer> ReplyRepliedId= mysqlR.getReplayReplyId(postId);
			HashMap<Integer, String>  Nickname=new HashMap();
			HashMap<Integer, String>  NicknameR=new HashMap();
			for(int Id: IdSet){
				Nickname.put(Id, mysqlR.getNickname(ReplyUserId.get(Id)));
				if(ReplyRepliedId.get(Id)!=-1)
					NicknameR.put(Id, mysqlR.getNickname(mysqlR.getReplayUserId1R(ReplyRepliedId.get(Id))));
				else
					NicknameR.put(Id,NicknameP);
			}
			request.setAttribute("PostUserId", PostUserId);
			request.setAttribute("PostContent", PostContent);
			request.setAttribute("PostTime", PostTime);
			request.setAttribute("NicknameP", NicknameP);
			
			request.setAttribute("IdSet", IdSet);
			request.setAttribute("ReplyUserId", ReplyUserId);
			request.setAttribute("ReplyRepliedId", ReplyRepliedId);
			request.setAttribute("ReplyContent", ReplyContent);
			request.setAttribute("ReplyTime", ReplyTime);
			request.setAttribute("Nickname", Nickname);
			request.setAttribute("NicknameR", NicknameR);
			
			request.getRequestDispatcher("/reply.jsp").forward(request, response);
//			out.println("<!DOCTYPE html>");
//			out.println("<html><head>"); 
//			out.println("<meta http-equiv='Content-Type' content='text/html; charset=UTF8'>"); 
//			out.println("<title>Reply In Post</title></head>");
//			out.println("<body>");  // says Hello 
//			// Echo client's request information 
//			out.println("<div>");
//			out.println("<font size=\"4\"> ");
//			out.println(""+PostContent+"");
//			out.println("</font>");
//			out.println("<br>");
//			out.println("<label >"+mysqlR.getNickname(PostUserId)+"</label><br>");
//			out.println("<label >"+PostTime.toString()+"</label>");
//			out.println("</div>");
//			out.println("<div>");
//			out.println("<a href=" +request.getRequestURI().
//					replaceAll("/GetPostServlet", "/AddReply")
//					+"?"+">");
//			out.println("<font size=\"2\"> ");
//			out.println("Reply");
//			out.println("</font>");
//			out.println("</a>");
//			out.println("	");
//			out.println("</div>");
//			for(int Id : IdSet){
//				out.println("<div>");
//				if(ReplyRepliedId.get(Id)!=-1){
//					out.println("<label > Reply: "+mysqlR.getNickname(ReplyRepliedId.get(Id))+"</label><br>");
//				}else{
//					out.println("<label > Reply: "+mysqlR.getNickname(PostUserId)+"</label><br>");
//				}
//				out.println("<font size=\"4\"> ");
//				if(ReplyContent.get(Id).length()<=100){
//					out.println(""+ReplyContent.get(Id)+"");
//				}else{
//					out.println(""+ReplyContent.get(Id).substring(0,100)+"");
//				}
//				
//				out.println("</font>");
//				out.println("<br>");
//				out.println("<font size=\"4\"> ");
//				out.println("<label >"+mysqlR.getNickname(ReplyUserId.get(Id))+"</label><br>");
//				out.println("<label >"+ReplyTime.get(Id).toString()+"</label><br>");
//				out.println("</font>");
//				out.println("</div>");
//				out.println("<div>");
//				out.println("<a href=" +request.getRequestURI().
//						replaceAll("/GetPostServlet", "/AddReply")
//						+"?ReplyId="+ Id+">");
//				out.println("<font size=\"2\"> ");
//				out.println("Reply");
//				out.println("</font>");
//				out.println("</a>");
//				out.println("	");
//				out.println("</div>");
//			}
//			out.println("<jsp:include page=\"/WEB-INF/jspf/footer.jspf\" >");
			
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
