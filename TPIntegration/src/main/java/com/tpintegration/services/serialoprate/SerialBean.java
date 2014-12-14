package com.tpintegration.services.serialoprate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.comm.CommPortIdentifier;
import javax.comm.NoSuchPortException;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;

public class SerialBean {
	   static String PortName;
	   CommPortIdentifier portId;
	   SerialPort serialPort;
	   static OutputStream out;
	   static InputStream  in;
	  
	   SerialBuffer SB;
	   ReadSerial   RT;
	     /**
	      *
	      * Constructor
	      *
	      * @param PortID the ID of the serial to be used. 1 for COM1,
	      * 2 for COM2, etc.
	      *
	      */
	     public SerialBean(int PortID)
	     {
	       PortName = "COM" + PortID;
	     }
	     /**
	      *
	      * This function initialize the serial port for communication. It startss a
	      * thread which consistently monitors the serial port. Any signal capturred
	      * from the serial port is stored into a buffer area.
	      * @param type 0 do not start the ReadSerial thread 
	      * 			  type 1 start the ReadSerial thread
	      */
	     public int Initialize(int type)
	     {
	       int InitSuccess = 1;
	       int InitFail    = -1;
	     try
	     {
	       portId = CommPortIdentifier.getPortIdentifier(PortName);
	       try
	       {
	         serialPort = (SerialPort)portId.open("Serial_Communication", 2000);
	       } catch (PortInUseException e)
	       {
	    	 System.out.println("The serial port was occupy !!");
	         return InitFail;
	       }
	       //Use InputStream in to read from the serial port, and OutputStream
	       //out to write to the serial port.
	       try
	       {
	         in  = serialPort.getInputStream();
	         out = serialPort.getOutputStream();
	       } catch (IOException e)
	       {
	    	 System.out.println("open stream failed !!");
	         return InitFail;
	       }
	       //Initialize the communication parameters to 9600, 8, 1, none.
	       try
	       {
	          serialPort.setSerialPortParams(9600,
	               SerialPort.DATABITS_8,
	               SerialPort.STOPBITS_1,
	               SerialPort.PARITY_NONE);
	       } catch (UnsupportedCommOperationException e)
	       {
	    	 System.out.println("Initial params failed !!");
	         return InitFail;
	       }
	     } catch (NoSuchPortException e)
	     {
	       return InitFail;
	     }
	     // when successfully open the serial port,  create a new serial buffer,
	     // if type=1 then create a thread that consistently accepts incoming signals from
	     // the serial port. Incoming signals are stored in the serial buffer.
	     
	     if(type==1){
	    	 SB = new SerialBuffer();
	    	 RT = new ReadSerial(SB, in);
		     RT.start();
	     }
	     // return success information
	     return InitSuccess;
	     }
	     /**
	      *
	      * This function returns a string with a certain length from the incomin
	      * messages by ReadSerial thread.
	      *
	      * @param Length The length of the string to be returned.
	      *
	      */
	     public String ReadPort(int Length)
	     {
	       String Msg;
	       Msg = SB.GetMsg(Length);
	       return Msg;
	     }
	     
	     /**
	      * To get Message without start a thread
	      * @return String
	      */
	     public String getMessage(int length){
	 		byte[] readBuffer;
	 		String result = null;
	 		
	 		try {
	 			 readBuffer = new byte[length];
	 			 in.read(readBuffer);
	 			 result =  new String(readBuffer);
	 		} catch (IOException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
	 		
	 		return result.trim();
	 		
	 	}
	     /**
	      *
	      * This function sends a message through the serial port.
	      *
	      * @param Msg The string to be sent.
	      *
	      */
	     public void WritePort(String Msg)
	     {
	       try
	       {
	    	   out.write(Msg.getBytes());
	    	   out.flush();
	       } catch (IOException e)  {}
	     }
	     /**
	      *
	      * This function closes the serial port in use.
	      *
	      */
	     public void ClosePort()
	     {
	    	 
	      if(RT!=null){
	    	  RT.setStopRequest(true);
		      RT.interrupt();
	      }
	      serialPort.close();
	     }
	     
	     public static void main(String[] args){
	    	//TO DO: Add your JAVA codes here
	         SerialBean SB = new SerialBean(3);
	         String initialMsg = "$L0!";
	         String Msg = "$L211111111!";
	         String Msg2 = "$L210101010!";
	         String Msg3 = "$L7!";
	         String Msg4 = "$T1!";
	         SB.Initialize(0);
	         SB.WritePort(Msg4);
	         System.out.println(SB.getMessage(12)); 
//	         System.out.println(SB.getMessage(11)); 
//	         System.out.println(SB.getMessage(11)); 
	         Timer timer  = new Timer();
	         timer.schedule(new TimerTask(){
	        	 public void run(){
	        		 SB.WritePort(Msg4);
	    	         System.out.println(SB.getMessage(12)); 
	    	         
	        	 }
	         }, 0,1000);
	         
	         timer.schedule(new TimerTask(){
	        	 public void run(){
	        		 SB.ClosePort();
	    	         timer.cancel();
	        	 }
	         }, 5000);
	        
//	         SB.ClosePort();         
	     }
}
