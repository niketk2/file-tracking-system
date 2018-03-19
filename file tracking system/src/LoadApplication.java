
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
//import javax.swing.JRootPane;

import java.awt.Font;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Cursor;
//import java.awt.Rectangle;
import javax.swing.ImageIcon;

public class LoadApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JPanel panel;
	public JLabel loading;
	public JProgressBar progressBar;
	private JPanel panel_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public LoadApplication() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setBounds(100, 100, 564, 418);
		setUndecorated(true);
		setLocationRelativeTo(null);
		//getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setForeground(new Color(255, 0, 0));
		panel.setBackground(new Color(0, 0, 255));
		panel.setBounds(0, 330, 564, 88);
		contentPane.add(panel);
		panel.setLayout(null);
		
		loading = new JLabel("Loading...");
		loading.setForeground(Color.RED);
		loading.setFont(new Font("Engravers MT", Font.PLAIN, 20));
		loading.setBounds(196, 11, 244, 25);
		panel.add(loading);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(51, 153, 0));
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setBounds(23, 52, 531, 25);
		panel.add(progressBar);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 564, 330);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("FILE TRACKING SYSTEM");
		lblNewLabel_1.setIcon(new ImageIcon(LoadApplication.class.getResource("/img/FILETRAC.png")));
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(20, 131, 534, 125);
		panel_1.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(153, 51, 255));
		lblNewLabel.setIcon(new ImageIcon(LoadApplication.class.getResource("/img/wall.jpg")));
		lblNewLabel.setBounds(0, 0, 563, 329);
		panel_1.add(lblNewLabel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{contentPane, panel, loading, progressBar}));
	}
}
