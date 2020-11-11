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
	private Customer user = null;
	
	
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
			if(this.user != null)	
				CM.customerArrived(this.user);
			else
				System.out.println("Identifikuj sa");
			break;
		case "BUY_MEMB":
			this.buyMembership(user);
			break;
			
			
		//ADMIN cmds ..TODO
		case "SHOW_U":			
			CM.showRegCustomers();
		default:
			System.out.println("Neznamy prikaz!");
			break;
		}
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
	}
}
