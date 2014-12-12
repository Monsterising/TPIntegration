package com.tpintegration.services.serialoprate;

import java.io.IOException;
import java.io.InputStream;

/**
*
* This class reads message from the specific serial port and save
* the message to the serial buffer.
* @author rising
* @since 2014-12-11
*
*/
public class ReadSerial extends Thread{
	   private SerialBuffer ComBuffer;
	   private InputStream ComPort;
	   
	   private volatile boolean stopRequested;
	   private Thread runThread;
	     /**
	      *
	      * Constructor
	      *
	      * @param SB The buffer to save the incoming messages.
	      * @param Port The InputStream from the specific serial port.
	      *
	      */
	   public ReadSerial(SerialBuffer SB, InputStream Port)
	   {
	     ComBuffer = SB;
	     ComPort = Port;
	   }
	   public void run()
	   {
	     int c;
	     runThread = Thread.currentThread();
	     stopRequested = false;
	     try
	     {
	       while ( !stopRequested)
	       {
	         c = ComPort.read();
	         ComBuffer.PutChar(c);
	       }
	     } catch (IOException e) {
	    	 Thread.currentThread().interrupt();
	     }
	   }
	   
	   public boolean getStopRequest(){
		   return this.stopRequested;
	   }
	   
	   public void setStopRequest( boolean flag){
		   this.stopRequested = flag;
	   }
}
