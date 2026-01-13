package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.AppoinmentRequest;
import models.AppoinmentRequest.Status;
import models.SignupRequest;

public class CompletedDAO {
	
	public List<AppoinmentRequest> completed(String dept){

		List<AppoinmentRequest> list=new ArrayList<>();
		
		
		Connection con=DButils.getConnection();
		try {
			String query =
			        "SELECT a.appointment_id, a.user_id, a.reason, a.preferred_date, a.status, u.name "
			      + "FROM appointments a "
			      + "JOIN users u ON a.user_id = u.user_id "
			      + "JOIN patient_history ph ON ph.user_id = a.user_id "
			      + "WHERE a.department = ? "
			      + "AND a.status = 'Completed' "
			      + "ORDER BY ph.updated_at DESC";
			
             PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1, dept);
			
			ResultSet rs= statement.executeQuery();
			while(rs.next()) {
				AppoinmentRequest appoinmentRequest=new AppoinmentRequest();
				appoinmentRequest.setAppoint_id(rs.getInt("appointment_id"));
				appoinmentRequest.setUser_id(rs.getInt("user_id"));
				appoinmentRequest.setName(rs.getString("name"));
				appoinmentRequest.setAppointmentDate(rs.getTimestamp("preferred_date"));
				appoinmentRequest.setReason(rs.getString("reason"));
				appoinmentRequest.setStatus(Status.valueOf(rs.getString("status")));
				
				list.add(appoinmentRequest);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return list;
	}

}
