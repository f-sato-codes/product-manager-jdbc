import java.util.List;

public class Main {

	public static void main(String[] args) {
		JdbcProductRepository jdbc = new JdbcProductRepository();
		List<Product> product = jdbc.findAll();
		System.out.println(product);
	}

}
