package Assignment2;

import java.util.ArrayList;

public class FileWriter {
	public void printSummary(myList<Invoices> invoice) {
		System.out.println("Executive Summary Report");
		System.out.println("=============================");
		System.out.printf("%7s %10s %60s %30s %15s %15s %15s %15s\n", "Invoice","Customer","Salesperson","Subtotal"
				,"Fee","Taxes","Discount","Total");
		//		System.out.printf("%7s %10s %60s %30s %15s %15s %15s %15s", );
		double sumSubTotal = 0.0;
		double sumTax =0.0;
		double sumDiscount =0.0;
		double sumFee =0.0;
		double sumGrandTotal=0.0;
		for(int i = 0;i<invoice.getSize();i++) {
			double subTotal=0.0;
			double tax=0.0;
			double discount=0.0;
			double fee=0.0;
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getSubTotal().size();j++) {
				subTotal += invoice.getNodeAtIndex(i).getItem().getSubTotal().get(j);
			}
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getTaxes().size();j++) {
				tax += invoice.getNodeAtIndex(i).getItem().getTaxes().get(j);
			}
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getDiscount().size();j++) {
				discount = invoice.getNodeAtIndex(i).getItem().getDiscount().get(j);
			}
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getFees().size();j++) {
				fee = invoice.getNodeAtIndex(i).getItem().getFees().get(j);
			}
			double grandTotal = subTotal+tax+discount+fee;
			subTotal = Math.round(subTotal*100.0)/100.0;
			tax = Math.round(tax*100.0)/100.0;
			discount = Math.round(discount*100.0)/100.0;
			fee = Math.round(fee*100.0)/100.0;
			grandTotal = Math.round(grandTotal*100.0)/100.0;
			System.out.printf("%-9s %-57s %-28s $     %-12.2f $     %-8.2f $     %-7.2f $     %-9.2f $     %7.2f\n", invoice.getNodeAtIndex(i).getItem().getInvoiceCode()
					,invoice.getNodeAtIndex(i).getItem().getCustomerCode().getName() + "[" + invoice.getNodeAtIndex(i).getItem().getCustomerCode().getType() + "]",
					invoice.getNodeAtIndex(i).getItem().getSalesPersonCode().getLastName()+", "+invoice.getNodeAtIndex(i).getItem().getSalesPersonCode().getFirstName(),
					subTotal,fee, tax,discount,grandTotal);
			sumSubTotal +=subTotal;
			sumTax +=tax;
			sumDiscount +=discount;
			sumFee+=fee;
			sumGrandTotal+=grandTotal;
			//			sumSubTotal = Math.round(sumSubTotal*100.0)/100.0;
			//			sumTax = Math.round(sumTax*100.0)/100.0;
			//			sumFee = Math.round(sumFee*100.0)/100.0;
			//			sumGrandTotal = Math.round(sumGrandTotal*100.0)/100.0;
		}
		System.out.println("=================================================================================================================================================================================");
		System.out.printf("%-96s $    %-13.2f $    %-9.2f $    %-9.2f $    %-9.2f $    %9.2f\n\n\n\n","TOTAL",sumSubTotal,sumFee,sumTax,sumDiscount,sumGrandTotal);
	}

	public void printDetail(myList<Invoices> invoice, ArrayList<Products> product) {
		System.out.println("Individual Invoice Detail Reports");
		System.out.println("=====================================================");

		for(int i = 0;i<invoice.getSize();i++) {
			double subTotal=0.0;
			double tax=0.0;
			double discount=0.0;
			double fee=0.0;
			double sumSubTotal = 0.0;
			double sumTax =0.0;
			double sumDiscount =0.0;
			double sumFee =0.0;
			double sumGrandTotal =0.0;
			int movieTicketUnit = 0;
			int seasonPassUnit = 0;
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getSubTotal().size();j++) {
				subTotal += invoice.getNodeAtIndex(i).getItem().getSubTotal().get(j);
			}
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getTaxes().size();j++) {
				tax += invoice.getNodeAtIndex(i).getItem().getTaxes().get(j);
			}
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getDiscount().size();j++) {
				discount = invoice.getNodeAtIndex(i).getItem().getDiscount().get(j);
			}
			for(int j=0;j<invoice.getNodeAtIndex(i).getItem().getFees().size();j++) {
				fee = invoice.getNodeAtIndex(i).getItem().getFees().get(j);
			}
			double grandTotal = subTotal+tax;
			subTotal = Math.round(subTotal*100.0)/100.0;
			tax = Math.round(tax*100.0)/100.0;
			discount = Math.round(discount*100.0)/100.0;
			fee = Math.round(fee*100.0)/100.0;
			grandTotal = Math.round(grandTotal*100.0)/100.0;
			sumSubTotal +=subTotal;
			sumTax +=tax;
			sumDiscount +=discount;
			sumFee+=fee;
			sumGrandTotal+=grandTotal;
			System.out.println("Invoice "+invoice.getNodeAtIndex(i).getItem().getInvoiceCode());
			System.out.println("===========================");
			System.out.println("Salesperson: "+invoice.getNodeAtIndex(i).getItem().getSalesPersonCode().getLastName()+", "
					+invoice.getNodeAtIndex(i).getItem().getSalesPersonCode().getFirstName());
			System.out.println("Customer Info:");
			System.out.println("\t"+invoice.getNodeAtIndex(i).getItem().getCustomerCode().getName()+" ("
					+invoice.getNodeAtIndex(i).getItem().getCustomerCode().getCustomerCode()+")");
			if(invoice.getNodeAtIndex(i).getItem().getCustomerCode().getType().equals("G")) {
				System.out.println("\t[General]");
			}else {
				System.out.println("\t[Student]");
			}
			System.out.println("\t"+invoice.getNodeAtIndex(i).getItem().getCustomerCode().getContact().getLastName()+", "
					+invoice.getNodeAtIndex(i).getItem().getCustomerCode().getContact().getFirstName());
			System.out.println("\t"+invoice.getNodeAtIndex(i).getItem().getCustomerCode().getAddress().formattedtAddress());
			System.out.println("------------------------------------------------");
			System.out.printf("%-10s %-80s %-20s %-15s %-15s\n","Code","Item","SubTotal","Tax","Total");
			ArrayList<ProductInList> pl = invoice.getNodeAtIndex(i).getItem().getProductList();

			for(int a =0; a<pl.size();a++) {
				for(Products pro : product) {
					if(pl.get(a).getProductCode().equals(pro.getProductCode())) {

						if(pro.getProductType().equals("Movie Ticket")) {
							movieTicketUnit = pl.get(a).getProductUnit();
							System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n ",pl.get(a).getProductCode(),("MovieTicket "+((MovieTicket)pro).getMovieName()+"' @ "
									+((MovieTicket)pro).getAddress().formattedtAddress()), invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a),invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));		
							System.out.println("          "+((MovieTicket)pro).getMovieDateTime()+" ("
									+pl.get(a).getProductUnit()+" units @ $"+((MovieTicket)pro).getPricePerUnit()+"/unit)");

						}else if(pro.getProductType().equals("Parking Pass")) {
							if((movieTicketUnit>0 && pl.get(a).getProductUnit()<movieTicketUnit)) {
								int freeUnit;
								if(seasonPassUnit>0 && pl.get(a).getProductUnit()>seasonPassUnit) {
									freeUnit = seasonPassUnit;
								}else {
									freeUnit = pl.get(a).getProductUnit();
								}
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n",pl.get(a).getProductCode(),("ParkingPass "+pl.get(a).getAttachedCode()
										+" ("+pl.get(a).getProductUnit()+" units @ $"+((ParkingPass)pro).getParkingFee()+" with "+freeUnit+" free)"), invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a), invoice.getNodeAtIndex(i).getItem().getTaxes().get(a)
										,invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}else if(seasonPassUnit>0 && pl.get(a).getProductUnit()<seasonPassUnit) {
								int freeUnit;
								if(movieTicketUnit>0 && pl.get(a).getProductUnit()>movieTicketUnit) {
									freeUnit = movieTicketUnit;
								}else {
									freeUnit = pl.get(a).getProductUnit();
								}
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n",pl.get(a).getProductCode(),("ParkingPass "+pl.get(a).getAttachedCode()
										+" ("+pl.get(a).getProductUnit()+" units @ $"+((ParkingPass)pro).getParkingFee()+" with "+freeUnit+" free)"), invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a), invoice.getNodeAtIndex(i).getItem().getTaxes().get(a)
										,invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}
							else if(movieTicketUnit>0 && pl.get(a).getProductUnit()>movieTicketUnit){
								int freeUnit = movieTicketUnit;
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n",pl.get(a).getProductCode(),("ParkingPass "+pl.get(a).getAttachedCode()
										+" ("+pl.get(a).getProductUnit()+" units @ $"+((ParkingPass)pro).getParkingFee()+" with "+freeUnit+" free)"), invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a), invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}else if(seasonPassUnit>0 && pl.get(a).getProductUnit()>seasonPassUnit) {
								int freeUnit = seasonPassUnit;
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n",pl.get(a).getProductCode(),("ParkingPass "+pl.get(a).getAttachedCode()
										+" ("+pl.get(a).getProductUnit()+" units @ $"+((ParkingPass)pro).getParkingFee()+" with "+freeUnit+" free)"), invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a), invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}
							else {
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n",pl.get(a).getProductCode(),("ParkingPass "+pl.get(a).getAttachedCode()
										+" ("+pl.get(a).getProductUnit()+" units @ $"+((ParkingPass)pro).getParkingFee()+")"), invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a), invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));

							}

						}else if(pro.getProductType().equals("Season Pass")) {
							seasonPassUnit = pl.get(a).getProductUnit();
							System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n",pl.get(a).getProductCode()
									,("Season Pass - "+((SeasonPass)pro).getName()),invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a),invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							System.out.printf("	(%d units @ $%.2f/unit + $8 fee/unit)\n", pl.get(a).getProductUnit(),((SeasonPass)pro).getCost());
						}else if(pro.getProductType().equals("Refreshment")) {
							if(movieTicketUnit>0) {
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n", pro.getProductCode()
										,(((Refreshments)pro).getName()+" ("+pl.get(a).getProductUnit()+" units"+" @ $"
												+((Refreshments)pro).getCost()+"/unit with 5% off)")
										,invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a),invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}else if(seasonPassUnit>0) {
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n", pro.getProductCode()
										,(((Refreshments)pro).getName()+" ("+pl.get(a).getProductUnit()+" units"+" @ $"
												+((Refreshments)pro).getCost()+"/unit with 5% off)")
										,invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a),invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}
							else {
								System.out.printf("%-10s %-77s $%10.2f $%14.2f $%16.2f\n", pro.getProductCode()
										,(((Refreshments)pro).getName()+" ("+pl.get(a).getProductUnit()+" units"+" @ $"
												+((Refreshments)pro).getCost()+"/unit)"),invoice.getNodeAtIndex(i).getItem().getSubTotal().get(a),invoice.getNodeAtIndex(i).getItem().getTaxes().get(a),invoice.getNodeAtIndex(i).getItem().getGrandTotal().get(a));
							}
						}

					}
				}

			}
			System.out.printf("%135s\n","===========================================");
			System.out.printf("%-90s $%8.2f $%14.2f $%16.2f\n","SUB-TOTALS",sumSubTotal,sumTax,sumGrandTotal);

			if(invoice.getNodeAtIndex(i).getItem().getCustomerCode().getType().equals("Student")) {
				sumDiscount = sumSubTotal*0.08+sumTax;
				sumGrandTotal = sumGrandTotal -sumDiscount+sumFee;
				discount = Math.round(discount*100.0)/100.0;
				sumGrandTotal = Math.round(sumGrandTotal*100.0)/100.0;
				System.out.printf("%-122s $%10.2f\n","DISCOUNT (8% STUDENT & NO TAX)",-sumDiscount);
				System.out.printf("%-122s $%10.2f\n", "ADDITIONAL FEE (Student)", sumFee);
				System.out.printf("%-122s $%10.2f\n", "TOTAL",sumGrandTotal);
			}
			else {
				System.out.printf("%-122s $%10.2f\n","TOTAL", sumGrandTotal);
			}
			System.out.println("			Thank you for your purchase!\n\n");
		}

	}
}

