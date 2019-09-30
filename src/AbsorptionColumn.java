public class AbsorptionColumn extends Column implements Integral, Root
{
  private double V_0;                       // Inlet vapour flow rate
  private double y_A0;                      // Inlet vapour mole fraction of component A
  private double L_N;                       // Inlet liquid flow rate
  private double x_AN;                      // Inlet liquid mole fraction of component A
  private int packingType;                  // Packing type
  private double y_AN;                      // Outlet vapour mole fractiom of component A
  private double x_A0;                      // Outlet liquid mole fraction of component A
  private double Vp;                        // Pure liquid flow rate
  private double Lp;                        // Pure vapour flow rate
  private double stepSize;                  // Step size for numerical integration
  private EqCurve eqCurve;                  // Equilibrium data 
  private double N_ScV;                     // Schmidt number for vapour stream
  private double N_ScL;                     // Schmidt number for liquid stream
  private double[][] equilibriumProfile;    // Profile of the equilibrium data at each height and corresponding dZ values
  private double g_y_AG;                    // Bulk composition in vapour 
  private double g_x_AL;                    // Bulk composition in liquid
  private double g_gasMolarFlow;            // Gas molar flow rate
  private double g_liquidMolarFlow;         // Liquid molar flow rate
  private double g_remainderLogMeanX;       // Log mean remainder of x_Ai and x_AL
  private double g_remainderLogMeanY;       // Log mean remainder of y_Ai and y_AG
  private double g_kxa;                     // Liquid mass transfer coefficient 
  private double g_kya;                     // Vapour mass transfer coefficient 
  private int g_count;                      // Integral increment count for setting equilibrium profile
  private double g_maxXAI;                  // Stores maximum allowable value for x_Ai
  
  AbsorptionColumn(double[] inputs)throws CouldNotConstructObjectException{
    super((int)inputs[0], inputs[1], (int)inputs[6] );
    
    /* Check if input values are in acceptable range */
    if( V_0InRangeCheck(inputs[2])&&y_A0InRangeCheck(inputs[3])&&L_NInRangeCheck(inputs[4])&& x_ANInRangeCheck(inputs[5])&&packingTypeInRangeCheck((int)inputs[6])){
      
      /* Assign input values to AbsorptionColumn instance variables */
      this.V_0 = inputs[2];
      this.y_A0 = inputs[3];
      this.L_N = inputs[4];
      this.x_AN = inputs[5];
      this.Vp = this.V_0 * (1 - this.y_A0);
      this.Lp = this.L_N * (1 - this.x_AN);
      
      /* Calculate vapour outlet mole fraction of A */
      this.y_AN = (1 - this.getR()) * this.y_A0*V_0/((1 - this.getR())*this.V_0*this.y_A0+this.Vp);  
      this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
      this.packingType = (int)inputs[6];
      this.N_ScL = Constant.getVisL() / (Constant.getDenL() * Constant.getD_ABL());
      this.N_ScV = Constant.getVisV() / (Constant.getDenV() * Constant.getD_ABV());
      
      /* Calculate step size */
      this.stepSize = (this.x_A0 - this.x_AN) / super.getNumStep();
      
      /* Create new equilibrium object */
      this.eqCurve = new EqCurve(Constant.getEqCoeff());
      this.equilibriumProfile=new double[8][super.getNumStep()];
      this.close();
      this.size();
    }
    else{
      throw new CouldNotConstructObjectException("AbsorptionColumn");
    }
  } // End of AbsorptionColumn constructor
  
  AbsorptionColumn(AbsorptionColumn source)throws CouldNotConstructObjectException{
    super(source);
    
    /* Check if AbsorptionColumn object is null */
    if (source != null){ 
      /* Assign copied AbsorptionColumn object values to new AbsorptionColumn instance variables  */
      this.V_0 = source.V_0;
      this.y_A0 = source.y_A0;
      this.L_N = source.L_N;
      this.x_AN = source.x_AN;
      this.y_AN = source.y_AN;
      this.x_A0 = source.x_A0;
      this.Vp = source.Vp;
      this.Lp = source.Lp;
      this.packingType = source.packingType;
      this.N_ScL = source.N_ScL;
      this.N_ScV = source.N_ScV;
      
      /* Create new equilibrium object */
      this.eqCurve = source.eqCurve.clone();
      this.g_y_AG=source.g_y_AG;
      this.g_x_AL=source.g_x_AL;
      this.g_gasMolarFlow=source.g_gasMolarFlow;
      this.g_liquidMolarFlow=source.g_liquidMolarFlow;
      this.g_remainderLogMeanX=source.g_remainderLogMeanX;
      this.g_remainderLogMeanY=source.g_remainderLogMeanY;
      this.g_kxa=source.g_kxa;
      this.g_kya=source.g_kya;
      this.g_count=source.g_count;
      this.equilibriumProfile=new double[8][super.getNumStep()];
    }
    
    else{
      throw new CouldNotConstructObjectException("AbsorptionColumn");
    }
  } // End of AbsorptionColumn copy constructor
  
  public AbsorptionColumn clone()
  {
    return new AbsorptionColumn(this);
  } // End of clone method
  
  public boolean equals(Object source){
    if (!super.termsEquals(source))
    {
      return false;
    }
    else if(source.getClass()!=this.getClass()){
      return false;
    }
    else{
      AbsorptionColumn object=(AbsorptionColumn)source;
      return this.V_0==object.V_0&&this.y_A0==object.y_A0&&this.L_N==object.L_N&&this.x_AN==object.x_AN&&this.y_AN==object.y_AN&&this.x_A0==object.x_A0&&this.Vp==object.Vp&&this.Lp==object.Lp&&this.packingType==object.packingType&&this.N_ScL==object.N_ScL&&this.N_ScV==object.N_ScV&&this.eqCurve.equals(object.eqCurve)&&this.g_y_AG==object.g_y_AG&&this.g_x_AL==object.g_x_AL&&this.g_gasMolarFlow==object.g_gasMolarFlow&&g_liquidMolarFlow==object.g_liquidMolarFlow&&this.g_remainderLogMeanX==object.g_remainderLogMeanX&&this.g_remainderLogMeanY==object.g_remainderLogMeanY&&this.g_kxa==object.g_kxa&&this.g_kya==object.g_kya&&this.g_count==object.g_count;
    }
  } // End of equals method
  
  public String toString(){
    return super.toString() +" the initial gas flow rate is " + this.V_0 + " the initial mole fration of A in the gas is "+this.y_A0 + " the inital liquid flow rate is " +this.L_N+ " the initial mole fration of A in the liquid is "+this.x_AN+" the exit composition of A in the mole fraction is "+this.y_AN+" the exit composition of A in the liquid fraction is "+this.x_A0+" the pure gas has a flow rate of "+this.Vp+" the pure liquid has a flow rate of "+this.Lp+" the packing type is identified as "+this.packingType+" the Schmidt number of the liquid is "+this.N_ScL+" the Schmidt number of the gas is "+this.N_ScV+" the equilibrium curve constants are "+eqCurve.toString()+" g_y_AG is "+this.g_y_AG+" g_x_AL is "+this.g_x_AL+" g_gasMolarFlow "+this.g_gasMolarFlow+" g_liquidMolarFlow is "+g_liquidMolarFlow+" g_remainderLogMeanX is "+this.g_remainderLogMeanX+" g_remainderLogMeanY is "+this.g_remainderLogMeanY+" g_kxa is "+this.g_kxa+" g_kya is "+this.g_kya+" the current count is "+this.g_count;
  }
  
  public boolean setV_0(double V_0){
    if(V_0>0){
      this.V_0=V_0;
      this.Vp = this.V_0 * (1 - this.y_A0);
      this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
      this.stepSize = (this.x_A0 - this.x_AN) / super.getNumStep();
      this.size();
      return true;
    }
    return false;
  } // End of mutator
  
  public boolean setY_A0(double y_A0){
    if(0.0<y_A0 &&y_A0<1.0){
      this.y_A0=y_A0;
      this.Vp = this.V_0 * (1 - this.y_A0);
      this.y_AN = (1 - this.getR()) * this.y_A0;  
      this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
      this.stepSize = (this.x_A0 - this.x_AN) / super.getNumStep();
      this.size();
      return true;
    }
    else
      return false;
  } // End of mutator
  
  public boolean setL_N(double L_N){
    if(L_N>0){
      this.L_N=L_N;
      this.Lp = this.L_N * (1 - this.x_AN);
      this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
      close();
      this.size();
      return true;
    }
    else
      return false;
  } // End of mutator
  
  public boolean setX_AN(double x_AN){
    if(0<x_AN&&x_AN<1){
      this.x_AN=x_AN;
      this.Lp = this.L_N * (1 - this.x_AN); 
      this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
      this.stepSize = (this.x_A0 - this.x_AN) / super.getNumStep();
      this.size();
      return true;
    }
    else
      return false;
  } // End of mutator
  
  public boolean setY_AN(double y_AN){
    System.out.println("The exit mole fraction of A in the vapour is based off the inlet mole fraction of A and the percent recovery. It can  not be manually entered.");
    this.y_AN = (1 - this.getR()) * this.y_A0;
    return false;
  } // End of mutator
  
  public boolean setX_A0(double x_A0){
    System.out.println("The exit mole fraction of A in the liquid is based off the inlet mole fraction of A and the percent recovery from the vapour, found using a mole balance. It can  not be manually entered.");
    this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
    return false;
  } // End of mutator
  
  public boolean setVp(double Vp){
    System.out.println("The pure vapour flow rate is set based of the inlet vapour flow and the inlet vapour fraction of A. It can  not be manually entered.");
    this.Vp = this.V_0 * (1 - this.y_A0);
    return false;
  } // End of mutator
  
  public boolean setLp(double Lp){
    System.out.println("The pure liquid flow rate is set based of the inlet liquid flow and the inlet mole fraction of A. It can  not be manually entered.");
    this.Lp = this.L_N * (1 - this.x_AN);
    return false;
  } // End of mutator
  
  public boolean setN_ScV(double N_ScV){
    System.out.println("The vapour Schmidt number \"N_ScV\" is set based on vapour characteristics, and can not be manually altered"); 
    this.N_ScV= Constant.getVisV() / (Constant.getDenV() * Constant.getD_ABV());
    return false;
  } // End of mutator
  
  public boolean setN_ScL(double N_ScL){
    System.out.println("The liquid Schmidt number \"N_ScL\" is set based on liquid characteristics, and can not be manually altered"); 
    return false;
  } // End of mutator
  
  public boolean setStepSize(int stepSize){
    System.out.println("The liquid Schmidt number \"N_ScL\" is set based on liquid characteristics, and can not be manually altered");     
    return false;
  } // End of mutator
  
  public boolean setPackingType(int packingType){
    if(super.setPackingType(packingType)){
      this.size();
      return true;
    }
    return false;
  } // End of mutator
  
  public double[][] getEquilibriumProfile(){
    double[][] tempArray = new double[9][equilibriumProfile[0].length];
    
    /* Deep copy equilibriumProfile array */
    for (int i = 0; i < 8; i++)
    {
      for (int j = 0; j < tempArray[0].length; j++)
      {
        if (i==0||i==4){
          tempArray[i][j] = this.equilibriumProfile[i][equilibriumProfile[0].length-1-j];
        }
        else{
          tempArray[i][j] = this.equilibriumProfile[i][j];
        } 
      }
    }
    
    /* Assign the highest of the vapour/liquid column heights as the actual column height */
    for (int j = 0; j < tempArray[0].length; j++)
    {
      if (tempArray[0][j] >= tempArray[4][j])
      {
        tempArray[8][j] = tempArray[0][j];
      }
      else
      {
        tempArray[8][j] = tempArray[4][j];
      }
    }
    return tempArray;
  } // End of accessor
  
  public double getV_0(){
    return this.V_0;
  } // End of accessor
  
  public double getY_A0(){
    return this.y_A0;
  } // End of accessor
  
  public double getL_N(){
    return this.L_N;
  } // End of accessor
  
  public double getX_AN(){
    return this.x_AN;
  } // End of accessor
  
  public double getY_AN(){
    return this.y_AN;
  } // End of accessor
  
  public double getX_A0(){
    return this.x_A0;
  } // End of accessor
  
  public double getVp(){
    return this.Vp;
  } // End of accessor
  
  public double getLp(){
    return this.Lp;
  } // End of accessor
  
  public double getN_ScV(){
    return this.N_ScV;
  } // End of accessor
  
  public double getN_ScL(){
    return this.N_ScL;
  } // End of accessor
  
  public double getStepSize(){
    return this.stepSize;
  } // End of accessor
  
  
  public boolean setValues(double[] columnValues)
  {
    boolean inRange = true;
    
    try
    {
      /* Check if input values are in acceptable range */
      inRange = V_0InRangeCheck(columnValues[0]);
      inRange = y_A0InRangeCheck(columnValues[1]);
      inRange = L_NInRangeCheck(columnValues[2]);
      inRange = x_ANInRangeCheck(columnValues[3]);
      inRange = packingTypeInRangeCheck((int)columnValues[5]);
      
      if (inRange == false)
      {
        System.out.println("Error: Input values are out of acceptable range!");
        return false;
      }
      
      /* Assign values to AbsorptionColumn instance variables */
      this.V_0 = columnValues[0];
      this.y_A0 = columnValues[1];
      this.L_N = columnValues[2];
      this.x_AN = columnValues[3];
      this.Vp = this.V_0 * (1 - this.y_A0);
      this.Lp = this.L_N * (1 - this.x_AN);
      
      /* Calculate vapour outlet mole fraction of A */
      this.y_AN = (1 - this.getR()) * this.y_A0;  
      this.x_A0 = ((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN))))/(1+((Vp/Lp)*((y_A0/(1-y_A0))-(y_AN/(1-y_AN)))));
      this.packingType = (int)columnValues[5];
      this.N_ScL = Constant.getVisL() / (Constant.getDenL() * Constant.getD_ABL());
      this.N_ScV = Constant.getVisV() / (Constant.getDenV() * Constant.getD_ABV());
      this.stepSize = (this.x_A0 - this.x_AN) / super.getNumStep();
      this.stepSize = (this.x_A0 - this.x_AN) / super.getNumStep();
      this.eqCurve = new EqCurve(Constant.getEqCoeff());
      try{
        this.equilibriumProfile=new double[8][super.getNumStep()];
      }
      catch(CouldNotConstructObjectException e){
        System.out.println(e.getMessage());
        System.exit(0);
      }
      catch(Exception e){
        System.out.println("Object was not created");
        System.exit(0);
      }
      this.size();
      
    }
    catch(Exception e)
    {
      System.out.println("Error: Could not assign values to AbsorptionColumn instance variables in setValues mutator!");
      return false;
    }
    
    return true;
  } // End of setValues mutator
  
  /* Check if vapour flow rate (V_0) is in range */
  public static boolean V_0InRangeCheck(double V_0)
  {
    if ( (V_0 >= Constant.V_0LowerLimit) && (V_0 <= Constant.V_0UpperLimit) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Inlet vapour flow rate (V_0) is out of acceptable range!");
      return false;
    }   
  } // End of V_0InRangeCheck method
  
  /* Check if initial vapour composition of component A (y_A0) is in range */
  public static boolean y_A0InRangeCheck(double y_A0)
  { 
    if ( (y_A0 >= Constant.y_A0LowerLimit) && (y_A0 <= Constant.y_A0UpperLimit) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Inlet vapour mole fraction of component A (y_A0) is out of acceptable range!");
      return false;
    }   
  } // End of y_A0InRangeCheck method
  
  /* Check if initial liquid flow rate (L_N) is in range */
  public static boolean L_NInRangeCheck(double L_N)
  {
    if ( (L_N >= Constant.L_NLowerLimit) && (L_N <= Constant.L_NUpperLimit) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Inlet liquid flow rate (L_N) is out of acceptable range!");
      return false;
    }   
  } // End of L_NInRangeCheck method
  
  /* Check if initial liquid composition of component A (x_AN) is in range */
  public static boolean x_ANInRangeCheck(double x_AN)
  {
    if ( (x_AN >= Constant.x_ANLowerLimit) && (x_AN <= Constant.x_ANUpperLimit) )
    {
      return true;
    }
    else
    {
      System.out.println("Error: Inlet liquid mole fraction of component A (x_AN) is out of acceptable range!");
      return false;
    }
  } // End of x_ANInRangeCheck method
  
  /* Size the absorption column */
  public void size()
  {
    /* Calculate column height */
    this.close();
    try{
      TrapazoidRuleForDoubleIntegral integral = new TrapazoidRuleForDoubleIntegral(super.getNumStep(), this);
      this.g_maxXAI =this.eqCurve.newXA(.999);
      double[][] holdingTemperary = new double[2][super.getNumStep()];
      holdingTemperary = integral.solveIntegral(this.x_AN, this.x_A0);
      for (int i = 0; i < super.getNumStep(); i++)
      {
        this.equilibriumProfile[0][i] = holdingTemperary[0][i];
        this.equilibriumProfile[4][i] = holdingTemperary[1][i];
      }
      if (this.equilibriumProfile[0][super.getNumStep()-1] > this.equilibriumProfile[4][super.getNumStep()-1])
      {
        super.setColumnHeight(this.equilibriumProfile[0][super.getNumStep() - 1]);
      }
      else {
        super.setColumnHeight(this.equilibriumProfile[4][super.getNumStep() - 1]);
      }
      this.close();
    }
  catch(RangeLimitException e){
        System.out.println(e.getMessage());
        System.exit(0);
      }
  catch(CouldNotConstructObjectException e){
    System.out.println(e.getMessage());
    System.exit(0);
  }
  catch(Exception e){
    System.out.println("Object was not created");
    System.exit(0);
  }
} // End of size method
  
  private void close()
  {
    this.g_y_AG = 0;
    this.g_x_AL = 0;
    this.g_gasMolarFlow = 0;
    this.g_liquidMolarFlow = 0;
    this.g_remainderLogMeanX = 0;
    this.g_remainderLogMeanY = 0;
    this.g_kxa = 0;
    this.g_kya = 0;
    this.g_count = 0;
  } // End of close
  
  public double getYAG()
  {
    return this.g_y_AG;
  }
  
  /* Store the equation of the integral and all associated equations */
  public double[] numericalIntegral(double xAL)
  {
    /* Set the molar compositions based on input */
    this.g_x_AL = xAL;
    this.g_y_AG = ((this.y_AN / (1 - this.y_AN) + (Lp / Vp) * (this.g_x_AL / (1 - this.g_x_AL))) / (1 + (this.y_AN / (1 - this.y_AN)) + (Lp / Vp) * (this.g_x_AL / (1 - this.g_x_AL))));
    
    /* Set the molar flow rates */
    double gasMolarFlow = this.Vp / (1 - this.g_y_AG) / 3600;
    double liquidMolarFlow = this.Lp / (1 - this.g_x_AL) / 3600;
    
    /* Create the Ridders object */
    try{
      Ridders ridders = new Ridders(this);
      /* Create array which will return dZ's and y_AG */
      double[] dz = new double[3];
      
      /* Set current molar masses */
      double currentLiquidMolecularMass = Constant.getMW_L() * (1 - this.g_x_AL) + Constant.getMW_A() * this.g_x_AL;
      double currentGasMolecularMass = Constant.getMW_V() * (1 - this.g_y_AG) + Constant.getMW_A() * this.g_y_AG;
      
      /* Set mass fluxes*/
      double liquidMassFlux = liquidMolarFlow * currentLiquidMolecularMass / getColumnAc();
      double gasMassFlux = gasMolarFlow * currentGasMolecularMass / getColumnAc();
      
      /* Set transfer coefficient (also used in hasRoot )*/
      this.g_kxa = 1 / ((getColumnAc() / liquidMolarFlow) * (0.357 / Constant.getPackingValues()[packingType][4]) * Math.sqrt(this.N_ScL / 372) * Math.pow(((liquidMassFlux / Constant.getVisL()) / (6.782 / 0.0008937)), .3));
      this.g_kya = 1 / ((getColumnAc() / gasMolarFlow) * (0.226 / Constant.getPackingValues()[packingType][4]) * Math.sqrt(this.N_ScV / 0.66) * (1 / Math.sqrt(liquidMassFlux / 6.782)) * Math.pow((gasMassFlux / 0.678), .35));
      
      /* Calculate new equilibrium liquid fraction (uses hasRoot) */
      double xAI = ridders.findRoot(xAL + .0000000001, this.g_maxXAI);
      double yAI = this.eqCurve.newYA(xAI);
      
      /* Ensures stripping does not occur in column */
      if(yAI>=this.g_y_AG){
        double[][] tempArray=new double[9][g_count+1];
        for (int i = 0; i < 8; i++)
        {
          for (int j = 0; j <= g_count; j++)
          {
            tempArray[i][j] = equilibriumProfile[i][j];
          }
        }
        for (int j = 0; j <= g_count; j++)
        {
          if (tempArray[0][j] >= tempArray[4][j])
          {
            tempArray[8][j] = tempArray[0][j];
          }
          else
          {
            tempArray[8][j] = tempArray[4][j];
          }
        }
        Menu.printEquilibriumProfileToCSV(tempArray);
        System.out.println("Insufficient liquid flow rate to perform desired seperation");
        System.exit(0);
      }
      
      /* Fill equilibriumProfile for current iteration */
      this.equilibriumProfile[1][g_count] = xAI;
      this.equilibriumProfile[2][g_count] = yAI;
      this.equilibriumProfile[5][g_count] = this.g_x_AL;
      this.equilibriumProfile[6][g_count] = this.g_y_AG;
      this.equilibriumProfile[3][g_count] = liquidMolarFlow / ((this.g_kxa * this.getColumnAc() / this.g_remainderLogMeanX) * (1 - this.g_x_AL) * (xAI - this.g_x_AL));
      this.equilibriumProfile[7][g_count] = gasMolarFlow / (((this.g_kya * getColumnAc()) / this.g_remainderLogMeanY) * (1 - this.g_y_AG) * (this.g_y_AG - yAI));
      
      /* Fill return array */
      dz[0] = liquidMolarFlow / ((this.g_kxa * this.getColumnAc() / this.g_remainderLogMeanX) * (1 - this.g_x_AL) * (xAI - this.g_x_AL));
      dz[1] = gasMolarFlow / (((this.g_kya * getColumnAc()) / this.g_remainderLogMeanY) * (1 - this.g_y_AG) * (this.g_y_AG - yAI));
      dz[2] = this.g_y_AG;
      
      this.g_count++;
      
      return dz;
      }
    catch(NoRootException e){
        System.exit(0);
      }
    catch(CouldNotConstructObjectException e){
      System.out.println(e.getMessage());
      System.exit(0);
    }
    catch(Exception e){
      System.out.println("Object was not created");
      System.exit(0);
    }
    return null;
  } // End of numericalIntegral
  
  public double getKxa()
  {
    return this.g_kxa; 
  } // End of getKxa accessor
  
  /* Store the equation that bounds the root and associated equations */
  public double hasRoot(double xAI)
  {
    /* Calculate new yAI */
    try{
      double yAI = eqCurve.newYA(xAI);
      
      /* Update log mean remainder values */
      this.g_remainderLogMeanX = ((1 - this.g_x_AL) - (1 - xAI)) / Math.log((1 - this.g_x_AL) / (1 - xAI));
      this.g_remainderLogMeanY = ((1 - yAI) - (1 - this.g_y_AG)) / Math.log((1 - yAI) / (1 - this.g_y_AG));
      
      /* Create and calculate equation to bound root */
      double m = -(this.g_kxa / this.g_remainderLogMeanX) / (this.g_kya / this.g_remainderLogMeanY);
      double b = 0.5 * (this.g_y_AG + yAI - m * (xAI + this.g_x_AL));
      
      return (yAI - (m * xAI + b));
    }
    catch(RangeLimitException e){
      System.out.println(e.getMessage());
      System.exit(0);
    }
    return 0;
  } // End hasRoot method
  
} // End of AbsorptionColumn class