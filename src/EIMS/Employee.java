package EIMS;

public class Employee implements Comparable<Employee> {		// the "implements comparable" part allows them to be sorted. found this on the internet.

  public String firstname;
  public String lastname;
  public int empID;
  public double salary;
  public String department;
  public int yearsofwork;
  public String worklocation;

  public Employee() {
	firstname = "";
	lastname = "";
	empID = 0;
	salary = 0.0;
	department = "";
	yearsofwork = 0;
	worklocation = "";
  }

  public Employee(String fn, String ln, int ID,
		  double sal, String dep, int yow, String loc) {
	firstname = fn;
	lastname = ln;
	empID = ID;
	salary = sal;
	department = dep;
	yearsofwork = yow;
	worklocation = loc;
  }

  public int getPension() {	// can also be used as getYearsOfWork.
	return yearsofwork;
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

  public String getLocation() {
	return worklocation;
  }

  public int compareTo(Employee compareEmp) {		// allows objects to be sorted according to a specific field (in this case, ID)

	int empID = this.empID;
	int compareEmpID = compareEmp.empID;

	//ascending order
	return empID - compareEmpID;

  }
}
