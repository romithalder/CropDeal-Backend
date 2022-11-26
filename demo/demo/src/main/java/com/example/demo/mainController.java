package com.example.demo;

import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class mainController {
	
	@RequestMapping("Login")
	public ModelAndView Verify(@RequestParam("username") String usn,@RequestParam("password") String pass) {
		
		boolean flag=false;
		String vpass=usn.substring(0, 3)+"@123";
		HashMap<String, String> log=new HashMap<String, String>();
		log.put("Romit Halder", "Roh@123");
		log.put("Abhinav Akash", "abhi@123");
		log.put("Nandish","Nan@123");
		ModelAndView mav=new ModelAndView();
		
		for(Entry<String, String> e : log.entrySet()) {
			if(usn.equals(e.getKey()) && pass.equals(e.getValue())) {
				mav.setViewName("Display.html");
				mav.addObject("result", usn);
				flag=true;
			}
		}
		
		
			if(pass.equalsIgnoreCase(vpass)) {
				mav.setViewName("Display.html");
				mav.addObject("result", usn);
				flag=true;
			}
			
		if(flag==false) {
			mav.setViewName("sorry.html");
		}
		
		return mav;
	}

}
