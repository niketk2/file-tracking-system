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
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import javax.swing.UIManager;
//import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class User extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 476444126511183126L;
	/**
	 * 
	 */
	
	//private static final long serialVersionUID = 1L;
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField name;
	private JTextField id;
	private JButton update;
	private JButton delete;
	private JComboBox<String> Role;
	private JComboBox<String> office;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		try {
			User dialog = new User();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public User() {
		setBounds(100, 100, 817, 463);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 507, 369);
		contentPanel.add(scrollPane);
		
	
		JPanel panel = new JPanel();
		panel.setBounds(527, 11, 264, 369);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Staffname");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 82, 26);
		panel.add(lblNewLabel);
		
		JLabel lblStaffid = new JLabel("StaffId");
		lblStaffid.setBounds(10, 72, 82, 26);
		panel.add(lblStaffid);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(10, 121, 82, 26);
		panel.add(lblRole);
		
		JLabel lblOffice = new JLabel("Office");
		lblOffice.setBounds(10, 172, 82, 26);
		panel.add(lblOffice);
		
		name = new JTextField();
		name.setEditable(false);
		name.setBounds(75, 14, 179, 20);
		panel.add(name);
		name.setColumns(10);
		
		id = new JTextField();
		id.setEditable(false);
		id.setBounds(75, 75, 179, 20);
		panel.add(id);
		id.setColumns(10);
		
		
		//add item to combonbox
		 String[] item ={"Adminstrator","Secretary","staff"};
			
		Role = new JComboBox<String>();
		for (int i = 0; i < item.length; i++) {
			Role.addItem(item[i]);
		}
		Role.setBounds(75, 124, 179, 20);
		//table
	   
		Role.setSelectedItem(null);

		panel.add(Role);
		
		office = new JComboBox<String>();
		//add item to the combox
		String sql="select * from office";
		try(
		Statement stmt=conn.createStatement();
		ResultSet rs=stmt.executeQuery(sql);
			)
		{
		
		while(rs.next())
			{
			   office.setSelectedItem(null);
			   office.addItem(rs.getString("officeName"));
			}
		}
		catch
		(SQLException e)
		{
			System.err.println(e.getMessage());
		}
		office.setFont(new Font("Times New Roman", Font.BOLD, 10));
		office.setBounds(75, 175, 179, 23);
		panel.add(office);
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
						//get selected row from the table
				        delete.setEnabled(true);
				        update.setEnabled(true);
						
						//forward.setEnabled(true);
						DefaultTableModel model=(DefaultTableModel)table.getModel();
						int selectedRowIndex = table.getSelectedRow();
						id.setText(model.getValueAt(selectedRowIndex, 0).toString());
						name.setText(model.getValueAt(selectedRowIndex,1).toString());
						Role.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
						office.setSelectedItem(model.getValueAt(selectedRowIndex,3).toString());
					 //  comment.setText(model.getValueAt(selectedRowIndex, 2).toString());
					   //set the current file barcode and name for transfer
					  
					
			}
		});
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
			     final int key = evt.getKeyCode();
				if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN
		                || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
					DefaultTableModel model=(DefaultTableModel)table.getModel();
					int selectedRowIndex = table.getSelectedRow();
					id.setText(model.getValueAt(selectedRowIndex, 0).toString());
					name.setText(model.getValueAt(selectedRowIndex,1).toString());
					Role.setSelectedItem(model.getValueAt(selectedRowIndex, 2).toString());
					office.setSelectedItem(model.getValueAt(selectedRowIndex,3).toString());
					
				}      
			}
		});
		table.setFont(new Font("Times New Roman", Font.BOLD, 10));
		table.setBorder(null);
		scrollPane.setViewportView(table);
		//ADD CONTENT IN TABLE
        String sql1="select staff_id,staff_name,staff_type,officeName from staff"
        		+ " join office on staff.office=office.offId";
		
		ResultSet rs=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql1);
	     		 ){
	      	
	      	rs=stmt.executeQuery();
	      	table.setModel(DbUtils.resultSetToTableModel(rs));
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			
			
		} 
	      update = new JButton("update");
	      update.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			 update.addActionListener(new ActionListener() {
			 	public void actionPerformed(ActionEvent arg0) {
			 		if (name.getText().equals("")||Role.getSelectedItem().equals(null)||office.getSelectedItem().equals(null)||id.getText().equals("")) {
			 			JOptionPane.showMessageDialog(null, " No user selected");
						
					}
			 		else{
			 		       
			 				//update table
			 		       StaffManager.setStype((String)Role.getSelectedItem());
			 		       StaffManager.setOffice((String)office.getSelectedItem());
			 		       StaffManager.setSid(id.getText());
			 		       StaffManager updstaff=new StaffManager();
			 		       if(updstaff.updatestaff()){
			 		    	   setVisible(false);
			 				 String sqlupd="select staff_id,staff_name,staff_type,officeName from staff"
			 		        		+ " join office on staff.office=office.offId";
			 				
			 				ResultSet res=null;
			 			      
			 			      try(
			 			     		
			 			     		   PreparedStatement stmt1=conn.prepareStatement(sqlupd);
			 			     		 ){
			 			      	
			 			      	res=stmt1.executeQuery();
			 			      	table.setModel(DbUtils.resultSetToTableModel(res));
			 			      	res.close();
			 			      } catch (SQLException e) {
			 					// TODO Auto-generated catch block
			 					System.err.println(e.getMessage());
			 					
			 					
			 				} 
			 			
			 			     setVisible(true);
			 		       }
			 		
			 		       }
			 		//ConnectionManager.getInstance().Close();
			 	}
			 });
			update.setBounds(39, 252, 102, 23);
			panel.add(update);
			
		    delete = new JButton("delete");
		    delete.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    delete.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		if (name.getText().equals("")||Role.getSelectedItem().equals(null)||office.getSelectedItem().equals(null)||id.getText().equals("")) {
			 			JOptionPane.showMessageDialog(null, " No user selected");
						
					}
			 		else{
					int userpick=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete", "massage box",JOptionPane.YES_NO_OPTION);
					if (userpick==JOptionPane.YES_OPTION) {
						//deleting process
						String sql="DELETE from staff where  staff_id=?";
				 		
				 		
				 	    
				 	    try(
				 	   		
				 	   		   PreparedStatement stmt=conn.prepareStatement(sql);
				 	   		 ){
				 	  	
				 			stmt.setString(1,id.getText());	
				 			
				 			
				 			
				 			int affected=stmt.executeUpdate();
				 			if (affected==1) {
				 				 setVisible(false);
				 				JOptionPane.showMessageDialog(null, " sucessfully deleted");
				 				//update table
				 				 String sqlupd="select staff_id,staff_name,staff_type,officeName from staff"
				 		        		+ " join office on staff.office=office.offId";
				 				
				 				ResultSet res=null;
				 			      
				 			     
				 			     		
				 			     		   PreparedStatement stmt1=conn.prepareStatement(sqlupd);
				 			     		
				 			      	
				 			      	res=stmt1.executeQuery();
				 			      	table.setModel(DbUtils.resultSetToTableModel(res));
				 			      	res.close();
				 			      
				 			
				 			} else {
				 				JOptionPane.showMessageDialog(null, "failed to update");
				 				 setVisible(false);
				 			}
				 	    	
				 			
				 			 setVisible(true);	
				 	    } catch (SQLException exp) {
				 			// TODO Auto-generated catch block
				 			System.err.println(exp.getMessage());
				 			JOptionPane.showMessageDialog(null, "Error");
				 			
				 			
				 		}
					
					}
					else{
						 setVisible(false);
						 setVisible(true);
					}
					}
		    	}
		    });
			delete.setBounds(151, 252, 103, 23);
//			delete.setEnabled(false);
//		    update.setEnabled(false);
			panel.add(delete);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						//ConnectionManager.getInstance().Close();
						
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		
			
			
		}
	}
	
}
