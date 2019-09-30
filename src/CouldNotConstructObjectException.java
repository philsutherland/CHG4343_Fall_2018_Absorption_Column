public class CouldNotConstructObjectException extends RuntimeException 
{
  
  public CouldNotConstructObjectException()
  {
    super("Critical Error: Could not construct object!"); 
  }
  
  public CouldNotConstructObjectException(String message) 
  { 
    super("Critical Error: " + message + " object could not be constructed!");
    
  }
  
} // End of RangeLimitException class