package assignment6;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import assignment6.Seat.Section;
public class TheaterShow {
	
	public static Queue<Seat> theatreSeats;
	
	protected static int seatsPerSection = 338; // 27 rows * 27 seats per row
	protected static int numberSections = 3; // 6 total sections (front middle, front left, front right, back middle, back left, back right)
	
	protected static int maxRows = 26; //total Rows
	protected static int maxSeatsperRow = 13; //total Seats per Row
	
	public static int theatreCapacity = 0; //total theatreCapacity
	
	public static final ArrayList<String> rowArray = new ArrayList<String>(); // Make an array with all characters of the alphabet.
	
	private Lock changeLock = new ReentrantLock(); // Lock seat resources during access period so no two tickets sold for same seat.
	
	
	public TheaterShow(){
		initSeats(); // Set up theatre configuration according to Bates Recital Hall standards.
	}
	
	/* Create array of all alphabet characters. Will use for setting row letters. */
	public static void initRows()
	{
		char character = 'A';
		for(int i =0; i<26; i++){
			rowArray.add(character++ + "");
		}
		
	}
	
	public static void initSeats(){
		initRows(); // Create array of all alpha chars.
		theatreSeats = new LinkedList<Seat>(); // theatreSeats is a queue of seats.
		// Order of queue: Row A, middle section -> Row A, left section -> Row A, right section -> Row B, middle section, and so on.
		
		for(int j=0; j < 26; j++){
			for(int k = 108; k < 122; k++){
				theatreSeats.add(new Seat(Section.Middle_Section, rowArray.get(j), k)); // Add Row j middle section seats to queue.
			}
			for(int k = 122; k < 129; k++){
				theatreSeats.add(new Seat(Section.House_Left,rowArray.get(j),k)); // Add Row j left wing seats to queue.
			}
			for(int k = 101; k < 108; k++){
				theatreSeats.add(new Seat(Section.House_Right,rowArray.get(j),k)); // Add Row j right wing seats to queue.
			}
		}
	}
	
			
			
			
		
		
		
		
	
	
	public Seat bestAvailableSeat(){
		changeLock.lock(); // Lock this method so no two threads try to poll the same seat. 
		Seat bestSeat = null;
		try{
		
			if(theatreSeats.isEmpty()){
				throw new SoldOutException();
			} else{
				bestSeat = theatreSeats.poll();
				//printTicket(bestSeat);
			}
		} finally{
			changeLock.unlock();
		}
		return bestSeat;
		
	}

}
