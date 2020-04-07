package Assignment2;

public class ProductInList {
	private String productCode;
	private int productUnit;
	private String attachedCode;
	
	
	public ProductInList(String productCode, int productUnit) {
		this.productCode = productCode;
		this.productUnit = productUnit;
	}
	public ProductInList(String productCode, int productUnit, String attachedCode) {
		this.productCode = productCode;
		this.productUnit = productUnit;
		this.attachedCode = attachedCode;
	}
	public ProductInList(ProductInList oldProductInList) {
		this.productCode = oldProductInList.productCode;
		this.productUnit = oldProductInList.productUnit;
		this.attachedCode = oldProductInList.attachedCode;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getProductUnit() {
		return productUnit;
	}
	public void setProductUnit(int productUnit) {
		this.productUnit = productUnit;
	}
	public String getAttachedCode() {
		return attachedCode;
	}
	public void setAttachedCode(String attachedCode) {
		this.attachedCode = attachedCode;
	}
	
	
//	public String getFormattedProduct() {
//		String formattedProduct = productCode + ":" + productUnit;
//		return formattedProduct;
//	}
//	
	
	
}
