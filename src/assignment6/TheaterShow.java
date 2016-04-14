package assignment6;

import java.util.*;

import assignment6.Seat.Section;
public class TheaterShow {
	
	/*
	 * Queue of seats at the theater
	 */
	
	protected Queue<Seat> theatreSeats;
	
	public final ArrayList<String> rowArray = new ArrayList<String>();
	
	
	
	private void initRows(){
		
		for(int i =0; i<28; i++){
			
			char character = 'A';
			rowArray.add(character++ + "");
			
		}
		
		
	}
	
	private void initSeats(){
		
		theatreSeats = new LinkedList<Seat>();
		
		
		/*Change this method later to make a more accurate representation of BATES HALL*/
		
		for(int seatNumber = 101; seatNumber <= 128; seatNumber++){
			theatreSeats.add(new Seat(Section.HouseLeft, "A", seatNumber));
		}
		 	
	}

}
