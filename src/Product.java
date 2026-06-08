
public class Product {
	//フィールド	
	private int id;
	private String name;
	private int price;
	private int stock;
	
	//コンストラクタ
	public Product(int id,String name,int price,int stock){
		this.id = id;
		this.name = name;
		this.price = price;
		this.stock = stock;
	}
	

	//ゲッター	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}
	

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", stock=" + stock + "]";
	}

	
}
