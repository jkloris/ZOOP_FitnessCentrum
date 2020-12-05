package UnplacedManagers;
import java.util.ArrayList;
import java.util.Random;

import Interfaces.Generatable;
import Pasiv.*;

import Persons.Customer;

//managuje zakaznikov
public class CustomerManager implements Generatable{
	private static CustomerManager instance = null;
	private ArrayList<Customer> regCustomers = new ArrayList<Customer>(); //registrovany zakaznici
	private ArrayList<Customer> customersInside = new ArrayList<Customer>(); //zakaznici v posilke
	private int customerLimit = 6;//max pocet zakaznikov v posilke
	
	private CustomerManager() {
		
	}
	
	public static CustomerManager getInstance() {
		if(instance == null)
			 instance = new CustomerManager();
		return instance;
	}
	
	public int getCustomerLimit() {
		return this.customerLimit;
	}
	
	public void setCustomerLimit(int limit) {
		if(limit > 0) {
			this.customerLimit = limit;
		}else {
			this.customerLimit = 0;
		}
	}
	
	//ked pride zakaznik do posilky...
	public boolean customerArrived(Customer customer) {
		if(this.getCustomerLimit() > this.getCustomersInside().size()) { //..skontroluje ci nie je plno
			
			if(this.getRegCustomers().indexOf(customer) >= 0 ) { //..ci je zakaznik registrovany
				if(this.getCustomersInside().indexOf(customer) < 0) { //..ci uz nie je vo vnutri
					
					//..ci ma platnu permanentku a ak ano, vykona s nou to co ma
					if( customer.getMembership()!= null && customer.getMembership().onArrival()) {
						this.getCustomersInside().add(customer);
					
						System.out.println("Mozete ist cvicit");
						return true;
					} else {
							System.out.println("Nemate platnu permanentku");	
							return false;
					}
				}else {
					System.out.println("Uz ste v posilke");
					return false;
				}
			}else {
				System.out.println("Nie ste registrovany");
				return false;
			}
		}else {
			System.out.println("Posilka je plna");
			return false;
		}
	}
	
	//ak zakaznik odchadza z posilky, odstrani ho zo zoznamu a vymaze jednorazovu permanentku
	public void customerIsLeaving(Customer customer) {
		if(customer.getMembership() instanceof Membership_PAYG) {
			customer.setMembership(null);
		}
		this.customersInside.remove(customer);
		customer.setLocker(null);
	}
	
	//pridanie zakaznika do zoznamu registrovanych zakaznikov
	public boolean registerCustomer(Customer customer) {
		if(this.getRegCustomers().indexOf(customer) >= 0) {
			System.out.println("Zakaznik uz bol registrovany");
			return false;
		}
		this.getRegCustomers().add(customer);
		System.out.println("Uspesna registracia zakaznika");
		return true;
	}
	
	public ArrayList<Customer> getRegCustomers(){
		return this.regCustomers;
	}
	
	public ArrayList<Customer> getCustomersInside(){
		return this.customersInside;
	}
	
	public void showCustomersIn() {
		for(Customer cust : this.getCustomersInside()) {
			System.out.println(cust.getName() + " " + cust.getAge() + " " + cust.getId());
		}
	}
	
	public void showRegCustomers() {
		for(Customer cust : this.getRegCustomers()) {
			System.out.println(cust.getName() + " " + cust.getAge() + " " + cust.getId());
		}
	}
	
	//identifikuje zakaznika na zaklade id
	public Customer identifyCustomer(int id) {
		for(int i = 0; i < this.getRegCustomers().size(); i++) {
			if(this.getRegCustomers().get(i).getId() == id) {
				return this.getRegCustomers().get(i);
			}
		}
		System.out.println("Neregistrovany zakaznik");
		return null;
	}
	
	//identifikuje na zaklade mena
	public Customer identifyCustomer(String name, int age) {
		int id = Math.abs(age*name.hashCode());
		return identifyCustomer(id);
	}

	@Override
	public void randomG() { //nahodne vygenerovanie registrovanych zakaznikov
		Random r = new Random();
		int rnd_name = r.nextInt(Generatable.names.length );
		int rnd_surname = r.nextInt(Generatable.surnames.length );
		int rnd_age = r.nextInt(40) + 15 ;
		this.getRegCustomers().add(new Customer(Generatable.names[rnd_name]+"_"+Generatable.surnames[rnd_surname], rnd_age)) ;
		
	}
	
	
	
	
}
