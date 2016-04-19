package assignment6;

public class SoldOutException extends RuntimeException {

    public SoldOutException(){
        super();
    }

    public SoldOutException(String message){
        super(message);
    }
}
