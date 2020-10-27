package Pasiv;
import java.util.ArrayList;
import Persons.*;

public class LockerManager {
	//TODO
	private ArrayList<Customer> lockers = new ArrayList<Customer>();
	private int maxCapacity = 20;
	private static LockerManager instance = null;
	
	private LockerManager() {}
	
	public static LockerManager getInstance() {
		if(instance == null)
			instance = new LockerManager();
		return instance;
	}
	
	
}
