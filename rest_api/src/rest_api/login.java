package rest_api;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class login extends HttpServlet {
private static final long serialVersionUID = 1L;
     
   
   public login() {
       super();
       // TODO Auto-generated constructor stub
   }


protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
	String username = request.getParameter("username");
	String password = request.getParameter("password");
Connection con=null;               
PrintWriter out=response.getWriter();

try      
{  
Class.forName("com.mysql.jdbc.Driver");

con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranjith?useSSL=false", "root" , "qburst" );              

PreparedStatement pst = con.prepareStatement("select * from register where username='"+username+"' and password='"+password+"';");

ResultSet rd=pst.executeQuery();
if(rd.first())
{
	out.println("login successfull");
	out.println("\n USER DETAILS");
	out.println(rd.getString("username"));
	out.println(rd.getString("email"));
	out.println(rd.getString("password"));
}
else
{
	out.println("login unsuccessfull");
}
}
catch(Exception e)        
{          out.println(e);              
}    

}

}