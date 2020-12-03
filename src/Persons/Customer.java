package Persons;
import Pasiv.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import Calendar.Termin;
import Lockers.*;

public class Customer extends Human {
	private long id;
	private Locker locker;
	private Trainer trainer;
	private Membership membership;
	private ArrayList<Termin> trainings = new ArrayList<Termin>();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");
	
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
	
	public ArrayList<Termin> getTrainings(){
		return this.trainings;
	}
	
	public void showMyTrainings() {
		
		System.out.println("Treningy s trenerom " + this.getTrainer().getName());
		for(Termin t : this.getTrainings()) {
			t.showTermin(1);
		}
	}
	
	public Trainer getTrainer() {
		return this.trainer;
	}
	
	public void assignTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	
	public long getId() {
		return this.id;
	}
	
	public void setId() {
		this.id = Math.abs(this.getAge()*this.getName().hashCode());
	}
	
	public Locker getLocker() {
		return this.locker;
	}
	public void setLocker(Locker locker) {
		this.locker = locker;
	}

	@Override
	public void introduceMyself() {
		System.out.println("meno: "+ this.getName() +
				"; vek: " + this.getAge() +
				"; id: " + this.getId() ); 
		if(this.getLocker() != null)		
			System.out.println("skrinka: " + this.getLocker().getLockerNumber()); 
		if(this.getTrainer() != null)
			System.out.println("trener: " + this.getTrainer().getName());
		if(this.membership != null) {
			System.out.println("Permanentka: " +this.membership.getType());
			if(this.membership instanceof Membership_Visits) {
				System.out.println("pocet vstupov: "+ ((Membership_Visits)membership).getVisitsLeft());
			}else if(this.membership instanceof Membership_Term) {	
				System.out.println("Platná do: " + this.sdf.format(((Membership_Term)membership).getExpDate().getTime()));
			}
		}
		
	}
	
	
}
