import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {	
		// キーボード入力を受け取る道具
		Scanner scanner = new Scanner(System.in);
		ProductRepository repository = new JdbcProductRepository();		
		boolean running = true;
		
		while(running) {
			printMenu();
			int menu = scanner.nextInt();
			if(menu == 1) {
				List<Product> products = repository.findAll();
				System.out.println(products);
			}else if(menu == 2){
				System.out.println("商品IDを入力してください");
				int id = scanner.nextInt();
				Product product = repository.findById(id);
				if(product != null) {
					System.out.println(product);
				}else {
					System.out.println("商品が見つかりませんでした");
				}
			}else if(menu == 3) {
				System.out.println("商品IDを入力してください");
				int id = scanner.nextInt();
				System.out.println("在庫数を入力してください");
				int stock = scanner.nextInt();
				int result = repository.updateStock(id,stock);
				if( result != 0) {
					System.out.println(result + "件更新しました");
				}else {
					System.out.println("更新できませんでした");
				}
			}else if(menu == 4){
				System.out.println("商品IDを入力してください");
				int id = scanner.nextInt();
				int result = repository.deleteById(id);
				if(result != 0) {
					System.out.println(result + "件削除しました");
				}else {
					System.out.println("削除できませんでした");
				}
			}else if(menu == 5){
				System.out.println("商品名を入力してください");
				String name = scanner.next();
				System.out.println("値段を入力してください");
				int price = scanner.nextInt();
				System.out.println("在庫数を入力してください");
				int stock = scanner.nextInt();
				int result = repository.insert(name,price,stock);
				if( result != 0) {
					System.out.println(result + "件登録しました。");
				}else {
					System.out.println("登録できませんでした");
				}
			}else if(menu == 9) {
				System.out.println("終了します");
				running = false;
			}else {
				System.out.println("無効なメニューです");
			}
		}
		scanner.close();
	}  
	private static void printMenu() {
        System.out.println("===========");
        System.out.println("1:商品一覧を表示");
        System.out.println("2:商品IDを検索");
        System.out.println("3:在庫数を変更");
        System.out.println("4:商品を削除");
        System.out.println("5:商品を登録");
        System.out.println("9:終了");
        System.out.println("番号を入力してください。");
    }
}
