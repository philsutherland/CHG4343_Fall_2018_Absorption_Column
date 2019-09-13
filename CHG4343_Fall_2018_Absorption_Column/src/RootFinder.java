public abstract class RootFinder
{
  private Root equation;
 
  public RootFinder(Root equation)throws CouldNotConstructObjectException
  {
    if (equation != null)
    {
      this.equation = equation;
    }
    else
    {
      throw new CouldNotConstructObjectException("RootFinder");
    }
  } // End of RootFinder constructor
  
  public RootFinder(RootFinder source)throws CouldNotConstructObjectException
  {
    if(source == null)
    {
      throw new CouldNotConstructObjectException("RootFinder");
    }
    this.equation = source.equation.clone();
  } // End of RootFinder copy constuctor
  
  public boolean setEquation(Root equation)
  {
    if (equation == null)
      return false;
    else
      this.equation = equation;
    return true;
  } // End of setEqCurve mutator
  
  public Root getEquation()
  {
    return this.equation;
  } // End of getEqCurve accessor
    
  public String toString(){
    return "The equation: " + this.equation.toString();
  } // End of toString 
  
  public boolean equals(Object source)
  {
    if (source == null)
    {
      return false;
    }
    else if (source.getClass() != this.getClass())
    {
      return false;
    }
    else
    {
      RootFinder object = (RootFinder)source;
      return this.equation.equals(object.equation);
    }
  }
 
  public abstract RootFinder clone()throws CouldNotConstructObjectException;
  
  public abstract double findRoot(double xL, double xU)throws NoRootException;
  
} // End of RootFinder class