import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Cos extends JFrame {

	
	private static final long serialVersionUID = 1L;

	private JPanel contentPane_1;
	private static JTable table;
	private JTextField prodname;
	private JTextField prodcant;
	private String prodid;
	private String prodprice;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cos frame = new Cos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public Cos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 487);
		
		contentPane_1 = new JPanel();
		contentPane_1.setBackground(Color.CYAN);
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane_1);
		contentPane_1.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(68, 285, 639, 152);
		contentPane_1.add(scrollPane);
		
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
				prodcant.setText(model.getValueAt(index, 3).toString());
				
			}
		});
		
		
		
		

		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_4 = new JLabel("Shopping Cart List");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(300, 243, 181, 42);
		contentPane_1.add(lblNewLabel_4);
		
		JButton btnBack = new JButton("Main menu");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setForeground(Color.BLACK);
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(568, 232, 139, 42);
		contentPane_1.add(btnBack);
		
		JLabel lblNewLabel_4_1 = new JLabel("Shopping Cart");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.RED);
		lblNewLabel_4_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4_1.setBounds(300, 11, 150, 36);
		contentPane_1.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_1 = new JLabel("ProdName");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_1.setBounds(42, 85, 111, 36);
		contentPane_1.add(lblNewLabel_1);
		
		prodname = new JTextField();
		prodname.setColumns(10);
		prodname.setBounds(163, 87, 148, 36);
		contentPane_1.add(prodname);
		
		JLabel lblNewLabel_3 = new JLabel("ProdCant");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_3.setBounds(405, 87, 111, 36);
		contentPane_1.add(lblNewLabel_3);
		
		prodcant = new JTextField();
		prodcant.setColumns(10);
		prodcant.setBounds(526, 87, 148, 36);
		contentPane_1.add(prodcant);
		
		JButton btnChange = new JButton("Change");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
try {
					
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");	
					PreparedStatement sta2 = conn.prepareStatement("update prod set prodcant = prodcant + ? where prodid = ?");

					sta2.setInt(1, Integer.valueOf(prodcant.getText()));
					sta2.setInt(2, Integer.valueOf(prodid));
					
					sta2.executeUpdate();
					
					PreparedStatement sta = conn.prepareStatement("update cos set prodcant = ? where prodid = ?");
					
					sta.setInt(1, Integer.valueOf(prodcant.getText()));
					sta.setInt(2, Integer.valueOf(prodid));
					
					sta.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Quantity changed!");
					
					conn.close();
					cosTable();
					
				}
					
				catch (Exception exc){
					exc.printStackTrace();
					
				}
				
				
				
				
				
			}
		});
		btnChange.setForeground(Color.BLACK);
		btnChange.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnChange.setBackground(Color.RED);
		btnChange.setBounds(300, 134, 139, 42);
		contentPane_1.add(btnChange);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");						
					String query = "delete from cos where prodid=" + prodid;
					PreparedStatement sta = myConn.prepareStatement(query);
					sta.executeUpdate();			
					
					cosTable();
					JOptionPane.showMessageDialog(null, "Deleted!");
					
					myConn.close();
					
				}catch (Exception exc){
					exc.printStackTrace();						
				}								
				
				
				
				
				
				
				
			}
		});
		btnDelete.setForeground(Color.BLACK);
		btnDelete.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnDelete.setBackground(Color.RED);
		btnDelete.setBounds(300, 187, 139, 42);
		contentPane_1.add(btnDelete);
		
		
		
		cosTable();
	}
	
	public void cosTable() {
		
		try {
			//Conection
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");
			
			//statement
		
			String query="select * from cos";
			
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
