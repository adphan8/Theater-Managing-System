package Assignment2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


public class DatabaseReader {
	public ArrayList<Products> readProducts(){
		ArrayList<Products> pro = new ArrayList<Products>();
		DatabaseInfo di = new DatabaseInfo();
		Connection con = di.getConnection();

		String query1 = "SELECT pro.ProductsID, pro.ProductCode, pro.productType, pro.ProductCost, m.MovieTicketsID, m.ProductsID, m.AddressID, m.MovieDateTime, m.MovieName, m.MovieScreen, a.Street, a.City, a.State, a.ZipCode,a.Country FROM Products pro "
				+ "JOIN MovieTickets m ON pro.ProductsID = m.ProductsID "
				+ "JOIN Address a ON a.AddressID = m.AddressID;";
		String query2 = "SELECT pro.ProductsID, pro.ProductCode, pro.productType, pro.ProductCost, p.ParkingPassesID FROM Products pro "
				+ "JOIN ParkingPasses p ON pro.ProductsID = p.ProductsID";
		String query3 = "SELECT pro.ProductsID, pro.ProductCode, pro.productType, pro.ProductCost, s.SeasonPassID, s.SeasonPassName, s.SeasonPassStart, s.SeasonPassEnd FROM Products pro "
				+ "JOIN SeasonPasses s ON pro.ProductsID = s.ProductsID";
		String query4 = "SELECT pro.ProductsID, pro.ProductCode, pro.productType, pro.ProductCost, r.RefreshmentsID, r.RefreshmentName FROM Products pro "
				+ "JOIN Refreshments r ON pro.ProductsID = r.ProductsID";

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(query1);
			rs = ps.executeQuery();
			while(rs.next()) {
				int movieTicketID = rs.getInt("MovieTicketsID");
				String productType = rs.getString("productType");
				String productCode = rs.getString("ProductCode");
				double productCost = rs.getDouble("ProductCost");
				int productID = rs.getInt("ProductsID");
				int addressID = rs.getInt("AddressID");
				String movieDateTime = rs.getString("MovieDateTime");
				String movieName = rs.getString("MovieName");
				String movieScreen = rs.getString("MovieScreen");
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String country = rs.getString("Country");
				String zipCode = rs.getString("Zipcode");

				Address a = new Address(street,city,state,country,zipCode);
				Products m = new MovieTicket(productCode, productType,
						movieDateTime, movieName, a, movieScreen, productCost );

				pro.add(m);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}




		try {
			ps = con.prepareStatement(query2);
			rs = ps.executeQuery();
			while(rs.next()) {
				int productID = rs.getInt("ProductsID");
				String productCode = rs.getString("ProductCode");
				String productType = rs.getString("productType");
				double productCost = rs.getDouble("ProductCost");
				int parkingPassID = rs.getInt("ParkingPassesID");

				Products p = new ParkingPass(productCode,productType,productCost);
				pro.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}



		try {
			ps = con.prepareStatement(query3);
			rs = ps.executeQuery();
			while(rs.next()) {
				int productID = rs.getInt("ProductsID");
				String productCode = rs.getString("ProductCode");
				String productType = rs.getString("productType");
				double productCost = rs.getDouble("ProductCost");
				int seasonPassID = rs.getInt("SeasonPassID");
				String seasonPassName = rs.getString("SeasonPassName");
				String seasonPassStart = rs.getString("SeasonPassStart");
				String seasonPassEnd = rs.getString("SeasonPassEnd");

				Products s = new SeasonPass(productCode,productType,seasonPassName,seasonPassStart,seasonPassEnd,productCost);


				pro.add(s);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}


		try {
			ps = con.prepareStatement(query4);
			rs = ps.executeQuery();
			while(rs.next()) {
				int productID = rs.getInt("ProductsID");
				String productCode = rs.getString("ProductCode");
				String productType = rs.getString("productType");
				double productCost = rs.getDouble("ProductCost");
				int refreshmentsID = rs.getInt("RefreshmentsID");
				String refreshmentName = rs.getString("RefreshmentName");

				Products r = new Refreshments(productCode,productType,refreshmentName,productCost);
				pro.add(r);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		di.closeConnection(con);
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return pro;
	}
	public ArrayList<Customers> readCustomers(ArrayList<Person> p){
		ArrayList<Customers> c = new ArrayList<Customers>();
		DatabaseInfo di = new DatabaseInfo();
		Connection con = di.getConnection();
		String query = "SELECT c.CustomersID, c.PersonsID, c.AddressID, c.CustomerCode, c.CustomerType, c.CustomerName, p.PersonsCode, p.PersonFirstName, p.PersonLastName, a.Street, a.City, a.State, a.Country, a.ZipCode FROM Customers c "
				+ "JOIN Persons p ON c.PersonsID = p.PersonsID "
				+ "JOIN Address a ON c.AddressID = a.AddressID";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
				int customerID = rs.getInt("CustomersID");
				int personID = rs.getInt("PersonsID");
				int addressID = rs.getInt("AddressID");
				String customerCode = rs.getString("CustomerCode");
				String customerType = rs.getString("CustomerType");
				//				System.out.println(customerType);
				String customerName = rs.getString("CustomerName");
				String personCode = rs.getString("PersonsCode");
				Person contact = null;
				for(Person person : p) {
					if(person.getPersonCode().equals(personCode))
						contact = person;						
				}

				String firstName = rs.getString("PersonFirstName");
				String lastName = rs.getString("PersonLastName");
				String name = firstName +" "+lastName;
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String country = rs.getString("Country");
				String zipCode = rs.getString("ZipCode");

				Address a = new Address(street,city,state,country,zipCode);
				if(customerType.equals("General")) {
					Customers gen = new General(customerCode,customerType,contact,name,a);
					c.add(gen);
				}
				if(customerType.equals("Student")) {
					Customers stu = new Student(customerCode,customerType,contact,name,a);
					c.add(stu);
				}
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		di.closeConnection(con);
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return c;

	}

	public <T> myList<Invoices> readInvoice(ArrayList<Person> per, ArrayList<Products> prod, ArrayList<Customers> cus, Comparator<Invoices> comp) throws ParseException{
		int unit=0;
		double fee=6.75;
		ArrayList<Double> subTotal=null;
		ArrayList<Double> Fees=null;
		ArrayList<Double> Taxes=null;
		ArrayList<Double> Discount=null;
		ArrayList<Double> grandTotal=null;
		double sumSubTotal=0.0;
		double sumTax=0.0;
		double sumGrandTotal=0.0;
		double subTotalStudent = 0.0;
		double grandTotalStudent = 0.0;
		double discount = 0.0;
		long totalDay = 0;
		long remainingDay =0;
		String attachedCode = "";
		int movieTicketUnit = 0;
		int seasonPassUnit =0;
		String[] productContainer = null;
		Customers customerCode = null;
		Person contact = null;

		myList<Invoices> invoiceList = new myList<Invoices>(comp);
		DatabaseInfo di = new DatabaseInfo();
		Connection con = di.getConnection();
		String query1 = "SELECT i.InvoicesID, i.InvoiceCode, i.InvoiceDate, c.CustomerCode, p.PersonsCode FROM Invoices i JOIN Customers c ON c.CustomersID = i.CustomersID JOIN Persons p ON p.PersonsID = c.PersonsID";
		PreparedStatement ps = null;          
		ResultSet rs = null;
		ResultSet rsm = null;
		ResultSet rss = null;
		ResultSet rsp = null;
		ResultSet rsr = null;
		ArrayList<ProductInList> proList = null;

		try {
			ps = con.prepareStatement(query1);
			rs = ps.executeQuery();
			while(rs.next()) {
				subTotal = new ArrayList<Double>();
				grandTotal = new ArrayList<Double>();
				Fees = new ArrayList<Double>();
				Taxes = new ArrayList<Double>();
				Discount = new ArrayList<Double>();
				proList = new ArrayList<ProductInList>();
				String personCode = rs.getString("PersonsCode");
				for(Person person : per) {
					if(person.getPersonCode().equals(personCode)) {
						contact = person;
					}
				}
					String cusCode = rs.getString("CustomerCode");
					for(Customers customer: cus) {
						if(cusCode.equals(customer.getCustomerCode())) {					
							customerCode = customer;
						}
					}
					String invoiceCode = rs.getString("InvoiceCode");
					String invoiceDate = rs.getString("InvoiceDate");
					int invoiceID = rs.getInt("InvoicesID");
					


					String queryMovie = "SELECT i.ProductsID, i.ProductCount, pro.ProductCode, pro.ProductType, pro.ProductCost, m.MovieName, m.MovieDateTime, m.MovieScreen, a.Street,a.City,a.State,a.Country,a.ZipCode From InvoiceProducts i Join Products pro ON i.productsID = pro.ProductsID "
							+ "JOIN MovieTickets m ON m.ProductsID= pro.productsID "
							+ "JOIN Address a ON a.AddressID = m.AddressID where i.InvoicesID = ?";
					String querySeason = "SELECT i.ProductsID, i.ProductCount, pro.ProductCode, pro.ProductType, pro.ProductCost, s.SeasonPassName, s.SeasonPassStart, s.SeasonPassEnd From InvoiceProducts i Join Products pro ON i.productsID = pro.ProductsID "
							+ "JOIN SeasonPasses s ON s.ProductsID= pro.productsID where i.InvoicesID = ?";
					String queryParking = "SELECT i.ProductsID, i.ProductCount, pro.ProductCode, pro.ProductType, pro.ProductCost From InvoiceProducts i Join Products pro ON i.productsID = pro.ProductsID "
							+ "JOIN ParkingPasses p ON p.ProductsID= pro.productsID where i.InvoicesID = ?";
					String queryRefreshments ="SELECT i.ProductsID, i.ProductCount, pro.ProductCode, pro.ProductType, pro.ProductCost, r.RefreshmentName From InvoiceProducts i Join Products pro ON i.productsID = pro.ProductsID "
							+ "JOIN Refreshments r ON r.ProductsID= pro.productsID where i.InvoicesID = ?";

					ps = con.prepareStatement(queryMovie);
					ps.setInt(1, invoiceID);
					rsm = ps.executeQuery();
					while(rsm.next()) {
						String productCode = rsm.getString("ProductCode");
						int productCount = rsm.getInt("ProductCount");
						String productType = rsm.getString("ProductType");
						double productCost = rsm.getDouble("ProductCost");
						String movieName = rsm.getString("MovieName");
						String movieDateTime = rsm.getString("MovieDateTime");
						String movieScreen = rsm.getString("MovieScreen");
						String street = rsm.getString("Street");
						String city = rsm.getString("City");
						String state = rsm.getString("State");
						String country = rsm.getString("Country");
						String zipCode = rsm.getString("ZipCode");
						Address a = new Address(street,city,state,country,zipCode);
						ProductInList p = new ProductInList(productCode, productCount);
						Products m = new MovieTicket(productCode,productType,movieName,movieDateTime,a,movieScreen,productCost);
						proList.add(p);
					}

					ps = con.prepareStatement(querySeason);
					ps.setInt(1, invoiceID);
					rss = ps.executeQuery();
					while(rss.next()) {
						String productCode = rss.getString("ProductCode");
						int productCount = rss.getInt("ProductCount");
						String productType = rss.getString("ProductType");
						double productCost = rss.getDouble("ProductCost");
						String seasonPassName = rss.getString("SeasonPassName");
						String seasonPassStart = rss.getString("SeasonPassStart");
						String seasonPassEnd = rss.getString("SeasonPassEnd");
						ProductInList p = new ProductInList(productCode, productCount);
						proList.add(p);
					}

					ps = con.prepareStatement(queryParking);
					ps.setInt(1, invoiceID);
					rsp = ps.executeQuery();
					while(rsp.next()) {
						String productCode = rsp.getString("ProductCode");
						int productCount = rsp.getInt("ProductCount");
						String productType = rsp.getString("ProductType");
						double productCost = rsp.getDouble("ProductCost");
						ProductInList p = new ProductInList(productCode, productCount);
						proList.add(p);
					}
					ps = con.prepareStatement(queryRefreshments);
					ps.setInt(1, invoiceID);
					rsr = ps.executeQuery();
					while(rsr.next()) {
						String productCode = rsr.getString("ProductCode");
						int productCount = rsr.getInt("ProductCount");
						String productType = rsr.getString("ProductType");
						double productCost = rsr.getDouble("ProductCost");
						String refreshmentName = rsr.getString("RefreshmentName");
						ProductInList p = new ProductInList(productCode, productCount);
						proList.add(p);
					}
					for(ProductInList proinlist: proList) {													//Loop through products in invoice
						for(Products pro : prod) {
							//Loop through products in product file
							if(pro.getProductCode().equals(proinlist.getProductCode())) {					//Compare if 2 products match each other
								if(pro.getProductType().equals("Movie Ticket")) {		//Movie Ticket type
									//Calculation
									double movieTax = ((MovieTicket)pro).getTax(((MovieTicket)pro).getPricePerUnit(), proinlist.getProductUnit());
									double subTotalMovie = ((MovieTicket)pro).getSubTotal(((MovieTicket)pro).getPricePerUnit(), proinlist.getProductUnit());
									double grandTotalMovie = ((MovieTicket)pro).computeGrandTotal(((MovieTicket)pro).getPricePerUnit(), proinlist.getProductUnit());
									movieTax = Math.round(movieTax*100.0)/100.0;
									subTotalMovie = Math.round(subTotalMovie*100.0)/100.0;
									grandTotalMovie = Math.round(grandTotalMovie*100.0)/100.0;
									movieTicketUnit = proinlist.getProductUnit();
									subTotal.add(subTotalMovie);
									Taxes.add(movieTax);
									grandTotal.add(grandTotalMovie);									
									break;
								}

								if(pro.getProductType().equals("Season Pass")) {		//Season Pass type
									//Initialize Date Format
									String dateStart = ((SeasonPass)pro).getDateStart();
									String dateEnd = ((SeasonPass)pro).getDateEnd();
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Date startDate = sdf.parse(dateStart);
									Date endDate = sdf.parse(dateEnd);
									Date inv = sdf.parse(invoiceDate);
									double subTotalSeason=0.0;
									double seasonTax=0.0;
									double grandTotalSeason=0.0;
									totalDay = endDate.getTime()-startDate.getTime();
									totalDay = totalDay/(1000*60*60*24);
									remainingDay = endDate.getTime()-inv.getTime();
									remainingDay = remainingDay/(1000*60*60*24);
									seasonPassUnit = proinlist.getProductUnit();
									//Compare the invoice date with date start and date end of the product
									if((inv.compareTo(startDate)==1 && inv.compareTo(endDate)==-1) || inv.compareTo(startDate)==0 || inv.compareTo(endDate)==0) {		//if the date is between date start and date end
										//Calculation


										double rate = remainingDay*1.00/totalDay;
										subTotalSeason = (((SeasonPass)pro).getSubTotal(((SeasonPass)pro).getCost(), proinlist.getProductUnit())*rate)+((SeasonPass)pro).getFee(proinlist.getProductUnit());
										seasonTax = subTotalSeason*0.06;
										grandTotalSeason = subTotalSeason+seasonTax;
										seasonTax = Math.round(seasonTax*100.0)/100.0;
										grandTotalSeason = Math.round(grandTotalSeason*100.0)/100.0;
										subTotalSeason = Math.round(subTotalSeason*100.0)/100.0;		
										subTotal.add(subTotalSeason);
										Taxes.add(seasonTax);
										grandTotal.add(grandTotalSeason);
										break;

									}else {
										//if the date is not between date start and date end
										subTotalSeason = ((SeasonPass)pro).getSubTotal(((SeasonPass)pro).getCost(), proinlist.getProductUnit())+((SeasonPass)pro).getFee(proinlist.getProductUnit());
										seasonTax = subTotalSeason*0.06;
										grandTotalSeason = subTotalSeason + seasonTax;
										seasonTax = Math.round(seasonTax*100.0)/100.0;
										grandTotalSeason = Math.round(grandTotalSeason*100.0)/100.0;
										subTotalSeason = Math.round(subTotalSeason*100.0)/100.0;
										subTotal.add(subTotalSeason);
										Taxes.add(seasonTax);
										grandTotal.add(grandTotalSeason);
										break;
									}


								}
								if(pro.getProductType().equals("Parking Pass")) {		//Parking Pass type

									//Write if statements in order to finish the parking pass system associating with other types
									//Calculation

									if(movieTicketUnit > 0 && proinlist.getProductUnit()>movieTicketUnit) {
										int freeParkingUnit = movieTicketUnit;
										int parkingUnit = proinlist.getProductUnit() - freeParkingUnit;
										double parkingTax = ((ParkingPass)pro).getTax(((ParkingPass)pro).getParkingFee(), parkingUnit);
										double subTotalParking = ((ParkingPass)pro).getSubTotal(((ParkingPass)pro).getParkingFee(), parkingUnit);
										double grandTotalParking = ((ParkingPass)pro).computeGrandTotal(((ParkingPass)pro).getParkingFee(), parkingUnit);
										parkingTax = Math.round(parkingTax*100.0)/100.0;
										subTotalParking = Math.round(subTotalParking*100.0)/100.0;
										grandTotalParking = Math.round(grandTotalParking*100.0)/100.0;
										subTotal.add(subTotalParking);
										Taxes.add(parkingTax);
										grandTotal.add(grandTotalParking);
										break;
									}
									else if(seasonPassUnit >0 && remainingDay ==0) {
										int parkingUnit = proinlist.getProductUnit();
										double parkingTax = ((ParkingPass)pro).getTax(((ParkingPass)pro).getParkingFee(), parkingUnit);
										double subTotalParking = ((ParkingPass)pro).getSubTotal(((ParkingPass)pro).getParkingFee(), parkingUnit);
										double grandTotalParking = ((ParkingPass)pro).computeGrandTotal(((ParkingPass)pro).getParkingFee(), parkingUnit);
										parkingTax = Math.round(parkingTax*100.0)/100.0;
										subTotalParking = Math.round(subTotalParking*100.0)/100.0;
										grandTotalParking = Math.round(grandTotalParking*100.0)/100.0;
										subTotal.add(subTotalParking);
										Taxes.add(parkingTax);
										grandTotal.add(grandTotalParking);
										break;
									}
									else if(proinlist.getProductUnit()>seasonPassUnit && seasonPassUnit >0 && remainingDay>0) {
										int freeParkingUnit = seasonPassUnit;
										int parkingUnit = proinlist.getProductUnit() - freeParkingUnit;
										double parkingTax = ((ParkingPass)pro).getTax(((ParkingPass)pro).getParkingFee(), parkingUnit);
										double subTotalParking = ((ParkingPass)pro).getSubTotal(((ParkingPass)pro).getParkingFee(), parkingUnit);
										double grandTotalParking = ((ParkingPass)pro).computeGrandTotal(((ParkingPass)pro).getParkingFee(), parkingUnit);
										parkingTax = Math.round(parkingTax*100.0)/100.0;
										subTotalParking = Math.round(subTotalParking*100.0)/100.0;
										grandTotalParking = Math.round(grandTotalParking*100.0)/100.0;
										subTotal.add(subTotalParking);
										Taxes.add(parkingTax);
										grandTotal.add(grandTotalParking);
										break;
									}
									else if(movieTicketUnit==0 && seasonPassUnit==0) {
										double parkingTax = ((ParkingPass)pro).getTax(((ParkingPass)pro).getParkingFee(), proinlist.getProductUnit());
										double subTotalParking = ((ParkingPass)pro).getSubTotal(((ParkingPass)pro).getParkingFee(), proinlist.getProductUnit());
										double grandTotalParking = ((ParkingPass)pro).computeGrandTotal(((ParkingPass)pro).getParkingFee(), proinlist.getProductUnit());
										subTotal.add(subTotalParking);
										Taxes.add(parkingTax);
										grandTotal.add(grandTotalParking);
										break;
									}else {
										double subTotalParking = 0.0;
										double parkingTax = 0.0;
										double grandTotalParking = subTotalParking + parkingTax;
										subTotal.add(subTotalParking);
										Taxes.add(parkingTax);
										grandTotal.add(grandTotalParking);
										break;
									}
								}
								if(pro.getProductType().equals("Refreshment")) {		//Refreshments type
									//Check if they are pre-ordered
									//Calculation
									if(movieTicketUnit > 0) {

										double subTotalRefreshments = ((Refreshments)pro).getSubTotal(((Refreshments)pro).getCost(), proinlist.getProductUnit());
										double refreshmentsTax = ((Refreshments)pro).getTax(((Refreshments)pro).getCost(), proinlist.getProductUnit());
										double refreshmentsGrandTotal = ((Refreshments)pro).computeGrandTotal(((Refreshments)pro).getCost(), proinlist.getProductUnit());
										subTotal.add(subTotalRefreshments);	
										Taxes.add(refreshmentsTax);
										grandTotal.add(refreshmentsGrandTotal);
										break;
									}else if(seasonPassUnit > 0) {
										double subTotalRefreshments = ((Refreshments)pro).getSubTotal(((Refreshments)pro).getCost(), proinlist.getProductUnit());
										double refreshmentsTax = ((Refreshments)pro).getTax(((Refreshments)pro).getCost(), proinlist.getProductUnit());
										double refreshmentsGrandTotal = ((Refreshments)pro).computeGrandTotal(((Refreshments)pro).getCost(), proinlist.getProductUnit());
										subTotal.add(subTotalRefreshments);	
										Taxes.add(refreshmentsTax);
										grandTotal.add(refreshmentsGrandTotal);
										break;
									}
									else if(movieTicketUnit==0) {
										double subTotalRefreshments = ((Refreshments)pro).getSubTotal(((Refreshments)pro).getCost(), proinlist.getProductUnit())/0.95;
										double refreshmentsTax = subTotalRefreshments*0.04;
										double refreshmentsGrandTotal = subTotalRefreshments+refreshmentsTax;
										subTotalRefreshments = Math.round(subTotalRefreshments*100.0)/100.0;
										refreshmentsTax = Math.round(refreshmentsTax*100.0)/100.0;
										refreshmentsGrandTotal = Math.round(refreshmentsGrandTotal*100.0)/100.0;

										subTotal.add(subTotalRefreshments);	
										Taxes.add(refreshmentsTax);
										grandTotal.add(refreshmentsGrandTotal);
										break;
									}
								}
							}
						}
					}
					Invoices invoice = new Invoices(invoiceCode, customerCode,
							contact, invoiceDate,
							proList, subTotal, Fees, Taxes, Discount, grandTotal);

					for(int i = 0;i<invoice.getSubTotal().size();i++) {
						sumSubTotal += subTotal.get(i);
					}
					for(int i = 0;i<invoice.getTaxes().size();i++) {
						sumTax += Taxes.get(i);
					}
					for(int i = 0;i<invoice.getGrandTotal().size();i++) {
						sumGrandTotal += grandTotal.get(i);
					}
					//Condition when a customer is a student
					//Calculation
					if(customerCode.getType().equals("Student")) {
						discount = sumSubTotal*0.08+sumTax;
						sumGrandTotal = sumGrandTotal -discount;
						discount = Math.round(discount*100.0)/100.0;
						sumGrandTotal = Math.round(sumGrandTotal*100.0)/100.0;
						Fees.add(fee);
						Discount.add(-discount);

					}else {
						Fees.add(0.0);
						Discount.add(-0.0);
					}
					//add the object to the created invoice list
					invoiceList.addElements(invoice);
					//reset each variable to get into the next loop
					discount =0.0;
					sumSubTotal = 0.0;
					sumTax = 0.0;
					sumGrandTotal = 0.0;
					movieTicketUnit = 0;
					seasonPassUnit = 0;
				}
			

		}catch(SQLException e) {
			e.printStackTrace();
		}
		di.closeConnection(con);
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(rsm != null && !rsm.isClosed()) {
				rsm.close();
			}
			if(rss != null && !rss.isClosed()) {
				rss.close();
			}
			if(rsp != null && !rsp.isClosed()) {
				rsp.close();
			}
			if(rsr != null && !rsr.isClosed()) {
				rsr.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return invoiceList;
	}

	public ArrayList<Person> readPersons(){
		ArrayList<Person> p = new ArrayList<Person>();
		DatabaseInfo di = new DatabaseInfo();
		Connection con = di.getConnection();
		String query = "SELECT p.PersonsID, p.PersonFirstName, p.PersonLastName, p.PersonsCode, a.Street, a.City, a.State, a.Country, a.Zipcode  FROM Persons p"
				+ " JOIN Address a ON p.AddressID = a.AddressID";
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs1 = null;

		try {
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {

				ArrayList<String> email = new ArrayList<String>();
				String firstName = rs.getString("PersonFirstName");
				String lastName = rs.getString("PersonLastName");
				int personID = rs.getInt("PersonsID");
				String personCode = rs.getString("PersonsCode");
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String country = rs.getString("Country");
				String zipCode = rs.getString("Zipcode");

				String query1 = "SELECT e.EmailAddress, p.PersonsID FROM PersonEmails e JOIN Persons p ON p.PersonsID = e.PersonsID WHERE p.personsID = ?";
				ps = con.prepareStatement(query1);
				ps.setInt(1, personID);
				rs1 = ps.executeQuery();
				while(rs1.next()) {
					String e = rs1.getString("EmailAddress");
					email.add(e);
				}

				Address a = new Address(street, city, state, country, zipCode);
				Person person = new Person(personCode, firstName, lastName, a, email);

				p.add(person);
			}

		}catch(SQLException e) {
			e.printStackTrace();
		}
		di.closeConnection(con);
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(ps != null && !ps.isClosed()) {
				ps.close();
			}
		}catch(SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return p;
	}

}

