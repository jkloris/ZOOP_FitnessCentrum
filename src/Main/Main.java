package Main;
import Persons.*;
import Calendar.*;
import Lockers.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import Pasiv.*;

public class Main {

	public static void main(String[] args) {
		Customer ja = new Customer("Ja", 21);
		Customer ty = new Customer("TY", 25);
		Customer on = new Customer("On", 18);
		Customer ona = new Customer("Ona", 45);
		Customer ono = new Customer("Ono", 15);
		Trainer tr1 = new Trainer("Trendo", 23, "kardio", 10);
		
		
		Gym gym = Gym.getInstance();
		
		gym.customerManager.customerArrived(gym.customerManager.identifyCustomer("Ja", 21));
		gym.customerManager.registerCustomer(ja);
		gym.customerManager.identifyCustomer("Ja", 21).setMembership(new Membership_Visits(10));
		gym.customerManager.customerArrived(ja);
//		ja.getMembership().showExpDate();
//		System.out.println(ja.getMembership().getType());
//		
//		ty.setMembership(new Membership_Visits(3));
//		System.out.println(ty.getMembership().getPrice());
//		System.out.println(ty.getMembership().getType());
//		ty.getMembership().showVisitsLeft();
//		ty.getMembership().remOneVisit();
//		ty.getMembership().remOneVisit();
//		ty.getMembership().remOneVisit();
//		ty.getMembership().remOneVisit();
//		
		
		//Membership_Term mt = new Membership_Term(3);
		//System.out.println(mt.getPrice());
		//mt.showExpDate();
		
//		
//		LockerManager lm = LockerManager.getInstance();
//		lm.assignLocker(ty);
//		lm.assignLocker(on);
//		lm.assignLocker(ja);
//		lm.assignLocker(ono);
//		lm.freeLocker(ja);
//		lm.showAllLockers();
		
		//tr1.getSchedule().showSchedule();
		
	}

}
