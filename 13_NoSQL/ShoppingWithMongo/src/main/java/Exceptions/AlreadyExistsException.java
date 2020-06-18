package Exceptions;

public class AlreadyExistsException extends IllegalArgumentException{
    public AlreadyExistsException(String s) {
        super(s);
    }
}
