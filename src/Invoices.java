package Assignment2;

import java.util.ArrayList;

public class Invoices {
	

	private String invoiceCode;
	private Customers customerCode;
	private Person salesPersonCode;
	private String invoiceDate;
	private ArrayList<ProductInList> productList;
	private ArrayList<Double> subTotal;
	private ArrayList<Double> Fees;
	private ArrayList<Double> Taxes;
	private ArrayList<Double> Discount;
	private ArrayList<Double> grandTotal;
	
	
	public Invoices(String invoiceCode, Customers customerCode, Person salesPersonCode, String invoiceDate,
			ArrayList<ProductInList> productList, ArrayList<Double> subTotal, ArrayList<Double> fees,
			ArrayList<Double> taxes, ArrayList<Double> discount, ArrayList<Double> grandTotal) {
		this.invoiceCode = invoiceCode;
		this.customerCode = customerCode;
		this.salesPersonCode = salesPersonCode;
		this.invoiceDate = invoiceDate;
		this.productList = productList;
		this.subTotal = subTotal;
		Fees = fees;
		Taxes = taxes;
		Discount = discount;
		this.grandTotal = grandTotal;
	}
	@Override
	public String toString() {
		return "Invoices [invoiceCode=" + invoiceCode + ", customerCode=" + customerCode + ", salesPersonCode="
				+ salesPersonCode + ", invoiceDate=" + invoiceDate + ", productList=" + productList + ", subTotal="
				+ subTotal + ", Fees=" + Fees + ", Taxes=" + Taxes + ", Discount=" + Discount + ", grandTotal="
				+ grandTotal + "]\n";
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Customers getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(Customers customerCode) {
		this.customerCode = customerCode;
	}
	public Person getSalesPersonCode() {
		return salesPersonCode;
	}
	public void setSalesPersonCode(Person salesPersonCode) {
		this.salesPersonCode = salesPersonCode;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public ArrayList<ProductInList> getProductList() {
		return this.productList;
	}
	public void setProductList(ArrayList<ProductInList> productList) {
		this.productList = productList;
	}
	public ArrayList<Double> getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(ArrayList<Double> subTotal) {
		this.subTotal = subTotal;
	}
	public ArrayList<Double> getFees() {
		return Fees;
	}
	public void setFees(ArrayList<Double> fees) {
		Fees = fees;
	}
	public ArrayList<Double> getTaxes() {
		return Taxes;
	}
	public void setTaxes(ArrayList<Double> taxes) {
		Taxes = taxes;
	}
	public ArrayList<Double> getDiscount() {
		return Discount;
	}
	public void setDiscount(ArrayList<Double> discount) {
		Discount = discount;
	}
	public ArrayList<Double> getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(ArrayList<Double> grandTotal) {
		this.grandTotal = grandTotal;
	}
	public double getTotalValue() {
		double total=0.0;
		for(int i =0; i < this.grandTotal.size();i++) {
			total = total + grandTotal.get(i);
		}
		return total;
	}
	
	
	
}
