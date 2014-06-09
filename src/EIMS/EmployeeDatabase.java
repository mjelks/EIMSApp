package EIMS;

import java.io.*;
import java.util.Arrays;

public class EmployeeDatabase {

  private static final int MAX_EMPLOYEE_COUNT = 1000;
  private int CURRENT_EMPLOYEE_COUNT;
  private Employee[] database = new Employee[MAX_EMPLOYEE_COUNT];

//  public static void main(String[] args) {
//	EmployeeDatabase empDB = new EmployeeDatabase();
//	empDB.buildDB();
//	empDB.pension(5);
//  }

  public void pension(int numberofyears) {		//determines which employees worked for more than 5 years and returns their names.

	for (int i = 0; i < CURRENT_EMPLOYEE_COUNT; i++) {
	  if (database[i].getPension() >= numberofyears) {
		System.out.println(database[i].getFirstname() + " is eligible for pension");
	  }
	}
  }

  public void buildDB() {

	BufferedReader br = null;
	String delimeters = "[~]";

	try {
	  br = new BufferedReader(new FileReader("Employees.txt"));

	  int i = 0;
	  String line = "";
	  while ((line = br.readLine()) != null) {
		String[] empl = line.split(delimeters);
		try {
		  int empID = Integer.parseInt(empl[2]);
		  double salary = Double.parseDouble(empl[3]);
		  int yearsofwork = Integer.parseInt(empl[5]);
		  database[i] = new Employee(empl[0], empl[1], empID,
				  salary, empl[4], yearsofwork, empl[6]);
		  if (i++ == MAX_EMPLOYEE_COUNT) {
			break;
		  }
		} catch (NumberFormatException e) {
		  System.out.println("Wrong info in line. Line ignored: " + line);
		}

	  }
	  br.close();
	  CURRENT_EMPLOYEE_COUNT = i;
	} catch (IOException e) {
	  e.printStackTrace();
	  if (br != null) {
		try {
		  br.close();
		} catch (IOException e1) {
		  // TODO Auto-generated catch block
		  e1.printStackTrace();
		}
		System.exit(0);
	  }
	}
	sort();
  }

  public void sort() {
	Arrays.sort(database, 0, CURRENT_EMPLOYEE_COUNT - 1);
  }

  public void list() {
	for (int i = 0; i < CURRENT_EMPLOYEE_COUNT; i++) {
	  System.out.println(database[i].getFirstname() + " is eligible for pension");
	}
  }
}
