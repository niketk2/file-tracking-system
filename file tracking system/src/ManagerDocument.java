import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JOptionPane;

import Connection.ConnectionManager;

public class ManagerDocument {

	
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private String barcode;
	private String document;
	private String staff;
	private Date d_created;
	public ManagerDocument(String barcode, String document, String staff) {
		this.barcode = barcode;
		this.document = document;
		this.staff = staff;
		
	}
	public boolean InsertDocumenttoFile() throws SQLException{
		d_created=new Date();
		java.sql.Date sqldate=new java.sql.Date(d_created.getTime());
		String sql="insert into document(document_name,file_id,d_belong_staff,date_modified)"
				+ " values(?,?,?,?)";
		
		
	      
	      try(
	     		
	     		   PreparedStatement stmt=conn.prepareStatement(sql);
	     		 ){
	    	stmt.setString(1,document);
	    	stmt.setString(2,barcodeget());
	  		
	  		stmt.setString(3,staff);
			
	  		stmt.setDate(4,sqldate);
	  		
	  		int affected=stmt.executeUpdate();
	  		stmt.close();
	  		if (affected==1) {
	  			JOptionPane.showMessageDialog(null, "one row inserted");
	  			return true;
	  		} else {
	  			JOptionPane.showMessageDialog(null, "Error");
	  			return false;
	  		}
	      	
	      	
	      	
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
	    	  JOptionPane.showMessageDialog(null, "The is no staff with that id");
	    	  
	    	  return false;
			//System.err.println(e.getMessage());
			//JOptionPane.showMessageDialog(null, "Error"+sid);
	    	  
			
		}
	      
	}

public String barcodeget() throws SQLException{
	
	
	String sql="SELECT *FROM file WHERE file_name=?";
	
	ResultSet rs=null;
      
      try(
     		
     		   PreparedStatement stmt=conn.prepareStatement(sql);
     		 ){
    	stmt.setString(1,barcode);
    	//stmt.setString(2,filename);
  		
  		
  		rs=stmt.executeQuery();
  		while(rs.next()){
  			//setModel(DbUtils.resultSetToTableModel(rs));
  			return rs.getString("barcode");
  		}
      	
      	
      } catch (SQLException e) {
		// TODO Auto-generated catch block
		System.err.println(e.getMessage());
		JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		return null; 
		
	}
      finally{
    	  rs.close();
      }
      return null; 
}
}
