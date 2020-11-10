package Controllers;

import UnplacedManagers.*;
import Lockers.LockerManager;

public class CommandController {
	private static CommandController instance = null;
	public CustomerManager CM;
	public LockerManager LM;
	public MembershipManager MM;
	public StoreManager SM;
	
	
	private CommandController(CustomerManager CM, LockerManager LM, MembershipManager MM, StoreManager SM) {
		this.CM = CM;
		this.LM = LM;
		this.MM = MM;
		this.SM = SM;
		
	}
	
	public static CommandController getInstance(CustomerManager CM, LockerManager LM, MembershipManager MM, StoreManager SM) {
		if(instance == null)
			instance = new CommandController(CM, LM,  MM, SM);
		return instance;
	}
}
