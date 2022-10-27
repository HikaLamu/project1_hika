package exceptions;

public class InvalidUserIdException extends Exception{
    public InvalidUserIdException(String msg){
        super(msg);
    }
}
