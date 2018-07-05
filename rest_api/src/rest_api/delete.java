package rest_api;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class delete extends HttpServlet {
private static final long serialVersionUID = 1L;
     
   
   public delete() {
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

PreparedStatement pst = con.prepareStatement("delete from register where (username='"+username+"' and password='"+password+"');");

int i = pst.executeUpdate();
if(i>0)                
out.println("deleted Successfully");              
else                
out.println("deleted Unsuccessful");        
}        
catch(Exception e)        
{          out.println(e);              
}    

}

}