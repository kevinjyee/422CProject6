/*
 TestTicketServer.java
 Solves EE422C programming assignment #6
 @author Stefan Bordovsky () Kevin Yee (kjy252)
 @version 1.01 2016-04-013
 */

package assignment6;

import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import org.junit.Test;

public class TestTicketOffice {

	public static int score = 0;
/*
	@Test
	public void basicServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16791, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;
		Queue<String> lineOne = new LinkedList<String>();
		fillQueues(numCustomers, lineOne);
		Line line1 = new Line("localhost", "Single Office");
		Thread thread = new Thread(line1);
		thread.start();
		System.out.println("Stop");
		TicketServer.stop(16791);
	}

	@Test
	public void testServerCachedHardInstance() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16790, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		fillQueues(numCustomers, lineOne, lineTwo);
	
		Thread line1 = new Thread(new Line("localhost", "c1"));
		Thread line2 = new Thread(new Line("localhost", "c2"));
		
		line1.start();
		line2.start();
	}



	@Test
	public void twoNonConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16791, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
//		TicketClient c1 = new TicketClient("localhost", "nonconc1", lineOne);
//		TicketClient c2 = new TicketClient("localhost", "nonconc2", lineTwo);
//		TicketClient c3 = new TicketClient("localhost", "nonconc3", lineThree);
//		c1.requestTicket();
//		c2.requestTicket();
//		c3.requestTicket();
	}
	*/
	@Test
	public void twoConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16792, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
		Thread t1 = new Thread(new Line("localhost", "c1"));
		Thread t2 = new Thread(new Line("localhost", "c2"));
		Thread t3 = new Thread(new Line("localhost", "c3"));
		
		
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void fillQueues(int numCustomers, Queue<String> line1, Queue<String> line2, Queue<String> line3){
		for(int i = 0; i < numCustomers; i++){
			String customer = "customer" + Integer.toString(i);
			if((i % 3) == 0){
				line1.add(customer);
			} else if((i % 3) == 1){
				line2.add(customer);
			} else if((i % 3) == 2){
				line3.add(customer);
			}
		}
	}
	
	public void fillQueues(int numCustomers, Queue<String> line1, Queue<String> line2){
		for(int i = 0; i < numCustomers; i++){
			String customer = "customer" + Integer.toString(i);
			if((i % 2) == 0){
				line1.add(customer);
			} else if((i % 2) == 1){
				line2.add(customer);
			}
		}
	}
	
	public void fillQueues(int numCustomers, Queue<String> line1){
		for(int i = 0; i < numCustomers; i++){
			String customer = "customer" + Integer.toString(i);
			line1.add(customer);
		}
	}
}
