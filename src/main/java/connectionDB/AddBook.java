package connectionDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.PreparableStatement;
@WebServlet("/addlink")
public class AddBook extends HttpServlet{
	Connection con=null;
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja8","root","Abhi@123");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name=req.getParameter("bookname");
		double price=Double.parseDouble(req.getParameter("price"));
		String author=req.getParameter("author");
		PreparedStatement ps=null;
		String query="insert into book_store values(?,?,?,?)";
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, 0);
			ps.setString(2, name);
			ps.setDouble(3, price);
			ps.setString(4, author);
			int count=ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+"Record Inserted</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
