public class Polynomial implements Root{
  
  private double[] consts;//the coefficents for the equation
  private int order;//note this is actually one above the order, due to the 0 power term
  private double yA;
  
  /*Construct throws exeption if object can not be made*/
  public Polynomial( double[] consts)throws CouldNotConstructObjectException//start of EqCurve constructor
  {
    if(consts==null){
      throw new CouldNotConstructObjectException("EqCurve");
    }
    this.yA=0;
    this.order=consts.length-1;
    this.consts = new double[this.order+1];
    /*deep copies input values*/
    for(int i=0; i <= order; i++)
    {
      this.consts[i]=consts[i];
    }    
  }//end of constructor
  
  /*Copy construct throws exeption if can not be constructed*/
  public Polynomial(Polynomial object)throws CouldNotConstructObjectException//start of EqCurve copy constructor
  {
    if(object==null){
      throw new CouldNotConstructObjectException("EqCurve");
    }
    this.yA=object.yA;
    this.order  = object.order;
    this.consts = new double[this.order+1];
    for(int i=0; i <=this.order; i++)
    {
      this.consts[i]=object.consts[i];
    }    
  }//end of copy constructor
  
  /*Clone*/
  public Polynomial clone(){//start of clone
    return new Polynomial(this);
  }//end of clone
  
  /*equals*/
  public boolean equals(Object source){
    /*Checks for null object*/
    if (source==null){
      return false;
    }
    /*Checks classes*/
    else if(source.getClass()!=this.getClass()){
      return false;
    }
    else{
      /*recasts object*/
      Polynomial object=(Polynomial)source;
      /*Compairs insanse varibles and returns compairison*/
      return this.yA==object.yA&&this.order==object.order;
    }
  }
  
  /*Compairs input to object for use by chaild classes*/
  public boolean termsEquals(Object source){
    /*null check*/
    if (source==null){
      return false;
    }
    
    /*No class check as object is presumable of child class*/
    else{
      /*recasts object*/
      Polynomial object=(Polynomial)source;
      /*Compairs insanse varibles and returns compairison*/
      return this.yA==object.yA&&this.order==object.order;
    }
  }
  
  /*Creates a string of relevent values*/
  public String toString(){
    String response=new String("");
    
    /*Records constant with the power of the assosiated term*/
    for(int i=0;i<=this.order;i++){
      if(i%10==0||i%10==0||i%10==4||i%10==5||i%10==6||i%10==7||i%10==8||i%10==9){
        response=response+"the "+i+"th constant is "+this.consts[i];
      }
      else if(i%10==1){
        response=response+"the "+i+"st constant is "+this.consts[i];
      }
      else if(i%10==2){
        response=response+"the "+i+"nd constant is "+this.consts[i];
      }
      else if(i%10==3){
        response=response+"the "+i+"rd constant is "+this.consts[i];
      }
    }
  return response +" and the curve is to the order of "+this.order;
}

/*Changes constants of equation*/
public boolean setCurve(double[] consts){//end of mutator
  if(consts==null){
    System.out.println("Null value given to the equilibrium curve in mutation ");
    return false;
  }
  /*As array size can not be updated ensures the new equation is of proper order*/
  if (consts.length!=this.order+1){
    System.out.println("An equation of order different than current equilibrium curve order was given in mutation ");
    return false; 
  }
  /*deep copies the constants*/
  for(int i=0; i <=this.order; i++)
  {
    this.consts[i]=consts[i];
  }    
  return true;
}//end of mutator  

/*returns deep copied array of constants*/
public double[] getEqCurveCoefficients()
{
  double[] data = new double[7];
  for (int i=0; i<=order; i++)
  {
    data[i]=this.consts[i];
  }
  return data;
}

/*Returns polynomial order*/
public int getOrder(){//order accessor
  return this.order;
}

/*Calculates a new independent variable from dependent variables*/
public double newYA(double xA)throws RangeLimitException
{
  double yA=0;
  /*Sums all terms*/
  for(int i=0;i<=this.order;i++){
    /*Multiples the constant and the independent variable by the assosiated power and adds to total sum of terms*/
    yA=yA+this.consts[i]*Math.pow(xA,this.order-i);
  }//end of loop
  return yA;
}

/* Calculates a new xa value based on a provided yA */
public double newXA(double yA, double xL, double xU)throws RangeLimitException{//finds the xa to a corresponding ya
  
  /*Creates a ridders object to find a root between given values*/
    Ridders rootFinder= new Ridders(this);
  this.yA=yA;
  /* finds the lowest x value which produces a given y value between a provided range */
  try{
    double root=rootFinder.findRoot(xL,xU);
    return root;
  }
  catch(NoRootException e){
    System.exit(0);
  }
  return 0;
}//end of new xa

/* Holds equation to bind root and assosiated equations */
public double hasRoot(double xA){//allows use of root finders
  double currentValue=0;
  /* calcluates an output fraction based off current guess */
  for(int i=0;i<=this.order;i++){//loop to add all terms in sereis
    currentValue=currentValue+this.consts[i]*Math.pow(xA,this.order-i);
  }//end of loop
  /* returns difference between current yA and the provided */
  return this.yA-currentValue;
}//end of has root
}
