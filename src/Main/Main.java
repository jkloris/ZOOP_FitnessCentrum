package Main;
import Persons.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import Pasiv.*;

public class Main {

	public static void main(String[] args) {
		Customer ja = new Customer("Jergo", 21);
		Customer ty = new Customer("TY", 21);
	
		Trainer tr1 = new Trainer("Trendo", 23, "kardio", 10);
		
		Membership_Term mt = new Membership_Term(3);
		System.out.println(mt.getPrice());
		mt.showExpDate();
		LockerManager lm = LockerManager.getInstance();
		
	}

}
