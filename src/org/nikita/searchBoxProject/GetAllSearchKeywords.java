package org.nikita.searchBoxProject;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetAllSearchKeywords
 */
@WebServlet("/getAllSearchKeywords")
public class GetAllSearchKeywords extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllSearchKeywords() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			//response.setContentType("application/json");
			DbConnection dbConn = new DbConnection();
			dbConn.connection();
			// List<String> keywords = getAllSearchKeywords();
			String keywords = dbConn.getAllSearchKeywords1();
			dbConn.closeConnection();
			response.getWriter().println(keywords);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().append("Error :" + e.getMessage());
		}
	}

}
