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
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketServer {
	static int PORT = 2222;
	// EE422C: no matter how many concurrent requests you get,
	// do not have more than three servers running concurrently
	final static int MAXPARALLELTHREADS = 3;
	
	public static TheaterShow theatre = null; 	// Will hold theater configuration and vacancy.
	private static HashMap<Integer, Thread> threadMap = new HashMap<Integer, Thread>(); // Keeps track of open threads.
	
	public TicketServer(){
		if(theatre == null){
			theatre = new TheaterShow(); // Instantiate theatre configuration using Bates Recital Hall model.
		}
	}
	
	// Start server thread given theater configuration and portnum.
	public static void start(int portNumber,  TheaterShow venue) throws IOException {
		PORT = portNumber; // Assign portnum to TicketServer.
		Runnable serverThread = new ThreadedTicketServer(portNumber, venue); // Create ThreadedTicketServer to handle seat requests.
		Thread t = new Thread(serverThread); // Create thread for ThreadedTicketServer.
		threadMap.put(portNumber, t); // Add server thread to thread map, organizing by portnum.
		
		/*
		Thread.UncaughtExceptionHandler h = new Thread.UncaughtExceptionHandler() {
		    public void uncaughtException(Thread th, Throwable ex) {
		        t.interrupt();
		        System.out.println("WE DID IT REDDIT");
		    }
		};
		t.setUncaughtExceptionHandler(h);
		t.setDaemon(true);
		*/
		t.start(); // Start TicketServer thread.
	}
	
public static void stop(int portNumber) {
		// Halt a given server thread.
		Thread thread = threadMap.get(portNumber); // Threads keyed on portnumber.
		if(thread == null){return;}
			thread.interrupt(); // Interrupt selected thread, lest it run wild.
			System.out.println("System gracefully stopped");
		}
	
}

class ThreadedTicketServer implements Runnable {

	protected String hostname = "127.0.0.1"; // Holds hostname of ticket server.
	protected String threadname = "X"; // Hold threadname of ticket server.
	protected TicketClient sc; // Associated ticketclient for given server.
	
	protected int port; // Port number for ticket server.
	protected TheaterShow theatre; // Hold theatre configuration.
	
	
	public ThreadedTicketServer(int port, TheaterShow venue){
	
		this.port = port; // Assign port no.
		this.theatre = venue; // Assign theatre configuration.
		
	}
	
	
	public void run() {
		// TODO 422C
		ServerSocket serverSocket;
		String outputLine = "";
		try {
			serverSocket = new ServerSocket(TicketServer.PORT); // Open up server socket to let ThreadedTicketClient connect.
			while (true) {
				Socket clientSocket = serverSocket.accept(); // Accept client connection.
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // Open up print output.
				try {
					outputLine = theatre.bestAvailableSeat().toString(); // Output seat information to requesting ThrTickClient.
				}
				catch(SoldOutException e){ // Shut down TicketServer when out of seats.
					System.out.println("The Bates Recital Hall has sold out of tickets. Ticket offices now closing.");
					TicketServer.stop(this.port);
					//throw new SoldOutException();
					System.exit(0);
				}
				catch(NullPointerException e){
					outputLine = "null";
				}
		    	out.println(outputLine); // Output result of seat request to requesting Threaded Ticket Client.
		    
		    }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
}