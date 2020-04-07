package Assignment2;

public class ParkingPass extends Products {
	private String type;
	private double parkingFee;
	public ParkingPass( String code, String type, double parkingFee) {
		super(code,type);
		this.type = type;
		this.parkingFee = parkingFee;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getParkingFee() {
		return parkingFee;
	}
	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}
	@Override
	public double getTax(double price,int unit) {
		// TODO Auto-generated method stub
		double tax = getSubTotal(price,unit)*0.04;
		tax = Math.round(tax*100.0)/100.0;
		return tax;
	}
	@Override
	public double computeGrandTotal(double price, int unit) {
		double total = getSubTotal(price,unit) + getTax(price,unit);
		total = Math.round(total*100.0)/100.0;
		return total;
	}
	@Override
	public double getSubTotal(double price, int unit) {
		// TODO Auto-generated method stub
		double subTotal = price*unit;
		subTotal = Math.round(subTotal*100.0)/100.0;
		return subTotal;
	}
	
	
	

}
