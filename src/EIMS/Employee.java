package EIMS;

// the "implements comparable" part allows them to be sorted. 
// (found this on the internet.)
public class Employee implements Comparable<Employee> {		

  public String firstname;
  public String lastname;
  public int empID;
  public double salary;
  public String department;
  public int yearsOfWork;
  public String workLocation;
  
  private static final int DEFAULT_PENSION = 5;

  public Employee() {
	firstname = "";
	lastname = "";
	empID = 0;
	salary = 0.0;
	department = "";
	yearsOfWork = 0;
	workLocation = "";
  }

  public Employee(
		  String fn, 
		  String ln, 
		  int ID, 
		  double sal, 
		  String dep, 
		  int yow, String loc
  ) {
	firstname = fn;
	lastname = ln;
	empID = ID;
	salary = sal;
	department = dep;
	yearsOfWork = yow;
	workLocation = loc;
  }

  public int getPension() {	// can also be used as getYearsOfWork.
	return yearsOfWork;
  }

  public String getFirstname() {
	return firstname;
  }

  public String getLastname() {
	return lastname;
  }

  public String getDepartment() {
	return department;
  }

  public double getSalary() {
	return salary;
  }
  
  public int getID() {
	return empID;
  }

  public String getLocation() {
	return workLocation;
  }
  
  public int getEmpId() {
    return empID;
  }
  
  public boolean isPensioner() {
    boolean pensioner = false;
    if (this.getPension() >= DEFAULT_PENSION) {
      pensioner = true;
    }
    return pensioner;
  }

  
  

  public int compareTo(Employee compareEmp) {		// allows objects to be sorted according to a specific field (in this case, ID)

	int empID = this.empID;
	int compareEmpID = compareEmp.empID;

	//ascending order
	return empID - compareEmpID;

  }
}
