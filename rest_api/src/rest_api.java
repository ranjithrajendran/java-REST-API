
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

@WebServlet("/rest_api")
public class rest_api extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public rest_api() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Connection con=null;              
		PrintWriter out=response.getWriter();
		try      
		{  
		Class.forName("com.mysql.jdbc.Driver");

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ranjith?useSSL=false", "root" , "qburst" );              

		PreparedStatement pst = con.prepareStatement("select * from register;");
		ResultSet rd=pst.executeQuery();

		while(rd.next())
		{
		                           
			out.println(rd.getString("username"));
			out.println(rd.getString("email"));
			out.println(rd.getString("password"));
		}
		  

		}
		catch(Exception e)        
		{          out.println(e);              
		}   

}
}

		
