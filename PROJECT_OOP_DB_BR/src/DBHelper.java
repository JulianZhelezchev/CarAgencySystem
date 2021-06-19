import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JComboBox;

import com.sun.source.tree.WhileLoopTree;

public class DBHelper {
	
	private static Connection conn = null;
	static PreparedStatement state = null;
	static MyModel model = null;
	static ResultSet result = null;
	
	public static String CreateReadFile() {
		
		File conFile = new File("C:\\Users\\Dell\\Desktop\\configFile.txt");
		String[] data = new String[3];
		byte i = 0;
		try {
			conFile.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileWriter writer = new FileWriter(conFile);
			writer.write("jdbc:h2:C:\\\\Users\\\\Dell\\\\Desktop\\\\h2-2019-10-14 (2)\\\\DB;AUTO_SERVER=TRUE\n");
			writer.write("sa\n");
			writer.write("happy");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner scanner;
		try {
			scanner = new Scanner(conFile);
			while(scanner.hasNextLine()) {
				data[i] = scanner.nextLine();
				i++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = data[0];
		String username = data[1];
		String password = data[2];
		
		return url + ", " + username + ", " + password;
	
	}
	
	static String[] data = CreateReadFile().split(", ");
	static String url = data[0];
	static String username= data[1];
	static String password = data[2];
	
	public static Connection getConnection() {
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
		}//end GetConnection
	
	static MyModel getAllData(String tableName) {
		conn = getConnection();
		String sql = "select * from " + tableName;
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end getAllData
	
	static MyModel getAllDataCars() {
		conn = getConnection();
		String sql = "select c.car_id, b.brand, c.model, c.production_year, c.travelled_kms, c.price, c.comment from cars c join brands b on c.brand_id=b.brand_id";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end getAllDataCars
	
	static MyModel getAllDataSales() {
		conn = getConnection();
		String sql = "select s.sale_id, c.model, s.sale_date, s.first_name, s.last_name, s.final_price, s.price_difference from brands b join cars c on b.brand_id=c.brand_id join sales s on c.car_id=s.car_id";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			model = new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}//end getAllDataCars
	
	static void fillBrandCombo(JComboBox<String> combo) {
		conn = getConnection();
		String sql = "select brand from brands";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			combo.removeAllItems();
			while(result.next()) {
				String item = result.getObject(1).toString();
				combo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void fillChooseCarCombo(JComboBox<String> combo) {
		conn = getConnection();
		String sql = "select c.car_id, b.brand, c.model, c.production_year, c.price from cars c join brands b on c.brand_id = b.brand_id";
		try {
			state = conn.prepareStatement(sql);
			result = state.executeQuery();
			combo.removeAllItems();
			while(result.next()){
				String item = result.getObject(1).toString() + ", " + result.getObject(2).toString() + ", " 
			    + result.getObject(3).toString() + ", " + result.getObject(4).toString() + ", " + result.getObject(5).toString();
				combo.addItem(item);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
	

}
