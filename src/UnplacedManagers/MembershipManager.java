package UnplacedManagers;
import Interfaces.Revenue;
import Main.Gym;
import Pasiv.*;
import Persons.Customer;

//ma za ulohu pracu s permanentkami
public class MembershipManager implements Revenue {
	private static MembershipManager instance = null;
	private Gym gym; //presirena trieda Gym, kvoli globalnym financiam
 
	private MembershipManager(Gym gym) {
		this.gym = gym;

	}
 
	public static MembershipManager getInstance(Gym gym) {
		if(instance == null)
			instance = new MembershipManager(gym);
		return instance;
	}
 
	//predaj permanentky
	public void sellMembership(Customer customer, Membership membership) {
		customer.setMembership(membership);
		this.gym.setRevenue(increaseProfit(this.gym.getRevenue(), membership.getPrice()));
	}

	@Override
	public void onDeal(float bank) { //pri praci s peniazmi sa aktualizuje financny stav
		this.gym.setRevenue(bank);
	}



}
