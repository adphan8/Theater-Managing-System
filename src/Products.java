package Assignment2;

public abstract class Products {
	private String productCode;
	private String productType;

	public Products(String productCode, String productType) {
		this.productCode = productCode;
		this.productType = productType;
	}
	public String getProductType() {
		return this.productType;
	}
	
	public String getProductCode() {
		return this.productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	
	
	@Override
	public String toString() {
		return "Products [productCode=" + productCode + ", productType=" + productType + "]";
	}
	public abstract double getSubTotal(double price, int unit);
	
	public abstract double getTax(double price, int unit);
	
	public abstract double computeGrandTotal(double price, int unit);
	
	
	
}
