import java.util.*;
import java.io.*;

public class Constant
{
  public final static double numStepUpperLimit = 10000;       // Number of steps for solving numerical integration upper limit
  public final static double numStepLowerLimit = 1;           // Number of steps for solving numerical integration lower limit
  public final static double RUpperLimit = 99.9;              // Percent recovery upper limit
  public final static double RLowerLimit = 0.1;               // Percent recovery lower limit
  public final static double V_0UpperLimit = 1000000000;      // Inlet vapour flow rate upper limit
  public final static double V_0LowerLimit = 0.001;           // Inlet vapour flow rate lower limit
  public final static double L_NUpperLimit = 1000000000;      // Inlet liquid flow rate upper limit
  public final static double L_NLowerLimit = 0.001;           // Inlet liquid flow rate lower limit
  public final static double y_A0UpperLimit = 0.5;            // Inlet vapour mole fraction of component A upper limit
  public final static double y_A0LowerLimit = 0.00;           // Inlet vapour mole fraction of component A lower limit
  public final static double x_ANUpperLimit = 0.25;           // Inlet liquid mole fraction of component A upper limit
  public final static double x_ANLowerLimit = 0.00;           // Inlet liquid mole fraction of component A lower limit

  public static int order;                                    // Order of the equilibrium equation
  public static double[] eqCoeff;                             // Equilbium coefficients 
  
  private static double D_ABV;                                // Needs to be labeled
  private static double D_ABL;                                // Needs to be labeled
  private static double visL;                                 // Viscosity of the liquid phase
  private static double visV;                                 // Viscosity of the vapour phase
  private static double denL;                                 // Density of the liquid phase
  private static double denV;                                 // Density of the vapour phase
  private static double MW_A;                                 // Molar weight of component A
  private static double MW_L;                                 // Molar weight of the liquid phase
  private static double MW_V;                                 // Molar weight of the vapour phase
  private static double packingMatD;                          // Packing material diameter [mm]
  
  /* {[0]: Raschig Rings, [1]: Ceramic Saddles, [2]: Pall Rings} */
  private static double[][] packingValues = new double[3][6];
  
  static
  {
    Scanner equilibriumInputStream = null;
    Scanner constantsInputStream = null;
    
    /* Attempt to open the EquilibriumData.txt file so the equilibrium coefficients can be later imported */
    try
    {
      equilibriumInputStream = new Scanner(new FileInputStream("src/Inputs/EquilibriumData.txt"));
    }
    catch (FileNotFoundException e)
    {
      Menu.clear();
      System.out.println("Critical Error: The EquilibriumData.txt file can not be found or could not be opened!");
      System.exit(0);
    }
    catch (Exception e)
    {
      Menu.clear();
      System.out.println("Critical Error: Unknown exception!");
      System.exit(0);
    }
    
    /* Attempt to open the Constants.txt file so the constants can later be imported */
    try
    {
      constantsInputStream = new Scanner(new FileInputStream("src/Inputs/Constants.txt"));
    }
    catch (FileNotFoundException e)
    {
      Menu.clear();
      System.out.println("Critical Error: The Constants.txt file can not be found or could not be opened!");
      System.exit(0);
    }
    catch (Exception e)
    {
      Menu.clear();
      System.out.println("Critical Error: Unknown exception!");
      System.exit(0);
    }
    
    /* Read in equilibrium coefficients from EquilibriumData.txt */
    try
    {
      /* ------------------------------> Set Equilibrium Coefficients  <--------------------------- */
      equilibriumInputStream.nextLine();
      Constant.order = (int)Double.parseDouble(equilibriumInputStream.nextLine().split("(:)", 2)[1]);
      Constant.eqCoeff = new double[order + 1];

      for (int i = 0; i <= order; i++)
      {
        Constant.eqCoeff[i] = Double.parseDouble(equilibriumInputStream.nextLine());
      }
      /* ------------------------------------------------------------------------------------------ */
      
      /* Check if equilibrium coefficients are within range */
      if (order < 0)
      {
        throw new RangeLimitException("order");
      }
    }
    catch (NullPointerException e)
    {
      Menu.clear();
      System.out.println("Critical Error: An empty string was passed!");
      System.exit(0);
    }
    catch (NumberFormatException e)
    {
      Menu.clear();
      System.out.println("Critical Error: A string did not contain a valid double value!");
      System.exit(0);
    }
    catch (RangeLimitException e)
    {
      Menu.clear();
      System.out.println(e);
      System.exit(0);
    }
    catch (Exception e)
    {
      Menu.clear();
      System.out.println("Critical Error: Unknown exception!");
      System.exit(0);
    }
    
    /* Read in constant values from Constants.txt */
    try
    { 
      /* ------------------------------> Set Constant Values <------------------------------------- */
      Constant.D_ABV = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.D_ABL = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.visL = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.visV = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.denL = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.denV = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.MW_A = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.MW_L = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.MW_V = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingMatD = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[0][0] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[0][1] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[0][2] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[0][3] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[0][4] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[0][5] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[1][0] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[1][1] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[1][2] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[1][3] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[1][4] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[1][5] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[2][0] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[2][1] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[2][2] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[2][3] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[2][4] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      Constant.packingValues[2][5] = Double.parseDouble(constantsInputStream.nextLine().split("(=)|(//)", 3)[1]);
      /* ------------------------------------------------------------------------------------------ */
      
      /* Check if constants are within range */
      if (Constant.D_ABV <= 0)
      {
        throw new RangeLimitException("D_ABV");
      }
      else if (Constant.D_ABL <= 0)
      {
        throw new RangeLimitException("D_ABL");
      }
      else if (Constant.visL <= 0)
      {
        throw new RangeLimitException("visL");
      }
      else if (Constant.visV <= 0)
      {
        throw new RangeLimitException("visV");
      }
      else if (Constant.denL <= 0)
      {
        throw new RangeLimitException("denL");
      }
      else if (Constant.denV <= 0)
      {
        throw new RangeLimitException("denV");
      }
      else if (Constant.MW_A <= 0)
      {
        throw new RangeLimitException("MW_A");
      }
      else if (Constant.MW_L <= 0)
      {
        throw new RangeLimitException("MW_L");
      }
      else if (Constant.MW_V <= 0)
      {
        throw new RangeLimitException("MW_V");
      }
      else if (Constant.packingMatD <= 0)
      {
        throw new RangeLimitException("packingMatD");
      }
      else if (Constant.packingValues[0][0] <= 0)
      {
        throw new RangeLimitException("packingValues[0][0]");
      }
      else if (Constant.packingValues[0][1] <= 0)
      {
        throw new RangeLimitException("packingValues[0][1]");
      }
      else if (Constant.packingValues[0][2] <= 0)
      {
        throw new RangeLimitException("packingValues[0][2]");
      }
      else if (Constant.packingValues[0][3] <= 0)
      {
        throw new RangeLimitException("packingValues[0][3]");
      }
      else if (Constant.packingValues[0][4] <= 0)
      {
        throw new RangeLimitException("packingValues[0][4]");
      }
      else if (Constant.packingValues[0][5] <= 0)
      {
        throw new RangeLimitException("packingValues[0][5]");
      }
      else if (Constant.packingValues[1][0] <= 0)
      {
        throw new RangeLimitException("packingValues[1][0]");
      }
      else if (Constant.packingValues[1][1] <= 0)
      {
        throw new RangeLimitException("packingValues[1][1]");
      }
      else if (Constant.packingValues[1][2] <= 0)
      {
        throw new RangeLimitException("packingValues[1][2]");
      }
      else if (Constant.packingValues[1][3] <= 0)
      {
        throw new RangeLimitException("packingValues[1][3]");
      }
      else if (Constant.packingValues[1][4] <= 0)
      {
        throw new RangeLimitException("packingValues[1][4]");
      }
      else if (Constant.packingValues[1][5] <= 0)
      {
        throw new RangeLimitException("packingValues[1][5]");
      }
      else if (Constant.packingValues[2][0] <= 0)
      {
        throw new RangeLimitException("packingValues[2][0]");
      }
      else if (Constant.packingValues[2][1] <= 0)
      {
        throw new RangeLimitException("packingValues[2][1]");
      }
      else if (Constant.packingValues[2][2] <= 0)
      {
        throw new RangeLimitException("packingValues[2][2]");
      }
      else if (Constant.packingValues[2][3] <= 0)
      {
        throw new RangeLimitException("packingValues[2][3]");
      }
      else if (Constant.packingValues[2][4] <= 0)
      {
        throw new RangeLimitException("packingValues[2][4]");
      }
      else if (Constant.packingValues[2][5] <= 0)
      {
        throw new RangeLimitException("packingValues[2][5]");
      } 
    }
    catch (NullPointerException e)
    {
      Menu.clear();
      System.out.println("Critical Error: An empty string was passed!");
      System.exit(0);
    }
    catch (NumberFormatException e)
    {
      Menu.clear();
      System.out.println("Critical Error: A string did not contain a valid double value!");
      System.exit(0);
    }
    catch (RangeLimitException e)
    {
      Menu.clear();
      System.out.println(e);
      System.exit(0);
    }
    catch (Exception e)
    {
      Menu.clear();
      System.out.println("Critical Error: Unknown exception!");
      System.exit(0);
    }

    equilibriumInputStream.close();
    constantsInputStream.close();
  } // End of static block
  
  public static double getOrder()
  {
    return Constant.order;
  } // End of getOrder accessor
  
  public static double[] getEqCoeff()
  {
    double[] equilibriumCoefficients = new double[Constant.eqCoeff.length];
    
    for (int i = 0; i < Constant.eqCoeff.length; i++)
    {
      equilibriumCoefficients[i] = Constant.eqCoeff[i];
    }
        
    return equilibriumCoefficients;
  } // End of getEqCoeff accessor
    
  public static double getD_ABV()
  {
    return Constant.D_ABV;
  } // End of getD_ABV accessor
  
  public static double getD_ABL()
  {
    return Constant.D_ABL;
  } // End of getD_ABL accessor
  
  public static double getVisL()
  {
    return Constant.visL;
  } // End of getVisL accessor
  
  public static double getDenL()
  {
    return Constant.denL;
  } // End of getDenL accessor
  
  public static double getVisV()
  {
    return Constant.visV;
  } // End of getVisV accessor
  
  public static double getDenV()
  {
    return Constant.denV;
  } // End of getDenV accessor
  
  public static double getMW_A()
  {
    return Constant.MW_A;
  } // End of getMW_A accessor
  
  public static double getMW_L()
  {
    return Constant.MW_L;
  } // End of getMW_L accessor
  
  public static double getMW_V()
  {
    return Constant.MW_V;
  } // End of getMW_V accessor
  
  public static double getPackingMatD()
  {
    return Constant.packingMatD;
  } // End of getPackingMatD accessor
  
  public static double[][] getPackingValues()
  {
    double[][] packingValues = new double[Constant.packingValues.length][Constant.packingValues[0].length];
    
    for (int i = 0; i < Constant.packingValues.length; i++)
    {
      for (int j = 0; j < Constant.packingValues[0].length; j++)
      {
        packingValues[i][j] = Constant.packingValues[i][j];
      }
    }
        
    return packingValues;
  } // End of getPackingMatD accessor
   
} // End of Constants class