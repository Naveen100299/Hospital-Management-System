package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.AppoinmentRequest;
import models.AppoinmentRequest.Status;

public class AppoinmentDAO {
	 
	public List<AppoinmentRequest> viewAppointment(String  dept){
		System.out.println(dept);
		List<AppoinmentRequest> list=new ArrayList<>();
		Connection con=DButils.getConnection();
		try {
			String query =
				    "SELECT a.appointment_id, a.user_id, u.name,a.reason, a.status " +
				    "FROM appointments a " +
				    "JOIN users u ON a.user_id = u.user_id " +
				    "WHERE u.role = 'PATIENT' " +
				    "AND a.department = ? " +
				    "AND a.preferred_date = CURDATE() " +
				    "AND a.status='Pending'"+
				    "ORDER BY a.preferred_date ASC";

			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1, dept);
			ResultSet rs=statement.executeQuery();
			while(rs.next()) {
				AppoinmentRequest appoinmentRequest =new AppoinmentRequest();
				appoinmentRequest.setUser_id(rs.getInt("user_id"));
				appoinmentRequest.setAppoint_id(rs.getInt("appointment_id"));
				appoinmentRequest.setName(rs.getString("name"));
				appoinmentRequest.setReason(rs.getString("reason"));
				appoinmentRequest.setStatus(Status.valueOf(rs.getString("status")));
	
				list.add(appoinmentRequest);

			}

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	public List<AppoinmentRequest> viewTodayAppointments() {

	    List<AppoinmentRequest> list = new ArrayList<>();

	    String query =
	        "SELECT a.appointment_id, a.user_id, u.name, a.reason, a.status, a.preferred_date " +
	        "FROM appointments a " +
	        "JOIN users u ON a.user_id = u.user_id " +
	        "WHERE u.role = 'PATIENT' " +
	        "AND a.created_at >= CURDATE() " +
	        "AND a.created_at < CURDATE() + INTERVAL 1 DAY " +
	        "ORDER BY a.preferred_date DESC";

	    try (Connection con = DButils.getConnection();
	         PreparedStatement ps = con.prepareStatement(query);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            AppoinmentRequest ar = new AppoinmentRequest();
	            ar.setAppoint_id(rs.getInt("appointment_id"));
	            ar.setUser_id(rs.getInt("user_id"));
	            ar.setName(rs.getString("name"));
	            ar.setReason(rs.getString("reason"));
	            ar.setStatus(Status.valueOf(rs.getString("status")));
	            ar.setAppointmentDate(rs.getTimestamp("preferred_date"));

	            list.add(ar);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}

}
