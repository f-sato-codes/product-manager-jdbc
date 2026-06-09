import java.util.List;

public interface ProductRepository {
	
	List<Product> findAll();
	
	Product findById(int id);
	
	int insert(String name,int price,int stock);
	
	int updateStock(int id,int stock);
	
	int deleteById(int id);
}
