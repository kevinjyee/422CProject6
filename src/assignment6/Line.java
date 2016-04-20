package assignment6;

import java.util.*;
public class Line implements Runnable {

	
	private TicketClient client;
	public static String custName;
	String name;
	private Queue<String> custLine;
	public Line(String hostname, String threadname, Queue<String> Line){
		
		custLine = Line;
		client = new TicketClient(hostname, threadname);
		name = threadname;
	}
	
	
	public void run(){
		
		while(!custLine.isEmpty()){
			custName = custLine.poll();
			client.requestTicket();
		
			if(client.result.equals("null")){
				System.out.println(custName + "  did not get a ticket from office " + name);
			}
			else{
				System.out.println(custName + " " + client.result + " from " + name);
			}
			
			
				
			
		}
		
		
	}
}
