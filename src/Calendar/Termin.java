package Calendar;

import Persons.Customer;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//Jedna hodina v tyzdni vyhradena pre trening
public class Termin {
	private Calendar date;
	private Customer customer = null;
	private final SimpleDateFormat sdf = new SimpleDateFormat("| YYYY dd. MMM HH:mm |");
	
	public Termin(int day, int hour) {
		this.date = Calendar.getInstance();
		this.date.set(Calendar.WEEK_OF_YEAR, this.date.get(Calendar.WEEK_OF_YEAR) + 1 );
		this.date.set(Calendar.DAY_OF_WEEK, day );
		this.date.set(Calendar.HOUR,hour-12);
		this.date.set(Calendar.MINUTE, 0);
	}
	
	//priradi zakaznika na tento termin
	protected boolean setCustomer(Customer customer) {
		if(this.customer == null) {
			this.customer = customer;
			//nazaciatku sa nastavia obedne pauzy a zbytocne sa vypisovala fraza "termin priradeny", tak toto to osetruje
			if(!customer.getName().equals("Lunch Break"))
				System.out.println("termin priradeny");
			return true;
		}
		System.out.println("Termin je obsadeny");
		return false;
	}
	
	//len ukaze termin, 2 moznosti vypisu
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
	
	//zobrazi zakaznika alebo volny termin
	public void showCustomer() {
		System.out.print("Zakaznik: ");
		if(this.getCustomer() != null)
			System.out.print(this.getCustomer().getName());
		else
			System.out.print("free ");
	}
	
	
	
	
	
	

}

