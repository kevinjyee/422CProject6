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

import assignment6.ThreadedTicketClient;

class ThreadedTicketClient implements Runnable {
	String hostname = "127.0.0.1";
	String threadname = "X";
	TicketClient sc;

	public ThreadedTicketClient(TicketClient sc, String hostname, String threadname) {
		this.sc = sc;
		this.hostname = hostname;
		this.threadname = threadname;
	}

	public void run() {
		System.out.flush();
		try {
			Socket echoSocket = new Socket(hostname, TicketServer.PORT);
			// PrintWriter out =
			PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
			BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
			
			while(!sc.customers.isEmpty()){
				try{
					String cust = sc.customers.poll(); // Find next customer
					Seat nextSeat = sc.venue.bestAvailableSeat(); // Reserve a seat for the next customer.
					if(nextSeat != null){
						printTicketSeat(nextSeat, cust);
					}
				} catch(SoldOutException e){
					echoSocket.close();
					throw new SoldOutException();
				}
			}
			echoSocket.close();
		} catch(SoldOutException e){
			throw new SoldOutException();
		} catch (Exception e) {
			//e.printStackTrace();
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

	TicketClient(String hostname, String threadname, Queue<String> line) {
		tc = new ThreadedTicketClient(this, hostname, threadname);
		hostName = hostname;
		threadName = threadname;
		customers = line;		
	}

	TicketClient(String name) {
		this("localhost", name, null);
	}

	TicketClient() {
		this("localhost", "unnamed client", null);
	}


	void requestTicket() {
		// TODO thread.run()
		try{
			tc.run();
		} catch (SoldOutException e){
			System.out.println("The Bates Recital Hall has sold out of tickets. Ticket office " + threadName + " now closing.");
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