package Main;
import java.util.ArrayList;

import Lockers.LockerManager;
import Pasiv.*;
import Persons.*;
import UnplacedManagers.CustomerManager;

public class Gym {
	static Gym instance = null;
	private ArrayList<Trainer> trainers = new ArrayList<Trainer>();
	public LockerManager lockerManager = LockerManager.getInstance();
	public CustomerManager customerManager = CustomerManager.getInstance();
	private Gym() {
		
	}
	
	public static Gym getInstance() {
		if(instance == null)	
			return new Gym();
		return instance;
	}
	
//	public void hireTrainer(Trainer trainer) {
//		this.getTrainers().add(trainer);
//	}
//	
//	public boolean fireTrainer(Trainer trainer) {
//		return this.getTrainers().remove(trainer);	
//	}
//	
//	public ArrayList<Trainer> getTrainers(){
//		return this.trainers;
//	}

}