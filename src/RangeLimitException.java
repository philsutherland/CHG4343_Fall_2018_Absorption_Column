public class RangeLimitException extends Exception 
{
  
  public RangeLimitException()
  {
    super("Critical Error: Value is out of acceptable range!"); 
  }
  
  public RangeLimitException(String message) 
  { 
    super("Critical Error: " + message + " is out of acceptable range!");
    
  }
  
} // End of RangeLimitException class