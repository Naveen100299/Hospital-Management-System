package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
