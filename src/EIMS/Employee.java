package EIMS;

public class Employee {
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

	public Employee(String fn, String ln, int ID, double sal, String dep,
			int yow, String loc) {
		firstname = fn;
		lastname = ln;
		empID = ID;
		salary = sal;
		department = dep;
		yearsofwork = yow;
		worklocation = loc;
	}
}
