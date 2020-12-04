package Pasiv;
//import java.util.Calendar;

//rodicovky objekt pre Membershipy, priamo sa nepouziva ale vyuziva sa cez polymorfizmus
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
	//spusti sa pri vstupe zakaznika do posilky
	public boolean onArrival() {
		return true;
	}

	protected void setPrice(int price) {
		this.price = price;
	}
	

	
	
}
