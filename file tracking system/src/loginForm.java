import java.awt.EventQueue;

	import javax.swing.JFrame;
	import javax.swing.JToolBar;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;

	import java.awt.Font;
	import javax.swing.JPanel;
	import javax.swing.JTextField;
	import javax.swing.border.BevelBorder;

import Connection.ConnectionManager;

import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.awt.event.ActionEvent;
	import javax.swing.ImageIcon;
	import java.awt.Color;
	import java.awt.SystemColor;
	import javax.swing.JPasswordField;

	public class loginForm {
	    private static Connection conn=ConnectionManager.getInstance().getConnection();
		private JTextField username;
		private JPasswordField password;
		public  JFrame frame;
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						loginForm window = new loginForm();
						window.frame.setVisible(true);
					} catch (Exception e) {
						System.err.println(e.getMessage());
					}
				}
			});
		}

		/**
		 * Create the application.
		 * @throws SQLException 
		 */
		public loginForm() throws SQLException {
			initialize();
		}

		/**
		 * Initialize the contents of the frame.
		 * @throws SQLException 
		 */
		private void initialize() throws SQLException {
			frame = new JFrame();
			frame.getContentPane().setBackground(SystemColor.control);
			
			frame.setBounds(100, 100, 843, 596);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setResizable(false);
			frame.getContentPane().setLayout(null);
			frame.setLocationRelativeTo(null);
			JLabel banner = new JLabel("       FILE TRACKING SYSTEM");
			banner.setBackground(new Color(0, 0, 139));
			banner.setFont(new Font("Times New Roman", Font.BOLD, 14));
			banner.setBounds(0, 0, 824, 33);
			frame.getContentPane().add(banner);
			
			JPanel panel = new JPanel();
			panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			panel.setBackground(new Color(192, 192, 192));
			panel.setForeground(new Color(0, 0, 0));
			panel.setBounds(513, 76, 314, 349);
			frame.getContentPane().add(panel);
			panel.setLayout(null);
			
			username = new JTextField();
			username.setForeground(new Color(0, 0, 0));
			username.setBackground(new Color(255, 255, 224));
			username.setBounds(20, 37, 235, 36);
			panel.add(username);
			username.setColumns(10);
			
			JLabel lblUsername = new JLabel("USERNAME");
			lblUsername.setBounds(20, 11, 93, 28);
			panel.add(lblUsername);
			lblUsername.setFont(new Font("Times New Roman", Font.BOLD, 13));
			
			JLabel lblPassword = new JLabel("PASSWORD");
			lblPassword.setBounds(20, 76, 93, 28);
			panel.add(lblPassword);
			lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 13));
			
			JButton btnLogIn = new JButton("LOG IN");
			btnLogIn.setBackground(new Color(60, 179, 113));
			btnLogIn.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent arg0) {
					String user=username.getText();
					String pass=password.getText();
					String sql="SELECT staff_name,office,staff_type,office.officeName FROM staff "
							+ " join office on staff.office=office.offId"
							+ " WHERE staff_id=? AND password=?";
					
					
					try(
						PreparedStatement stmt=conn.prepareStatement(sql);
						)
					{
						stmt.setString(1,user);
						stmt.setString(2,pass);
						ResultSet rs=stmt.executeQuery();
						
						if(user.equals("")&&pass.equals(""))
						{
							JOptionPane.showMessageDialog(null,"username and password field must filled");
						}
						else
						{
							if(rs.next())
							{
								StaffManager.setSname(rs.getString("staff_name"));
								StaffManager.setStype(rs.getString("staff_type"));
								StaffManager.setOffice(rs.getString("officeName"));
								ManagerFile.setCurrentoffice(rs.getInt("office"));
								
									//JOptionPane.showMessageDialog(null, "Suceessfuly Log In As    "+rs.getString("staff_name"));
								if(rs.getString("staff_type").equals("Secretary")){
									Staff page=new Staff();
									page.getFrmFileTrackingSystem().setVisible(true);
									username.setText(null);
									password.setText(null);
									frame.setVisible(false);
								}
								else if(rs.getString("staff_type").equals("Adminstrator")){
								Admin page=new Admin();
								page.getFrmFileTrackingSystem().setVisible(true);
								username.setText(null);
								password.setText(null);
								frame.setVisible(false);
								//conn.close();
								}
								else{
									JOptionPane.showMessageDialog(null, "The staff panel will come soon");
									username.setText(null);
									password.setText(null);
								}
							}
							else
							{
									JOptionPane.showMessageDialog(null, "wrong username or password");
									password.setText(null);
							}
							
							
						}
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//System.err.println(e.getMessage());
					}
				}
				
			});
			btnLogIn.setBounds(20, 174, 235, 28);
			panel.add(btnLogIn);
			btnLogIn.setFont(new Font("Tempus Sans ITC", Font.BOLD, 12));
			
			JButton register = new JButton("Register");
			register.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0) 
				{
					Registered_form rg = null;
					try {
						rg = new Registered_form();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//System.err.println(e.getMessage());
					}
					rg.setVisible(true);
					//frame.setVisible(false);
					//rg.setAlwaysOnTop(true);
				}
			
			});
			register.setForeground(Color.WHITE);
			register.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
			register.setBackground(new Color(100, 149, 237));
			register.setBounds(20, 245, 235, 33);
			panel.add(register);
			
			password = new JPasswordField();
			password.setBackground(new Color(255, 255, 224));
			password.setBounds(20, 109, 235, 36);
			panel.add(password);
			
			JLabel lblNewLabel = new JLabel("No account register bellow!!");
			lblNewLabel.setForeground(new Color(255, 51, 0));
			lblNewLabel.setBackground(new Color(0, 0, 204));
			lblNewLabel.setBounds(30, 213, 199, 14);
			panel.add(lblNewLabel);
		    
			
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBackground(SystemColor.text);
			lblNewLabel_1.setIcon(new ImageIcon(loginForm.class.getResource("/img/uchet_rabochego_vremeni_na_predpriyatiyah-1.png")));
			lblNewLabel_1.setBounds(10, 58, 814, 384);
			frame.getContentPane().add(lblNewLabel_1);
			
			JToolBar toolBar = new JToolBar();
			toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
			toolBar.setBackground(new Color(100, 149, 237));
			toolBar.setBounds(0, 33, 837, 32);
			frame.getContentPane().add(toolBar);
			
			JLabel lblNewLabel_2 = new JLabel("");
			lblNewLabel_2.setIcon(new ImageIcon(loginForm.class.getResource("/img/bg.jpg")));
			lblNewLabel_2.setBounds(0, 67, 837, 500);
			frame.getContentPane().add(lblNewLabel_2);
			
			
			
		}
	}

