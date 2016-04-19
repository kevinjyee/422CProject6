package assignment6;

public class Line implements Runnable {

	
	private int customersLeftInLine;
	private TicketClient client;
	public static String custName;
	
	public Line(String hostname, String threadname){
		customersLeftInLine = ((int)(Math.random()*900) + 100);
		client = new TicketClient(hostname, threadname);
	}
	
	
	public void run(){
		
		int custNo = 1;
		while(customersLeftInLine > 0){
			custName = "Customer" + custNo;
			client.requestTicket();
			customersLeftInLine--; //customer leaves b/c he is satisfied
		}
		
		
	}
}
