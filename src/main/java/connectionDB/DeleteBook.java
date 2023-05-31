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
@WebServlet("/deletelink")
public class DeleteBook extends HttpServlet{
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
		int id=Integer.parseInt(req.getParameter("bookid"));
//		String name=req.getParameter("bookname");
//		double price=Double.parseDouble(req.getParameter("price"));
//		String author=req.getParameter("author");
		PreparedStatement ps=null;
		String query="delete from book_store where book_id=?";
//		
		try {
			ps=con.prepareStatement(query);
			ps.setInt(1, id);
//			ps.setString(1, name);
//			ps.setDouble(2, price);
//			ps.setString(3, author);
			int count=ps.executeUpdate();
			PrintWriter pw=resp.getWriter();
			pw.print("<h1>"+count+" Book Deleted Successfully</h1>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
