package EIMS;

import java.io.*;
import java.util.Arrays;
import javax.swing.*;

public class EmployeeDatabase {

  private static final int MAX_EMPLOYEE_COUNT = 1000;
  private int CURRENT_EMPLOYEE_COUNT;
  private Employee[] database = new Employee[MAX_EMPLOYEE_COUNT];

  //determines which employees worked for more than 
  // 5 years and returns their names.
  public void pension(int numberofyears) {		

	for (int i = 0; i < CURRENT_EMPLOYEE_COUNT; i++) {
	  if (database[i].getPension() >= numberofyears) {
		System.out.println(database[i].getFirstname() + 
                " is eligible for pension");
	  }
	}
  }
  
  public void buildDB() {

	BufferedReader br = null;
	String delimeters = "[~;]";

	try {
	  InputStreamReader reader = new InputStreamReader(
        EmployeeDatabase.class.getResourceAsStream("Employees.txt"), "UTF-8");
	  br = new BufferedReader(reader);

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
  
  public Employee[] getDatabase() {
    return this.database;
  }

  public void sort() {
	  for (int a = 1; a < CURRENT_EMPLOYEE_COUNT; a++) {
	        for (int b = 0; b < CURRENT_EMPLOYEE_COUNT - a; b++) {
	            if (((database[b].getID()) > (database[b + 1].getID()))) {
	                // swap movies[b] with movies[b+1]
	                Employee temp = database[b];
	                database[b] = database[b + 1];
	                database[b + 1] = temp;
	            }
	        }
		}
  }

  public void list() {
	for (int i = 0; i < CURRENT_EMPLOYEE_COUNT; i++) {
	  // generic output
	}
  }
  
  private static int getDeletedID(){
		int buffer;
		buffer = Integer.parseInt( (String) JOptionPane.showInputDialog
				(null,"Employee ID: ","Delete an employee", JOptionPane.INFORMATION_MESSAGE,null,null,null));
		return buffer;		
	}
	
	private int[] getIDs(){
		int [] empIDs;
		empIDs = new int[CURRENT_EMPLOYEE_COUNT];
		
		for(int io=0; io<empIDs.length; io++){
			empIDs[io] = database[io].getID();
		}
		return empIDs;
	}
	
	public boolean delete(int employeeID){
		int delIndex = binarySearch(getIDs() , 0, CURRENT_EMPLOYEE_COUNT, employeeID);
		boolean deletesuccess = false;				
		if(delIndex != -1){ // if database is empty, or if the employee ID is invalid,
							//  will not enter for loop.		
			for(int i = delIndex; i < CURRENT_EMPLOYEE_COUNT-1; i++){
				database[i] = database[i+1];
			}
			CURRENT_EMPLOYEE_COUNT--;
			System.out.println(CURRENT_EMPLOYEE_COUNT);
			list();
			deletesuccess = true;
			
		}
		return deletesuccess;
	}
	
	public int binarySearch(int[] sorted, int first, int upto, int key) {
	    
	    while (first < upto) {
	        int mid = (first + upto) / 2;  // Compute mid point.
	        if (key < sorted[mid]) {
	            upto = mid;     // repeat search in bottom half.
	        } else if (key > sorted[mid]) {
	            first = mid + 1;  // Repeat search in top half.
	        } else {
	            return mid;     // Found it. return position
	        }
	    }
	    return -1;    // Failed to find key
	}
	
	public boolean addEmployee(String firstname, 
            String lastname, 
            int empID, 
            double salary, 
            String department, 
            int yearsofwork, 
            String worklocation){
		if(CURRENT_EMPLOYEE_COUNT == MAX_EMPLOYEE_COUNT){
			return false;
		}
		if(query(empID) != null){		// returns false if employee ID already exists.
			return false;
		}
		database[CURRENT_EMPLOYEE_COUNT] = new Employee(firstname, lastname, empID, salary, department, yearsofwork, worklocation);
		CURRENT_EMPLOYEE_COUNT++;
		sort();
		return true;
		
	}
	
	public Employee query (int employeeID){
		int index =  binarySearch(getIDs() , 0, CURRENT_EMPLOYEE_COUNT-1, employeeID);
		if(index == -1){	// if there is no array, or if the entered ID is invalid, it will return null.
			return null;
		}
		
		return database[index];
	}
}
