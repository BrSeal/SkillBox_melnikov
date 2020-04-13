package Exceptions;

public class NoEnoughMoneyException extends Exception
{
	public NoEnoughMoneyException() {
	}
	
	public NoEnoughMoneyException(String message) {
		super(message);
	}
}
