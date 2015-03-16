package com.tpintegration.util;
import java.io.*;
import java.net.*;
public class SocketUtil {
	Socket socket;
	PrintWriter os;
	BufferedReader is;
	public void openSocket(){
		System.out.println("socket Test");
		try {
		
			 if(socket==null){
				 socket=new Socket("192.168.16.254",8543); 
			 }
			 if(os==null){
				 os = new PrintWriter(socket.getOutputStream());
			 }
			 if(is==null){
				 is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 }
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeSocket(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doC1(String var1,String var2){
		try {
			openSocket();
			String msg = "$C1 2 "+var1+" "+var2+"#";
			System.out.println(msg);
			os.println(msg);
			os.flush();
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doC2(String var1){
		try {
			openSocket();
			String msg = "$C2 1 "+var1+"#";
			System.out.println(msg);
			os.println(msg);
			os.flush();
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doC3(String var1){
		try {
			openSocket();
			String msg = "$C3 1 "+var1+"#";
			System.out.println(msg);
			os.println(msg);
			os.flush();
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doC4(String var1){
		try {
			openSocket();
			String msg = "$C4 1 "+var1+"#";
			System.out.println(msg);
			os.println(msg);
			os.flush();
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doC5(String var1){
		try {
			openSocket();
			String msg = "$C5 1 "+var1+"#";
			System.out.println(msg);
			os.println(msg);
			os.flush();
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doC6(String var1){
		try {
			String[] prams = var1.split(" ");
			openSocket();
			String msg = "$C6 2 "+prams[0]+" "+prams[1]+"#";
			System.out.println(msg);
			os.println(msg);
			os.flush();
			System.out.println(is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket socket=new Socket("192.168.16.254",8543);
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			os.println("$C1 2 500 500#");
			os.flush();
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			System.out.println(is.readLine());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
