package OnlineOrder;

public class Employee {
	private String empID;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	private int empType;
	
	
	public Employee(String empID, String firstName, int empType) {
		super();
		this.empID = empID;
		this.firstName = firstName;
		this.empType = empType;
	}

	public Employee(String empID, String firstName, String lastName, String phone, String email, String address,
			int empType) {
		super();
		this.empID = empID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.empType = empType;
	}
	
	public String getEmpInfo() {
		String empInfo = "Employee";
		if(empID.startsWith("0")) {
			empInfo = "Manager";
		}
		return empInfo;
	}
	
	public int getEmpType() {
		if(empID.startsWith("0"))
			return 0; //manager
		else
			return 1;
	}
}
