package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import models.AppoinmentRequest;
import models.AppoinmentRequest.Status;

public class ViewAppoinmentDAO {
	
	public List<AppoinmentRequest> viewAppointment(int patientId){
		List<AppoinmentRequest> list=new ArrayList<>();
		Connection con=DButils.getConnection();
		try {
			String queries;
			
			queries="SELECT * FROM appointments WHERE user_id=? AND preferred_date >= CURDATE() ORDER BY preferred_date ASC";
			PreparedStatement statement=con.prepareStatement(queries);
			statement.setInt(1, patientId);
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				AppoinmentRequest appoinmentRequest=new AppoinmentRequest();
				appoinmentRequest.setUser_id(patientId);
				appoinmentRequest.setReason(rs.getString("reason"));
				appoinmentRequest.setDepartment(rs.getString("department"));
				appoinmentRequest.setAppointmentDate(rs.getTimestamp("preferred_date"));
				list.add(appoinmentRequest);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<AppoinmentRequest> patientHistory(int userId){
		List<AppoinmentRequest> list=new ArrayList<>();
		String query = "SELECT user_id, preferred_date, department, reason, status "
		        + "FROM appointments "
		        + "WHERE user_id = ? AND preferred_date < CURDATE() "
		        + "ORDER BY preferred_date DESC";

		
		try {
			PreparedStatement statement=DButils.getConnection().prepareStatement(query);
		    statement.setInt(1, userId);
		    ResultSet rs=statement.executeQuery();
		    while(rs.next()) {
		    	AppoinmentRequest ap=new AppoinmentRequest();
		    	
		    	System.out.println(rs.getInt("user_id")+" this class");
		    	System.out.println(rs.getString("reason")+" : ");
		    	
		    	ap.setUser_id(rs.getInt("user_id"));
		    	ap.setAppointmentDate(rs.getTimestamp("preferred_date"));
		    	ap.setDepartment(rs.getString("department"));
		    	ap.setReason(rs.getString("reason"));
		    	ap.setStatus(Status.valueOf(rs.getString("status")));
		    	list.add(ap);
		    }
		    
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		
		return list;
		
	}

	
}
