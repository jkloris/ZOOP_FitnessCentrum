package Interface;

public interface Revenue {
	
	default public float increaseProfit(float bank,float amount) {
		bank+=amount;
		onDeal(bank);
		return bank;
	}
	default public float decreaseProfit(float bank,float amount) {
		if(bank - amount >= 0) {
			bank -= amount;
		}
		onDeal(bank);
		return bank;
	}
	
	public void onDeal(float bank);
	
}
