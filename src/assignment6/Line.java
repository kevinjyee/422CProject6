package assignment6;

import java.util.*;
public class Line implements Runnable {

	
	private TicketClient client;
	public static String custName;
	String name;
	private Queue<String> custLine;
	public Line(String hostname, String threadname, Queue<String> Line){
		
		client = new TicketClient(hostname, threadname);
		name = threadname;
		custLine = Line;
	}
	
	
	public void run(){
		
		while(!custLine.isEmpty()){
			String cust = custLine.poll();
			custName = cust;
			client.requestTicket();
		
			if(client.result.equals("null")){
				System.out.println(custName + "  did not get a ticket from office " + name);
			}
			else{
				System.out.println(cust + " " + client.result + " from " + name);
			}
			
			
				
			
		}
		
		
	}
}
