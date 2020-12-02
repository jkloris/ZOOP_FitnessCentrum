package UnplacedManagers;
import Interface.Revenue;
import Pasiv.*;
import Persons.Customer;
public class MembershipManager implements Revenue {
	private static MembershipManager instance = null;
	private float MemRevenue = 100.0f;
 
 
	private MembershipManager() {}
 
	public static MembershipManager getInstance() {
		if(instance == null)
			instance = new MembershipManager();
		return instance;
	}
 
 
	public float getMemRevenue() {
		return MemRevenue;
	}

	public void setMemRevenue(float memRevenue) {
		MemRevenue = memRevenue;
	}
	
	public void sellMembership(Customer customer, Membership membership) {
		customer.setMembership(membership);
		this.setMemRevenue(increaseProfit(this.getMemRevenue(), membership.getPrice()));
	}



}
