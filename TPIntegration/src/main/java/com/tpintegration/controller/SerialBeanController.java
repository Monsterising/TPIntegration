package com.tpintegration.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SerialBeanController {
	
	@RequestMapping(value="readSP")
	public ModelAndView index(){
		 ModelAndView mv = new ModelAndView("/serialPort/index");
		return mv;
	}
	
	@RequestMapping(value="transmit")
	public @ResponseBody HashMap<String,String> transmit(){
		HashMap<String,String> result = new HashMap<String,String>();
		result.put("result", "hello client");
		return result;
	}
	@RequestMapping(value="transmit2")
	public @ResponseBody String transmit2(){
		String result = "hello client";
		return result;
	}
}
