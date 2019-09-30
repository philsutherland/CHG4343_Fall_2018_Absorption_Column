public class Column
{ 
  private int numStep;                 // Number of steps for solving numerical integration
  private double R;                    // Percent recovery
  private int packingType;             // Indicates the type of packing used
  private double columnD;              // Column diameter
  private double columnAc;             // Column cross sectional area
  private double columnHeight;         // Column height
  
  public Column(int numStep, double R, int packingType)throws CouldNotConstructObjectException
  {
      /* Check if input values are in acceptable range */
    if(numStepInRangeCheck(numStep)&& RInRangeCheck(R)&& packingTypeInRangeCheck(packingType)){
      /* Assign input values to Column instance variables */
      this.numStep = numStep;
      this.R = R/100;
      this.packingType=packingType;
      this.columnD=Constant.getPackingValues()[packingType][0] * Constant.getPackingMatD()/1000;  
      this.columnAc=Math.PI*Math.pow(this.columnD,2)/4;
    }
    else 
    {
      throw new CouldNotConstructObjectException("Column");
    }

  } // End of Column constructor
  
  public Column(Column source)throws CouldNotConstructObjectException
  { 
    /* Check if Column object is null */
    if (source == null)    
    {
      throw new CouldNotConstructObjectException("Column");
    } 
    /* Assign input values to Column instance variables */
    this.numStep = source.numStep;
    this.R = source.R;
    this.packingType=source.packingType;
    this.columnD=source.columnD;
    this.columnAc=source.columnAc;  
  } // End of Column copy constructor
  
  /* Check if packing type value (packingType) is in range */
  public static boolean packingTypeInRangeCheck(double packingType)
  {
    /* Convert packingType double to int */
    int packingTypeInt = (int)packingType;
    
    if ( (packingTypeInt == 0) || (packingTypeInt == 1) || (packingTypeInt == 2) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Packing type value (packingType) is out of acceptable range!");
      return false;
    }
  } // End of packingTypeInRangeCheck method
  
  /* Check if number of numerical integration steps (numStep) is in range */
  public static boolean numStepInRangeCheck(double numStep){ 
    if ( (numStep >= Constant.numStepLowerLimit) && (numStep <= Constant.numStepUpperLimit) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Number of numerical integration steps (numStep) is out of acceptable range!");
      return false;
    }
  } // End of  numStepInRangeCheck method
  
  /* Check if pecent recovery value (R) is in range */
  public static boolean RInRangeCheck(double R){ 
    if ( (R >= Constant.RLowerLimit) && (R <= Constant.RUpperLimit) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Percent recovery value (R) is out of acceptable range!");
      return false;
    }
  } // End of RInRangeCheck method
  
  public int getNumStep(){
    return this.numStep;
  } // End of getNumStep
  
  public boolean setNumStep(int numStep){
    if (!numStepInRangeCheck(numStep))
      return false;
    this.numStep = numStep;    
    return true;
  } // End of setNumStep
  
  public double getR(){
    return this.R;
  } // End of getR
  
  public boolean setR(double R){
    if (!numStepInRangeCheck(R))
      return false;
    this.R = R;    
    return true;
  } // End of setR
  
  public double getColumnD(){
    return this.columnD;
  } // End of getColumnD
  
  public boolean setColumnD(double columnD){
    if (columnD < 0)
    {
      System.out.println("Error: The column diameter value (columnD) must be greater than zero!");
      return false;
    }
    this.columnD = columnD;    
    this.columnAc=Math.PI*Math.pow(this.columnD,2)/4;
    return true;
  } // End of setColumnD
  
  public boolean setPackingType(int packingType){
    if(packingType==1||packingType==0||packingType==2){
      this.columnD=Constant.getPackingValues()[packingType][0] * Constant.getPackingMatD()/1000;  
      this.columnAc=Math.PI*Math.pow(this.columnD,2)/4;
      return true;
     }
     return false;
  } // End of mutator
  
  public double getPackingType(){
    return this.packingType;
  } // End of accessor
  
  public double getColumnAc(){
    return this.columnAc;
  } // End of getColumnAc
  
  public double getColumnHeight()
  {
    return this.columnHeight;
  } // End of getColumnHeight
  
  public boolean setColumnHeight(double columnHeight)
  {
    if (columnHeight < 0)
    {
      System.out.println("Error: The column height value (columnHeight) must be greater than zero!");
      return false;
    }
    
    this.columnHeight = columnHeight;    
    return true;
  } // End of setColumnHeight
  
  public boolean equals(Object source){
    if (source==null){
      return false;
    }
    else if(source.getClass()!=this.getClass()){
      return false;
    }
    else{
      Column object=(Column)source;
      return this.numStep==object.numStep && this.R==object.R &&this.columnD==object.columnD&&this.columnHeight==object.columnHeight;
    }
  }
  
  public boolean termsEquals(Object source){
    if (source==null){
      return false;
    }
    else{
      Column object=(Column)source;
      return this.numStep==object.numStep && this.R==object.R &&this.columnD==object.columnD&&this.columnHeight==object.columnHeight;
    }
  }
  
  public String toString(){
    return "The recovery is "+this.R+" the  number of steps specified is "+this.numStep+" the column diameter is "+this.columnD+" the column's cross sectional area is "+this.columnAc+" the column's Height is "+ this.columnHeight;
  }
  
  public Column clone() 
  {
    return new Column(this);
  }
  
} // End of Column class