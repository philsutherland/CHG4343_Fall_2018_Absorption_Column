public class TrapazoidRuleForDoubleIntegral extends NumericalIntegration
{
  
  public TrapazoidRuleForDoubleIntegral(int numStep, Integral equation)throws CouldNotConstructObjectException
  {
    super(numStep, equation);//need to check for even number
    if(equation==null){
      throw new CouldNotConstructObjectException("TrapazoidRuleForDoubleIntegral");
    }
  } //end of constructor
  
  public TrapazoidRuleForDoubleIntegral(TrapazoidRuleForDoubleIntegral object)throws CouldNotConstructObjectException{//start of copy constructor
    super(object);
    if(object==null){
      throw new CouldNotConstructObjectException("TrapazoidRuleForDoubleIntegral");
    }
  }//end of copy constructor
    
  public TrapazoidRuleForDoubleIntegral clone(){//start of clone\
    return new TrapazoidRuleForDoubleIntegral(this);
  }//end of clone
  
  public boolean equals(Object source)
  {
    if (!super.equals(source))
    {
      return false;
    }
    
    return source.getClass() == this.getClass();
  }
  
  public boolean equals(TrapazoidRuleForDoubleIntegral object){
    if(super.equals(object) && object!=null&&object.getClass()==this.getClass()){
      return true;
    }
    return false;
  }
  
  public double[][] solveIntegral(double xN, double x0){//start of solve integral
    /* Initilizes values and arrays */
    double yOld=0;
    double dZVOld=0;
    double dZLOld=0;
    double[] response=new double[3];
    double[] xValues=new double[super.getSteps()];
    double h=(x0-xN)/super.getSteps();
    /* sets first x value to the initial concentratition */
    xValues[0]=xN;
    double[][] areaUnderCurve= new double[2][super.getSteps()];
    /* generates a series of evenly spaced x values */
    for (int i=1;i<xValues.length;i++){//start of xValue generation
      xValues[i]=xValues[i-1]+h;
    }//end of x point generation
    /* loops the integral for as many steps as x values */
    for(int i=0;i<xValues.length;i++){//start of solution
      /* Recieves array of dzs and y_AG */
      response=super.getEquation().numericalIntegral(xValues[i]);
      /* Uses Trapazoid rule for each step (x based on even steps, y based on difference of current and last y */
      if (i>0){
        areaUnderCurve[0][i]=(response[0]+dZLOld)*h/2+areaUnderCurve[0][i-1];
        areaUnderCurve[1][i]=(response[1]+dZVOld)*(response[2]-yOld)/2+areaUnderCurve[0][i-1];
      }
      else {
        areaUnderCurve[0][i]=0;
        areaUnderCurve[1][i]=0;
      }
      /* records prior values to use in trapazoid rule */
      dZLOld=response[0];
      dZVOld=response[1];
      yOld=response[2];
    }//end of loop
    return areaUnderCurve;
  }//end of solve integral
}//end of class