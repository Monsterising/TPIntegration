package com.tpintegration.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tpintegration.util.SocketUtil;


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
	@RequestMapping(value="doC1/{var1}/{var2}",method=RequestMethod.GET)
	public @ResponseBody String doC1(@PathVariable String var1,@PathVariable String var2){
		SocketUtil so = new SocketUtil();
		so.doC1(var1,var2);
		return "success";
	}
	@RequestMapping(value="doC2/{var1}",method=RequestMethod.GET)
	public @ResponseBody String doC2(@PathVariable String var1){
		SocketUtil so = new SocketUtil();
		so.doC2(var1);
		return "success";
	}
	@RequestMapping(value="doC3/{var1}",method=RequestMethod.GET)
	public @ResponseBody String doC3(@PathVariable String var1){
		SocketUtil so = new SocketUtil();
		so.doC3(var1);
		return "success";
	}
	@RequestMapping(value="doC4/{var1}",method=RequestMethod.GET)
	public @ResponseBody String doC4(@PathVariable String var1){
		SocketUtil so = new SocketUtil();
		so.doC4(var1);
		return "success";
	}
	@RequestMapping(value="doC5/{var1}",method=RequestMethod.GET)
	public @ResponseBody String doC5(@PathVariable String var1){
		SocketUtil so = new SocketUtil();
		so.doC5(var1);
		return "success";
	}
	@RequestMapping(value="doC6/{var1}",method=RequestMethod.GET)
	public @ResponseBody String doC6(@PathVariable String var1){
		SocketUtil so = new SocketUtil();
		so.doC6(var1);
		return "success";
	}
	
}
