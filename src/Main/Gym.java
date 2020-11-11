package Main;
import java.util.ArrayList;

import Controllers.CommandController;
import Lockers.LockerManager;
import Pasiv.*;
import Persons.*;
import UnplacedManagers.CustomerManager;
import UnplacedManagers.MembershipManager;
import UnplacedManagers.StoreManager;

public class Gym {
	static Gym instance = null;
	private ArrayList<Trainer> trainers = new ArrayList<Trainer>();
	public LockerManager lockerManager = LockerManager.getInstance();
	public CustomerManager customerManager = CustomerManager.getInstance();
	public StoreManager storeManager = StoreManager.getInstance();
	public MembershipManager membershipManager = MembershipManager.getInstance();
	public CommandController cmdController = CommandController.getInstance(customerManager, lockerManager, membershipManager, storeManager);
	private Gym() {
		
	}
	
	public static Gym getInstance() {
		if(instance == null)	
			instance = new Gym();
		return instance;
	}
	
	
	public void loop() {
		while(true) {
			cmdController.manageInput();
		}
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