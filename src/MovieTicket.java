package Assignment2;

public class MovieTicket extends Products {
	private String type;
	private String movieDateTime;
	private String movieName;
	private Address address;
	private String screenNo;
	private double pricePerUnit;
	public MovieTicket(String code, String type, String movieDateTime, String movieName, Address address, String screenNo,
			double pricePerUnit) {
		super(code, type);
		this.type = type;
		this.movieDateTime = movieDateTime;
		this.movieName = movieName;
		this.address = address;
		this.screenNo = screenNo;
		this.pricePerUnit = pricePerUnit;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMovieDateTime() {
		return movieDateTime;
	}
	public void setMovieDateTime(String movieDateTime) {
		this.movieDateTime = movieDateTime;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getScreenNo() {
		return screenNo;
	}
	public void setScreenNo(String screenNo) {
		this.screenNo = screenNo;
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
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
		CheckDate cd = new CheckDate();
		if(cd.getDay(this.getMovieDateTime()).equals("Tuesday") || cd.getDay(this.getMovieDateTime()).equals("Thursday")) {
			double subTotal = price*unit*0.93;
			subTotal = Math.round(subTotal*100.0)/100.0;
			return subTotal;
		}
		else {
			double subTotal = price*unit;
			subTotal = Math.round(subTotal*100.0)/100.0;
			return subTotal;
		}
	}


}
