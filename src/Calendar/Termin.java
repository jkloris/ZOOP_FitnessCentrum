package Calendar;

import Persons.Customer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Termin {
	private Calendar date;
	private Customer customer = null;
	private SimpleDateFormat sdf = new SimpleDateFormat("| YYYY dd. MMM HH:mm |");
	
	protected Termin(int day, int hour) {
		this.date = Calendar.getInstance();
		this.date.set(Calendar.WEEK_OF_YEAR, this.date.get(Calendar.WEEK_OF_YEAR) + 1 );
		this.date.set(Calendar.DAY_OF_WEEK, day );
		this.date.set(Calendar.HOUR,hour-12);
		this.date.set(Calendar.MINUTE, 0);
	}
	
	protected boolean setCustomer(Customer customer) {
		if(this.customer == null) {
			this.customer = customer;
			System.out.println("termin priradeny");
			return true;
		}
		System.out.println("Termin je obsadeny");
		return false;
	}
	
	public void showTermin(int ln) {
		if(ln == 1)
			System.out.println(sdf.format(this.getDate().getTime()));
		else
			System.out.print(sdf.format(this.getDate().getTime()));
			
	}
	
	public Calendar getDate() {
		return this.date;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void showCustomer() {
		System.out.print("Zakaznik: ");
		if(this.getCustomer() != null)
			System.out.print(this.getCustomer().getName());
		else
			System.out.print("free ");
	}
	
	
	
	
	
	

}

