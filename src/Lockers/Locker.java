package Lockers;
import Persons.Customer;

//skrinka s cislom a jej majitelom
public class Locker {
	private Customer occupant = null ;
	//final lebo cislo sa nikdy nebude menit
	private final int number;
	
	public Locker(int number) {
		this.number = number;
	}
	
	//...samoopisne funkcie..
	
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
			return true;
		}
		return false;
	}
	
	
	
	
}
