package com.engineyard.javademo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class HelloJNDI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3349334178242798897L;
	private Context ctx = null;
	private DataSource ds = null;
	private String message = null;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			initializeDB();
			getMessage("jndi");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			message = e.toString();
		}

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(message);
		response.getWriter().println(
				"session=".concat(request.getSession(true).getId()));
	}

	private void initializeDB() throws NamingException {
		ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/EYMySQL");
	}

	private void getMessage(String string) {
		Connection con = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			con = ds.getConnection();
			statement = con.createStatement();
			resultSet = statement
					.executeQuery("select message from javademo where id = 'jndi'");
			while (resultSet.next()) {
				message = resultSet.getString(1);
			}

		} catch (SQLException e) {
			message = "Query failed: " + e;
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					// I DON'T CARE
				}
		}
	}

}