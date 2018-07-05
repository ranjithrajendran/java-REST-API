package rest_api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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

@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletInputStream inputjson = null;
	ObjectMapper mapper = new ObjectMapper();
	String username;
	String email;
	String password;
	public register() {
		super();
	}	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con=null;                
		PrintWriter out=response.getWriter();
		inputjson = request.getInputStream();
		try {
			jackson stud1 = mapper.readValue(inputjson, jackson.class);
			username = stud1.get_username();
			email = stud1.get_email();
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
		{          
			out.println(e);              
		}    
	}

}