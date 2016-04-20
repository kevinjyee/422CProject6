package assignment6;

import java.util.*;
public class Line implements Runnable {

	
	private TicketClient client; // Each line needs its own TicketClient to handle ticket requests.
	private String custName; // Holds name of current customer at head of line.
	private String officeName; // Name of the ticket office processing a given customer queue.
	private Queue<String> custLine; // Customer queue.
	public Line(String hostname, String threadname, Queue<String> Line){
		
		client = new TicketClient(hostname, threadname); // Create ticket client for each line.
		officeName = threadname; // Assign office name.
		custLine = Line; // Assigns customer queue.
	}
	
	
	public void run(){
		if(custLine.isEmpty()){
			System.out.println("Ticket office " + officeName + " has no customers in line.");
		}
		while(!custLine.isEmpty()){		// When there are still customers,
			custName = custLine.poll(); // 		find the next customer in line,
			client.requestTicket();		//		look for a ticket for said customer.
		
			if(client.result.equals("null")){
				System.out.println(custName + "  did not get a ticket from office " + officeName);
			}
			else{
				System.out.println(custName + " has reserved seat " + client.result + " from ticket office " 
						+ officeName + "."); // print ticket information if seat found.
			}
			
			
				
			
		}
		
		
	}
}
