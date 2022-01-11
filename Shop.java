//import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
//import java.awt.Color;
//import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

//import java.awt.Font;
//import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
//import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.xdevapi.Table;



public class Shop extends JFrame {
	
	private JPanel contentPane;
	
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private JTextField prodname;
	private JTextField prodcant;
	private String prodid;
	private String prodprice;
	
	
	
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
		
		
			
	}

	
	public Shop() {
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 285, 639, 152);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setForeground(Color.BLACK);
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(Color.ORANGE);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				
				
				
				prodid=(model.getValueAt(index, 0).toString());
				prodname.setText(model.getValueAt(index, 1).toString());
				prodprice=(model.getValueAt(index, 2).toString());
				
				
			}
		});
		
		
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Main menu");
		btnBack.setForeground(Color.BLACK);
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(568, 177, 139, 42);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_4 = new JLabel("PRODUCTS LIST");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(309, 236, 150, 36);
		contentPane.add(lblNewLabel_4);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");	
					PreparedStatement sta2 = conn.prepareStatement("update prod set prodcant = prodcant - ? where prodid = ?");

					sta2.setInt(1, Integer.valueOf(prodcant.getText()));
					sta2.setInt(2, Integer.valueOf(prodid));
					
					sta2.executeUpdate();
					
					PreparedStatement sta = conn.prepareStatement("insert into cos values(?,?,?,?)");
					
					sta.setInt(1, Integer.valueOf(prodid));
					sta.setString(2, prodname.getText());
					sta.setInt(3, Integer.valueOf(prodprice));
					sta.setInt(4, Integer.valueOf(prodcant.getText()));
					
					sta.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "New prod added!");
					
					conn.close();
					shopTable();
					
				}
					
				catch (Exception exc){
					exc.printStackTrace();
					
				}
				
				
				
			}
		});
		
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnAdd.setBackground(Color.RED);
		btnAdd.setBounds(68, 177, 139, 42);
		contentPane.add(btnAdd);
		
		JLabel lblNewLabel_1 = new JLabel("ProdName");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_1.setBounds(68, 72, 111, 36);
		contentPane.add(lblNewLabel_1);
		
		prodname = new JTextField();
		prodname.setColumns(10);
		prodname.setBounds(189, 74, 148, 36);
		contentPane.add(prodname);
		
		JLabel lblNewLabel_3 = new JLabel("ProdCant");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_3.setBounds(392, 72, 111, 36);
		contentPane.add(lblNewLabel_3);
		
		prodcant = new JTextField();
		prodcant.setColumns(10);
		prodcant.setBounds(518, 74, 148, 36);
		contentPane.add(prodcant);
		
		JLabel lblNewLabel_4_1 = new JLabel("SHOP");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.RED);
		lblNewLabel_4_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4_1.setBounds(309, 11, 150, 36);
		contentPane.add(lblNewLabel_4_1);
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
