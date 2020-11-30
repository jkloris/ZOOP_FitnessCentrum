package Lockers;
import Persons.Customer;

public class Locker {
	private Customer occupant = null ;
	private int number;
	
	public Locker(int number) {
		this.number = number;
	}
	
	public void showOccupant() {
		System.out.println(this.occupant.getName());
	}
	
	public Customer getOccupant() {
		return this.occupant;
	}
	
	public int getLockerNumber() {
		return this.number;
	}
	
	public boolean assignOccupant(Customer occupant){
		if(this.occupant == null || occupant == null)
		{
			this.occupant = occupant;
			//System.out.println(this.occupant);
			return true;
		}
		return false;
	}
	
	
	
	
}
