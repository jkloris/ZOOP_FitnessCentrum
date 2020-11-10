package UnplacedManagers;
import Pasiv.*;
import Persons.Customer;
public class MembershipManager {
	private static MembershipManager instance = null;
	private float MemRevenue = 1000.0f;
 
 
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
		this.setMemRevenue(this.getMemRevenue() + membership.getPrice());
	}



}
