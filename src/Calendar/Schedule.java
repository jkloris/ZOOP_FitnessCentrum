package Calendar;
import Persons.*;

//Nieco ako kalendar alebo rozpis treningov pre trenera
public class Schedule {
	//zoznam vsetkych terminov pre jeden Schedule. - 7dni  v tyzdni po 12hod 
	private Termin termins[][] = new Termin[7][12];
	
	public Schedule() {		
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
	
	//nastavi obedne pauzy
	private void setLunchBreaks() {
		for(int i = 0; i < 7; i++)
		this.termins[i][3].setCustomer(new Customer("Lunch Break", 1));
	}
	
	//priradi zakaznika na zadany den a hodinu
	public boolean assignCustomer(Customer customer, int day, int hour) {
		if(day >= 0 && day <= 6 && hour >= 9 && hour <= 20 
							    && this.termins[day][hour - 9].setCustomer(customer) ) {
			return true;
		}
		return false;
	}
	
	
	
}
