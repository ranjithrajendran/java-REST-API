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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
* Servlet implementation class Register
*/
@WebServlet("/register")
public class register extends HttpServlet {
private static final long serialVersionUID = 1L;
     
   public register() {
       super();
       // TODO Auto-generated constructor stub
   }

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
String username = request.getParameter("username");
String email = request.getParameter("email");
String password = request.getParameter("password");

Connection con=null;                
PrintWriter out=response.getWriter();

try      
{  
Class.forName("com.mysql.jdbc.Driver");

con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranjith?useSSL=false", "root" , "qburst" );              

PreparedStatement pst = con.prepareStatement("insert into register values(?,?,?);");

pst.setString(1,username);
pst.setString(2,email);
pst.setString(3,password);

int i = pst.executeUpdate();
if(i>0)                
out.println("Inserted Successfully");              
else                
out.println("Insert Unsuccessful");        
}        
catch(Exception e)        
{          out.println(e);              
}    

}

}