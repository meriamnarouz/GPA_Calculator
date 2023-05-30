
/*
 * Description: This file contains the getters and setters for all info
 * 				for a singular class such as CSE110. 
 */


public class Grades 
{
	private String className;
	private char grade;
	private String gradeCheck;
	private int credit;
	private double classGPA;
	
	public Grades(String className, char grade, String gradeCheck, int credit, double classGPA)
	{
		this.className = className;
		this.grade = grade;
		this.gradeCheck = gradeCheck;
		this.credit = credit;
	}
	
	//all getters
	public String getClassName()
	{
		return className;
	}
	
	public char getGrade()
	{
		return grade;
	}
	
	public String getGradeCheck()
	{
		return gradeCheck;
	}
	
	public int getCredit()
	{
		return credit;
	}	
	
	public double getClassGPA()
	{
		return classGPA;
	}

	
	//all setters
	public void setGrade(char grade) 
	{
		this.grade = Character.toUpperCase(grade);
	}
	
	public void setGradeCheck(String gradeCheck)
	{		
		this.gradeCheck = gradeCheck.toUpperCase();
	}
	
	public void setCredit(int credit)
	{
		this.credit = credit;
	}

	//set up the GPA for the class correctly
	public void setClassGPA(double classGPA)
	{
		switch(getGrade())
		{
		case 'A':
			classGPA = 4.00;										
		break;
		
		case 'B':
			classGPA = 3.00;
		break;
		
		case 'C':
			classGPA = 2.00;
		break;
		
		case 'D':
			classGPA = 1.00;
		break;
		
		default:
		break;
		}
		
		if(getGrade() != 'E')
		{
			if(getGradeCheck().equalsIgnoreCase("plus"))
			{
				classGPA = classGPA + 0.33;
			}
			else if(getGradeCheck().equalsIgnoreCase("minus"))
			{
				classGPA = classGPA - 0.33;
			}
		}
				
		this.classGPA = classGPA;
	}
	
	//method to convert it all into a string
	public String toString()
	{
		String str = ""; //string to be returned
		String gc = ""; //so that you can customize the actual grade if its an A- or an A
		
		if(!gradeCheck.equalsIgnoreCase("none"))
		{
			gc = gradeCheck;
		}
				
		//string to store all info to return;
		str = "Class Name: " + className 
				+ "\n     Grade: " + grade + " " + gc
				+"\n    Credit: " + credit
				+"\n Class GPA: " + String.format("%.2f", classGPA) + "\n\n";
		return str;
	}//end of toString method
	
	
}
