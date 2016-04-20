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
	 * Function: singleServerTest()
	 * ----------------------------------
	 * @ Tests a creation of one Server and Client
	 */
	@Test
	public void singleServerTest() {
		try{
			TheaterShow batesRecitalHall = new TheaterShow(); // Create theatre configuration.
			TicketServer.start(16791, batesRecitalHall); // Start TicketServer.
		}
		catch(Exception e){
			fail();
		}
		Random rand = new Random(); // Create new random number generator.
		int numCustomers = rand.nextInt(901) + 100; // Create random number of customers between 100 and 1000.
		Queue<String> lineOne = new LinkedList<String>(); // Make queue to hold customer id's.
		fillQueues(numCustomers, lineOne); // Fill queue with customer id's (0 -> numCustomers - 1)
		Line line1 = new Line("localhost", "T1", lineOne); // Create thread which finds a seat for each customer.
		
		Thread thread = new Thread(line1);
		thread.start(); // Start line1 thread.
		try{
			thread.join();  // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		
		TicketServer.stop(16791); // Shut down TicketServer.
	}

	/*
	 * Function: twoNonConcurrentServerTest()
	 * -------------------------------------------
	 * @ Tests a creation of 2 non-concurrent servers.
	 */
	@Test
	public void twoNonConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16790, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100; // Generate random number of customers between 100 and 1000.
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		// Create two customer queues, each with size (numCustomers / 2)
		fillQueues(numCustomers, lineOne, lineTwo);
	
		// Create line thread for processing seat requests from first half of customers.
		Thread line1 = new Thread(new Line("localhost", "T1", lineOne));
		// Create line thread for processing seat requests from second half of customers.
		Thread line2 = new Thread(new Line("localhost", "T2", lineTwo));
		
		line1.start(); // Begin line1 thread.
		
		try{
			line1.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		
		line2.start(); // Begin line 2 thread after line1 thread has ceased.
		try{
			line2.join();  // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		TicketServer.stop(16790); // Shut down TicketServer.
	}

	
	
	/*
	 * Function: threeNonConcurrentServerTest()
	 * ----------------------------------------
	 * @ Tests the application of three Non Concurrent Servers
	 */
	@Test
	public void threeNonConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16791, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100; // Generate random number of customers between 100 and 1000.
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		// Create three customer queues, each with size (numCustomers / 3)
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
		
		// Create line thread for processing seat requests from first third of customers.
		Thread line1 = new Thread(new Line("localhost", "T1",lineOne));
		// Create line thread for processing seat requests from second third of customers.
		Thread line2 = new Thread(new Line("localhost", "T2",lineTwo));
		// Create line thread for processing seat requests from last third of customers.
		Thread line3 = new Thread(new Line("localhost","T3",lineThree));
		
		line1.start(); // Begin line1 thread.
		
		try{
			line1.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		line2.start(); // Begin line2 thread after line1 finishes processing.
		try{
			line2.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		line3.start(); // Begin line3 thread after line2 finishes processing.
		try{
			line3.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		TicketServer.stop(16791);
	}
	
	
	/*
	 * Function: twoConcurrentServerTest()
	 * ----------------------------------
	 * @ Tests 2 concurrent Servers
	 */
	@Test
	public void twoConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16782, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;  // Generate random number of customers between 100 and 1000.
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		// Create two customer queues, each with size (numCustomers / 2)
		fillQueues(numCustomers, lineOne, lineTwo);
		// Create line thread for processing seat requests from first half of customers.
		Thread t1 = new Thread(new Line("localhost", "T1", lineOne));
		// Create line thread for processing seat requests from second half of customers.
		Thread t2 = new Thread(new Line("localhost", "T2", lineTwo));
		
		t1.start(); // Begin line1 thread.
		t2.start(); // Begin line2 thread.
		try {
			 // Halt each thread when finishes searching for seats for all customers or show sells out.
			t1.join();
			t2.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TicketServer.stop(16782);

	}
	
	
	/*
	 * Function: threeConcurrentServerTest()
	 * ----------------------------------
	 * @ Tests 3 concurrent Servers
	 */
	@Test
	public void threeConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16782, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;  // Generate random number of customers between 100 and 1000.
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		// Create three customer queues, each with size (numCustomers / 3)
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
		// Create line thread for processing seat requests from first third of customers.
		Thread t1 = new Thread(new Line("localhost", "T1", lineOne));
		// Create line thread for processing seat requests from second third of customers.
		Thread t2 = new Thread(new Line("localhost", "T2", lineTwo));
		// Create line thread for processing seat requests from last third of customers.
		Thread t3 = new Thread(new Line("localhost", "T3", lineThree));
		
		
		t1.start(); // Begin line1 thread.
		t2.start(); // Begin line2 thread.
		t3.start(); // Begin line3 thread.
		try {
			 // Halt each thread when finishes searching for seats for all customers or show sells out.
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TicketServer.stop(16782);

	}
	
	/*
	 * Function: singleServerTestManyCustomers()
	 * ----------------------------------
	 * @ Tests a creation of one Server and guaranteed sellout.
	 */
	@Test
	public void singleServerTestManyCustomers() {
		try{
			TheaterShow batesRecitalHall = new TheaterShow(); // Create theatre configuration.
			TicketServer.start(16791, batesRecitalHall); // Start TicketServer.
		}
		catch(Exception e){
			fail();
		}
		Random rand = new Random(); // Create new random number generator.
		int numCustomers = rand.nextInt(1001) + 1000; // Create random number of customers between 1000 and 2000.
		Queue<String> lineOne = new LinkedList<String>(); // Make queue to hold customer id's.
		fillQueues(numCustomers, lineOne); // Fill queue with customer id's (0 -> numCustomers - 1)
		Line line1 = new Line("localhost", "T1", lineOne); // Create thread which finds a seat for each customer.
		
		Thread thread = new Thread(line1);
		thread.start(); // Start line1 thread.
		try{
			thread.join();  // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		
		TicketServer.stop(16791); // Shut down TicketServer.
	}

	/*
	 * Function: threeNonConcurrentServerTestManyCustomers()
	 * ----------------------------------------
	 * @ Tests the application of three Non Concurrent Servers with guaranteed sold-out show.
	 */
	@Test
	public void threeNonConcurrentServerManyCustomers() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16795, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(1001) + 1000; // Generate random number of customers between 1000 and 2000.
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		// Create three customer queues, each with size (numCustomers / 3)
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
		// Create line thread for processing seat requests from first third of customers.
		Thread line1 = new Thread(new Line("localhost", "T1",lineOne));
		// Create line thread for processing seat requests from second third of customers.
		Thread line2 = new Thread(new Line("localhost", "T2",lineTwo));
		// Create line thread for processing seat requests from last third of customers.
		Thread line3 = new Thread(new Line("localhost","T3",lineThree));
		line1.start(); // Begin line1 thread.
		
		try{
			line1.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		line2.start(); // Begin line2 thread once line1 finishes processing.
		try{
			line2.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		line3.start(); // Begin line3 thread once line2 finishes processing.
		try{
			line3.join(); // Halt thread when finishes searching for seats for all customers or show sells out.
		}
		catch(Exception e){
			fail();
		}
		TicketServer.stop(16795);
	}
	
	
	/*
	 * Function: threeConcurrentServersManyCustomers() {
	 * ----------------------------------
	 * @ Tests 3 concurrent Servers with guaranteed sold out show
	 */
	@Test
	public void threeConcurrentServersManyCustomers() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16782, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(1001) + 1000; // Generate random number of customers between 1000 and 2000.
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		// Create three customer queues, each with size (numCustomers / 3)
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
		// Create line thread for processing seat requests from first third of customers.
		Thread t1 = new Thread(new Line("localhost", "T1", lineOne));
		// Create line thread for processing seat requests from second third of customers.
		Thread t2 = new Thread(new Line("localhost", "T2", lineTwo));
		// Create line thread for processing seat requests from last third of customers.
		Thread t3 = new Thread(new Line("localhost", "T3", lineThree));
		
		t1.start(); // Begin line1 thread.
		t2.start(); // Begin line2 thread.
		t3.start(); // Begin line3 thread.
		try {
			 // Halt each thread when finishes searching for seats for all customers or show sells out.
			t1.join();
			t2.join();
			t3.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		TicketServer.stop(16782);
	}

	
	// Fill customer queues for three lines based on the given numCustomers.
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
	
	// Fill customer queues for two lines based on the given numCustomers.
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
	
	// Fill customer queues for one line based on the given numCustomers.
	public void fillQueues(int numCustomers, Queue<String> line1){
		for(int i = 0; i < numCustomers; i++){
			String customer = "customer" + Integer.toString(i);
			line1.add(customer);
		}
	}
}
