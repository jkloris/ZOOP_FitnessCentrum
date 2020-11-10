package Persons;
import Pasiv.*;
import Lockers.*;

public class Customer extends Human{
	private int id;
	private Locker locker;
	private Trainer trainer;
	private Membership membership;
	
	public Customer(String name, int age) {
		super( name, age);
		this.setId();
	}
	
	public Membership getMembership() {	
		return this.membership;
	}
		
	public void setMembership(Membership membership) {
		this.membership = membership;
	}
	
	public Trainer getTrainer() {
		return this.trainer;
	}
	
	public void assignTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	
	public int getId() {
		return this.id;
	}
	
	public void setId() {
		this.id = this.getAge()*this.getName().hashCode();
	}
	
	public Locker getLocker() {
		return this.locker;
	}
	public void setLocker(Locker locker) {
		this.locker = locker;
	}
	
	
}
