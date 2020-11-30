package Lockers;
//import Persons.Customer;
import java.util.ArrayList;
import Persons.*;

public class LockerManager {
	private ArrayList<Locker> lockers = new ArrayList<Locker>();
	private int maxCapacity = 100;
	private static LockerManager instance = null;
	
	private LockerManager() {
		for(int i = 0; i < this.maxCapacity; i++) {
			this.lockers.add(new Locker(i));
		}
	}
	
	public static LockerManager getInstance() {
		if(instance == null)
			instance = new LockerManager();
		return instance;
	}
	
	public void showAllLockers() {
	
		for(int i = 0; i < this.maxCapacity; i++) {
			if(this.lockers.get(i).getOccupant() == null)
				System.out.println("Skrinka cislo:" + i + " je volna.");
			else
				System.out.println("Skrinka cislo: " + i + " je obsadena.");
		}
	}
	
	public boolean assignLocker(Customer customer) {
		for(int i = 0; i < this.maxCapacity; i++) {
			if(this.lockers.get(i).assignOccupant(customer)) {
				customer.setLocker(this.lockers.get(i));
				System.out.println("Priradena skrinka cislo: " + i);
				return true;
			}else if(customer == this.lockers.get(i).getOccupant()) {
				System.out.println("Uz mate jednu skrinku");
				return false;
			}
		}
		
		System.out.println("Nie su volne skrinky.");
		return false;
	}
	
	public void freeLocker(Customer customer) {
		System.out.println("Skrinka cislo: "+ customer.getLocker().getLockerNumber() +" uvolnena");
		this.lockers.get(customer.getLocker().getLockerNumber()).assignOccupant(null);
		customer.setLocker(null);
	}
	
	
}
