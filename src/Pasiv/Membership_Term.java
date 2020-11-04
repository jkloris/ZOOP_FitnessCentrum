package Pasiv;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Membership_Term extends Membership {
	private int timePeriod;
	private Calendar expDate;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	
	public Membership_Term(int timePeriod) {
		super();
		this.timePeriod = timePeriod;
		this.setPrice(timePeriod * 20 - timePeriod);
		this.setType(timePeriod + "-mesacna");
		this.setExpDate(timePeriod);
	}
	
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
	
	public boolean onArrival() {
		Calendar now = Calendar.getInstance();
		if(now.after(this.getExpDate()) ) {
			System.out.println("Platnost permanentky vyprsala");
			return false;
		}
		return true;
		
	}

	
	
	
	
	
}
