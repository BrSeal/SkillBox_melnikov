package Exceptions;

public class BlockedAccountException extends Exception
{
	public BlockedAccountException(){}
public BlockedAccountException(String errMsg){
	super(errMsg);
}
}
