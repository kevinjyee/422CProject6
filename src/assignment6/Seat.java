package assignment6;

public class Seat {

	public enum Section { FrontMiddle, FrontLeft, FrontRight, BackMiddle, BackLeft, BackRight }
	
	protected String validSection = "[A-Z]";

	
	protected Section section;
	
	/*Designates the row that this seat is in*/
	protected String row;
	
	/*Designates the row number*/
	protected int number;
	
	
	public Seat(Section section, String row, int number){
		this.section = section;
		this.row = row;
		this.number = number;
	}

	
	public int getNumber(){
		return number;
	}
	

	public String getRow(){
		return row;
	}

	public Section getSection(){
		return section;
	}
}

