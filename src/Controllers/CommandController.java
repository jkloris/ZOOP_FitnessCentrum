package Controllers;

import Persons.*;
import UnplacedManagers.*;
import Pasiv.*;

import java.util.Scanner;

import Lockers.LockerManager;

public class CommandController {
	private static CommandController instance = null;
	public CustomerManager CM;
	public LockerManager LM;
	public MembershipManager MM;
	public StoreManager SM;
	public Scanner scanner = new Scanner(System.in);
	private Owner owner;		
	private Customer user = null;
	private boolean admin = false;
	
	
	private CommandController(CustomerManager CM, LockerManager LM, MembershipManager MM, StoreManager SM, Owner owner) {
		this.CM = CM;
		this.LM = LM;
		this.MM = MM;
		this.SM = SM;
		this.owner = owner;
		
	}
	
	public static CommandController getInstance(CustomerManager CM, LockerManager LM, MembershipManager MM, StoreManager SM, Owner owner) {
		if(instance == null)
			instance = new CommandController(CM, LM,  MM, SM, owner);
		return instance;
	}
	
	public void manageInput() {
		System.out.println("HELP pre zoznam prikazov");
		String command = scanner.next();
		switch (command) {
		case "HELP":
			this.helpCmd();
			break;
		case "IDENTIFY":
			this.user = this.getUserInfo();;
			break;
		case "REGISTER":
			this.user = this.register(this.user);
			break;
		case "ARRIVE":
			if(this.user != null) {
				if(CM.customerArrived(this.user))
					LM.assignLocker(this.user);
			} else
				System.out.println("Identifikuj sa");
			break;
		case "LEAVING":
			if(this.user != null) {
				CM.customerIsLeaving(this.user);
			}else 
				System.out.println("Identifikuj sa");
			break;
		case "BUY_MEMB":
			this.buyMembership(user);
			break;
		case "SHOW_TR":
			this.getOwner().showTrainers();
			break;
			
		//ADMIN cmds
		case "SHOW_U_REG":	
			if(this.admin)
				CM.showRegCustomers();
			break;
		case "SHOW_U_IN":
			if(this.admin)
				CM.showCustomersIn();
			break;
		case "ADMIN":
			this.adminLog();
			break;
		case "HIRE_TR":
			if(this.admin)
				this.addTrainer();	
			break;
		case "ADM_LOGOUT":
			this.admin = false;
			break;
		default:
			System.out.println("Neznamy prikaz!");
			break;
		}
	}
	
	private void addTrainer() {
		System.out.println("Zadajte meno, vek, plat a specializaciu trenera:");
		String name = scanner.next();
		int age = scanner.nextInt();
		int price = scanner.nextInt();
		String skill = scanner.next();
		
		owner.addTrainer(new Trainer(name, age, skill, price));
	}
	
	private void buyMembership(Customer customer) {
		if(customer == null) {
			System.out.println("Najprv sa identifikujte");
			return;
		}
		System.out.println("Zvol si pozadovanu permanentku");
		System.out.println("T \t permanentka na dobu");
		System.out.println("V \t vstupova permanentka");
		System.out.println("J \t jernorazovy vstup");
		
		String cmd = scanner.next();
		
		int n ;
		Membership m = null;
		
		switch (cmd) {
		case "T":
			System.out.println("Kolko mesacnu? Napis cislom: 1; 3; 6; 12");
			n = scanner.nextInt();
			if(n == 1 || n == 3 || n == 6 || n == 12) {
				m = new Membership_Term(n);
			}else {
				System.out.println("Zle zvolene parametre");
			}
			break;
		case "V":
			System.out.println("Kolko vstupovu? Napis cislom: 10; 20; 30");
			n = scanner.nextInt();
			if(n == 10 || n == 20 || n == 30) {
				m = new Membership_Visits(n);
			}else {
				System.out.println("Zle zvolene parametre");
			}
			break;
		case "J":
			m = new Membership_PAYG();
			break;
		default:
			System.out.println("Zly vstup");
			break;
		}
		
		if(m != null) {
			MM.sellMembership(customer, m);
			System.out.println("Uspesne zakupena permanentky. Cena: " + m.getPrice() + "€");
		}
		
	}
	
	private Customer register(Customer customer) {
		if(customer != null) {
			CM.registerCustomer(customer);
		}else {
			System.out.println("Zadajte meno a vek:");
			String name = scanner.next();
			int age = scanner.nextInt();
			if(name instanceof String && age > 0) {
				Customer newUser =  new Customer(name, age);
				CM.registerCustomer( newUser);
				return newUser;
			}
			
		}
		return customer;
	}
	
	
	private Customer getUserInfo() {
		System.out.println("Zadajte meno a vek:");
		String name = scanner.next();
		int age = scanner.nextInt();
		if(name instanceof String && age > 0) {
			Customer  user = CM.identifyCustomer(name, age);
			if(user != null) {
				System.out.println("Vitajte!");
				return user;
			}
		}else {
			System.out.println("Zle vlozene hodnoty");
		}
		return null;
	}
	
	private void helpCmd() {
		System.out.println("HELP \t zoznam prikazov");
		System.out.println("IDENTIFY \t identifikovanie sa");
		System.out.println("REGISTER \t zaregistrovanie sa");
		System.out.println("BUY_MEMB \t identifikovany zakaznik si moze kupit permanentku");
		System.out.println("ARRIVE \t identifikovany zakaznik s platnou permanentkou vstupi do posilky");
		System.out.println("ADMIN \t prihlasenie sa admina");
		System.out.println("SHOW_TR \t ukaze trenerov ktorí su k dispozícií");
		System.out.println("LEAVING \t prikaz zada zakaznik pri odchode");
		if(this.admin) {
			System.out.println("ADM_LOGOUT \t odhlasi admina");
			System.out.println("HIRE_TR \t zamestnaj noveho trenera");
			System.out.println("SHOW_U_REG \t ukaze registrovanych zakaznikov");
			System.out.println("SHOW_U_IN \t ukaze zakaznikov v posilke");
		}
	}
	
	private Owner getOwner() {
		return this.owner;
	}
//	public Owner getOwner(String password) {
//		if(password == "heslo")
//			return this.owner;
//		System.out.println("Zle heslo");
//		return null;
//	}
////	private boolean getAdmin() {
//		return this.admin;
//	}
	
	private boolean setAdmin(String password) {
		System.out.println(password);
		if(password.contentEquals("heslo123")) {
			this.admin = true;
			return true;
		}
		System.out.println("Zle heslo");
		return false;
	}
	
	private void adminLog() {
		System.out.println("Zadajte heslo:");
		String passw = scanner.next();
		this.setAdmin(passw);
	}
}
