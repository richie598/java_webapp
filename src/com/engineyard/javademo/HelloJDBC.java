package com.engineyard.javademo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

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

	// Properties for database connection
	private Properties props = new Properties();
	private String dbdriver = null;
	private String dbtype = null;
	private String dbhost = null;
	private String dbuser = null;
	private String dbpassword = null;
	private String connectURL = null;
	private String dbname = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		initializeDB();
		getMessage("jdbc");
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(message);
		response.getWriter().println(
				"session=".concat(request.getSession(true).getId()));
	}

	private void initializeDB() throws IOException {
		props.load(this.getClass().getClassLoader().getResourceAsStream("hellojdbc.properties"));
		dbdriver = props.getProperty("dbdriver");
		dbtype = props.getProperty("dbtype");
		dbhost = props.getProperty("dbhost");
		dbuser = props.getProperty("dbuser");
		dbpassword = props.getProperty("dbpassword");
		dbname  = props.getProperty("schemaname");
		connectURL = "jdbc:" + dbtype + "://" + dbhost + "/" + dbname + "?" + "user=" +dbuser+"&password=" +dbpassword;
	}

	private void getMessage(String string) {
		try {
			Class.forName(dbdriver);
			connect = DriverManager
					.getConnection(connectURL);
			statement = connect.createStatement();
			resultSet = statement
					.executeQuery("select message from javademo.javademo where id = '" + string + "'");
			while (resultSet.next()) {
				message = resultSet.getString(1);
			}

		} catch (SQLException e) {
			message = "Query failed: " + connectURL + e;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			message = "Couldn't load driver class: " + e;
		} finally {
			try {
				if (statement != null )	statement.close();
				if (connect != null) connect.close();
			} catch (SQLException e) {
				// I don't really care if this can't close
				e.printStackTrace();
			}

		}

	}
}
