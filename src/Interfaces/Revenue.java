package Interfaces;
//sluzi na organizaciu financii a ich spracovanie medzi objektami
public interface Revenue {
	
	//zvysi profit, default lebo aj tak by bolo telo funkcie rovnaky
	default public float increaseProfit(float bank,float amount) {
		bank+=amount;
		onDeal(bank);
		return bank;
	}
	//znizi profit, ..rovnaky princip
	default public float decreaseProfit(float bank,float amount) {
		if(bank - amount >= 0) {
			bank -= amount;
		}
		onDeal(bank);
		return bank;
	}
	
	//spusti sa ak sa nieco deje s peniazmi, v implementovanych objektoch by mala zabezpecit globalizaciu penazi
	public void onDeal(float bank);
	
}
