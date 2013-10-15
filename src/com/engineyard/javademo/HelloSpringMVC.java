package com.engineyard.javademo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

@Controller
@RequestMapping("/")
public class HelloSpringMVC {
 
	@Autowired DataSource ds;
	private String message = null;

    public void setDataSource(DataSource dataSource){
        this.ds = dataSource;
    }
 	
   @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
   public String printHello(ModelMap model) {
		getMessage("spf");
   	  	model.addAttribute("message", message);
      	return "hello";
    }

	private void getMessage(final String idString) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);

		try {
		    message = (String)jdbcTemplate.query( 
		    	"select message from javademo where ID=?",
		    	new Object[] { idString },
		    	new ResultSetExtractor<String>() {
				    public String extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				        if (resultSet.next()) {
				          return resultSet.getString(1);
				        }
				        return null;
      				}
      			}		
      		);

		} catch (DataAccessException e) {
			message = "Query failed: " + e;
		}
	}
}