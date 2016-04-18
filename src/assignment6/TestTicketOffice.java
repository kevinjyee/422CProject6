/*
 TestTicketServer.java
 Solves EE422C programming assignment #6
 @author Stefan Bordovsky () Kevin Yee (kjy252)
 @version 1.01 2016-04-013
 */

package assignment6;

import static org.junit.Assert.fail;

import java.util.Random;

import org.junit.Test;

import assignment6.TheaterShow;
import assignment6.TicketClient;

public class TestTicketOffice {

	public static int score = 0;

	// @Test
	public void basicServerTest() {
		try {
			TicketServer.start(16789);
		} catch (Exception e) {
			fail();
		}
		TheaterShow batesRecitalHall = new TheaterShow();
		Random rand = new Random();
		int numCustomers = rand.nextInt(901) + 100; // Generates random int between 100 and 1000.
		TicketClient client = new TicketClient("localhost", "c_test", numCustomers, batesRecitalHall);
		client.requestTicket();
	}

	@Test
	public void testServerCachedHardInstance() {
		try {
			TicketServer.start(16790);
		} catch (Exception e) {
			fail();
		}
		TheaterShow batesRecitalHall = new TheaterShow();
		Random rand = new Random();
		int numCustomers1 = rand.nextInt(451) + 50; // Generates random int between 50 and 500.
		int numCustomers2 = rand.nextInt(451) + 50;
		TicketClient client1 = new TicketClient("localhost", "c1", numCustomers1, batesRecitalHall);
		TicketClient client2 = new TicketClient("localhost", "c2", numCustomers2, batesRecitalHall);
		client1.requestTicket();
		client2.requestTicket();
		
	}

	@Test
	public void twoNonConcurrentServerTest() {
		try {
			TicketServer.start(16791);
		} catch (Exception e) {
			fail();
		}
		TicketClient c1 = new TicketClient("nonconc1");
		TicketClient c2 = new TicketClient("nonconc2");
		TicketClient c3 = new TicketClient("nonconc3");
		c1.requestTicket();
		c2.requestTicket();
		c3.requestTicket();
	}

	@Test
	public void twoConcurrentServerTest() {
		try {
			TicketServer.start(16792);
		} catch (Exception e) {
			fail();
		}
		TheaterShow batesRecitalHall = new TheaterShow();
		Random rand = new Random();
		int numCustomers1 = rand.nextInt(4501) + 50; // Generates random int between 50 and 500.
		int numCustomers2 = rand.nextInt(4501) + 50;
		int numCustomers3 = rand.nextInt(4501) + 50;
		TicketClient c1 = new TicketClient("localhost", "nonconc1", numCustomers1, batesRecitalHall);
		TicketClient c2 = new TicketClient("localhost", "nonconc2", numCustomers2, batesRecitalHall);
		TicketClient c3 = new TicketClient("localhost", "nonconc3", numCustomers3, batesRecitalHall);
		Thread t1 = new Thread() {
			public void run() {
				c1.requestTicket();
			}
		};
		Thread t2 = new Thread() {
			public void run() {
				c2.requestTicket();
			}
		};
		Thread t3 = new Thread() {
			public void run() {
				c3.requestTicket();
			}
		};
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
}
