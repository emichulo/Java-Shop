
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;

public class LogInPanel extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField userIN;
	private JPasswordField passIN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInPanel frame = new LogInPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel Username = new JLabel("Username");
		Username.setHorizontalAlignment(SwingConstants.CENTER);
		Username.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		Username.setBounds(70, 143, 193, 40);
		contentPane.add(Username);
		
		JLabel Password = new JLabel("Password");
		Password.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		Password.setHorizontalAlignment(SwingConstants.CENTER);
		Password.setBounds(70, 194, 193, 40);
		contentPane.add(Password);
		
		userIN = new JTextField();
		userIN.setBounds(273, 147, 193, 40);
		contentPane.add(userIN);
		userIN.setColumns(10);
		
		passIN = new JPasswordField();
		passIN.setBounds(273, 198, 190, 40);
		contentPane.add(passIN);
		
		JButton LogInButton = new JButton("Log In");
		LogInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usernameVerf = userIN.getText();
				@SuppressWarnings("deprecation")
				String passVerf = passIN.getText();
				
				if(usernameVerf.equals("emi") && passVerf.equals("parola"))
				{
		
					try {
						JOptionPane.showMessageDialog(null, "You are sucessfully logined ... ");
						TimeUnit.SECONDS.sleep(2);
						dispose();
						AdminOptions adminOptFrm = new AdminOptions();
						adminOptFrm.setVisible(true);
						
					} catch (InterruptedException e1) {
						
						e1.printStackTrace();
					}
					
									
				}
				
				else if (!usernameVerf.equals("emi")){
					
					JOptionPane.showMessageDialog(LogInButton,"Acest username nu exista !");
					
				}
				
				else {
					
					JOptionPane.showMessageDialog(LogInButton,"          Ai gresit parola !");
					
				}
			}
		});
		LogInButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		LogInButton.setBounds(296, 269, 150, 40);
		contentPane.add(LogInButton);
		
		JButton BackButton = new JButton("<- Back");
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dashboard frame = new Dashboard();
				frame.setVisible(true);
				dispose();
				
			}
		});
		BackButton.setForeground(Color.BLACK);
		BackButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		BackButton.setBounds(10, 403, 150, 40);
		contentPane.add(BackButton);
		
		JLabel lblNewLabel_4_1 = new JLabel("LOG IN");
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setForeground(Color.RED);
		lblNewLabel_4_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel_4_1.setBounds(273, 11, 193, 64);
		contentPane.add(lblNewLabel_4_1);
	}
}
