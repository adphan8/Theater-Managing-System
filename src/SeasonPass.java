package Assignment2;

public class SeasonPass extends Products{
	private String type;
	private String name;
	private String dateStart;
	private String dateEnd;
	private double cost;
	public SeasonPass(String code, String type, String name, String dateStart, String dateEnd, double cost) {
		super(code,type);
		this.type = type;
		this.name = name;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
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
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public double getFee(int unit) {
		double fee = 8*unit;
		return fee;
	}
	@Override
	public double getTax(double price, int unit) {
		// TODO Auto-generated method stub
		double tax = getSubTotal(price,unit)*0.06;
		tax = Math.round(tax*100.0)/100.0;
		return tax;
	}
	@Override
	public double computeGrandTotal(double price, int unit) {
		// TODO Auto-generated method stub
		double grandTotal = getSubTotal(price,unit) + getTax(price, unit);
		grandTotal = Math.round(grandTotal*100.0)/100.0;
		return grandTotal;
	}
	@Override
	public double getSubTotal(double price, int unit) {
		// TODO Auto-generated method stub
		double subTotal = price*unit;
		subTotal = Math.round(subTotal*100.0)/100.0;
		return subTotal;
	}
	

	
	
	
	
}
