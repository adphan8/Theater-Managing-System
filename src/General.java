package Assignment2;

public class General extends Customers  {

	public General(String customerCode, String type, Person contact, String name, Address address) {
		super(customerCode, type, contact, name, address);
		// TODO Auto-generated constructor stub
	}

	@Override	
	public double getTax() {
		// TODO Auto-generated method stub		
		return 0;
	}

	@Override
	public double getDiscount(double price) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getAdditionalFee() {
		// TODO Auto-generated method stub
		
		return 0;
	}

}
