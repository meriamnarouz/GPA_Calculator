
/*
 * Description: This file is to ask the user which action they want to do
 * 				and to take all necessary info from the user. This is a GPA 
 * 				calculator so classes can be added and GPA will be calculated. 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CalculatorGPA 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		//local variables
		//input info
		char inputOpt = ' ';
        String inputLine;
        
        //Class and grade info
        String className = "";
        char grade = ' ';
        String gradeCheck = "";
        int credit = 0;
        double classGPA = 0.0;
        
        //Calculation Options
        CalculationOptions calOp = new CalculationOptions();
        
        
        try {            
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader stdin = new BufferedReader(isr);

            do {
            	printMenu();
                System.out.print("\nWhat action would you like to perform?\n");
                inputLine = stdin.readLine().trim();
                if (inputLine.isEmpty()) {
                    continue;
                }
                inputOpt = inputLine.charAt(0);
                inputOpt = Character.toUpperCase(inputOpt);

                switch (inputOpt) {

                    case 'A': // Add a new class and grade
                        System.out.print("Please enter the class information:\n");
                        System.out.print("Enter the class name without spacing:\n");
                        className = stdin.readLine().trim();
                        className = className.toUpperCase();
                        System.out.print("Enter the grade (A, B, C, D, E) without a plus or minus:\n");
                        grade = (stdin.readLine().trim()).charAt(0);
                        grade = Character.toUpperCase(grade);
                        
                        //has to be one of the given grades
                        while(grade != 'A' && grade != 'B' && grade != 'C'
                        	  && grade != 'D' && grade != 'E')
                        {
                        	System.out.print("Please enter a valid grade without plus or minus:\n");
                            grade = (stdin.readLine().trim()).charAt(0);
                            grade = Character.toUpperCase(grade);
                        }
                        
                        //an E grade does not have a plus or minus 
                        if(grade != 'E')
                        {
                        	System.out.print("Enter 'plus' or 'minus' or 'none' for the grade:\n");
                            gradeCheck = stdin.readLine().trim();
                            gradeCheck = gradeCheck.toUpperCase();
                        }
                        
                        System.out.print("Enter the number of credits for this class:\n");
                        credit = Integer.parseInt(stdin.readLine().trim());
                        
                        //check if class exits or not
                        if (calOp.addGrade(className, grade, gradeCheck, credit, classGPA))
                        {
                        	System.out.print("Class added\n");
                        }
                        else 
                        {
                        	System.out.print("Duplicate class, class NOT added\n");
                        }//end of if statement
                    break;
                    
                    case 'C': //Check GPA
                    	System.out.print("\nYour total GPA: " + String.format("%.2f", calOp.totalGPA()) + "\n\n");
                    break;
                                         
                    case 'L': // List all grades
                        System.out.print("\n" + calOp.listGrades() + "\n");
                        break;             
                                    
                    case 'Q': // Quit
                        break;

                    case 'R': // Remove a grade
                        System.out.print("Please enter the class name of the class you want to remove:\n");
                        className = stdin.readLine().trim();

                        //if statement to check if it was removed
                        if(calOp.removeGrade(className)) 
                        {
                        	System.out.print(className.toUpperCase() + " was removed\n");
                        }
                        else 
                        {
                        	//the reason it wasn't removed is because it does not exist
                        	System.out.print(className.toUpperCase() + " was NOT removed\n");
                        }//end of if else statement
                    break;
                    
                    case 'U': //update a grade
                        System.out.print("Please enter the class name of the grade you want to update:\n");
                        className = stdin.readLine().trim();
                        
                        System.out.print("Please choose what you would like to update in your class:\n"
                        					+ "grade OR gradeCheck(plus, minus or none) OR credit\n");
                        String cin = stdin.readLine().trim();
                        
                        int decider = 0; //to tell me which of the three is going to be updated
                        
                        //if the incorrect info is not given, repeat until it is given
                        while((!cin.equalsIgnoreCase("grade"))
                        		&& (!cin.equalsIgnoreCase("gradeCheck")) && (!cin.equalsIgnoreCase("grade check"))
                        		&& (!cin.equalsIgnoreCase("credit")))
                        {
                            System.out.print("Please enter a valid option such as \n"
                           			+ "grade OR gradeCheck(plus, minus or none) OR credit:\n");
                           	cin = stdin.readLine().trim();
                        }

                        if(cin.equalsIgnoreCase("grade"))
                        {
                        	System.out.print("Enter the grade (A, B, C, D, E) without a plus or minus:\n");
                        	grade = (stdin.readLine().trim()).charAt(0);
                        	
                        	System.out.print("Please enter the gradeCheck(plus, minus or none)\n");
                        	gradeCheck = stdin.readLine().trim();
                        	decider = 1;
                        }
                        else if((cin.equalsIgnoreCase("gradeCheck")) || (cin.equalsIgnoreCase("grade check")))
                        {
                        	System.out.print("Please enter the gradeCheck(plus, minus or none)\n");
                        	gradeCheck = stdin.readLine().trim();
                        	decider = 2;
                        }
                        else if(cin.equalsIgnoreCase("credit"))
                        {
                        	System.out.print("Enter the number of credits for this class:\n");
                        	credit = Integer.parseInt(stdin.readLine().trim());
                        	decider = 3;
                        }
                        
                        //if statement to check if it was updated
                        if(calOp.updateGrade(className, grade, gradeCheck, credit, decider)) 
                        {
                        	System.out.print(className.toUpperCase() + " was updated\n");
                        }
                        else 
                        {
                        	//it was not updated because it did not exist or it was the same information
                        	System.out.print(className.toUpperCase() + " was NOT updated\n");
                        }//end of if else statement
                    break;
                                
                    case '?': // Display help
                        printMenu();
                        break;

                    default:
                        System.out.print("Unknown action\n");
                        break;
                }

            } while (inputOpt != 'Q' || inputLine.length() != 1);
        }
        catch (IOException exception) {
            System.out.print("IO Exception\n");
        }
		
	}

	private static void printMenu() 
	{
		// TODO Auto-generated method stub
		System.out.println("Choose the option to calculate your GPA!");

        System.out.print("Choice\t\tAction\n" + "------\t\t------\n" 
        		+ "A\t\tAdd a grade\n" + "C\t\tCheck total GPA\n"
                + "L\t\tList all grades\n" + "Q\t\tQuit\n" 
                + "R\t\tRemove a grade\n" + "U\t\tUpdate a grade\n"
                + "?\t\tDisplay Help\n");
		
	}
	

}
