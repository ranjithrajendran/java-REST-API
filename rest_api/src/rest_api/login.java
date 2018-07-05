package rest_api;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletInputStream inputjson = null;
	ObjectMapper mapper = new ObjectMapper();
	String username;
	String password;     
	public login() {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;               
		PrintWriter out=response.getWriter();
		inputjson = request.getInputStream();
		try {		
			jackson stud1 = mapper.readValue(inputjson, jackson.class);
			username = stud1.get_username();
			password = stud1.get_password();
		}
		catch (JsonGenerationException e) {
			e.printStackTrace();
		} 
		catch (JsonMappingException e) {
			e.printStackTrace();
		} 
		catch (JsonParseException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
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