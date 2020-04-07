package Assignment2;

import java.text.ParseException;
import java.util.ArrayList;

import com.ceg.ext.InvoiceData;

public class InvoiceReport {
	public static void main(String[] args) throws ParseException {
		DatabaseReader dr = new DatabaseReader();
		myList<Invoices> inv = dr.readInvoice(dr.readPersons(), dr.readProducts(), dr.readCustomers(dr.readPersons()), new totalOrder());
		ArrayList<Products> pro = new ArrayList<Products>();
		pro = dr.readProducts();
		FileWriter fw = new FileWriter();
		fw.printSummary(inv);
		fw.printDetail(inv,pro);
	}
	
	
}
