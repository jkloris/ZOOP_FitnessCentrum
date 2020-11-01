package Calendar;
import Persons.*;

public class Schedule {
	//private Trainer trainer;
	private Termin termins[][] = new Termin[7][12];
	
	public Schedule() {
		//this.setTrainer(trainer);
		
		for(int i = 0; i < 7; i++) {
			for(int e = 0; e < 12; e++) {
				this.termins[i][e] = new Termin(i,e + 9);
			}
		}
		this.setLunchBreaks();
	}
	
	public void showSchedule() {
		for(int i = 0; i < 7; i++) {
			for(int e = 0; e < 12; e++) {
				this.termins[i][e].showCustomer();;
				this.termins[i][e].showTermin(1);
			}
			System.out.println();
		}
		
	}
	
	private void setLunchBreaks() {
		for(int i = 0; i < 7; i++)
		this.termins[i][3].setCustomer(new Customer("Lunch Break", 1));
	}
	
//	private void setTrainer(Trainer trainer) {
//		this.trainer = trainer;
//	}
	
	
//	public Trainer getTrainer() {
//		return this.trainer;
//	}
	
	public boolean assignCustomer(Customer customer, int day, int hour) {
		if(day >= 0 && day <= 6 && hour >= 9 && hour <= 20 
							    && this.termins[day][hour - 9].setCustomer(customer) ) {
			return true;
		}
		return false;
	}
	
	
	
}
