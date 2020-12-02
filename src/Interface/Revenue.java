package Interface;

public interface Revenue {
	
	default public float increaseProfit(float bank,float amount) {
		bank+=amount;
		System.out.println(bank);
		return bank;
	}
	default public float decreaseProfit(float bank,float amount) {
		if(bank - amount >= 0) {
			bank -= amount;
		}
		return amount;
	}
	
}
