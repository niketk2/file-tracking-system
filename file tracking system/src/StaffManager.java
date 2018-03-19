

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import Connection.ConnectionManager;
//import net.proteanit.sql.DbUtils;

public class StaffManager {
	private static Connection conn=ConnectionManager.getInstance().getConnection();
	private String staffId;
	private String staffName;
	private String depId;
	private String password;
	private int dept;
	private static String Sname;
	private static String Stype;
	
	private static String Sid;
	public static String getSid() {
		return Sid;
	}

	public static void setSid(String sid) {
		Sid = sid;
	}
	private static String office;
	
	public static String getOffice() {
		return office;
	}

	public static void setOffice(String office) {
		StaffManager.office = office;
	}

	public static String getStype() {
		return Stype;
	}

	public static void setStype(String stype) {
		Stype = stype;
	}

	public static String getSname() {
		return Sname;
	}

	public static void setSname(String sname) {
		Sname = sname;
	}

	public StaffManager(){}
	
	public  StaffManager(String staffId,String staffName,String deptId,String password)
	{
		this.staffId=staffId;
		this.staffName=staffName;
		this.depId=deptId;
		this.password=password;
	}

	public String getStaffId() {
		return staffId;
	}

	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	//public static String office(String Sid){
		
	//}
    public  boolean insert() throws SQLException
    {
    	String sql="insert into staff(staff_id,staff_name,staff_type,office,password)values(?,?,?,?,?)";
    	
    	 String sql_1="select * from office where officeName=?";
		 
		ResultSet rs_1=null;
		PreparedStatement stmt_1=conn.prepareStatement(sql_1);
		stmt_1.setString(1,getDepId());
		rs_1=stmt_1.executeQuery();
		if(rs_1.next())
		{
	     dept=rs_1.getInt("offId");
		}
		
    	try (

			    PreparedStatement  stmt=conn.prepareStatement(sql);  
		    )
		{  
			 stmt.setString(1,getStaffId());
			 stmt.setString(2,getStaffName());
			 stmt.setString(3,"staff");
			 stmt.setInt(4,dept);
			 stmt.setString(5,getPassword());
       
			int sta= stmt.executeUpdate();
			if(sta==1)
			{
				System.out.println("Table Person was inserted");
				return true;
			}
			else
			{
				System.out.println("query was not affected the row");
				return false;
			}
			 
		}
		catch(SQLException e)
		{
			System.err.println(e);
			return false;
		}
    }
    private int officeId(){
    	String sqlupd="select offId from office where officeName=?";
			
			ResultSet res=null;
		      
		      try(
		     		
		     		   PreparedStatement stmt1=conn.prepareStatement(sqlupd);
		     		 ){
		    	stmt1.setString(1,office);
		      	res=stmt1.executeQuery();
		      	if (res.next()) {
					return res.getInt("offId");
					
				}
		      	else{
		      		return 0;
		      	}
		      
		      } catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
				return 0;
				
			} 
		     
    }
    public  boolean updatestaff(){
    	String sql="update staff set staff_type=?,office=? where  staff_id=?";
 		
 		
 	    
 	    try(
 	   		
 	   		   PreparedStatement stmt=conn.prepareStatement(sql);
 	   		 ){
	  	stmt.setString(1,Stype);
		stmt.setInt(2,officeId());
	    stmt.setString(3,Sid);	
			
 			
 			
 			int affected=stmt.executeUpdate();
 			stmt.close();
 			if (affected==1) {
 				//ConnectionManager.getInstance().Close();
 				
 				JOptionPane.showMessageDialog(null, "update sucessfully");
    	return true;
    	
 			}
 			else {
 				JOptionPane.showMessageDialog(null, "failed to update");
               return false;
 			}
 	    	
 	    	
 	    	
 	    } catch (SQLException e) {
 			// TODO Auto-generated catch block
 			System.err.println(e.getMessage());
 			JOptionPane.showMessageDialog(null, "Error");
 			return false;
 			
 		}
    }
    public boolean deletestaff(){
    	return true;
    }
    public static String File_from(String destination){
    	String sqlupd="select *from fileoperation where file_to=? and"
    			+ " status='moving'";
		
		ResultSet res=null;
	      
	      try(
	     		
	     		   PreparedStatement stmt1=conn.prepareStatement(sqlupd);
	     		 ){
	    	stmt1.setString(1,destination);
	      	res=stmt1.executeQuery();
	      	if (res.next()) {
				return res.getString("file_from");
				
			}
	      	else{
	      		return null;
	      	}
	      
	      } catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			return null;
			
		} 
    	
    	
    }
}

