package Pasiv;
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

	protected void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
