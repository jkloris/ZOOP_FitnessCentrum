package UnplacedManagers;
import java.util.ArrayList;
import java.util.Random;

import Interface.Generatable;
import Pasiv.*;

import Persons.Customer;

public class CustomerManager implements Generatable{
	private static CustomerManager instance = null;
	private ArrayList<Customer> regCustomers = new ArrayList<Customer>();
	private ArrayList<Customer> customersInside = new ArrayList<Customer>();
	private int customerLimit = 6;
	
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
	
	public boolean customerArrived(Customer customer) {
		if(this.getCustomerLimit() > this.getCustomersInside().size()) {
			
			if(this.getRegCustomers().indexOf(customer) >= 0 ) {
				if(this.getCustomersInside().indexOf(customer) < 0) {
					
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
	
	public void customerIsLeaving(Customer customer) {
		if(customer.getMembership() instanceof Membership_PAYG) {
			customer.setMembership(null);
		}
		this.customersInside.remove(customer);
		customer.setLocker(null);
	}
	
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
	
	public Customer identifyCustomer(int id) {
		for(int i = 0; i < this.getRegCustomers().size(); i++) {
			if(this.getRegCustomers().get(i).getId() == id) {
				return this.getRegCustomers().get(i);
			}
		}
		System.out.println("Neregistrovany zakaznik");
		return null;
	}
	
	public Customer identifyCustomer(String name, int age) {
		int id = Math.abs(age*name.hashCode());
		return identifyCustomer(id);
	}

	@Override
	public void randomG() {
		Random r = new Random();
		int rnd_name = r.nextInt(Generatable.names.length );
		int rnd_surname = r.nextInt(Generatable.surnames.length );
		int rnd_age = r.nextInt(40) + 15 ;
		this.getRegCustomers().add(new Customer(Generatable.names[rnd_name]+"_"+Generatable.surnames[rnd_surname], rnd_age)) ;
		
	}
	
	
	
	
}
