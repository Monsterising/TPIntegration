package com.tpintegration.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.TooManyListenersException;

import javax.comm.*;

/**
 * Operate the SerialPort(read and send message)
 * @author rising 
 * @since 2014-12-10
 *
 */
public class SerialPortServiceUtil {

	private static Thread readThread;
	
	protected List<String> portList; //serial port
	protected List<String> pPortList;//parallel port 
	protected static CommPortIdentifier portId;
	protected SerialPort  serialPort;
	protected String logMsg;
	
	protected OutputStream  outPutStream = null;
	protected InputStream inPutStream = null;
	
	//Initial serialPort params 
	protected int baudRate;
	protected int data;
	protected int stoped;
	protected int parity;
	protected int follow;
	
	protected SerialPortEventListener  serialPortEventListener;
	
	/*
	 * Initial the serialPortParams
	 */
	public void initial(){
		baudRate = 9600;
		data = 8;
		stoped = 1;
		parity = 0;
		follow = 0;
		try {
			serialPort.setSerialPortParams(baudRate, data, stoped, parity);
		} catch (UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Initial params failed !!");
		}
		
		//open the port of IO stream pipeline
        try {   
        	outPutStream = serialPort.getOutputStream();   
        	inPutStream = serialPort.getInputStream();   
        } catch (IOException e) {  
            e.printStackTrace();
            System.out.println("open stream failed !!");
        }  
        
        //add the eventListen for port
        
        try {
			serialPort.addEventListener(serialPortEventListener);
		} catch (TooManyListenersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("add serialPortEventListener failed !");
		}
        
        serialPort.notifyOnDataAvailable(true);
        
        
        serialPortEventListener = new SerialPortEventListener(){

			@Override
			public void serialEvent(SerialPortEvent e) {
				// TODO Auto-generated method stub
				switch(e.getEventType()){
				case SerialPortEvent.BI:
				case SerialPortEvent.OE:
				case SerialPortEvent.FE:
				case SerialPortEvent.PE:
				case SerialPortEvent.CD:
				case SerialPortEvent.CTS:
				case SerialPortEvent.DSR:
				case SerialPortEvent.RI:
				case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
				{
					//the data is not come
					break;
				}
				case SerialPortEvent.DATA_AVAILABLE:
				{
					//there are some data come
					//do someting here
					System.out.println("data transfered , do something ");
				}
				
				}
			}
        	
        };
        
	}
	
	/*
     * scan all the COM port
     */  
    public List<String> scanPorts() {  
        portList = new ArrayList<String>();  
        pPortList = new ArrayList<String>();
        Enumeration<?> en = CommPortIdentifier.getPortIdentifiers();  
        CommPortIdentifier portId;  
        
        while(en.hasMoreElements()){  
            portId = (CommPortIdentifier) en.nextElement();  
            if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL){  
                String name = portId.getName();  
                if(!portList.contains(name)) {  
                    portList.add(name);  
                }  
            }else if(portId.getPortType()== CommPortIdentifier.PORT_PARALLEL){
            	String name = portId.getName();
            	if(!pPortList.contains(name)){
            		pPortList.add(name);
            	}
            }  
        }  
        
        if(null == portList   
                || portList.isEmpty()) {  
            System.out.println("Did not find the avaliable serial port ,the program stopped !");
        }  
        
        return portList;
    }  
	/*
	 * Function to open the serialPort
	 */
	public String OpenPort(String portName){
		
		try {
			 portId= CommPortIdentifier.getPortIdentifier(portName);
			 serialPort = (SerialPort) portId.open("tpiPorts", 2000);
			 System.out.println("The serial port was opened");
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The serial port was occupy !!");
		}
		initial();
		
		return null;
	}
	
	/*
	 * send message to serialPort
	 */
	public void sendMessage(String msg){
		try {
			outPutStream.write(msg.getBytes());
			outPutStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//notifyAll();
	}
	
	public String getMessage(){
		byte[] readBuffer;
		String result = null;
		
		try {
			 readBuffer = new byte[30];
			 inPutStream.read(readBuffer);
			 result =  new String(readBuffer);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result.trim();
		
	}
	/*
	 * Function to close the serialPort
	 */
	public void  ClosePort(){
		if(serialPort!=null)serialPort.close();
		serialPort = null;
		System.out.println("The serial port was closed !");	
	}
	
	
	//splicing the byte to an array
	public byte[] splicingByte(List<Byte> input){
		byte[] tempByte = new byte[input.size()];
		for(int i=0;i<tempByte.length;i++){
			tempByte[i]=input.get(i);
		}
		return tempByte;
	}
	
	//just for test the available rate
	public void test_rates() throws Exception{
		baudRate = 9600;
		data = 8;
		stoped = 1;
		parity = 0;
		follow = 0;
		int[] rates = {4800,9600,19200,115200,460800,921600};
		 portId= CommPortIdentifier.getPortIdentifier("COM3");
		 serialPort = (SerialPort) portId.open("tpiPorts", 2000);
		 System.out.println("The serial port was opened");
		for(int i=0;i<rates.length;i++){
			System.out.println("NO:"+i);
			baudRate = rates[i];
			serialPort.setSerialPortParams(baudRate, data, stoped, parity);
			outPutStream = serialPort.getOutputStream();   
        	inPutStream = serialPort.getInputStream();
        	sendMessage("Hello");
        	String result = getMessage().trim();
        	System.out.println("NO."+i+":SUCCESS:"+result);
		}
	}
	
	public static void main(String[] args) {
		List<String> ports = new ArrayList<String>();
		
		SerialPortServiceUtil spUtil = new SerialPortServiceUtil();
		String Msg = "$L211111111";
		ports = spUtil.scanPorts();
		for(String port:ports){
			System.out.println(port);
		}
		for(String port:spUtil.pPortList){
			System.out.println(port);
		}
		String pid = spUtil.OpenPort("COM4");
		spUtil.sendMessage(Msg);
//		System.out.println(spUtil.getMessage());
		spUtil.sendMessage(Msg);
		System.out.println(spUtil.getMessage());
		spUtil.ClosePort();
		
	}

}
