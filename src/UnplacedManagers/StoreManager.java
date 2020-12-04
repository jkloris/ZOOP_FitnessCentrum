package UnplacedManagers;

import Pasiv.Product;
import java.util.ArrayList;

import Interface.Generatable;
import Interface.Revenue;
import Main.Gym;

public class StoreManager implements Revenue, Generatable {
	private static StoreManager instance = null;
	private ArrayList<Product> productsInStock = new ArrayList<Product>();
	private Gym gym;
	
	private StoreManager(Gym gym) {
		this.gym = gym;
	}

	public static StoreManager getInstance(Gym gym) {
		if (instance == null)
			instance = new StoreManager(gym);
		return instance;
	}

//	public void setStoreRevenue(float money) {
//		this.storeRevenue = money;
//	}

//	public float getStoreRevenue() {
//		return this.storeRevenue;
//	}

	public ArrayList<Product> getProductsInStock() {
		return this.productsInStock;
	}

	public void showProductsInStock() {
		for (int i = 0; i < this.getProductsInStock().size(); i++) {
			System.out.print(
					this.getProductsInStock().get(i).getName() + ": " + this.getProductsInStock().get(i).getSellPrice()
							+ "€; na sklade: " + this.getProductsInStock().get(i).getCount() + "ks; opis produktu: ");
			this.getProductsInStock().get(i).showDescription();
		}
	}

	public boolean restock(Product product) {
		int i;
		if (this.gym.getRevenue() - product.getCostPrice() >= 0) {
			for (i = 0; i < this.getProductsInStock().size(); i++) {
				if (product.getName() == this.getProductsInStock().get(i).getName()) {
					this.getProductsInStock().get(i).setCount(this.getProductsInStock().get(i).getCount() + 1);
					break;
				}
			}
			if (i == this.getProductsInStock().size()) {
				this.getProductsInStock().add(product);
			}
			this.gym.setRevenue(decreaseProfit(this.gym.getRevenue(), product.getCostPrice()));
			return true;
		}
		System.out.println("Nedostatok penazi");
		return false;
	}

	public boolean sellProduct(Product product) {
		int index = this.getProductsInStock().indexOf(product);
		Product p = this.getProductsInStock().get(index);
		if (index >= 0) {
			
			int count = p.getCount();
			if (count > 1) {
				p.setCount(count - 1);
				this.gym.setRevenue(increaseProfit(this.gym.getRevenue(), p.getSellPrice()));
				System.out.println("Nakup prebehol uspesne");	
				return true;
			} else if (count == 1) {
				this.gym.setRevenue(increaseProfit(this.gym.getRevenue(), p.getSellPrice()));
				p.setCount(count - 1);
				System.out.println("Nakup prebehol uspesne");
				System.out.println("Tento produkt bol posledny na sklade");
				return true;
			} else {
				System.out.println("Produkt nie je na sklade");
				return false;
			}
		}
		System.out.println("Produkt nebol najdeny");
		return false;
	}
	
	public Product identify(String name) {
		for(Product e : this.getProductsInStock()) {
			if(e.getName().equals(name)) {
				return e;
			}
		}
		return null;
	}

	@Override
	public void randomG() {
		for(int i = 0; i < 4; i++) {
			
			this.restock(new Product("Proteinova_tycinka", "80g tycinka s 20% obsahom proteinu", 1f));
			this.restock(new Product("Protein_davka", "30g dávka proteinu. Vhodné po tréningu.", 1.3f));
			this.restock(new Product("Protein_2KG", "Vhodné po tréningu. Super rozpustnost", 14f));
			this.restock(new Product("Energy_drink", "300ml, Doplni energiu.", 0.9f));
			this.restock(new Product("BCAA_tablety", "Vhodné na regeneraciu svalov. 100 tabliet", 7.5f));
		}
		
		
	}

	@Override
	public void onDeal(float bank) {
		this.gym.setRevenue(bank);
	}
}
