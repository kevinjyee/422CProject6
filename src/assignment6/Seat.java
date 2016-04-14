package assignment6;

public class Seat {

	public enum Section { HouseLeft, HouseMiddle, HouseRight }
	
	protected String validSection = "AA[A-Z]";
	/*Designates the section*/
	
	protected Section section;
	
	/*Designates the row that this seat is in*/
	protected int row;
	
	/*Designates the row number*/
	protected int number;
	
	
	
	public Seat(Section section, int row, int number){
		this.section = section;
		this.row = row;
		this.number = number;
	}

	
	public int getNumber(){
		return number;
	}
	

	public int getRow(){
		return row;
	}

	public Section getSection(){
		return section;
	}
}

