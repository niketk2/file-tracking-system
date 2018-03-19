package Connection;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ConnectionManager{
private static ConnectionManager Instance=null;

private static final String USER="root";
private static final String PASSWORD="madega";
private static final String SERVER="jdbc:mysql://localhost/file_tracking";

private DBtype dbtype=DBtype.MYSQL;
private Connection conn=null;

private ConnectionManager(){
	
}
 
public static ConnectionManager getInstance(){
	if (Instance==null) {
		Instance=new ConnectionManager();
		
	}
	return Instance;
}

public void setDbtype(DBtype dbtype){
	this.dbtype=dbtype;
}
private boolean openConnection(){
	try {
		switch (dbtype) {
		case MYSQL:
			conn=DriverManager.getConnection(SERVER,USER, PASSWORD );
			return true;

		default:
			System.out.println("no databse selected");
			return false;
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		System.err.println(e);
		return false;
	}
}
public Connection getConnection(){
	if (conn==null) {
		if(openConnection()){
			
			System.out.println();
			return conn;
		}
		else {
			return null;
		}
		
	}
	return conn;
}
public void Close(){
	//
	JOptionPane.showMessageDialog(null,"Closed connection" );
	
	try {
		conn.close();
		conn=null;
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		
	}
	
}

}



