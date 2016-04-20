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
	private Lock changeLock = new ReentrantLock();

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run() {
		System.out.flush();
		try {
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			//BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			sc.result = in.readLine();
			echoSocket.close();
			
			
			
			
			
					
		}
	 catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	public void printTicketSeat(Seat nextSeat, String cust){
		System.out.println(cust + " reserved seat " + nextSeat.getRow() + nextSeat.getNumber() + " in " + nextSeat.getSection() +
				" section from ticket office " + threadname);
	}
	
}

public class TicketClient {
	ThreadedTicketClient tc;
	String result = "dummy";
	String hostName = "";
	String threadName = "";
	Queue<String> customers;

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
			tc.run();
		} catch (SoldOutException e){
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