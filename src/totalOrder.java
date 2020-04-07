package Assignment2;

import java.util.Comparator;
public class totalOrder implements Comparator<Invoices>{
	@Override
	public int compare(Invoices i1, Invoices i2) {
		double value1 = 0.0;
		double value2 = 0.0;
		value1 = i1.getTotalValue();
		value2 = i2.getTotalValue();
		return this.compareValue(value1, value2);
	}

	public int compareValue(double value1, double value2){
		if(value1 > value2){
			return -1;
		}else if (value1 < value2){
			return 1;
		}
		return 0;
	}
}