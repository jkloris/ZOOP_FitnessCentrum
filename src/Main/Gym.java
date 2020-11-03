package Main;
import java.util.ArrayList;

import Lockers.LockerManager;
import Pasiv.*;
import Persons.*;

public class Gym {
	static Gym instance = null;
	private ArrayList<Trainer> trainers = new ArrayList<Trainer>();
	public LockerManager lockerManager = LockerManager.getInstance();
	
	private Gym() {
		
	}
	
	public Gym getInstance() {
		if(this.instance == null)	
			return new Gym();
		return this.instance;
	}
	
	public void hireTrainer(Trainer trainer) {
		this.getTrainers().add(trainer);
	}
	
	public boolean fireTrainer(Trainer trainer) {
		return this.getTrainers().remove(trainer);	
	}
	
	public ArrayList<Trainer> getTrainers(){
		return this.trainers;
	}

}