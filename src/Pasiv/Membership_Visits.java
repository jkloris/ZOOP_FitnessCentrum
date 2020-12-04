package Pasiv;

//permanentka na pocet vstupov
public class Membership_Visits extends Membership{
	private int visitsLeft;
	private int numOfVisits;
	
	public Membership_Visits(int numOfVisits) {
		super();
		this.setVisits(numOfVisits);
		this.setPrice(numOfVisits*3 - numOfVisits); //vypocet ceny aj so zlavou
		this.setType(numOfVisits + "-vstupova");
	}
	
	
	private void setVisits(int visits) {
		this.visitsLeft = visits;
		this.numOfVisits = visits;
	}
	
	public int getVisitsLeft() {
		return this.visitsLeft;
	}
	
	public void showVisitsLeft() {
		System.out.println(this.getVisitsLeft());
	}
	
	//odstrani jeden vstup z permanentky a oboznamuje o zostavajucom pocte vstupov
	public boolean remOneVisit() {
		if(this.getVisitsLeft() > 1) {
			this.setVisits(this.getVisitsLeft() - 1);
			System.out.println("Pocet zostavajucich vstupov: "+ this.getVisitsLeft());
		}else if(this.getVisitsLeft() == 1) {
			this.setVisits(0);
			System.out.println("Toto bol posledny vstup");
		}else {
			System.out.println("Nemate volne vstupy");
			return false;
		}
		return true;
	}
	
	//pri vstupe do posilky zisti ci ma dost vstupov na permanenke a ak ako, jeden zmaze
	public boolean onArrival() {
		if(this.remOneVisit())
			return true;
		return false;
	}
}
