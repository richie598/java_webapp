package com.engineyard.javademo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/")
public class HelloSpringMVC {
 
   @RequestMapping(method = {RequestMethod.GET, RequestMethod.HEAD})
   public String printHello(ModelMap model) {
      model.addAttribute("message", "Hello Spring MVC Framework!");

      return "hello";
   }

}