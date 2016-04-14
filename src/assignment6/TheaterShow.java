package assignment6;

import java.util.*;

import assignment6.Seat.Section;
public class TheaterShow {
	
	protected Queue<Seat> theatreSeats;
	
	protected int seatsPerSection = 729; // 27 rows * 27 seats per row
	protected int numberSections = 6; // 6 total sections (front middle, front left, front right, back middle, back left, back right)
	
	protected int maxRows = 27; //total Rows
	protected int maxSeatsperRow = 27; //total Seats per Row
	
	public int theatreCapacity = 0; //total theatreCapacity
	
	public final ArrayList<String> rowArray = new ArrayList<String>();
	
	
	private void initRows()
	{
		for(int i =0; i<27; i++){
			char character = 'A';
			rowArray.add(character++ + "");
		}
		
	}
	
	private void initSeats(){
		
		initRows();
		
		theatreSeats = new LinkedList<Seat>();
		
		ArrayList<Seat> frontMiddle = new ArrayList<Seat>();
		ArrayList<Seat> frontLeft = new ArrayList<Seat>();
		ArrayList<Seat> frontRight = new ArrayList<Seat>();

		ArrayList<Seat> backMiddle = new ArrayList<Seat>();
		ArrayList<Seat> backLeft = new ArrayList<Seat>();
		ArrayList<Seat> backRight = new ArrayList<Seat>();
		
		
		for(int i =0; i < seatsPerSection; i++)
		{
			frontMiddle.add(new Seat(Section.FrontMiddle, rowArray.get(i%maxRows), i%27));
			frontLeft.add(new Seat(Section.FrontLeft,rowArray.get(i%maxRows),i%27));
			frontRight.add(new Seat(Section.FrontRight,rowArray.get(i%maxRows),i%27));	
			backMiddle.add(new Seat(Section.BackMiddle,rowArray.get(i%maxRows),i%27));	
			backLeft.add(new Seat(Section.BackLeft,rowArray.get(i%maxRows),i%27));
			backRight.add(new Seat(Section.BackRight,rowArray.get(i%maxRows),i%27));	
		}
		
		
			theatreSeats.addAll(frontMiddle);
			theatreSeats.addAll(frontLeft);
			theatreSeats.addAll(frontRight);
			theatreSeats.addAll(backMiddle);
			theatreSeats.addAll(backLeft);
			theatreSeats.addAll(backRight);
			
			theatreCapacity = theatreSeats.size();
	}

}
