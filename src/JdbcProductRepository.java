import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class JdbcProductRepository implements ProductRepository{

	private static final String URL = "jdbc:mysql://localhost:3306/product_manager_db";
	private static final String USER = "product_user";
	private static final String PASSWORD = "product_pass";
	
	@Override
	public List<Product> findAll () {
		List<Product> products = new ArrayList<> ();
		String sql = "select * from products";
		
		try {
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				int price = resultSet.getInt("price");
				int stock = resultSet.getInt("stock");
				products.add(new Product(id,name,price,stock));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

}