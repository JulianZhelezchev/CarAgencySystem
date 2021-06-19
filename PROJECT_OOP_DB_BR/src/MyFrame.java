import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.print.attribute.standard.DialogOwner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.time.format.DateTimeFormatter;

public class MyFrame extends JFrame { // Фрейм представлява контейнер в който може да се слагат различни компоненти
	// наследяваме класа JFrame, за да стане моя клас MyFrame-> фрейм ( компонент от библиотека Swing с J започва)
	private Connection conn = null;
	private PreparedStatement state = null;
	static ResultSet result = null;
	int id = -1;
	private JPanel topPanel = new JPanel(new BorderLayout());
	private JPanel titlePanel = new JPanel(new GridLayout(2, 1));
	private JLabel pictureLabel = new JLabel();
	private JLabel carCoLabel = new JLabel("Car Rentals", JLabel.CENTER);
	private JLabel salesSysLabel = new JLabel(" Welcome to the Car Agency Software!", JLabel.CENTER);
	private JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
	//JTabbedPane tab = new JTabbedPane();
	Container c = getContentPane();
	//JTabbedPane tab = new JTabbedPane();
	
	JPanel brandPanel = new JPanel();
	JPanel carPanel = new JPanel();
	JPanel salePanel = new JPanel();
	JPanel searchPanel = new JPanel();
	JPanel searchCarDetailsPanel = new JPanel();
	//JPanel searchAgePanel = new JPanel();
	
	JPanel brandUpPanel = new JPanel();
	JPanel brandMidPanel = new JPanel();
	JPanel brandDownPanel = new JPanel();
	JPanel carUpPanel = new JPanel();
	JPanel carMidPanel = new JPanel();
	JPanel carDownPanel = new JPanel();
	JPanel saleUpPanel = new JPanel();
	JPanel saleMidPanel = new JPanel();
	JPanel saleDownPanel = new JPanel();
	JPanel searchUpPanel = new JPanel();
	JPanel searchDownPanel = new JPanel();
	JPanel searchCarDetailsUpPanel = new JPanel();
	JPanel searchCarDetailsDownPanel = new JPanel();
	//JPanel searchKmsUpPanel = new JPanel();
	//JPanel searchKmsDownPanel = new JPanel();
	
	
	JLabel brandsTabBrandL = new JLabel("Add Brand:");
	JLabel countryL = new JLabel("Add Country:");
	JLabel carsTabBrandL = new JLabel("Select Brand:");
	JLabel modelL = new JLabel("Add Model:");
	JLabel yearL = new JLabel("Add Year of Production:");
	JLabel travelledKmsL = new JLabel("Add Travelled Kms:");
	JLabel priceL = new JLabel("Add Price:");
	JLabel commentL = new JLabel("Add Comment:");
	//JLabel carSortByL = new JLabel("Sort by:");
	JLabel carL = new JLabel("Select Car");
	JLabel firstNameL = new JLabel("First name of customer:");
	JLabel lastNameL = new JLabel("Last name of customer:");
	//JLabel saleDateL = new JLabel("Sale date:");
	JLabel finalPriceL = new JLabel("Final price:");

	//JLabel saleSortByL = new JLabel("Sort by:");
	JLabel searchTabBrandL = new JLabel("Select brand:");
	JLabel searchTabPriceFromL = new JLabel("Min Price:");
	JLabel searchTabPriceToL = new JLabel("Max Price:");
	///JLabel searchTabYearFromL = new JLabel("Car Year From:");
	//JLabel searchTabYearToL = new JLabel("Car Year To:");
	JLabel sortTabCarDetailsL = new JLabel("Choose Criteria:");
//	JLabel searchTabKmsToL = new JLabel("Kms To:");

	
	JTextField brandsTabBrandTF = new JTextField();
	JTextField countryTF = new JTextField();
	JTextField brandSearchTF = new JTextField();
	JTextField modelTF = new JTextField();
	JTextField yearTF = new JTextField();
	JTextField travelledKmsTF = new JTextField();
	JTextField priceTF = new JTextField();
	JTextField commentTF = new JTextField();
	JTextField carSearchTF = new JTextField();
	JTextField firstNameTF = new JTextField();
	JTextField lastNameTF = new JTextField();
	//JTextField saleDateTF = new JTextField();
	JTextField finalPriceTF = new JTextField();
	JTextField saleSearchTF = new JTextField();
	JTextField priceFromTF = new JTextField();
	JTextField priceToTF = new JTextField();
//	JTextField yearFromTF = new JTextField();
//	JTextField yearToTF = new JTextField();
//	JTextField travelledKmsFromTF = new JTextField();
//	JTextField travelledKmsToTF = new JTextField();
	
	JButton brandAddButton = new JButton("Add");
	JButton brandDeleteButton = new JButton("Delete");
	JButton brandEditButton = new JButton("Edit");
	JButton brandSearchButton = new JButton("Search By Brand");
	JButton carAddButton = new JButton("Add");
	JButton carDeleteButton = new JButton("Delete");
	JButton carEditButton = new JButton("Edit");
	JButton carSearchButton = new JButton("Search By Model");
//	JButton carSortButton = new JButton("Sort");
	JButton saleAddButton = new JButton("Add");
	JButton saleDeleteButton = new JButton("Delete");
	JButton saleEditButton = new JButton("Edit");
	JButton saleSearchButton = new JButton("Search By First Name");
//	JButton saleSortButton = new JButton("Sort");
	JButton searchTabPriceButton = new JButton("Search");
	//JButton searchTabAgeButton = new JButton("SortByAge");
	JButton sortTabCarDetailsButton = new JButton("Sort");
	JButton refreshBrandsButton = new JButton("Refresh");
	JButton refreshSalesButton = new JButton("Refresh");
	JButton refreshCarsButton = new JButton("Refresh");
	
	JComboBox<String> brandComboBox = new JComboBox<String>();
//	String[] sortCarStrings = {"Production Year", "Price"};
	//JComboBox<String> carSortComboBox = new JComboBox<String>(sortCarStrings);
	JComboBox<String> chooseCarComboBox = new JComboBox<String>();
//	String[] sortSaleStrings = {"Sale Date", "Final Price", "Price Difference"};
//	JComboBox<String> saleSortComboBox = new JComboBox<String>(sortSaleStrings);
	JComboBox<String> searchTabComboBox = new JComboBox<String>();
	String[] sortCarDetailsString = {"Travelled Kms", "Production Year" };
	JComboBox<String> CarDetailsSortComboBox = new JComboBox<String>(sortCarDetailsString);
	
	JTable brandTable = new JTable();
	JTable carTable = new JTable();
	JTable saleTable = new JTable();
	JTable searchTabPriceTable = new JTable();
	//JTable searchTabAgeTable = new JTable();
	JTable searchTabCarDetailsTable = new JTable();
	JScrollPane brandScroller = new JScrollPane(brandTable);
	JScrollPane carScroller = new JScrollPane(carTable);
	JScrollPane saleScroller = new JScrollPane(saleTable);
	JScrollPane searchTabPriceScroller = new JScrollPane(searchTabPriceTable);
	//JScrollPane searchTabAgeScroller = new JScrollPane(searchTabAgeTable);
//	JScrollPane searchTabCarDetailsScroller = new JScrollPane(searchTabCarDetailsTable);
	
	public MyFrame(){// конструктор е метод, който съвпада с името на класа, тук  к-ра е без параметри
		this.setSize(600, 600);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// добавяне на табове във фрейма
		tab.add(brandPanel, "Brands");
		tab.add(carPanel, "Cars");
		tab.add(salePanel, "Sales");
		tab.add(searchPanel, "Price Search");
	//	tab.add(searchCarDetailsPanel, "Car Details");
		this.add(tab);
		
		pictureLabel.setIcon(new ImageIcon("car_logo.png"));
		titlePanel.add(carCoLabel);
		titlePanel.add(salesSysLabel);
		topPanel.add(pictureLabel, "East");
		topPanel.add(titlePanel, "West");
		
		String currentFont = carCoLabel.getFont().getName();
		carCoLabel.setFont(new Font(currentFont, Font.BOLD, 26));
		salesSysLabel.setFont(new Font(currentFont, Font.PLAIN, 16));
		c.setLayout(new BorderLayout());
		c.add(topPanel, "North");
		c.add(tab, "Center");
		//JOptionPane.showMessageDialog(null, null, getTitle(), id);
		
		//BRAND TAB --------------------------------------------------------------------------
		brandPanel.setLayout(new GridLayout(3, 1));  // 3 РЕДА, 1 КОЛОНА
		
		//Up Panel
		brandUpPanel.setLayout(new GridLayout(5, 2));
		
		brandUpPanel.add(brandsTabBrandL); // ДОБАВЯНЕ НА КОМПОНЕНТИ В ПАНЕЛА
		brandUpPanel.add(brandsTabBrandTF);
		brandUpPanel.add(countryL);
		brandUpPanel.add(countryTF);
		
		brandPanel.add(brandUpPanel);
		
		//Mid Panel
		brandMidPanel.add(brandAddButton);
		brandMidPanel.add(brandDeleteButton);
		brandMidPanel.add(brandEditButton);
		brandMidPanel.add(brandSearchTF);
		brandMidPanel.add(brandSearchButton);
		brandSearchTF.setPreferredSize(new Dimension(100, 27));
		brandMidPanel.add(refreshBrandsButton);


		brandAddButton.addActionListener(new BrandAddAction());
		brandDeleteButton.addActionListener(new BrandDeleteAction());
		brandEditButton.addActionListener(new BrandEditAction());
		brandSearchButton.addActionListener(new BrandSearchAction());
		refreshBrandsButton.addActionListener(new BrandRefreshAction());
		
		
		brandPanel.add(brandMidPanel);
		
		//Down Panel
		brandDownPanel.add(brandScroller);
		brandScroller.setPreferredSize(new Dimension(450, 160));
		brandTable.setModel(DBHelper.getAllData("brands"));
		brandTable.addMouseListener(new BrandTableListener());
		
		brandPanel.add(brandDownPanel);
		
		//CAR TAB -----------------------------------------------------------------------------
		carPanel.setLayout(new GridLayout(3,1));
		
		//Up Panel
		carUpPanel.setLayout(new GridLayout(6,2));
		
		carUpPanel.add(carsTabBrandL);
		carUpPanel.add(brandComboBox);
		carUpPanel.add(modelL);
		carUpPanel.add(modelTF);
		carUpPanel.add(yearL);
	    carUpPanel.add(yearTF);
	    carUpPanel.add(travelledKmsL);
	    carUpPanel.add(travelledKmsTF);
		carUpPanel.add(priceL);
		carUpPanel.add(priceTF);
		carUpPanel.add(commentL);
		carUpPanel.add(commentTF);
		
		DBHelper.fillBrandCombo(brandComboBox);
		
		
		carPanel.add(carUpPanel);
		
		//Mid Panel
		carMidPanel.add(carAddButton);
		carMidPanel.add(carDeleteButton);
		carMidPanel.add(carEditButton);
		carMidPanel.add(carSearchTF);
		carMidPanel.add(carSearchButton);
		carMidPanel.add(refreshCarsButton);
		//carMidPanel.add(carComboBox);
	//	carMidPanel.add(carSortByL);
		
		//carMidPanel.add(carSortButton);
		
		carSearchTF.setPreferredSize(new Dimension(100, 27));
		carAddButton.setPreferredSize(new Dimension(80, 27));
		carDeleteButton.setPreferredSize(new Dimension(80, 27));
		carEditButton.setPreferredSize(new Dimension(80, 27));
	//	carSearchButton.setPreferredSize(new Dimension(80, 27));
		refreshCarsButton.setPreferredSize(new Dimension(80, 27));

		carAddButton.addActionListener(new CarAddAction());
		carDeleteButton.addActionListener(new CarDeleteAction());
		carEditButton.addActionListener(new CarEditAction());
		carSearchButton.addActionListener(new CarSearchAction());
		refreshCarsButton.addActionListener(new CarRefreshAction());
	//	sortTabCarDetailsButton.addActionListener(new CarDetailsSortAction());
//	    carSortButton.addActionListener(new CarSortAction());
		
		carPanel.add(carMidPanel);
		
		//Down Panel
		carDownPanel.add(carScroller);
		carScroller.setPreferredSize(new Dimension(450, 100));
		carTable.setModel(DBHelper.getAllDataCars());
		carTable.addMouseListener(new CarTableListener());
		carPanel.add(carDownPanel);
		
		//SALES TAB ---------------------------------------------------------------------------
		salePanel.setLayout(new GridLayout(3, 1));
		
		//Up Panel
		saleUpPanel.setLayout(new GridLayout(10, 2));
		
		saleUpPanel.add(carL);
		saleUpPanel.add(chooseCarComboBox);
		saleUpPanel.add(firstNameL);
		saleUpPanel.add(firstNameTF);
		saleUpPanel.add(lastNameL);
		saleUpPanel.add(lastNameTF);
		saleUpPanel.add(finalPriceL);
		saleUpPanel.add(finalPriceTF);
		
		DBHelper.fillChooseCarCombo(chooseCarComboBox);
		
		salePanel.add(saleUpPanel);
		
		//Mid Panel
		
		saleMidPanel.add(saleAddButton);
		saleMidPanel.add(saleDeleteButton);
		saleMidPanel.add(saleEditButton);
		saleMidPanel.add(saleSearchTF);
		saleMidPanel.add(saleSearchButton);
		saleMidPanel.add(refreshSalesButton);
		
		saleSearchTF.setPreferredSize(new Dimension(100, 27));
		saleAddButton.setPreferredSize(new Dimension(80, 27));
		saleDeleteButton.setPreferredSize(new Dimension(80, 27));
		saleEditButton.setPreferredSize(new Dimension(80, 27));
		//saleSearchButton.setPreferredSize(new Dimension(80, 27));
		refreshSalesButton.setPreferredSize(new Dimension(80, 27));
		
		saleAddButton.addActionListener(new SaleAddAction());
		saleDeleteButton.addActionListener(new SaleDeleteAction());
		saleEditButton.addActionListener(new SaleEditAction());
		saleSearchButton.addActionListener(new SaleSearchAction());
		refreshSalesButton.addActionListener(new SaleRefreshAction());
	//	sortTabCarDetailsButton.addActionListener(new CarDetailsSortAction());
		
		salePanel.add(saleMidPanel);
		
		//Down Panel
		saleDownPanel.add(saleScroller);
		saleScroller.setPreferredSize(new Dimension(550, 160));
        saleTable.setModel(DBHelper.getAllDataSales());
        saleTable.addMouseListener(new SaleTableListener());
        
        salePanel.add(saleDownPanel);
        
        //SEARCH TAB ------------------------------------------------------------------------------
        searchPanel.setLayout(new GridLayout(2, 2));
        
        //Up Panel
        searchUpPanel.setLayout(new GridLayout(6, 2));
        
        searchUpPanel.add(searchTabBrandL);
        searchUpPanel.add(searchTabComboBox);
        searchUpPanel.add(searchTabPriceFromL);
        searchUpPanel.add(priceFromTF);
        searchUpPanel.add(searchTabPriceToL);
        searchUpPanel.add(priceToTF);
        searchUpPanel.add(searchTabPriceButton);
        
        searchTabPriceButton.addActionListener(new SearchTabButtonAction());
        
        DBHelper.fillBrandCombo(searchTabComboBox);
        searchPanel.add(searchUpPanel);
		
        //Down Panel
        searchDownPanel.add(searchTabPriceScroller);
		searchTabPriceScroller.setPreferredSize(new Dimension(450, 160));
		searchPanel.add(searchDownPanel);
		

	
	

this.setVisible(true);
}	
	public void ClearForm() {
		brandsTabBrandTF.setText("");
		countryTF.setText("");
		brandComboBox.setSelectedIndex(0);
		modelTF.setText("");
		yearTF.setText("");
		travelledKmsTF.setText("");
		priceTF.setText("");
		commentTF.setText("");
		chooseCarComboBox.setSelectedIndex(0);
		firstNameTF.setText("");
		lastNameTF.setText("");
	//	saleDateTF.setText("");
		finalPriceTF.setText("");
	}
	
	class BrandAddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String brand = brandsTabBrandTF.getText();
			String country = countryTF.getText();
			
			conn = DBHelper.getConnection();
			String sql = "insert into brands values(null, ?, ?)";
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, brand);
				state.setString(2, country);
				state.execute();
				brandTable.setModel(DBHelper.getAllData("brands"));
				DBHelper.fillBrandCombo(brandComboBox);
		        DBHelper.fillBrandCombo(searchTabComboBox);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			ClearForm();
			
		}
           
		
	}//end AddBrandAction
	
	class BrandTableListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				int row = brandTable.getSelectedRow();
				id = Integer.parseInt(brandTable.getValueAt(row, 0).toString());
				conn = DBHelper.getConnection();
				String sql = "select brand, country from brands where brand_id=?";
				try {
					state = conn.prepareStatement(sql);
					state.setInt(1, id);
					result = state.executeQuery();
					while(result.next()) {
						String item = result.getObject(1).toString() + " " + result.getObject(2).toString();
						String[] items = item.split(" ");
						brandsTabBrandTF.setText(items[0]);
						countryTF.setText(items[1]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						state.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				int row = brandTable.getSelectedRow();
				id = Integer.parseInt(brandTable.getValueAt(row, 0).toString());
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}//BrandTableListener
	
	class BrandDeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from brands where brand_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				JOptionPane.showMessageDialog(null, "Brand deletes everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
				state.execute();
				id = -1;
				brandTable.setModel(DBHelper.getAllData("brands"));
				DBHelper.fillBrandCombo(brandComboBox);
		        DBHelper.fillBrandCombo(searchTabComboBox);
		        DBHelper.fillChooseCarCombo(chooseCarComboBox);
		        carTable.setModel(DBHelper.getAllDataCars());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				//JOptionPane.showMessageDialog(null, "Brand deletes everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, "Can not delete this brand because there is a sale attached to it.", "Error", JOptionPane.ERROR_MESSAGE);
				
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}//BrandDeleteAction
	
	class BrandEditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String brand = brandsTabBrandTF.getText();
			String country = countryTF.getText();
			conn = DBHelper.getConnection();
			String sql = "update brands set brand=?, country=? where brand_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, brand);
				state.setString(2, country);
				state.setInt(3, id);
				JOptionPane.showMessageDialog(null, "Brand updates everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
			
				//state = conn.prepareStatement("update cars set brand=(select brand from brands where cars.brand_id=brands.brand_id)");
				state.execute();
				id = -1;
				brandTable.setModel(DBHelper.getAllData("brands"));
				carTable.setModel(DBHelper.getAllDataCars());
				DBHelper.fillBrandCombo(brandComboBox);
		        DBHelper.fillBrandCombo(searchTabComboBox);
				DBHelper.fillChooseCarCombo(chooseCarComboBox);
				ClearForm();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			
				
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}//BrandEditAction
	
	
	
	class BrandSearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchedBrand = brandSearchTF.getText();
			conn = DBHelper.getConnection();
			String sql = "select * from brands where brand like '%" + searchedBrand +"%'";
			try {
				state = conn.prepareStatement(sql);
				result = state.executeQuery();
				brandTable.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
			
		} // END BrandSearchAction
		
		
	
	
		    	
		  	
	}
	class BrandRefreshAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			brandTable.setModel(DBHelper.getAllData("brands"));
		}
		
	}
	//BRAND END -----------------------------------------------------
	

	class CarAddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int brand_id = -1;
			String brand = brandComboBox.getSelectedItem().toString();
			String model = modelTF.getText();
			short year = Short.parseShort(yearTF.getText());
			short travelledKms = Short.parseShort(travelledKmsTF.getText());
			float price = Float.parseFloat(priceTF.getText());
			String comment = commentTF.getText();
			conn = DBHelper.getConnection();
			try {
				state = conn.prepareStatement("select brand_id from brands where brand='" + brand + "'");
				result = state.executeQuery();
				while(result.next()) {
					brand_id = Integer.parseInt(result.getObject(1).toString());
				}
				state = conn.prepareStatement("insert into cars values(null, ?, ?, ?, ?, ?,?)");
				state.setInt(1, brand_id);
				state.setString(2, model);
				state.setShort(3, year);
				state.setShort(4, travelledKms);
				state.setFloat(5, price);
				state.setString(6, comment);
				state.execute();
				
				//carTable.setModel(DBHelper.getAllData("cars"));
				carTable.setModel(DBHelper.getAllDataCars());
				DBHelper.fillChooseCarCombo(chooseCarComboBox);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			ClearForm();
		}
		
	}//CarAddAction
	
	class CarTableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				int row = carTable.getSelectedRow();
				id = Integer.parseInt(carTable.getValueAt(row, 0).toString());
				conn = DBHelper.getConnection();
				String sql = "select b.brand, c.model, c.production_year, c.travelled_kms, c.price, c.comment from cars c join brands b on c.brand_id=b.brand_id where car_id=?";
				try {
					state = conn.prepareStatement(sql);
					state.setInt(1, id);
					result= state.executeQuery();
					while(result.next()) {
						String item = result.getObject(1).toString() + "#" + result.getObject(2).toString() + "#" 
						+ result.getObject(3).toString() + "#" + result.getObject(4).toString() + "#" + result.getObject(5).toString() + "#" + result.getObject(6).toString();
						String[] items = item.split("#");
						brandComboBox.setSelectedItem(items[0]);
						modelTF.setText(items[1]);
						yearTF.setText(items[2]);
						travelledKmsTF.setText(items[3]);
						priceTF.setText(items[4]);
						commentTF.setText(items[5]);
					} 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						state.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				int row = carTable.getSelectedRow();
				id = Integer.parseInt(carTable.getValueAt(row, 0).toString());
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}//CarTableListener
	
	class CarDeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from cars where car_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				JOptionPane.showMessageDialog(null, "Car deletes everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
				state.execute();
				id = -1;
				carTable.setModel(DBHelper.getAllDataCars());
				DBHelper.fillChooseCarCombo(chooseCarComboBox);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Can not delete this car because there is a sale attached to it.", "Error", JOptionPane.ERROR_MESSAGE);
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}//CarDeleteAction
	
    class CarEditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int brand_id = -1;
			String brand = brandComboBox.getSelectedItem().toString();
			String model = modelTF.getText();
			short year = Short.parseShort(yearTF.getText());
			short travelledKms = Short.parseShort(travelledKmsTF.getText());
			float price = Float.parseFloat(priceTF.getText());
			String comment = commentTF.getText();
			conn = DBHelper.getConnection();
			try {
				state = conn.prepareStatement("select brand_id from brands where brand='" + brand + "'");
				result = state.executeQuery();
				while(result.next()) {
					brand_id = Integer.parseInt(result.getObject(1).toString());
				}
				state = conn.prepareStatement("update cars set brand_id=?, model=?, production_year=?, travelled_kms=?, price=?, comment=? where car_id=?");
				state.setInt(1, brand_id);
				
				state.setString(2, model);
				state.setShort(3, year);
				state.setShort(4, travelledKms);
				state.setFloat(5, price);
				state.setString(6, comment);
				state.setInt(7, id);
				JOptionPane.showMessageDialog(null, "Car updates everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
				state.execute();
				carTable.setModel(DBHelper.getAllDataCars());
				DBHelper.fillChooseCarCombo(chooseCarComboBox);
				saleTable.setModel(DBHelper.getAllDataSales());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			ClearForm();
		}
    	
    }//CarEditAction
    
    class CarSearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchedWord = carSearchTF.getText();
			conn = DBHelper.getConnection();
			String sql = "select c.car_id, b.brand, c.model, c.production_year, c.travelled_kms, c.price, c.comment from cars c join brands b on c.brand_id=b.brand_id where c.model=?";
		//	String sql = "select * from cars where model like '%" + searchedWord +"%'";
		//	String sql = "select * from cars where model like '%" + searchedWord +"%'";
			
			try {
				state= conn.prepareStatement(sql);
				state.setString(1,searchedWord );
				result = state.executeQuery();
				carTable.setModel(new MyModel(result));
		//		carTable.setModel(DBHelper.getAllDataCars());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
    	
    }//CarSearchAction
    
    class CarRefreshAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			carTable.setModel(DBHelper.getAllDataCars());
			
		}
    	
    	
    } // END CarRefreshAction
    
   class CarSortAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
    	
    }//CAR END ------------------------------------------------------------------------------
   class SaleAddAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String item = chooseCarComboBox.getSelectedItem().toString();
			String[] content = item.split(", ");
			int car_id = Integer.parseInt(content[0]);
			float price = Float.parseFloat(content[4]);
			String firstname = firstNameTF.getText();
			String lastName = lastNameTF.getText();
			float final_price = Float.parseFloat(finalPriceTF.getText());
			float difference = price - final_price;
			conn = DBHelper.getConnection();
			String sql = "insert into sales values(null, ?, ?, ?, ?, ?,?)";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, car_id);
				state.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
				state.setString(3, firstname);
				state.setString(4, lastName);
				state.setFloat(5, final_price);
				state.setFloat(6, difference);
				state.execute();
				saleTable.setModel(DBHelper.getAllDataSales());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			ClearForm();
		}
   	
   }//SaleAddAction
   
   class SaleTableListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				int row =saleTable.getSelectedRow();
				id = Integer.parseInt(saleTable.getValueAt(row, 0).toString());
				conn = DBHelper.getConnection();
				String sql = "select first_name, last_name, final_price from sales where sale_id=?";
				try {
					state = conn.prepareStatement(sql);
					state.setInt(1, id);
					result = state.executeQuery();
					while(result.next()) {
						String item = result.getObject(1).toString() + "-" + result.getObject(2).toString()
								+ "-" + result.getObject(3).toString();
						String[] items = item.split("-");
						firstNameTF.setText(items[0]);
						lastNameTF.setText(items[1]);
						finalPriceTF.setText(items[2]);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally {
					try {
						state.close();
						conn.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}else {
				int row = saleTable.getSelectedRow();
				id = Integer.parseInt(saleTable.getValueAt(row, 0).toString());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
   	
   }//SaleTableListener
   
   class SaleDeleteAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			conn = DBHelper.getConnection();
			String sql = "delete from sales where sale_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, id);
				JOptionPane.showMessageDialog(null, "Sale deletes everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
				state.execute();
				saleTable.setModel(DBHelper.getAllDataSales());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
   	
   }//SaleDeleteAction
   
   class SaleEditAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String item = chooseCarComboBox.getSelectedItem().toString();
			String[] content = item.split(", ");
			int car_id = Integer.parseInt(content[0]);
			float price = Float.parseFloat(content[4]);
			String firstname = firstNameTF.getText();
			String lastName = lastNameTF.getText();
			float final_price = Float.parseFloat(finalPriceTF.getText());
			float difference = price - final_price;
			conn = DBHelper.getConnection();
			String sql = "update sales set car_id=?, sale_date=?, first_name=?, last_name=?, final_price=?, price_difference=? where sale_id=?";
			try {
				state = conn.prepareStatement(sql);
				state.setInt(1, car_id);
				state.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
				state.setString(3, firstname);
				state.setString(4, lastName);
				state.setFloat(5, final_price);
				state.setFloat(6, difference);
				state.setInt(7, id);
				JOptionPane.showMessageDialog(null, "Sale updates everywhere", "Attention", JOptionPane.INFORMATION_MESSAGE);
				state.execute();
				saleTable.setModel(DBHelper.getAllDataSales());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			ClearForm();
		}
   	
   }//SaleEditAction
   
   class SaleSearchAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchedWord = saleSearchTF.getText();
			conn = DBHelper.getConnection();
			String sql = "select s.sale_id, c.model, s.sale_date, s.first_name, s.last_name, s.final_price, s.price_difference from brands b join cars c on b.brand_id=c.brand_id join sales s on c.car_id=s.car_id where s.first_name=?";
			try {
				state = conn.prepareStatement(sql);
				state.setString(1,searchedWord);
				result = state.executeQuery();
				saleTable.setModel(new MyModel(result));
			//	saleTable.setModel(DBHelper.getAllDataSales());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
   	
   }//SaleSearchAction
   
    	class SaleRefreshAction implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				saleTable.setModel(DBHelper.getAllDataSales());
			}
    		
    	}
    
  

    	
    //SALES END --------------------------------------------------------------------------
    
    class SearchTabButtonAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String brand = searchTabComboBox.getSelectedItem().toString();
			if(priceFromTF.getText().isBlank() || priceToTF.getText().isBlank())
				JOptionPane.showMessageDialog(null, "Please provide correct input for Price from and Price to fields.", "Error", JOptionPane.ERROR_MESSAGE);	
		//	float priceFrom = Integer.parseInt(priceFromTF.getText());
		//	float priceTo = Integer.parseInt(priceToTF.getText());
			conn = DBHelper.getConnection();
			String sql = "select b.brand, c.model, c.production_year, c.price, c.comment from brands b  join cars c on b.brand_id=c.brand_id where b.brand=? and c.price>? and c.price<?";
					 
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, brand);
				state.setFloat(2,Float.parseFloat(priceFromTF.getText()));
				state.setFloat(3,Float.parseFloat(priceToTF.getText()));
				result = state.executeQuery();
				searchTabPriceTable.setModel(new MyModel(result));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
    	
    }
}

