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

	@Test
	public void basicServerTest() {
	try{
	   TheaterShow batesRecitalHall = new TheaterShow();
	   TicketServer.start(16791, batesRecitalHall);
	}
	catch(Exception e){
		fail();
	}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;
		Queue<String> lineOne = new LinkedList<String>();
		fillQueues(numCustomers, lineOne);
		Line line1 = new Line("localhost", "SingleOffice",lineOne);
		Thread thread = new Thread(line1);
		thread.start();
		try{
			thread.join();
		}
		catch(Exception e){
			fail();
		}
		
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
	
		Thread line1 = new Thread(new Line("localhost", "c1",lineOne));
		Thread line2 = new Thread(new Line("localhost", "c2",lineTwo));
		
		line1.start();
		
		try{
			line1.join();
		}
		catch(Exception e){
			fail();
		}
		line2.start();
		try{
			line2.join();
		}
		catch(Exception e){
			fail();
		}
		TicketServer.stop(16790);
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
		Thread line1 = new Thread(new Line("localhost", "nonconc1",lineOne));
		Thread line2 = new Thread(new Line("localhost", "nonconc2",lineTwo));
		Thread line3 = new Thread(new Line("localhost","nonconc3",lineThree));
line1.start();
		
		try{
			line1.join();
		}
		catch(Exception e){
			fail();
		}
		line2.start();
		try{
			line2.join();
		}
		catch(Exception e){
			fail();
		}
		line3.start();
		try{
			line3.join();
		}
		catch(Exception e){
			fail();
		}
		TicketServer.stop(16791);
	}
	
	@Test
	public void twoConcurrentServerTest() {
		try {
			TheaterShow batesRecitalHall = new TheaterShow();
			TicketServer.start(16782, batesRecitalHall);
		} catch (Exception e) {
			fail();
		}
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100;
		Queue<String> lineOne = new LinkedList<String>();
		Queue<String> lineTwo = new LinkedList<String>();
		Queue<String> lineThree = new LinkedList<String>();
		fillQueues(numCustomers, lineOne, lineTwo, lineThree);
		Thread t1 = new Thread(new Line("localhost", "c1",lineOne));
		Thread t2 = new Thread(new Line("localhost", "c2",lineTwo));
		Thread t3 = new Thread(new Line("localhost", "c3",lineThree));
		
		
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
		TicketServer.stop(16782);

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
