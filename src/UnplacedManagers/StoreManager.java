package UnplacedManagers;
import Pasiv.Product;
import java.util.ArrayList;

import Interface.Revenue;

public class StoreManager implements Revenue{
	private static StoreManager instance = null;
	private ArrayList<Product> productsInStock = new ArrayList<Product>();
	private float storeRevenue = 1000;
	
	private StoreManager() {}
	
	public static StoreManager getInstance() {
		if(instance == null)
			instance = new StoreManager();
		return instance;
	}
	
	public void setStoreRevenue(float money) {
		this.storeRevenue = money;
	}
	public float getStoreRevenue() {
		return this.storeRevenue;
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
		if(this.getStoreRevenue() - product.getCostPrice() > 0) {
			for( i = 0; i < this.getProductsInStock().size(); i++) {
				if(product.getName() == this.getProductsInStock().get(i).getName()) {
					this.getProductsInStock().get(i).setCount(this.getProductsInStock().get(i).getCount()+1);
					break;
				}
			}
			if(i == this.getProductsInStock().size()) {
				this.getProductsInStock().add(product);
			}
			this.setStoreRevenue(decreaseProfit(this.getStoreRevenue(), product.getCostPrice()));
			return true;
		}
		System.out.println("Nedostatok penazi");
		return false;
	}
	
	public boolean sellProduct(Product product) {
		int index = this.getProductsInStock().indexOf(product);
		Product p = this.getProductsInStock().get(index);
		if( index >= 0 ) {
			System.out.println("Nakup prebehol uspesne");
			int count = p.getCount();
			if( count > 1) {
				p.setCount(count - 1);
				this.setStoreRevenue(increaseProfit(this.getStoreRevenue(), p.getCostPrice()));
				
			}else if(count == 1) {
				this.setStoreRevenue(increaseProfit(this.getStoreRevenue(), p.getCostPrice()));
				p.setCount(count - 1);
				System.out.println("Tento produkt bol posledny na sklade");
			}else {
				System.out.println("Produkt nie je na sklade");
				return false;
			}
		}
		System.out.println("Produkt nebol najdeny");
		return false;
	}
}
