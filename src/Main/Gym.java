package Main;
import java.util.ArrayList;
import java.util.Random;

import Controllers.CommandController;
import Interfaces.Generatable;
import Lockers.LockerManager;
import Persons.*;
import UnplacedManagers.CustomerManager;
import UnplacedManagers.MembershipManager;
import UnplacedManagers.StoreManager;

public class Gym {
	static Gym instance = null;
	public boolean start = true;
	private float revenue = 1000;
	private ArrayList<Trainer> trainers = new ArrayList<Trainer>();
	private Owner owner = new Owner("Dr_Trendo", 45, "vsetko", 10, this.trainers);
	public LockerManager lockerManager = LockerManager.getInstance();
	public CustomerManager customerManager = CustomerManager.getInstance();
	public StoreManager storeManager = StoreManager.getInstance(this);
	public MembershipManager membershipManager = MembershipManager.getInstance(this);
	public CommandController cmdController = CommandController.getInstance(customerManager, lockerManager, membershipManager, storeManager, owner, this);
	
	public ArrayList<Generatable> gener = new ArrayList<Generatable>();
	private Gym() {
		
	}
	
	public static Gym getInstance() {
		if(instance == null)	
			instance = new Gym();
		return instance;
	}
	
	
	public void loop() {
		while(start) {
			cmdController.manageInput();
		}
		System.out.println("Aplikacia ukoncena..");
	}
	
	//nahodne vygenerovanie objektov
	public void generate(int amount) {
		//pocetnost jednotlivych objektov v poli urcuje pravdepodobnost s akou sa objekt vygeneruje
		gener.add(customerManager);
		gener.add(customerManager);
		gener.add(customerManager);
		gener.add(owner);
		gener.add(storeManager);
		
		Random r = new Random();
		for(int i = 0; i < amount; i++) {
			int rnd = r.nextInt(gener.size());
			
			gener.get(rnd).randomG();
		}
	}

	public float getRevenue() {
		return revenue;
	}

	public void setRevenue(float revenue) {
		this.revenue = revenue;
	}
	
	public static void main(String[] args) {
		Gym gym = Gym.getInstance();
		gym.generate(20);
		gym.loop();
	}
	
	
	

}