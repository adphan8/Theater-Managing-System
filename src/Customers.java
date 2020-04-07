package Assignment2;

public abstract class Customers {
	private String customerCode;
	private String type;
	private Person contact;
	private String name;
	private Address address;
	public Customers(String customerCode,String type, Person contact, String name, Address address) {
		this.customerCode = customerCode;
		this.type = type;
		this.contact = contact;
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Customers [customerCode=" + customerCode + ", type=" + type + ", contact=" + contact + ", name=" + name
				+ ", address=" + address + "]";
	}

	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public Person getContact() {
		return contact;
	}
	public void setContact(Person contact) {
		this.contact = contact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public abstract double getTax();
	
	public abstract double getDiscount(double price);
	
	public abstract double getAdditionalFee();
}
