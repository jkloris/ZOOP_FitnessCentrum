package Persons;
import Calendar.Schedule;
import java.util.ArrayList;

public class Trainer extends Human{
	private String skill;
	private int price;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private Schedule schedule;
	//TODO schedule
	
	public Trainer(String name, int age, String skill, int price) {
		super(name, age);
		this.price = price;
		this.skill = skill;
		this.schedule = new Schedule();
		// TODO Auto-generated constructor stub
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}
	
	

	public String getSkill() {
		return this.skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void showCustomers() {
		for(int i = 0; i < this.customers.size(); i++) {
			System.out.println(this.customers.get(i).getName()+ ' ' + this.customers.get(i).getAge());
		}		
	}

	public void assignCustomer(Customer customer) {
		this.customers.add(customer);
		System.out.println("Zakaznik pridany");
	}
	
	public void delCustomer(Customer customer) {
		if(this.customers.remove(customer)) {
			System.out.println("Zakaznik odstraneny");
		}else {
			System.out.println("Zakaznik nebol v zozname");
		}
	}
	
	public void delCustomer(int id) {
		for(int i = 0; i < this.customers.size(); i++){
			if(this.customers.get(i).getId() == id) {
				System.out.println("Zakaznik odstraneny");
				this.customers.remove(i);
				return;
			}
		}
		System.out.println("Zakaznik nebol v zozname");
	}
	
}
