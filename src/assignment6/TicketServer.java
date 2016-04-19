/*
 TicketServer.java
 Solves EE422C programming assignment #6
 @author Stefan Bordovsky (sb39782) Kevin Yee (kjy252)
 @version 1.01 2016-04-013
 */


package assignment6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketServer {
	static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;
	
	public static TheaterShow theatre = null;
	
	public TicketServer(){
		if(theatre == null){
			theatre = new TheaterShow();
		}
	}
	
	//Out put Booth 1 sold A17
	public static void start(int portNumber, TheaterShow venue) throws IOException {
		PORT = portNumber;
		Runnable serverThread = new ThreadedTicketServer(portNumber, venue);
		Thread t = new Thread(serverThread);
		t.start();
	}
}

class ThreadedTicketServer implements Runnable {

	String hostname = "127.0.0.1";
	String threadname = "X";
	String testcase;
	TicketClient sc;
	
	int port;
	TheaterShow theatre;
	
	
	
	public ThreadedTicketServer(int port, TheaterShow venue){
	
		this.port = port;
		this.theatre = venue;
		
	}
	public void run() {
		// TODO 422C
		ServerSocket serverSocket;
		String inputLine, outputLine;
		try {
			serverSocket = new ServerSocket(TicketServer.PORT);
			Socket clientSocket = serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while ((inputLine = in.readLine()) != null) {
			
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				String[] customerInfo = inputLine.split("\\s");
				
				
				try {
					outputLine = theatre.bestAvailableSeat().toString();
					printTicket(customerInfo[0], customerInfo[1], outputLine);
				}
				catch (SoldOutException e) {
					outputLine = null;
				}
				catch(NullPointerException e){
					outputLine = null;
				}
				
		        if (outputLine.equals(null)){
		            break;
		        }
		        
		    	out.println(outputLine);
		    
		    }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
	
	public void printTicket(String cust, String boxoffice, String seatInfo ){
		System.out.println(cust + " reserved seat " + seatInfo +
				" section from ticket office " + boxoffice);
	}
}