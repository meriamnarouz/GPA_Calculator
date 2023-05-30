
/*
 * Description: This file is for all the helper methods to add grades, get total GPA,
 * 				remove a grade, update a grade, or list all grades. It is where the 
 * 				actual setting up is done. 
 */

import java.util.ArrayList;

public class CalculationOptions 
{
	ArrayList<Grades> gradeList;
	
	public CalculationOptions()
	{
		gradeList = new ArrayList<>();
	}

	//method adds the class and its grade if it is not a duplicate class
	public boolean addGrade(String className, char grade, String gradeCheck, int credit, double classGPA) 
	{		
		//for loop to check if it is already an existing class
		for(int i = 0; i < gradeList.size(); i++)
		{
			if(gradeList.get(i).getClassName().equalsIgnoreCase(className))
			{
				return false;
			}
		}
		
		//if it is not an existing class then add it
		Grades g = new Grades(className, grade, gradeCheck, credit, classGPA);
		
		g.setClassGPA(classGPA);
		
		gradeList.add(g);
		return true;
		
	}
	
	//list all information of every class if there are classes in the list
	public String listGrades()
	{
		if(gradeList.size() > 0)
		{
			//iterate through and add class info
			String str = "";
			for(int i = 0; i < gradeList.size(); i++)
			{
				str = str + gradeList.get(i).toString();
			}
			return str;
		}
		else
		{
			return "\nNo grades listed!\n\n";
		}
	}
	
	//method to remove specific classes
    public boolean removeGrade(String c)
    {
	   	 for(int i = 0; i < gradeList.size(); i++)
	   	 {
	   		 //remove by class name
	   		 if((gradeList.get(i).getClassName().equalsIgnoreCase(c)))	 
	   		 {
	   			 gradeList.remove(gradeList.get(i)); 
	   			 return true; 
	   		 } //end of if statement
	   	 } //end of for loop
	   	 
	   	return false; 
    }//end of remove grade
    
    //method to update class information
	public boolean updateGrade(String className, char grade, String gradeCheck, int credit, int decider) 
	{
		//for loop to find the class that needs to be updated
		for(int i = 0; i < gradeList.size(); i++)
		{
			if(gradeList.get(i).getClassName().equalsIgnoreCase(className))
			{
				//update based on if it is grade or gradeCheck or credits
				if(decider == 1)
				{
					//update the grade and gradeCheck
					if(gradeList.get(i).getGrade() == Character.toUpperCase(grade)
						|| gradeList.get(i).getGradeCheck().equalsIgnoreCase(gradeCheck))
					{
						return false;
					}
					else
					{
						gradeList.get(i).setGrade(grade);
						gradeList.get(i).setGradeCheck(gradeCheck);
						gradeList.get(i).setClassGPA(0.0);
						return true;
					}
					
				}
				else if(decider == 2)
				{
					//update the gradeCheck
					if(gradeList.get(i).getGradeCheck().equalsIgnoreCase(gradeCheck))
					{
						return false;
					}
					else
					{
						gradeList.get(i).setGradeCheck(gradeCheck);
						gradeList.get(i).setClassGPA(0.0);
						return true;
					}
					
				}
				else if(decider == 3)
				{
					//update the credit
					if(gradeList.get(i).getCredit() != credit)
					{
						gradeList.get(i).setCredit(credit);
						gradeList.get(i).setClassGPA(0.0);
						return true;
					}
					else
					{
						return false;
					}
				}
				
			}//end of if statement for getting class
		}//end of for loop traversing arraylist
					
		return false; 
	}

	//method to return GPA of all classes together
	public double totalGPA() 
	{
		
		double total = 0.0;
		
		//get all gpa info
		for(int i = 0; i < gradeList.size(); i++)
		{
			total = total + gradeList.get(i).getClassGPA();
		}
		
		//if statement so that if there are no grades in the list, 
		//we don't accidently divide by zero
		if(gradeList.size() > 0)
		{
			//get the average
			total = total / gradeList.size();
		}
		return total;
	}


	
}//end of class
