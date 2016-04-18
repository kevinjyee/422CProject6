package assignment6;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import assignment6.Seat.Section;
public class TheaterShow {
	
	public static Queue<Seat> theatreSeats;
	
	protected static int seatsPerSection = 676; // 27 rows * 27 seats per row
	protected static int numberSections = 6; // 6 total sections (front middle, front left, front right, back middle, back left, back right)
	
	protected static int maxRows = 26; //total Rows
	protected static int maxSeatsperRow = 26; //total Seats per Row
	
	public static int theatreCapacity = 0; //total theatreCapacity
	
	public static final ArrayList<String> rowArray = new ArrayList<String>();
	
	private Lock changeLock = new ReentrantLock();
	
	
	public TheaterShow(){
		initSeats();
	}
	
	public static void initRows()
	{
		char character = 'A';
		for(int i =0; i<26; i++){
			rowArray.add(character++ + "");
		}
		
	}
	
	public static void initSeats(){
		
		initRows();
		
		theatreSeats = new LinkedList<Seat>();
		
		ArrayList<Seat> frontMiddle = new ArrayList<Seat>();
		ArrayList<Seat> frontLeft = new ArrayList<Seat>();
		ArrayList<Seat> frontRight = new ArrayList<Seat>();

		ArrayList<Seat> backMiddle = new ArrayList<Seat>();
		ArrayList<Seat> backLeft = new ArrayList<Seat>();
		ArrayList<Seat> backRight = new ArrayList<Seat>();
		
		
		for(int j=0; j < 26; j++){
			for(int i =0; i < 26; i++){
				frontMiddle.add(new Seat(Section.FrontMiddle, rowArray.get(j), i));
				frontLeft.add(new Seat(Section.FrontLeft,rowArray.get(j),i));
				frontRight.add(new Seat(Section.FrontRight,rowArray.get(j),i));	
				backMiddle.add(new Seat(Section.BackMiddle,rowArray.get(j),i));	
				backLeft.add(new Seat(Section.BackLeft,rowArray.get(j),i));
				backRight.add(new Seat(Section.BackRight,rowArray.get(j),i));	
			}
		}
		theatreSeats.addAll(frontMiddle);
		theatreSeats.addAll(frontLeft);
		theatreSeats.addAll(frontRight);
		theatreSeats.addAll(backMiddle);
		theatreSeats.addAll(backLeft);
		theatreSeats.addAll(backRight);
		
		theatreCapacity = theatreSeats.size();
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

	/*For Debugging Purposes* 
	 * Uncomment to test initSeats
//	 */
	/*
	public static void main(String args[]){
		
		TestTicketOffice test = new TestTicketOffice();
		test.basicServerTest();
	}
	*/
}
