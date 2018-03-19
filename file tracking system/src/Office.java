import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
//import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Connection.ConnectionManager;
import net.proteanit.sql.DbUtils;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import javax.swing.UIManager;
//import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;

public class Office extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField name;
	private JButton update;
	private JButton delete;
	private JButton add;
	private JButton reset;
	private JComboBox<Integer> id;
	private ResultSet rs=null;
	private ResultSet result=null;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			Office dialog = new Office();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Office() {
		setBounds(100, 100, 760, 463);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 377, 369);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
			     final int key = evt.getKeyCode();
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
		                || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int selectedRowIndex = table.getSelectedRow();
					id.setSelectedIndex((int)model.getValueAt(selectedRowIndex, 1));
					name.setText(model.getValueAt(selectedRowIndex,0).toString());
		           
				}      
			}
		});
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
						//get selected row from the table
				        delete.setEnabled(true);
				        update.setEnabled(true);
						add.setEnabled(false);
						//forward.setEnabled(true);
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						int selectedRowIndex = table.getSelectedRow();
						id.setSelectedItem((int)model.getValueAt(selectedRowIndex, 1));
						name.setText(model.getValueAt(selectedRowIndex,0).toString());
						
					 //  comment.setText(model.getValueAt(selectedRowIndex, 2).toString());
					   //set the current file barcode and name for transfer
					  
					
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		table.setBorder(null);
		scrollPane.setViewportView(table);
		//ADD CONTENT IN TABLE
        String sql="select officeName,level from office";
		
		
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql);
	     		 ){
	      	
	      	rs=stmt.executeQuery();
	      
	      	table.setModel(DbUtils.resultSetToTableModel(rs));
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			
			
		} 
		
		JPanel panel = new JPanel();
		panel.setBounds(397, 11, 337, 369);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("officeName");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 82, 26);
		panel.add(lblNewLabel);
		
		JLabel lblStaffid = new JLabel("Level");
		lblStaffid.setBounds(10, 72, 82, 26);
		panel.add(lblStaffid);
		
		name = new JTextField();
		name.setBounds(75, 14, 252, 20);
		panel.add(name);
		name.setColumns(10);
		
		 update = new JButton("update");
		update.setBounds(120, 191, 101, 23);
		panel.add(update);
		
	    delete = new JButton("delete");
	    delete.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		String sql1="DELETE from office where officeName=?";
				
				
			      
			      try(
			     		
			     		   PreparedStatement stmt=conn.prepareStatement(sql1);
			     		 ){
			    	stmt.setString(1,name.getText());
			  		
					
			  		
			  		
			  		int affected=stmt.executeUpdate();
			  		if (affected==1) {
			  			JOptionPane.showMessageDialog(null,name.getText()+ "office deleted");
			  			name.setText(null);
			  		  String sqlquery="select officeName,level from office";
			  		 PreparedStatement stmtupdate=conn.prepareStatement(sqlquery);
			  		 result=stmtupdate.executeQuery();
			  			table.setModel(DbUtils.resultSetToTableModel(result));
			  			
			  		} else {
			  			JOptionPane.showMessageDialog(null, "Error");
			  			
			  		}
			      	
			      	
			      	
			      } catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					//JOptionPane.showMessageDialog(null, "Error"+sid);
					
					
				}
	    	}
	    });
		delete.setBounds(231, 191, 92, 23);
		delete.setEnabled(false);
	    update.setEnabled(false);
		panel.add(delete);
		
		add = new JButton("CREATE");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sql="insert into office(officeName,level)  values(?,?)";
				
				
			      
			      try(
			     		
			     		   PreparedStatement stmt=conn.prepareStatement(sql);
			     		 ){
			    	stmt.setString(1,name.getText());
			  		
					stmt.setInt(2,(int)id.getSelectedItem());
			  		
			  		
			  		int affected=stmt.executeUpdate();
			  		if (affected==1) {
			  			 setVisible(false);
			  			JOptionPane.showMessageDialog(null, "new office created");
			  			 String sqlquery="select officeName,level from office";
				  		 PreparedStatement stmtupdate=conn.prepareStatement(sqlquery);
				  		 result=stmtupdate.executeQuery();
				  			table.setModel(DbUtils.resultSetToTableModel(result));
			  			
			  			
			  		} else {
			  			 setVisible(false);
			  			JOptionPane.showMessageDialog(null, "Error");
			  			
			  		}
			      	
			  		 setVisible(true);
			      	
			      } catch (SQLException e) {
					// TODO Auto-generated catch block
					System.err.println(e.getMessage());
					//JOptionPane.showMessageDialog(null, "Error"+sid);
					
					
				}
			     
			
			}
		});
		add.setEnabled(true);
		add.setBounds(10, 191, 101, 23);
		panel.add(add);
		
		 reset = new JButton("RESET");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name.setText(null);
				//id.setText(null);
				add.setEnabled(true);
				 delete.setEnabled(false);
			        update.setEnabled(false);
			}
		});
		reset.setEnabled(true);
		reset.setBounds(10, 151, 313, 23);
		panel.add(reset);
		
		id = new JComboBox<Integer>();
		id.addItem(new Integer(1));
		id.addItem(new Integer(2));
		id.addItem(new Integer(3));
		id.setEditable(false);
		id.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		id.setBounds(83, 75, 111, 23);
		panel.add(id);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
