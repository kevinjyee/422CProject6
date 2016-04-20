/*
 Seat.java
 Solves EE422C programming assignment #6
 @author Stefan Bordovsky (sb39782) Kevin Yee (kjy252)
 @version 1.01 2016-04-013
 */

package assignment6;

public class Seat {
	
	/* Seats can be either in left, middle, or right columns in front of the theatre stage. */
	public enum Section {Middle_Section, House_Left, House_Right}

	/* Section (left, middle, right) that a seat is in. */
	protected Section section;
	
	/*Designates the row that this seat is in*/
	protected String row;
	
	/*Designates an individual seat's numbering within a given row.*/
	protected int seatNumber;
	
	
	public Seat(Section section, String row, int number){
		this.section = section;
		this.row = row;
		this.seatNumber = number;
	}

	// Return string representation of a seat, including row, seat number, and section.
	public String toString(){
		String sec;
		if(section == Section.House_Right){
			sec = "House Right";
		} else if(section == Section.Middle_Section){
			sec = "Middle Section";
		} else{
			sec = "House Left";
		}
		return "" + row  + seatNumber + " in " + sec;
	}
	
	public int getNumber(){
		return seatNumber;
	}
	

	public String getRow(){
		return row;
	}

	public Section getSection(){
		return section;
	}
}

