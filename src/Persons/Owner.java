package Persons;
import java.util.ArrayList;
import java.util.Random;

import Interface.Generatable;
import UnplacedManagers.StoreManager;
public class Owner extends Trainer implements Generatable{
	//public StoreManager store = StoreManager.getInstance();
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


	@Override
	public void randomG() {
		Random r = new Random();
		int rnd_name = r.nextInt(Generatable.names.length );
		int rnd_surname = r.nextInt(Generatable.surnames.length );
		int rnd_age = r.nextInt(40) + 15 ;
		int rnd_price = r.nextInt(10)+5;
		int rnd_skill = r.nextInt(5);
		String skill;
		switch (rnd_skill) {
		case 0: skill = "kondièny";
			break;
		case 1: skill = "silove_treningy";
			break;
		case 2: skill = "martial_art";
			break;
		case 3: skill = "yoga";
			break;
		case 4: skill = "general";
			break;

		default:
			skill = "expert";
			break;
		}
		
		this.getTrainers().add(new Trainer(Generatable.names[rnd_name]+"_"+Generatable.surnames[rnd_surname], rnd_age, skill, rnd_price));
		
	}
	
}
