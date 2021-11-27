import java.awt.EventQueue;
import java.awt.Image;
//import java.awt.event.MouseListener;

//import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.border.LineBorder;
//import javax.swing.event.AncestorListener;
import javax.swing.event.MouseInputAdapter;

//import org.w3c.dom.events.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
//import javax.swing.JOptionPane;
//import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;


	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard frame = new Dashboard();
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
	
	public Dashboard() {
		setBackground(Color.CYAN);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 790, 487);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(Color.GREEN, 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 191, 255));
		panel.setBounds(0, 0, 297, 487);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		
		Image img = new ImageIcon(this.getClass().getResource("menuIcon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		
		lblNewLabel.setBounds(-33, 32, 346, 97);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addMouseListener(new ButtonHandler(btnNewButton));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("EXIT");
				if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to EXIT", "EXIT",
						JOptionPane.YES_NO_CANCEL_OPTION)==JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
			}
		});

		btnNewButton.setBounds(0, 415, 297, 61);
		panel.add(btnNewButton);
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(0, 206, 209));
		btnNewButton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBackground(new Color(0, 206, 209));
		btnNewButton_1.addMouseListener(new ButtonHandler(btnNewButton_1));
		btnNewButton_1.setBounds(0, 140, 297, 61);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBackground(new Color(0, 206, 209));
		btnNewButton_2.addMouseListener(new ButtonHandler(btnNewButton_2));
		btnNewButton_2.setBounds(0, 202, 297, 61);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("LogIn");
		btnNewButton_3.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnNewButton_3.setBackground(new Color(0, 206, 209));
		btnNewButton_3.setBounds(0, 267, 297, 61);
		panel.add(btnNewButton_3);
		btnNewButton_3.addMouseListener(new ButtonHandler(btnNewButton_3));
		
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LogInPanel panel2 = new LogInPanel();
				panel2.setVisible(true);
				dispose();
				
				
			}
		});
		

		
	}
	
	private class ButtonHandler extends MouseInputAdapter {
		
		JButton b;
		public ButtonHandler(JButton b) {
			this.b = b;
		}
		
		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {	
			b.setBackground(new Color(112, 128, 144));
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			b.setBackground(new Color(0, 206, 209));
		}
		
	
		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			b.setBackground(new Color(60, 179, 113));
		}
		
		
		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			b.setBackground(new Color(112, 128, 144));
		}
	}
}
