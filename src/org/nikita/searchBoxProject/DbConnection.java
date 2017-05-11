package org.nikita.searchBoxProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbConnection {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	void connection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager
		          .getConnection("jdbc:mysql://localhost:3306/SearchKeywords","root", "mysql");
		// Statements allow to issue SQL queries to the database
	    statement = connect.createStatement();
	}
	
	void closeConnection() throws SQLException {
		if(resultSet != null){
			resultSet.close();
		}
		if(statement != null){
			statement.close();
		}
		if(preparedStatement != null){
			preparedStatement.close();
		}
		if(connect != null){
			connect.close();
		}
	}
	
	private List<String> getAllSearchKeywords() throws SQLException{
		List<String> allSearchKeywords = new ArrayList<>();
		// pull records from db
		resultSet = statement.executeQuery("select * from keywordsStore");
		while(resultSet.next()){
			allSearchKeywords.add(resultSet.getString(2)); 
		}
		return allSearchKeywords;
	}
	
	String getAllSearchKeywords1() throws SQLException{
		String allSearchKeywords = "";
		// pull records from db
		resultSet = statement.executeQuery("select * from keywordsStore");
		while(resultSet.next()){
			allSearchKeywords += resultSet.getString(2) + " "; 
		}
		return allSearchKeywords;
	}
	
	int getKeywordCount(String keyword) throws SQLException{
		int count = 0;
		int keywordId ;
		resultSet = statement.executeQuery("select * from keywordsStore where keyword='"+keyword+"'");
		if(resultSet.next()){
			// get the count, increment, set count
			count = resultSet.getInt(3);
			keywordId = resultSet.getInt(1);
			count++;
			// update the count in db
			preparedStatement=connect.prepareStatement("update keywordsStore set keywordcount=? where keywordID="+keywordId);  
			preparedStatement.setInt(1, count);
			preparedStatement.executeUpdate();
			
		} else { // if not found in db, then insert
			count = 1;
			preparedStatement=connect.prepareStatement("insert into keywordsStore(keyword,keywordcount) values(?,?)"); 
			preparedStatement.setString(1, keyword);
			preparedStatement.setInt(2, count);
			preparedStatement.executeUpdate();
		}
		return count;
	}
}
