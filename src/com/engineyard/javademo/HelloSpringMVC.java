package com.engineyard.javademo;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

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

	private void getMessage(String string) {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		try {
		    message = (String)jdbcTemplate.queryForObject(
	    		"select message from javademo where id = '" + string + "'", 
	    		String.class);
		} catch (DataAccessException e) {
			message = "Query failed: " + e;
		}
	}
}