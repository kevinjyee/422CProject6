/*
 TicketClient.java
 Solves EE422C programming assignment #6
 @author Stefan Bordovsky (sb39782) Kevin Yee (kjy252)
 @version 1.01 2016-04-013
 */

package assignment6;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import assignment6.ThreadedTicketClient;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc; // Each ThreadedTicketClient associated with a unique TicketClient.
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run() {
		System.out.flush();
		try {
			Socket echoSocket = new Socket(hostname, TicketServer.PORT); // Connect to TicketServer.
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			// Want to read seat assignment output from TicketServer.
			sc.result = in.readLine(); // Save seat assignment in TicketClient's result variable.
			echoSocket.close(); // Shut socket connection to TicketServer.			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

public class TicketClient {
	ThreadedTicketClient tc; // ThreadedTicketClient associated with the current TicketClient.
	String result = "dummy"; // Will hold seat assignment information from TicketServer operations.
	String hostName = ""; // TicketClient host name.
	String threadName = ""; // TicketClient thread name.
	Queue<String> customers; // Queue of customers.

	TicketClient(String hostname, String threadname) {
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
		
	}

	TicketClient(String name) {
		this("localhost", name);
	}

	TicketClient() {
		this("localhost", "unnamed client");
	}


	void requestTicket() {
		// TODO thread.run()
		try{
			tc.run(); // Run ThreadedTicketClient operation to find a seat for a given customer.
		} catch (SoldOutException e){ // If sold out, print appropriate message.
			System.out.println("The Bates Recital Hall has sold out of tickets. Ticket office now closing.");
			return;
		} catch(Exception e){
			System.out.println("Something went wrong...");
		}
	}

	void sleep() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}