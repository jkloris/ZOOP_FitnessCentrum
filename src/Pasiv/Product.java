package Pasiv;

//predajny produkt do obchodu
public class Product {
	private float costPrice;
	private float sellPrice;
	private String name;
	private String description; //opis produktu
	private int count; //pocet kusov na sklade
	
	public Product(String name, String description, float costPrice) {
		this.setCostPrice(costPrice);
		this.setDescription(description);
		this.setName(name);
		this.setSellPrice(costPrice * 2); //predajna cena je 2x vyssia ako nakupna
		this.setCount(1);	
	}
	
	public Product(String name, String description, float costPrice, float sellPrice) {
		this.setCostPrice(costPrice);
		this.setDescription(description);
		this.setName(name);
		this.setSellPrice(sellPrice);
		this.setCount(1);
	}
	
	public int getCount() {
		return this.count;
	}
	public void setCount(int count) {
		if(count >= 0)
			this.count = count;
	}
	
	
	public float getCostPrice() {
		return costPrice;
	}
	private void setCostPrice(float costPrice) {
		this.costPrice = costPrice;
	}
	
	public float getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	private void setDescription(String description) {
		this.description = description;
	}
	public void showDescription() {
		System.out.println(this.description);
	}
	
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	
	
}
