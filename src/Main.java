public class Main {

	public static void main(String[] args) {
		JdbcProductRepository jdbc = new JdbcProductRepository();
		//List<Product> product = jdbc.findAll();
		Product product = jdbc.findById(1);
		System.out.println(product);
	}

}
