package com.tpintegration.controller;

import java.util.Timer;
import java.util.TimerTask;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tpintegration.services.serialoprate.SerialBean;

@Controller
public class MakeController {
	
	String temp;
	@RequestMapping(value="make")
	public ModelAndView index(){
		 ModelAndView mv = new ModelAndView("/make/index");
		return mv;
	}
	
	@RequestMapping(value="bt_com1")
	@ResponseBody 
	public String bt_com1(){
		 SerialBean SB = new SerialBean(5);
		 String result = null;
         String initialMsg = "$L0!";
         String Msg = "$L211111111!";
         String Msg2 = "$L210101010!";
         String Msg3 = "$L7!";
         String Msg4 = "$T1!";
         
         
         SB.Initialize(0);
         SB.WritePort(Msg4);
         result = SB.getMessage(12);
         SB.ClosePort();
//         System.out.println(SB.getMessage(12)); 
//         System.out.println(SB.getMessage(11)); 
//         System.out.println(SB.getMessage(11)); 
//         Timer timer  = new Timer();
//         timer.schedule(new TimerTask(){
//        	 public void run(){
//        		 SB.WritePort(Msg4);
//    	         System.out.println(SB.getMessage(12)); 
//    	         
//        	 }
//         }, 0,1000);
//         
//         timer.schedule(new TimerTask(){
//        	 public void run(){
//        		 SB.ClosePort();
//    	         timer.cancel();
//        	 }
//         }, 5000);
		return result ;
	}
	
	//open native app
	@RequestMapping(value="bt_com2")
	public String bt_com2(){
		Runtime rt = Runtime.getRuntime();
		         Process p = null;
		         String fileLac = "";
		         try {
		            // fileLac = "D:\\Program Files (x86)\\Notepad++\\notepad++.exe";
		        	 fileLac = "D:\\Program Files (x86)\\Youdao\\YoudaoNote\\YNotepad.exe";
		             p = rt.exec(fileLac);
		         } catch (Exception e) {
		             System.out.println("open failure");
		         }
		return null;
	}
	 
}
