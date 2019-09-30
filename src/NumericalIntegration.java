public abstract class NumericalIntegration{//start of class
  private int numStep;
  private Integral equation;
  
  public NumericalIntegration(int numStep, Integral equation)throws CouldNotConstructObjectException{//start of constructor
    if(numStep>0 && equation!= null ){
      this.numStep=numStep;
      this.equation=equation;
    }
    else{
      throw new CouldNotConstructObjectException("NumericalIntegration");
    }
  }//end of constructor
  
  public NumericalIntegration(NumericalIntegration object)throws CouldNotConstructObjectException{//start of copy constructor
    if(object!=null ){
      this.numStep=object.numStep;
      this.equation=object.equation.clone();
    }
    else{
      throw new CouldNotConstructObjectException("NumericalIntegration");
    }
  }//end of  copy constructor 
  
  public abstract NumericalIntegration clone();
  
  public boolean setSteps(int numStep){//start of mutator
    if(numStep>0 ){
      this.numStep=numStep;
        return true;
    }
    else{
      return false;
    }
  }//end of mutator
  
  public int getSteps(){//start of accessor
    return this.numStep;
  }//end of accessor
  
  public boolean setEquation(Integral equation){//start of mutator
    if(equation!=null){
      this.equation=equation.clone();
        return true;
    }
    else{
      return false;
    }
  }//end of mutator
  
  public Integral getEquation(){//start of accessor
    return this.equation;
  }//end of accessor
  
  public abstract double[][] solveIntegral(double x0, double xN);
  
  public boolean equals(Object source)
  {
    if (source == null)
    {
      return false;
    }
    else
    {
      NumericalIntegration object = (NumericalIntegration)source;
      return this.numStep == object.numStep && this.equation.equals(object);
    }
  }
  
  public String toString(){
    return "The number of steps is "+this.numStep+" and the equation: "+this.equation.toString();
  }
}//end of class