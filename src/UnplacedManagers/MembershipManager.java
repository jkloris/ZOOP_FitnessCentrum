package UnplacedManagers;
import Interface.Revenue;
import Main.Gym;
import Pasiv.*;
import Persons.Customer;
public class MembershipManager implements Revenue {
	private static MembershipManager instance = null;
	private Gym gym;
 
	private MembershipManager(Gym gym) {
		this.gym = gym;

	}
 
	public static MembershipManager getInstance(Gym gym) {
		if(instance == null)
			instance = new MembershipManager(gym);
		return instance;
	}
 
// 
//	public float getMemRevenue() {
//		return this.memRevenue;
//	}

//	public void setMemRevenue(float memRevenue) {
//		this.memRevenue = memRevenue;
//	}
	
	public void sellMembership(Customer customer, Membership membership) {
		customer.setMembership(membership);
		this.gym.setRevenue(increaseProfit(this.gym.getRevenue(), membership.getPrice()));
	}

	@Override
	public void onDeal(float bank) {
		this.gym.setRevenue(bank);
	}



}
