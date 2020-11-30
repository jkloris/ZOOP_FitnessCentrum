package Persons;
import java.util.ArrayList;

import UnplacedManagers.StoreManager;
public class Owner extends Trainer{
	public StoreManager store = StoreManager.getInstance();
	private ArrayList<Trainer> trainers; 
	
	
	
	public Owner(String name, int age, String skill, int price, ArrayList<Trainer> trainers ) {
		super(name, age, skill, price);
		this.trainers = trainers;
		this.addTrainer(this);
	}


	public ArrayList<Trainer> getTrainers() {
		return trainers;
	}
	
	public void addTrainer(Trainer trainer) {
		this.getTrainers().add(trainer);
	}
	
	public void showTrainers() {
		for(Trainer trainer : this.getTrainers()) {
			System.out.println(trainer.getName() + "; hodinovy plat: " + trainer.getPrice() 
												+ "€ specializacia: " + trainer.getSkill());
		}
	}
	
	public void delTrainer(Trainer trainer) {
		if(this.getTrainers().indexOf(trainer) >= 0) {
			this.getTrainers().remove(trainer);
			System.out.println("Trener bol vyhodeny");
			return;
		}
		System.out.println("Trener nie je v systeme");
	}
	
	public Trainer identifyTrener(String name) {
		for(Trainer t : this.getTrainers()) {
			if(name.contentEquals(t.getName() )) {
				return t;
			}
		}
		System.out.println("Trener sa nenasiel");
		return null;
	}
	
}
