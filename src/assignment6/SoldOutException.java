package assignment6;

public class SoldOutException extends RuntimeException {

	// Throw this exception when a TheaterShow is out of vacant seats.
    public SoldOutException(){
        super();
    }

    public SoldOutException(String message){
        super(message);
    }
}
