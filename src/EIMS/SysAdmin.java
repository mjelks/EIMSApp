package EIMS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class SysAdmin {
  public int numberOfAccts = 0;
  // each array element will be a login and password separated by a space
  public String[] accountList;			
  // each element will be a string of the login only
  public String[] loginsOnly;				
  // each element will be a string of the password only
  public String[] pwordsOnly;				
  public Account account;
  

  public boolean login(String user, String pword) throws IOException {
	boolean approved = false;
    
    if (
            user == null || 
            pword == null || 
            user.trim().equals("") || 
            pword.trim().equals("")) 
    {
      return false;
    }
	getLoginList();
	sortLoginList();
    

	/*System.out.println("\nHere is the Post Sorted logins only array:");
	 for(int z=0; z<numberOfAccts; z++){
	 System.out.println(loginsOnly[z]);
	 }
	 System.out.println();
	 System.out.println("\nHere is the Post Sorted Passwords only array:");
	 for(int z=0; z<numberOfAccts; z++){
	 System.out.println(pwordsOnly[z]);
	 }*/
	approved = checkUser(user, pword);   
	//System.out.println("Account is " + approved);	
	return approved;
  }

  public boolean checkUser(String user1, String pword1) {
	boolean goodUser = false;

	for (int y = 0; y < numberOfAccts; y++) {
	  if ((loginsOnly[y].equals(user1)) && (pwordsOnly[y].equals(pword1))) {
		goodUser = true;
	  }
	}

	return goodUser;
  }

  public void sortLoginList() {
	//System.out.println("\nnumber of accounts is "+ numberOfAccts);
	String tmpLogin;
	String tmpPword;

	for (int p = 0; p < (numberOfAccts - 1); p++) {
	  tmpLogin = loginsOnly[p];
	  int r;
	  for (r = (p + 1); r < numberOfAccts; r++) {

		int compareValue = (loginsOnly[p].compareTo(loginsOnly[r]));
		if (compareValue > 0) {
		  tmpLogin = loginsOnly[p];
		  loginsOnly[p] = loginsOnly[r];
		  loginsOnly[r] = tmpLogin;
		  //System.out.println("tmpLogin set to "+ tmpLogin);
          // changing order of passwords only array to match loginsOnly order
		  tmpPword = pwordsOnly[p];								
		  pwordsOnly[p] = pwordsOnly[r];
		  pwordsOnly[r] = tmpPword;
		}//else{System.out.println("tmpLogin not changed");}
	  }
	  //System.out.println("sorted "+ loginsOnly[p]);	
	}
  }
  
  // this method adds each line from the accts.txt login list to an array
  public void getLoginList() throws IOException {				
	BufferedReader br = null;
	String delimeter = "[ ]";		//
    
    // method call to find out how many accounts are in the txt file
	numberOfAccts = lineNumbers();							
	accountList = new String[numberOfAccts];
	//System.out.println("number of logins is "+numberOfAccts);
	loginsOnly = new String[numberOfAccts];
	pwordsOnly = new String[numberOfAccts];

	try {
      InputStreamReader reader = new InputStreamReader(
              EmployeeDatabase.class.getResourceAsStream("accts.txt"), "UTF-8");
	  br = new BufferedReader(reader);
	  String line = "";

	  int i = 0;
	  int n = 0;
	  
	  while ((line = br.readLine()) != null) {
        // adding account/password pair (in single strings) to array
		accountList[i] = line;							

        // this splits each file line as it's downloaded
		String[] fields = line.split(delimeter);		
		for (String field : fields) {
		  //System.out.print(field + " ");
		  if (n % 2 == 0) {
            // adding only logins to array
			loginsOnly[i] = field;					
			//System.out.println("login: "+ field);
		  } else {
            // adding only passwords to array
			pwordsOnly[i] = field;					
		
		  }
		  n++;
		}
		i++;
	  }
	  br.close();

	} catch (IOException e) {
	  e.printStackTrace();
	  if (br != null) {
		br.close();
		System.exit(0);
	  }
	}
  }
  
  // figures out how many accounts are in the txt file
  public int lineNumbers() throws IOException {				
	BufferedReader br = null;
	try {
	  InputStreamReader reader = new InputStreamReader(
              EmployeeDatabase.class.getResourceAsStream("accts.txt"), "UTF-8");
	  br = new BufferedReader(reader);
			//String line = "";

	  while ((br.readLine()) != null) {
		numberOfAccts++;
	  }
	  br.close();

	} catch (IOException e) {
	  e.printStackTrace();
	  if (br != null) {
		br.close();
		System.exit(0);
	  }
	}
	return numberOfAccts;
  }
}