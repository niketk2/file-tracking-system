import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JTextField;
import java.awt.Color;

public class FileLocation extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			FileLocation dialog = new FileLocation();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FileLocation() {
		setBounds(100, 100, 484, 320);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Fille name              :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel.setBounds(51, 16, 148, 40);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date Received        :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(51, 88, 129, 34);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblOffice = new JLabel("Office   Name       :");
		lblOffice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOffice.setBounds(51, 159, 129, 34);
		contentPanel.add(lblOffice);
		
		JLabel lblFile = new JLabel(ManagerFile.getFile());
		lblFile.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblFile.setForeground(new Color(0, 128, 128));
		lblFile.setBounds(209, 20, 212, 34);
		contentPanel.add(lblFile);
		
		JLabel lblDate = new JLabel(ManagerFile.getD_moved().toString());
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblDate.setForeground(new Color(0, 128, 128));
		lblDate.setBounds(209, 89, 212, 34);
		contentPanel.add(lblDate);
		
		JLabel lblOff = new JLabel(ManagerFile.getOfficeDestination());
		lblOff.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblOff.setForeground(new Color(0, 128, 128));
		lblOff.setBounds(209, 159, 212, 34);
		contentPanel.add(lblOff);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(27, 11, 406, 226);
		contentPanel.add(textArea);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				
			}
		}
	}
}
