//import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
//import java.awt.Color;
//import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

//import java.awt.Font;
//import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
//import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.xdevapi.Table;



public class Shop extends JFrame {
	
	private JPanel contentPane;
	
	private static final long serialVersionUID = 1L;
	private static JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Shop frame = new Shop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		/**try {
			//Conection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");
			
			//statement
			System.out.println("Connected!");
			
			PreparedStatement sta = myConn.prepareStatement("select * from prod");
						
			//result
			
			ResultSet myRes =  sta.executeQuery();
			
			//while(myRes.next() ) {
		//	System.out.println(myRes.getString("prodid") + "." + myRes.getString("prodname") + " pret: " +  myRes.getString("prodprice") + " cantitate: " + myRes.getString("prodcant"));
		//	}
			
			//table.setModel (DbUtils.resultSetToTableModel(myRes));

			myConn.close();
			
		}
	
		
		catch (Exception exc){
			exc.printStackTrace();
			
		}**/
			
	}

	
	public Shop() {
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(293, 102, 385, 287);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);

		scrollPane.setViewportView(table);
		shopTable();
	}
	
	public void shopTable() {
		
		try {
			//Conection
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");
			
			//statement
		
			
			String query="select * from prod";
			
			PreparedStatement sta = myConn.prepareStatement(query);
						
			//result
			
			ResultSet myRes =  sta.executeQuery();
			
			
			table.setModel (DbUtils.resultSetToTableModel(myRes));

			myConn.close();
			
		}
			
		catch (Exception exc){
			exc.printStackTrace();
			
		}
		
	}
}
