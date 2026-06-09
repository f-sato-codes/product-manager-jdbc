import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class JdbcProductRepository implements ProductRepository{

	private static final String URL = "jdbc:mysql://localhost:3306/product_manager_db";
	private static final String USER = "product_user";
	private static final String PASSWORD = "product_pass";
	
	//全データを取得する
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

	//idを1件取得する
	@Override
	public Product findById (int id) {
		String sql = "select * from products where id = ?";
		try (
			Connection connection  = DriverManager.getConnection(URL,USER,PASSWORD);	
			PreparedStatement statement = connection.prepareStatement(sql); 
			){
			statement.setInt(1,id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				int getId = resultSet.getInt("id");
				String getName = resultSet.getString("name");
				int getPrice = resultSet.getInt("price");
				int getStock = resultSet.getInt("stock");
				Product product = new Product(getId,getName,getPrice,getStock);
				return product;
			}else {
				System.out.println("商品が見つかりません");
				return null;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return null;
	}
	
	//登録する。
	@Override
	public int insert(String name,int price,int stock) {
		String sql = "insert into products (name,price,stock) values (?,?,?)";
		try {
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement statement  = connection.prepareStatement(sql);
			statement.setString(1,name);
			statement.setInt(2,price);
			statement.setInt(3,stock);
			
			int result = statement.executeUpdate();
			System.out.println(result + "件登録しました。");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//ストックを更新する。
	@Override
	public int updateStock(int id,int stock) {
		String sql = "update products set stock = ? where id = ?";
		
		try {
			Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1,stock);
			statement.setInt(2,id);
			int result = statement.executeUpdate();
			System.out.println(result + "件更新しました");
			return  result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//1件の削除
	@Override
	public int deleteById(int id) {
		String sql = "delete from products where id = ?";
		try {
			Connection connetion = DriverManager.getConnection(URL,USER,PASSWORD);
			PreparedStatement statement =  connetion.prepareStatement(sql);
			statement.setInt(1, id);
			int result = statement.executeUpdate();
			System.out.println(result + "件削除しました");
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}	
}