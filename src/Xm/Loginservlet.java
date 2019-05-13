package Xm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSet;
import com.mysql.jdbc.Statement;

public class Loginservlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Connection conn=null;
		try{
		    //有可能引发严重问题的代码
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("创建成功");
		 conn=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/Student","root","admin");
		 
		 Statement stmt=(Statement)conn.createStatement(); 
		 String user=request.getParameter("user");//??
	     String pwd=request.getParameter("pwd");
	     ResultSet rs=( ResultSet)stmt.executeQuery("SELECT * FROM student.new_table where user='"+user+"' and pwd='"+pwd+"'");
		
	     
	     if(rs.next()){//??
	    	 String yh=rs.getString("user");
	    	 String mm=rs.getString("pwd");
	    	 if(yh.equals(user)&& mm.equals(pwd)){
	    		 
	    		 
	    		 
	    		 HttpSession session = request.getSession();
	    	        session.setAttribute("name", user);//session存放数据
	    	        session.setAttribute("mima", pwd);
	    	        session.setMaxInactiveInterval(10);//设置session的有效时间，60秒
	    	        System.out.println("登录信息成功保存" );
	    	        request.getRequestDispatcher("home").forward(request, response);//转发到成功页面
	    	        
	    	        
	            }else{
	            	response.sendRedirect("/Htong/register.html");
	    	 }
	     }
	     
	     
	     
	     
	     
	     
	     
	     
		
		}
		catch(Exception e){
		   //捕获到问题的处理措施
		}
		finally{
		    //无论是否出现问题，都要执行的代码，通常用于释放资源
		}
		
		
		
		
		
		
		//String user=request.getParameter("user");//??
        //String pwd=request.getParameter("pwd");
		
		
       // if(user.equals("zhangsan") && pwd.equals(123)){
           
              //  request.getRequestDispatcher("success").forward(request, response);//转发到成功页面
          //  }else{
             //   response.sendRedirect("fail");//密码错误,重定向到失败页面
            //}
		
		
		
		
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
