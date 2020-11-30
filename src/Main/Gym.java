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
	private Owner owner = new Owner("Dr_Trendo", 45, "vsetko", 10, this.trainers);
	public LockerManager lockerManager = LockerManager.getInstance();
	public CustomerManager customerManager = CustomerManager.getInstance();
	public StoreManager storeManager = StoreManager.getInstance();
	public MembershipManager membershipManager = MembershipManager.getInstance();
	public CommandController cmdController = CommandController.getInstance(customerManager, lockerManager, membershipManager, storeManager, owner);
	
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
	

}