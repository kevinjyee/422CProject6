/*
 Seat.java
 Solves EE422C programming assignment #6
 @author Stefan Bordovsky (sb39782) Kevin Yee (kjy252)
 @version 1.01 2016-04-013
 */

package assignment6;

public class Seat {

	public enum Section {Middle_Section, Left_Wing, Right_Wing}
	
	protected String validSection = "[A-Z]";

	
	protected Section section;
	
	/*Designates the row that this seat is in*/
	protected String row;
	
	/*Designates the row number*/
	protected int seatNumber;
	
	
	public Seat(Section section, String row, int number){
		this.section = section;
		this.row = row;
		this.seatNumber = number;
	}

	public String toString(){
		
		return "" + row + "," + seatNumber + "in " + section ;
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

