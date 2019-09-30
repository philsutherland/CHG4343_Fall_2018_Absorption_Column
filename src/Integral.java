public interface Integral
{
  
  public double[] numericalIntegral(double x);
  
  public Integral clone() throws CouldNotConstructObjectException;
  
} // End of Integral interface