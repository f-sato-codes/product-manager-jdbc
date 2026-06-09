import java.util.List;

public class Main {

	public static void main(String[] args) {
		JdbcProductRepository jdbc = new JdbcProductRepository();

		//Product product = jdbc.findById(1);
		//jdbc.insert("クロノトリガー",1000,2);
		//jdbc.deleteById(4);
		List<Product> products = jdbc.findAll();
		System.out.println(products);
	}

}
