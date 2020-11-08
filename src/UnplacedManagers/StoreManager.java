package UnplacedManagers;
import Pasiv.Product;
import java.util.ArrayList;

public class StoreManager {
	private static StoreManager instance = null;
	private ArrayList<Product> productsInStock = new ArrayList<Product>();
	private float moneyLeft = 1000;
	
	private StoreManager() {}
	
	public static StoreManager getInstance() {
		if(instance == null)
			return new StoreManager();
		return instance;
	}
	
	public void setMoneyLeft(float money) {
		this.moneyLeft = money;
	}
	public float getMoneyLeft() {
		return this.moneyLeft;
	}
	
	public ArrayList<Product> getProductsInStock() {
		return this.productsInStock;
	}
	
	public void showProductsInStock() {
		for(int i = 0; i < this.getProductsInStock().size(); i++) {
			System.out.println(this.getProductsInStock().get(i).getName() + ": " 
								+ this.getProductsInStock().get(i).getSellPrice() + "€; na sklade: " 
								+ this.getProductsInStock().get(i).getCount() + "ks");
		}
	}
	
	public boolean restock(Product product) {
		int i;
		if(this.getMoneyLeft() - product.getCostPrice() > 0) {
			for( i = 0; i < this.getProductsInStock().size(); i++) {
				if(product.getName() == this.getProductsInStock().get(i).getName()) {
					this.getProductsInStock().get(i).setCount(this.getProductsInStock().get(i).getCount()+1);
					break;
				}
			}
			if(i == this.getProductsInStock().size()) {
				this.getProductsInStock().add(product);
			}
			this.setMoneyLeft(this.getMoneyLeft() - product.getCostPrice());
			return true;
		}
		System.out.println("Nedostatok penazi");
		return false;
	}
}
