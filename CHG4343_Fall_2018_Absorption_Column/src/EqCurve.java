public class EqCurve extends Polynomial {
  
  public EqCurve( double[] consts)throws CouldNotConstructObjectException//start of EqCurve constructor
  {
    super(consts);
    try{
      double xMax=this.newXA(.999);
      if (this.checkData(xMax)){
        throw new CouldNotConstructObjectException();
      } 
    }
    catch(RangeLimitException e)
    {
      System.exit(0);
    }   
    
  }//end of constructor
  
  public EqCurve(EqCurve object)throws CouldNotConstructObjectException//start of EqCurve copy constructor
  {
    super(object); 
  }//end of copy constructor
  
  public EqCurve clone(){//start of clone
    return new EqCurve(this);
  }//end of clone
  
  private boolean checkData(double xMax){
    Ridders ridder=new Ridders(this);
    try{
      double x=ridder.findRoot(0,xMax);
    }
    catch (NoRootException e){
      return true;
    }
    return false;
  }
  
  public boolean equals(Object source){
    if (!super.termsEquals(source)){
      return false;
    }
    
    return source.getClass()!=this.getClass();
  }
  
  
  /*Uses polynomial to find output and throws exeption for impossible outputs*/
  public double newYA(double xA)throws RangeLimitException{
    /*provides limits of concentration*/
    double yA=super.newYA(xA);

    if (yA<0){
      throw new RangeLimitException(" new yAi is bellow 0");
    }
    else if(yA>1) {
      throw new RangeLimitException(" new yAi is above 1");
    }
    return yA;
  }
  
  /*Uses polynomial to find output and throws exeption for impossible outputs*/
  public double newXA(double yA)throws RangeLimitException{//finds the xa to a corresponding ya
    double value;
    double xA=super.newXA(yA, .0000001,.99999999);
    if (xA<1&& xA >0){
      value=xA;
    }
    else if (xA<0){
      throw new RangeLimitException(" new xAi is bellow 0");
    }
    else {
      throw new RangeLimitException(" new xAi is above 1");
    }
    return value;
  }//end of new xa
  
}
