package com.willluongo.javademo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloJDBC extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3797999438165177024L;
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;
	private String message = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			getMessage("jdbc");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(message);
		response.getWriter().println(
				"session=".concat(request.getSession(true).getId()));
	}

	private void getMessage(String string) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://10.0.1.220/javademo?" +
			 "user=javademo&password=Bespoke2013");
			statement = connect.createStatement();
			resultSet = statement.executeQuery("select message from javademo.javademo where id = 'jdbc'");
			message = resultSet.getString(0);
		}
		catch (SQLException e) {
			throw e;
		}
		finally
		{
			statement.close();		
			connect.close();	
		}
		
	}
}
