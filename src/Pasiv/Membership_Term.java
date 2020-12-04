package Pasiv;

import java.text.SimpleDateFormat;
import java.util.Calendar;

//permanentka na urcite obdobie
public class Membership_Term extends Membership {
	private int timePeriod;
	private Calendar expDate;
	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	
	public Membership_Term(int timePeriod) {
		super();
		this.timePeriod = timePeriod;
		this.setPrice(timePeriod * 20 - timePeriod); //vypocet ceny aj so mnozstevnou zlavou
		this.setType(timePeriod + "-mesacna");
		this.setExpDate(timePeriod);
	}
	
	//nastavenie datumu platnosti
	private void setExpDate(int timePeriod) {
		this.expDate = Calendar.getInstance();
		this.expDate.set(Calendar.MONTH, expDate.get(Calendar.MONTH) + timePeriod);
		this.expDate.set(Calendar.HOUR, 11);
		this.expDate.set(Calendar.MINUTE, 59);
		this.expDate.set(Calendar.SECOND, 59);
			
	}

	public int getTimePeriod() {
		return timePeriod;
	}

	
	public Calendar getExpDate() {
		return expDate;
	}
	
	public void showExpDate() {
		System.out.println(sdf.format(this.getExpDate().getTime()));
	}
	
	//pri vstupe zakaznika do posilky sa skontroluje ci je permanentka platna
	public boolean onArrival() {
		Calendar now = Calendar.getInstance();
		if(now.after(this.getExpDate()) ) {
			System.out.println("Platnost permanentky vyprsala");
			return false;
		}
		return true;
		
	}

	
	
	
	
	
}
