
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ListSelectionModel;

public class AdminOptions extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private JPanel contentPane;
	private JTable table;
	private JTextField prodid;
	private JTextField prodname;
	private JTextField prodprice;
	private JTextField prodcant;


	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminOptions frame = new AdminOptions();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	// MAIN CONSTRUCT 
	public AdminOptions() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setForeground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
	
		scrollPane.setBounds(57, 280, 653, 157);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setForeground(Color.BLACK);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int index = table.getSelectedRow();
				
				prodid.setText(model.getValueAt(index, 0).toString());
				prodname.setText(model.getValueAt(index, 1).toString());
				prodprice.setText(model.getValueAt(index, 2).toString());
				prodcant.setText(model.getValueAt(index, 3).toString());
				
				
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setBackground(Color.ORANGE);
		scrollPane.setViewportView(table);
		
		JButton btnBack = new JButton("Main menu");
		
		
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
				dispose();
				
			}
		});
		btnBack.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnBack.setBackground(Color.RED);
		btnBack.setBounds(625, 227, 139, 42);
		contentPane.add(btnBack);
		
		prodid = new JTextField();
		prodid.setBounds(162, 66, 148, 36);
		contentPane.add(prodid);
		prodid.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("ProdId");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel.setBounds(28, 64, 111, 36);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ProdName");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_1.setBounds(28, 111, 111, 36);
		contentPane.add(lblNewLabel_1);
		
		prodname = new JTextField();
		prodname.setColumns(10);
		prodname.setBounds(162, 113, 148, 36);
		contentPane.add(prodname);
		
		JLabel lblNewLabel_2 = new JLabel("ProdPrice");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_2.setBounds(356, 64, 111, 36);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ProdCant");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		lblNewLabel_3.setBounds(356, 111, 111, 36);
		contentPane.add(lblNewLabel_3);
		
		prodprice = new JTextField();
		prodprice.setColumns(10);
		prodprice.setBounds(477, 66, 148, 36);
		contentPane.add(prodprice);
		
		prodcant = new JTextField();
		prodcant.setColumns(10);
		prodcant.setBounds(477, 113, 148, 36);
		contentPane.add(prodcant);
		
		JButton btnAdd = new JButton("AddProd");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(prodid.getText().isEmpty() || prodname.getText().isEmpty() || prodprice.getText().isEmpty() || prodcant.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Missing Information!");	
					
				}
				
				else {
				try {
			
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");	
					PreparedStatement sta = conn.prepareStatement("insert into prod values(?,?,?,?)");
								
					sta.setInt(1, Integer.valueOf(prodid.getText()));
					sta.setString(2, prodname.getText());
					sta.setInt(3, Integer.valueOf(prodprice.getText()));
					sta.setInt(4, Integer.valueOf(prodcant.getText()));
					sta.executeUpdate();
					JOptionPane.showMessageDialog(null, "New prod added!");
					
					conn.close();
					afisProd();
					
				}
					
				catch (Exception exc){
					exc.printStackTrace();
					
				}
				
			}}
				
		});
		
		btnAdd.setBackground(Color.GRAY);
		btnAdd.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnAdd.setBounds(149, 171, 133, 36);
		contentPane.add(btnAdd);
		
		JButton btnDel = new JButton("DeleteProd");
	
		btnDel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(prodid.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the ID!");
				}
				else
				{
					try {
					
						Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");						
						String query = "delete from prod where prodid=" + prodid.getText();
						PreparedStatement sta = myConn.prepareStatement(query);
						sta.executeUpdate();			
						
						afisProd();
						JOptionPane.showMessageDialog(null, "Deleted!");
						
						myConn.close();
						
					}catch (Exception exc){
						exc.printStackTrace();						
					}								
				}			
			}
		});
	
		

		btnDel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnDel.setBackground(Color.GRAY);
		btnDel.setBounds(457, 171, 133, 36);
		contentPane.add(btnDel);
		
		JLabel lblNewLabel_4 = new JLabel("PRODUCTS LIST");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4.setBounds(311, 244, 150, 36);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("ADMIN OPTIONS");
		lblNewLabel_4_1.setForeground(Color.RED);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_4_1.setBounds(311, 0, 150, 36);
		contentPane.add(lblNewLabel_4_1);
		
		JButton btnAdd_1 = new JButton("Clear");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				prodid.setText("");
				prodname.setText("");
				prodprice.setText("");
				prodcant.setText("");
				
			}
		});
		
		
		btnAdd_1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		btnAdd_1.setBackground(Color.GRAY);
		btnAdd_1.setBounds(305, 171, 133, 36);
		contentPane.add(btnAdd_1);
		
		afisProd();
	}
	
	public void afisProd() {
		
		try {
			
			// Conection
			
			Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/shopy", "root", "esddg1312");
			
			// Statement
						
			String query= "select * from prod";
			
			PreparedStatement sta = myConn.prepareStatement(query);
						
			// Result
			
			ResultSet myRes =  sta.executeQuery();
			
			table.setModel (DbUtils.resultSetToTableModel(myRes));

			myConn.close();
			
		}
			
		catch (Exception exc){
			exc.printStackTrace();
			
		}
		
	}
}
