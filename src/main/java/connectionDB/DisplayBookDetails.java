package connectionDB;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.Result;
import com.mysql.cj.xdevapi.Statement;
@WebServlet("/displaybook")
public class DisplayBookDetails extends HttpServlet{
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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		java.sql.Statement stmt=null;
		ResultSet rs=null;
		PrintWriter pw=resp.getWriter();
		String query="select * from book_store";
		try {
			stmt=con.createStatement();
			rs=stmt.executeQuery(query);
			pw.print("<table border='2'>");
			pw.print("<tr>");
			pw.print("<th> BOOK ID </th>");
			pw.print("<th> BOOK NAME </th>");
			pw.print("<th> BOOK PRICE </th>");
			pw.print("<th> BOOK AUTHOR </th>");
			pw.print("</tr>");
			while(rs.next()) {
			pw.print("<tr>");
			pw.print("<td>"+rs.getInt(1)+"</td>");
			pw.print("<td>"+rs.getString(2)+"</td>");
			pw.print("<td>"+rs.getDouble(3)+"</td>");
			pw.print("<td>"+rs.getString(4)+"</td>");
			pw.print("</tr>");
			}
			pw.print("</table>");

			} catch (SQLException e) {
			e.printStackTrace();
			}
			}
	}


