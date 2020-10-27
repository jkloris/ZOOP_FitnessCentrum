package Persons;
import Pasiv.*;

public class Customer extends Human{
	private int id;
	private int locker;
	private Trainer trainer;
	private Membership membership;
	
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

	public Customer(String name, int age) {
		super( name, age);
		this.setId();
	}
	
	
	public int getId() {
		return this.id;
	}
	
	public void setId() {
		this.id = this.getAge()*this.getName().hashCode();
	}
	
	public int getLocker() {
		return this.locker;
	}
	public void setLocker(int locker) {
		this.locker = locker;
	}
	
	
}
