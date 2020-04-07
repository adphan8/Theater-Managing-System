package Assignment2;

public class Refreshments extends Products {
	private String type;
	private String name;
	private double cost;
	public Refreshments(String code, String type, String name, double cost) {
		super(code, type);
		this.type = type;
		this.name = name;
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	@Override
	public double getSubTotal(double price, int unit) {
		// TODO Auto-generated method stub
		double subTotal = price*unit*0.95;
		subTotal = Math.round(subTotal*100.0)/100.0;
		return subTotal;
	}
	@Override
	public double getTax(double price, int unit) {
		// TODO Auto-generated method stub
		double tax = getSubTotal(price, unit)*0.04;
		tax = Math.round(tax*100.0)/100.0;
		return tax;
	}
	@Override
	public double computeGrandTotal(double price, int unit) {
		// TODO Auto-generated method stub
		double grandTotal = getSubTotal(price, unit) + getTax(price, unit);
		grandTotal = Math.round(grandTotal*100.0)/100.0;
		return grandTotal;
	}
	
	
	
}
