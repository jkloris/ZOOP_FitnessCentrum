package Pasiv;

//jednorazova permanentka, pri odchode by sa mala vymazat
public class Membership_PAYG extends Membership {

	public Membership_PAYG() {
		super();
		this.setPrice(3);
		this.setType("Jednorazova");
	}
	
}
