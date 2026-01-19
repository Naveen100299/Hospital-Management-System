package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.CountRequst;

public class CountDao {
	
	public  CountRequst viewcount(String deptStr ){
		CountRequst countRequst=new CountRequst();
		
    		// pending count
    		 
			try {
				String query ="SELECT COUNT(*) as count from appointments WHERE department=? AND status='Pending' AND preferred_date= CURDATE()";
	    		 PreparedStatement statement = DButils.getConnection().prepareStatement(query);
				 statement.setString(1,deptStr);
		    		ResultSet rs= statement.executeQuery();
		    		rs.next();
		    		int pendingCount=rs.getInt("count");
		    		 
		    		// Completed count
		    		
		    		String query1 = "SELECT COUNT(*) as count from appointments WHERE department=? AND status='Completed'"
		     		 		+ " AND preferred_date= CURDATE()";
		     		 PreparedStatement statement1=DButils.getConnection().prepareStatement(query1);
		     		statement1.setString(1,deptStr);
		     		ResultSet rs1= statement1.executeQuery();
		    		rs1.next();
		     		int CompletedCount=rs1.getInt("count");
		    	
		     		countRequst.setPendingCount(pendingCount);
		     		countRequst.setCompletedCount(CompletedCount);
			
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
    		
			return countRequst;
    		
    		
    		
	}
	}


