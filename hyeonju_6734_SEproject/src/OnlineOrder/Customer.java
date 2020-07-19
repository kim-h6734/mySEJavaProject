package OnlineOrder;

public class Customer {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private String address;
	private int status;
	
	
	public Customer(String userId, String password) {
		super();
		this.userId = userId;
		this.password = password;
		this.firstName = userId;
	}

	public Customer(String userId, String firstName, String lastName, String phone, String email, String address,
			int status) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.status = status;
	}
	
	public String getUserInfo() {
		return firstName + " " + lastName;
	}
	
}
