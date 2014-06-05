package EIMS;

import java.io.BufferedReader;
import java.io.IOException;

public class EmployeeDatabase {
  private String database[];
  
  public EmployeeDatabase() {
	//this.buildDB();
  }
  public void buildDB() {
	System.out.println("BUILDING DB");
	
	BufferedReader br = null;
	String delimeters = "[;]";
	
	 try {
		 br = new BufferedReader (new FileReader("C:\\Users\\Tarik\\Documents\\Java stuffz\\Employees.txt"));
		 String line = "";
		 
		 while((line = br.readLine()) != null) {
			 database[] = line.split(delimeters);
		 }
	 }
	 catch(IOException e){
		 e.printStackTrace();
		 if(br != null){
			 br.close();
			 System.exit(0);
		 }
	 }
	 
	 for(int i = 0; i < database.length; i++ )
		 System.out.println(database[i]);
  }
  
}