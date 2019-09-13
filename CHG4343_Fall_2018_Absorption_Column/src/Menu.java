import java.util.*;
import java.io.*;
import java.text.*;

public class Menu implements Root
{ 
  public static void mainMenu()
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("|                          Main Menu                          |");
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("| Options:                                                    |");
    System.out.println("|        1. Size Absorption Column Menu                       |");
    System.out.println("|        2. Optimize Absorption Column Menu                   |");
    System.out.println("|        3. About                                             |");
    System.out.println("|        4. Exit                                              |");
    System.out.println("|-------------------------------------------------------------|");
    
    System.out.print("Select Option: ");
      
    try
    {
      selection = scan.nextInt();
    }
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      mainMenu();
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      mainMenu();
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        sizeAbsorptionSubMenu();
        break;
      case 2:
        clear();
        optimizeAbsorptionSubMenu();
        break;
      case 3:
        clear();
        aboutSubMenu();
        break;
      case 4:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        mainMenu();
        break;
    } 
    scan.close();
  } // End of mainMenu method
  
  private static void sizeAbsorptionSubMenu()
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("|                 Size Absorption Column Menu                 |");
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("| Options:                                                    |");
    System.out.println("|        1. Size Absorption Column                            |");
    System.out.println("|        2. Instructions                                      |");
    System.out.println("|        3. Return                                            |");
    System.out.println("|        4. Exit                                              |");
    System.out.println("|-------------------------------------------------------------|");
    
    System.out.print("Select Option: ");
    
    try
    {   
      selection = scan.nextInt();
    }
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      sizeAbsorptionSubMenu();
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      sizeAbsorptionSubMenu();
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        sizeAbsorptionColumn();
        break;
      case 2:
        clear();
        sizeAbsorptionColumnInstructions();
        break;
      case 3:
        clear();
        mainMenu();
        break;
      case 4:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        sizeAbsorptionSubMenu();
        break;
    }
     scan.close();
  } // End of sizeAbsorptionMenu method

  private static void optimizeAbsorptionSubMenu()
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("|               Optimize Absorption Column Menu               |");
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("| Options:                                                    |");
    System.out.println("|        1. Optimize Absorption Column                        |");
    System.out.println("|        2. Instructions                                      |");
    System.out.println("|        3. Return                                            |");
    System.out.println("|        4. Exit                                              |");
    System.out.println("|-------------------------------------------------------------|");
    
    System.out.print("Select Option: ");
    
    try
    {   
      selection = scan.nextInt();
    }
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      optimizeAbsorptionSubMenu();
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      optimizeAbsorptionSubMenu();
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        optimizeAbsorptionColumn();
        break;
      case 2:
        clear();
        optimizeAbsorptionColumnInstructions();
        break;
      case 3:
        clear();
        mainMenu();
        break;
      case 4:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        optimizeAbsorptionSubMenu();
        break;
    }
     scan.close();
  } // End of optimizeAbsorptionSubMenu method
  
  private static void sizeAbsorptionColumnInstructions()
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("|=============================================================|");
    System.out.println("|             Size Absorption Column Instructions             |");
    System.out.println("|=============================================================|");
    System.out.println("|   a) Open the file containing the software .java files      |");
    System.out.println("|   b) Double click on the 'Inputs' folder to access files    |");
    System.out.println("|      for input parameters, constants and equlibirum data    |");
    System.out.println("|   c) Open the text file called InputValues and enter        |");
    System.out.println("|      desired user-specified values for your column          |");
    System.out.println("|   d) Save the InputValues text file                         |");
    System.out.println("|   e) Go to the AbsorptionDriver class, compile and run!     |");
    System.out.println("|   f) Find outputs for data analysis in the Outputs folder   |");
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("| Options:                                                    |");
    System.out.println("|        1. Return                                            |");
    System.out.println("|        2. Exit                                              |");
    System.out.println("|-------------------------------------------------------------|");
    
    System.out.print("Select Option: ");
    
    try
    {   
      selection = scan.nextInt();
    }
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      sizeAbsorptionColumnInstructions();
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      sizeAbsorptionColumnInstructions();
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        mainMenu();
        break;
      case 2:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        sizeAbsorptionColumnInstructions();
        break;
    }
     scan.close();
  } // End of sizeAbsorptionColumnInstructions method
  
  private static void optimizeAbsorptionColumnInstructions()
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("|=============================================================|");
    System.out.println("|           Optimize Absorption Column Instructions           |");
    System.out.println("|=============================================================|");
    System.out.println("|                                                             |");
    System.out.println("|   a) Enter your user-inputs                                 |");
    System.out.println("|   b) Select 'Optimize Absorption Column'                    |");
    System.out.println("|   c) The column will now be optimized                       |");
    System.out.println("|                                                             |");
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("| Options:                                                    |");
    System.out.println("|        1. Return                                            |");
    System.out.println("|        2. Exit                                              |");
    System.out.println("|-------------------------------------------------------------|");
    
    System.out.print("Select Option: ");
    
    try
    {   
      selection = scan.nextInt();
    }
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      optimizeAbsorptionColumnInstructions();
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      optimizeAbsorptionColumnInstructions();
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        mainMenu();
        break;
      case 2:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        optimizeAbsorptionColumnInstructions();
        break;
    }
     scan.close();
  } // End of optimizeAbsorptionColumnInstructions method
  
  private static void aboutSubMenu()
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    
    System.out.println("|=============================================================|");
    System.out.println("|                     About AbsorbSim Pro                     |");
    System.out.println("|=============================================================|");
    System.out.println("| This program was built by:                                  |");
    System.out.println("|   - David Beckham (alias)                                   |");
    System.out.println("|   - Peter Quill (alias)                                     |");
    System.out.println("|   - Devan Ethans (alias)                                    |");
    System.out.println("|   - Sparkling Soda (alias)                                  |");
    System.out.println("|   - Ken Bong (alias)                                        |");
    System.out.println("|-------------------------------------------------------------|");
    System.out.println("| Options:                                                    |");
    System.out.println("|        1. Return                                            |");
    System.out.println("|        2. Exit                                              |");
    System.out.println("|-------------------------------------------------------------|");
    
    System.out.print("Select Option: ");
    
    try
    {   
      selection = scan.nextInt();
    }
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      aboutSubMenu();
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      aboutSubMenu();
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        mainMenu();
        break;
      case 2:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        aboutSubMenu();
        break;
    }
     scan.close();
  } // End of aboutSubMenu method
  
  private static void sizeAbsorptionColumn()
  {
    double equilibriumProfile[][];
    
    double[] inputs = importInputValues();
    
    /* Create new absorption column and size it */
    try
    {
      AbsorptionColumn absorptionColumn = new AbsorptionColumn(inputs);
      equilibriumProfile = absorptionColumn.getEquilibriumProfile();

      /* Print out the equilibrium profile of the sized absorption column to a CSV file */
      Menu.printEquilibriumProfileToCSV(equilibriumProfile);
      
      /* Print out the equilibrium profile of the sized absorption column to console */
      Menu.printEquilibriumProfileToConsole(equilibriumProfile);
    }
    catch (CouldNotConstructObjectException e)
    {
      System.out.println(e.getMessage());
      System.exit(0);
    }
    catch (Exception e)
    {
      System.out.println("Critical Error: Could not size absorption column!");
      System.exit(0);
    }
  } // End of sizeAbsorptionColumn method
  
  private static void optimizeAbsorptionColumn()
  {
    double[] inputs = importInputValues();
    double lowerL_N = inputs[2] / 2.5;
    double upperL_N = Constant.L_NUpperLimit;
    double tol = 0.000000001;
    double[][] eqProfile;
    
    try
    {
      AbsorptionColumn absorptionColumn = new AbsorptionColumn(inputs);
      AbsorptionColumn tempAbsorpCol;
      eqProfile = absorptionColumn.getEquilibriumProfile();
      
      /* Check if absorption column is already sufficiently optimized */
      if (Math.abs(eqProfile[0][0] - eqProfile[4][0]) >= tol)
      {
        double L_N;
        double upperDiff;
        double lowerDiff;
        
        /* Find difference in lower limit */
        tempAbsorpCol = absorptionColumn.clone();
        tempAbsorpCol.setL_N(lowerL_N);
        eqProfile = tempAbsorpCol.getEquilibriumProfile();  
        upperDiff = Math.abs(eqProfile[0][0] - eqProfile[4][0]);
        
        /* Find difference in upper limit */
        tempAbsorpCol = absorptionColumn.clone();
        tempAbsorpCol.setL_N(upperL_N);
        eqProfile = tempAbsorpCol.getEquilibriumProfile();   

        lowerDiff = Math.abs(eqProfile[0][0] - eqProfile[4][0]);
        
        /* If there is a root, use Ridders */
        if (lowerDiff * upperDiff < 0)
        {
          Menu menu = new Menu();
          Ridders ridders = new Ridders(menu);
          L_N = ridders.findRoot(lowerL_N, upperL_N);
          tempAbsorpCol = absorptionColumn.clone();
          tempAbsorpCol.setL_N(L_N);
          eqProfile = tempAbsorpCol.getEquilibriumProfile();   
        }
        /* If no root is found, incrementally search */
        else 
        {
          L_N = lowerL_N;
            
          do
          {
            tempAbsorpCol = absorptionColumn.clone();
            tempAbsorpCol.setL_N(L_N);
            eqProfile = tempAbsorpCol.getEquilibriumProfile();  
            
            L_N = L_N + 0.1;
          } while (Math.abs(eqProfile[0][0] - eqProfile[4][0]) >= tol);
        }         
        
        L_N = L_N - 0.1;
        
        /* Print out liquid flow rate */
        System.out.printf("The liquid flow rate (L_N) is: %5.2f\n", L_N);
        
        /* Print out the equilibrium profile of the sized absorption column to a CSV file */
        Menu.printEquilibriumProfileToCSV(eqProfile);
      
        /* Print out the equilibrium profile of the sized absorption column to console */
        Menu.printEquilibriumProfileToConsole(eqProfile);
      }
      else
      {
        System.out.println("The column is already sufficiently optimized!");
        Menu.sizeAbsorptionColumn();
      } 
      
    }
    
    catch (CouldNotConstructObjectException e)
    {
      System.out.println(e.getMessage());
      System.exit(0);
    }
    catch (Exception e)
    {
      System.out.println("Critical error: The absorption column could not be optimized!");
      System.exit(0);
    }
  } // End of optimizeAbsorptionColumn method
  
  private static void printEquilibriumProfileToConsole(double equilibriumProfile[][])
  {
    int selection = 0;
    Scanner scan = new Scanner(System.in);
    int lastElement;
    
    try 
    {
      /* Find the last element of the equilibrium profile array */
      lastElement = equilibriumProfile[0].length - 1;
      
      System.out.println("|=============================================================|");
      System.out.println("|                           Results                           |");
      System.out.println("|=============================================================|");
      System.out.println("| Final parameters:                                           |");
      System.out.printf("|   x_Ai: %6.3f                                              |\n", equilibriumProfile[1][lastElement]);
      System.out.printf("|   y_Ai: %6.3f                                              |\n", equilibriumProfile[2][lastElement]);
      System.out.printf("|   x_AL: %6.3f                                              |\n", equilibriumProfile[5][lastElement]);
      System.out.printf("|   y_AG: %6.3f                                              |\n", equilibriumProfile[6][lastElement]);
      System.out.printf("|   dZ_L: %6.3f                                              |\n", equilibriumProfile[3][lastElement]);
      System.out.printf("|   dZ_V: %6.3f                                              |\n", equilibriumProfile[7][lastElement]);
      System.out.printf("|   Liquid Height: %6.3f                                     |\n", equilibriumProfile[0][0]);
      System.out.printf("|   Vapour Height: %6.3f                                     |\n", equilibriumProfile[4][0]);
      System.out.printf("|   Required Height: %6.3f                                   |\n", equilibriumProfile[8][0]);
      System.out.println("|                                                             |");
      System.out.println("|                                                             |");
      System.out.println("| The equilibrium data profile may be found in /Outputs       |");
      System.out.println("|-------------------------------------------------------------|");
      System.out.println("| Options:                                                    |");
      System.out.println("|        1. Return                                            |");
      System.out.println("|        2. Exit                                              |");
      System.out.println("|-------------------------------------------------------------|");
      
      System.out.print("Select Option: ");
      
    }
    catch(Exception e) 
    {
      Menu.clear();
      System.out.println("Critical Error: Unknown exception!");
      System.exit(0);
    }
    
    try
    {   
      selection = scan.nextInt();
    }
    
    catch(InputMismatchException e)
    {
      clear();
      System.out.println("Invalid Selection!");
      printEquilibriumProfileToConsole(equilibriumProfile);
    }
    catch(Exception e)
    {
      clear();
      System.out.println("Invalid Selection!");
      printEquilibriumProfileToConsole(equilibriumProfile);
    }
    
    switch (selection) 
    {
      case 1:
        clear();
        mainMenu();
        break;
      case 2:
        clear();
        System.out.println("Goodbye!");
        System.exit(0);
        break;
      default:
        clear();
        System.out.println("Invalid Selection!");
        printEquilibriumProfileToConsole(equilibriumProfile);
        break;
    }
    scan.close();
  } // End of printEquilibriumProfileToConsole
  
  public static boolean printEquilibriumProfileToCSV(double equilibriumProfile[][])
  {
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");  
    String name = ("Outputs/Results" + " ");
    
    /* Replaces all ":" characters with "-" because ":" can not be included in a file name */
    String legalDate = dateFormat.format(date).replaceAll(":", "-");
    
    String type = ".csv";
    File file = new File(name + legalDate + type);
    
    /* Makes sure the file does not exist */
    while (file.exists())
    {
      date = new Date();
      legalDate = dateFormat.format(date).replaceAll(":", "-");
      file = new File(name + legalDate + type);
    }
    
    try
    {
      double[] inputs = importInputValues();
      file.createNewFile(); 
      FileWriter writer = new FileWriter(file);
      writer.write("x_Ai, y_Ai, x_AL, y_AG, dZ_L, dZ_V, Liquid Column Height, Vapour Column Height, Required Height\n");
      
      
      for (int j = 0; j < equilibriumProfile[0].length; j++)
      {
        writer.append(equilibriumProfile[1][j] + "," + equilibriumProfile[2][j] + "," + equilibriumProfile[5][j] + "," + equilibriumProfile[6][j] + "," + equilibriumProfile[3][j] + "," + equilibriumProfile[7][j] + "," + equilibriumProfile[0][j] + "," + equilibriumProfile[4][j] + "," + equilibriumProfile[8][j]+ "\n");   
      }
      if (equilibriumProfile[8][0]/(Constant.getPackingValues()[(int)inputs[6]][0] * Constant.getPackingMatD()/1000)>30){
        writer.append("WARNING: The column height to diamter ratio is "+equilibriumProfile[8][0]/(Constant.getPackingValues()[(int)inputs[6]][0] * Constant.getPackingMatD()/1000)+", above the recommended value of 30");
      }
      if (equilibriumProfile[8][0]/(Constant.getPackingValues()[(int)inputs[6]][0] * Constant.getPackingMatD()/1000)<1.5){
        writer.append("WARNING: The column height to diamter ratio is "+equilibriumProfile[8][0]/(Constant.getPackingValues()[(int)inputs[6]][0] * Constant.getPackingMatD()/1000)+", bellow the recommended value of 1.5");
      }
       writer.close();
    }

    catch(FileNotFoundException e)
    {
      System.out.println(e.getMessage() + " for file: " + file);
      System.exit(0);
    }
    catch(IOException e)
    {
      System.out.println(e.getMessage() + " for file: " + file);
      System.exit(0);
    }
    catch(Exception e)
    {
      System.out.println(e.getMessage() + " for file: " + file);
      System.exit(0);
    }
    
    return true;
  } // End of printToCSV method
  
  private static double[] importInputValues()
  {
    Scanner inputStream = null;
    double inputs[] = new double[7];
    try
    {
      inputStream = new Scanner(new FileInputStream("Inputs/InputValues.txt"));
    }
    catch (FileNotFoundException e)
    {
      clear();
      System.out.println("System Error: The InputValues.txt file can not be found or could not be opened!");
      System.exit(0);
    }
    catch (Exception e)
    {
      clear();
      System.out.println("System Error: Unknown exception!");
      System.exit(0);
    }
    
    try
    {
      /* ----------------------------> Set Input Values <---------------------------- */
      inputs[0] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // numStep: Number of steps for solving numerical integration
      inputs[1] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // R: Percent recovery
      inputs[2] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // V_0: Inlet vapour flow rate
      inputs[3] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // y_A0: Inlet vapour mole fraction of component A
      inputs[4] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // L_N: Inlet liquid flow rate
      inputs[5] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // x_AN: Inlet liquid mole fraction of component A
      inputs[6] = Double.parseDouble(inputStream.nextLine().split("(=)|(//)", 3)[1]);       // packingType: 0 - Rashig Rings, 1 - Berl Saddles, 2 - Pall Rings
      /* ---------------------------------------------------------------------------- */
      
      /* Check if input values are within range */
      if (!Column.numStepInRangeCheck(inputs[0]))
      {
        throw new RangeLimitException("numStep");
      }
      else if (!Column.RInRangeCheck(inputs[1]))
      {
        throw new RangeLimitException("R");
      }
      else if (!AbsorptionColumn.V_0InRangeCheck(inputs[2]))
      {
        throw new RangeLimitException("V_0");
      }
      else if (!AbsorptionColumn.y_A0InRangeCheck(inputs[3]))
      {
        throw new RangeLimitException("y_A0");
      }
      else if (!AbsorptionColumn.L_NInRangeCheck(inputs[4]))
      {
        throw new RangeLimitException("L_N");
      }
      else if (!AbsorptionColumn.x_ANInRangeCheck(inputs[5]))
      {
        throw new RangeLimitException("x_AN");
      }
      else if (!AbsorptionColumn.packingTypeInRangeCheck(inputs[6]))
      {
        throw new RangeLimitException("packingType");
      }
      
    }
    catch (NullPointerException e)
    {
      clear();
      System.out.println("Critical Error: An empty string was passed!");
      System.exit(0);
    }
    catch (NumberFormatException e)
    {
      clear();
      System.out.println("Critical Error: A string did not contain a valid double value!");
      System.exit(0);
    }
    catch (RangeLimitException e)
    {
      clear();
      System.out.println(e);
      System.exit(0);
    }
    catch (Exception e)
    {
      clear();
      System.out.println("Critical Error: Unknown exception!");
      System.exit(0);
    }
    
    inputStream.close();
    return inputs;
  } // End of importInputValues method
  
  public double hasRoot(double L_N)
  {
    double[][] eqProfile;
    double[] inputs = importInputValues();
    inputs[4] = L_N;
    
    AbsorptionColumn absorptionColumn = new AbsorptionColumn(inputs);
    eqProfile = absorptionColumn.getEquilibriumProfile();
    
    return eqProfile[0][0] - eqProfile[4][0];
  } // End of hasRoot method
  
  /* This is needed in order for the Root interface to be included */
  public Menu clone()
  {
    return new Menu();
  }
  
  public static void clear()
  {
    System.out.printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
  } // End of clear method
    
} // End of Menu class