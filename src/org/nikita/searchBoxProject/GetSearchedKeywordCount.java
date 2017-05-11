package org.nikita.searchBoxProject;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class GetSearchedKeywordCount
 */
@WebServlet("/getSearchedKeywordCount")
public class GetSearchedKeywordCount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSearchedKeywordCount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String keyword = request.getParameter("keyword");
		DbConnection dbConn = new DbConnection();
		try {
			dbConn.connection();
			// logic to check if the keyword is already present in the db, if yes then increment the count else insert and set count to 1
			int keywordCount = dbConn.getKeywordCount(keyword);
			dbConn.closeConnection();
			response.getWriter().println("Searched Keyword Name: Searched Count Number");
			response.getWriter().println("<br/>");
			response.getWriter().println(keyword + " : " + keywordCount);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append("Error :" + e.getMessage());
		}
	}
}
