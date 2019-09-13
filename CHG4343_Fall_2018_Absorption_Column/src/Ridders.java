public class Ridders extends RootFinder
{
  
  public Ridders(Root equation)throws CouldNotConstructObjectException
  {
    super(equation);
    if(equation==null){
      throw new CouldNotConstructObjectException("Ridders");
    }
  } // End of Ridders constructor
  
 public Ridders(Ridders object)throws CouldNotConstructObjectException
 {
    super(object);
    if (object==null){
      throw new CouldNotConstructObjectException("Ridders");
    }
 } // End of Ridders copy constructor

 public Ridders clone()throws CouldNotConstructObjectException
 {
   
   return new Ridders(this);
 } // End of clone method

 public boolean equals(Object object)
 {
   if (!super.equals(object))
     return false;
 
     return this.getClass() == object.getClass();
 } // End of equals method

  public double findRoot(double xL, double xU) throws NoRootException
  {
    double xM, xR, fXL, fXU, fXM, fXR, signFunc;
    double xROld = 0;                      // Stores the previous xR
    double eS = 0.0001;                    // Tolerance value
    int counter = 0;                       // Increment counter
    double[] xRArray = new double[2];      // Root answer storage array
    
    do
    {
        /* Calculate xM */
        xM = (xL + xU) / 2;
        
        /* Calculate xR */
        fXL = super.getEquation().hasRoot(xL);
        fXU = super.getEquation().hasRoot(xU);
        fXM = super.getEquation().hasRoot(xM);
        signFunc = Math.signum(fXL - fXU);
        xR = xM + (xM - xL) * ((signFunc * fXM) / (Math.sqrt(Math.pow(fXM, 2) - (fXL * fXU))));
        
        /* Calculate fXR */
        fXR = super.getEquation().hasRoot(xR);

        /* Find which interval the root is bound and set new bounds */
        if (xR < xM)
        {
            if (100000000 * fXL * fXR < 0)
            {
                xU = xR;
            }
            else if (100000000 * fXR * fXM < 0)
            {
                xL = xR;
                xU = xM;
            }
            else if (100000000 * fXM * fXU < 0)
            {
                xL = xM;
            }
            else
            {
                System.out.println("Error: Ridder's method error 1! ");
                counter=0;
                throw new NoRootException();
            }
        }
        else
        {
            if (fXL * fXM < 0)
            {
                xL = xL;
                xU = xM;
            }
            else if (fXM * fXR < 0)
            {
                xL = xM;
                xU = xR;
            }
            else if (fXR * fXU < 0)
            {
                xL = xR;
                xU = xU;
            }
            else
            {
                System.out.println("Error: Ridder's method error 2!");
                counter=0;
                throw new NoRootException();
            }
        }
        
        /* Store current xR value for next iteration */
        xRArray[1] = xRArray[0];
        xRArray[0] = xR;
        xROld = xRArray[1];
        
        counter++;
        
    } while ((counter == 1) || (Math.abs((xR - xROld)/xR) > eS));
    
    /* Return root */
    return xR;

  } // End of findRoot method
} // End of Ridders class