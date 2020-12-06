package Controllers;

import Persons.*;
import UnplacedManagers.*;
import Pasiv.*;

import java.util.Scanner;

import Calendar.Termin;
import Lockers.LockerManager;
import Main.Gym;

//simuluje konzolovu aplikaciu 
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
	private Gym gym; 
	
	
	private CommandController(CustomerManager CM, LockerManager LM,
			MembershipManager MM, StoreManager SM, Owner owner, Gym gym) {
		this.CM = CM;
		this.LM = LM;
		this.MM = MM;
		this.SM = SM;
		this.owner = owner;
		this.gym = gym;
		
	}
	
	public static CommandController getInstance(CustomerManager CM, LockerManager LM, MembershipManager MM, StoreManager SM, Owner owner, Gym gym) {
		if(instance == null)
			instance = new CommandController(CM, LM,  MM, SM, owner, gym);
		return instance;
	}
	
	//kontroluje input pouzivatela a nasledne odpoveda
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
		case "CHOOSE_TR":
			if(this.user != null) {
				this.chooseTrainer(user);
			}else
				System.out.println("Identifikuj sa");
			break;
		case "CHOOSE_TG":
			if(this.user != null) {
				this.chooseTgTermin(user);
			}else
				System.out.println("Identifikuj sa");
			break;
		case "WHO":
			if(this.user != null) {
				this.user.introduceMyself();
			}else
				System.out.println("Identifikuj sa");
			break;
		case "SHOP_STOCK":
			SM.showProductsInStock();
			break;
		case "TO_SHOP":
			if(this.user != null) {
				this.toShop();
			}else
				System.out.println("Identifikuj sa");
			break;
		case "SHOW_TGS":
		if(this.user != null) {
			this.user.showMyTrainings();
		}else
			System.out.println("Identifikuj sa");
		break;
		case "EXIT":
			gym.start = false;
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
		case "FIRE_TR":
			if(this.admin)
				this.fireTrainer();	
			break;
		case "ADM_LOGOUT":
			this.admin = false;
			break;
		case "TR_INFO":
			if(this.admin)
				this.showInfoAboutTr();
			break;
		case "RESTOCK":
			if(this.admin) {
				this.restock();
			}
			break;
		case "SHOW_BANK":
			if(this.admin) {
				System.out.println(gym.getRevenue() + "€");
			}
			break;
		case "LIMIT":
			if(this.admin) {
				this.customerLimit();
			}
			break;
		default:
			System.out.println("Neznamy prikaz!");
			break;
		}
	}
	
	//zobrazi max pocet zakaznikov v posilke a umozni tuto hodnotu zmenit
	private void customerLimit() {
		System.out.println("Maximalny pocet zakaznikov v posilke je: " + CM.getCustomerLimit());
		System.out.println("Chcete ho zmenit? y/n");
		char f = scanner.next().charAt(0);
		if(f == 'y') {
			System.out.println("Zadajte novy limit:");
			int l = scanner.nextInt();
			CM.setCustomerLimit(l);
		}
	}
	
	
	//vyhodi trenera
	private void fireTrainer() {
		System.out.println("Zadajte meno trenera:");
		String name = scanner.next();
		Trainer t = owner.identifyTrener(name);
		if(t != null) {
			owner.delTrainer(t);
			System.out.println("Trener vyhodeny");
			return;
		}
		System.out.println("Taky trener tu nie je zamestany");
	}
	
	//umoznuje doplnit zasoby v obchode
	private void restock() {
		System.out.println("Aky produkt chcete doplnit?");
		String name = scanner.next();
		Product p = SM.identify(name);
		if(p == null) {
			System.out.println("Zadajte nakupnu cenu:");
			float price = scanner.nextFloat();
			System.out.println("Zadajte popis produktu:");
			String description  = scanner.next() + scanner.nextLine();
			System.out.println("Chcete specifikovat predajnú cenu? y/n");
			char y = scanner.next().charAt(0);
			if(y == 'y') {
				System.out.println("Tak ju zadajte:");
				float sellPrice = scanner.nextFloat();
				p = new Product(name, description, price, sellPrice);
			}else
				p = new Product(name,description,price);
		}
		System.out.println("Zadajte pocet kusov:");
		int amount = scanner.nextInt();
		for(int i = 0; i < amount; i++) {
			SM.restock(p);
		}
	}
	
	//identifikuje zadany produkt a ak taky je na sklade, preda mu zadany pocet
	private void toShop() {
		System.out.println("Zadajte nazov produktu a pocet kusov:");
		String name = scanner.next();
		int amount = scanner.nextInt();
		Product p = SM.identify(name);
		if(p != null) {
			int count = 0;
			for(int i = 0; i < amount; i++) {
				if(SM.sellProduct(p)) {
					count++;
				}
			}
			System.out.println("Ucet: " + name + ", "+ count+"ks, cena: " + p.getSellPrice()*count + "€");
			
		}else {
			System.out.println("Tento product v obchode nie je");
		}
	}
	
	//identifikuje trenera a zobrazi info o nom
	private void showInfoAboutTr() {
		System.out.println("Zadajte meno trenera:");
		String name = scanner.next();
		Trainer tr = this.owner.identifyTrener(name);
		if(tr != null) {
			tr.introduceMyself();
		}
	}
	
	//priradi zadaneho trenera k zakaznikovi a zakaznika do zoznamu zakaznikov daneho trenera 
	private void chooseTrainer(Customer customer) {
		System.out.println("Zadajte meno trenera:");
		String tr_name = scanner.next();

		Trainer trainer = this.owner.identifyTrener(tr_name);
		if(trainer != null) {
			customer.assignTrainer(trainer);
			trainer.assignCustomer(customer);
			System.out.println("Trener prideleny");
		}
		
	}
	//Zakaznik si vyberie termin treningu (musi mat trenera)
	private void chooseTgTermin(Customer customer) {
		if(customer.getTrainer() != null) {
			System.out.println("Zadajte hodinu treningu (9-20) a o kolko dni chcete trening (0-7):");
			int hour = scanner.nextInt();
			int day = scanner.nextInt();
			if(hour < 9 || hour > 20 || day < 0 || day > 7) {
				System.out.println("Zle zadane hodnoty!");
				return;
			}
			customer.getTrainer().getSchedule().assignCustomer(customer, day, hour);
			customer.getTrainings().add(new Termin(day, hour));
			
		} else
			System.out.println("Nemate prideleneho trenera!");
	}
	
	//zamestna trenera
	private void addTrainer() {
		System.out.println("Zadajte meno, vek, plat a specializaciu trenera:");
		String name = scanner.next();
		int age = scanner.nextInt();
		int price = scanner.nextInt();
		String skill = scanner.next();
		
		owner.addTrainer(new Trainer(name, age, skill, price));
	}
	
	//zakaznik si odklika aku permanentku si chce kupit
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
	//registracia zakaznika
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
	
	//zobrazi dolezite info o identifikovanom zakaznikovi
	private Customer getUserInfo() {
		System.out.println("Zadajte svoje ID alebo svoje meno a vek:");
		String flag = scanner.next();
	
		int id = 0;
		try {
			id = Integer.parseInt(flag);
		} catch (NumberFormatException nfe) {
			int age = scanner.nextInt();
			Customer user = CM.identifyCustomer(flag, age);

			if (user != null) {
				System.out.println("Vitajte!");
				return user;
			}else {
				System.out.println("Neregistrovany uzivatel");
				return null;
			}
		}
		Customer user = CM.identifyCustomer(id);
		if(user != null) {
			System.out.println("Vitajte!");
			return user;
		}else {
			System.out.println("Neregistrovany uzivatel");
			return null;
		}
		
	}
		
		
		
	//vysvetlivky
	private void helpCmd() {
		System.out.println("HELP \t\t zoznam prikazov");
		System.out.println("IDENTIFY \t identifikovanie sa");
		System.out.println("REGISTER \t zaregistrovanie sa");
		System.out.println("BUY_MEMB \t identifikovany zakaznik si moze kupit permanentku");
		System.out.println("ARRIVE \t\t identifikovany zakaznik s platnou permanentkou vstupi do posilky");
		System.out.println("ADMIN \t\t prihlasenie sa admina");
		System.out.println("SHOW_TR \t ukaze trenerov ktorí su k dispozícií");
		System.out.println("LEAVING \t prikaz zada zakaznik pri odchode");
		System.out.println("CHOOSE_TR \t priradi osobneho trenera");
		System.out.println("CHOOSE_TG \t vytvori trening u osobneho trenera v zvolenom termine");
		System.out.println("WHO \t \t zobrazi info o uzivatelovi");
		System.out.println("SHOP_STOCK \t zobrazi produkty v obchode");
		System.out.println("TO_SHOP \t zakaznik si moze nakupit produkty");
		System.out.println("SHOW_TGS \t ukaze terminy zakaznikovych treningov");
		if(this.admin) {
			System.out.println("ADM_LOGOUT \t odhlasi admina");
			System.out.println("HIRE_TR \t zamestnaj noveho trenera");
			System.out.println("FIRE_TR \t vyhod trenera");
			System.out.println("SHOW_U_REG \t ukaze registrovanych zakaznikov");
			System.out.println("SHOW_U_IN \t ukaze zakaznikov v posilke");
			System.out.println("TR_INFO \t info o trenerovi");
			System.out.println("RESTOCK \t dopln zasoby v obchode");
			System.out.println("SHOW_BANK \t zobrazi peniaze na ucte");
			System.out.println("LIMIT \t\t zobrazi maximalny pocet zakaznikov v posilke a umozni ho zmenit");
			
		}
	}
	
	private Owner getOwner() {
		return this.owner;
	}

	//kontrola hesla admina
	private boolean setAdmin(String password) {
		System.out.println(password);
		if(password.contentEquals("heslo123")) {
			this.admin = true;
			return true;
		}
		System.out.println("Zle heslo");
		return false;
	}
	//prihlasenia admina do systemu - utvori nove prikazy
	private void adminLog() {
		System.out.println("Zadajte heslo:");
		String passw = scanner.next();
		this.setAdmin(passw);
	}
}
