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

@WebServlet("/update")
public class update extends HttpServlet {
private static final long serialVersionUID = 1L;
     
   public update() {
       super();
       // TODO Auto-generated constructor stub
   }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
String username = request.getParameter("username");
String password = request.getParameter("password");
String email = request.getParameter("email");

Connection con=null;                
PrintWriter out=response.getWriter();

try      
{  
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranjith?useSSL=false", "root" , "qburst" );              

PreparedStatement pst = con.prepareStatement("update register set email='"+email+"' where username='"+username+"'and password='"+password+"';");
int i = pst.executeUpdate();
if(i>0)                
out.println("updated Successfully");              
else                
out.println("updated Unsuccessful");        
}        
catch(Exception e)        
{          out.println(e);              
}    

}

}