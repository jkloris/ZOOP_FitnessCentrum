package Pasiv;
import java.util.Calendar;

import Persons.*;
public  class Membership {
	private String type;
	private int price;
	
	
	public Membership() {
		this.price = 10;
	}
	
	public void buyMembership(Customer customer) {
		customer.setMembership(this);
	}

	public String getType() {
		return this.type;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public int getPrice() {
		return this.price;
	}
	
	public boolean onArrival() {
		return true;
	}

	protected void setPrice(int price) {
		this.price = price;
	}
	
	//metody pre Membership_Term
//	public int getTimePeriod() {
//		return 0;
//	}
//	
//	public Calendar getExpDate() {
//		return Calendar.getInstance();
//	}
//	
//	public void showExpDate() {};
	
	//metody pre Membership_Visits
//	public int getVisitsLeft() {
//		return 0;
//	}
//	
//	public void showVisitsLeft() {}
//	
//	public boolean remOneVisit() {
//		return false;
//	}
	
	
}
