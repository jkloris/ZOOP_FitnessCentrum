package UnplacedManagers;
import Pasiv.*;
import Persons.Customer;
public class MembershipManager {
 private static MembershipManager instance = null;
 
 
 private MembershipManager() {
	 
 }
 
 public static MembershipManager getInstance() {
	 if(instance == null)
		 return instance =new MembershipManager();
	 return instance;
 }
}
