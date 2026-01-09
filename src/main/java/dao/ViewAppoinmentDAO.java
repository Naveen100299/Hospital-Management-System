package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.AppoinmentRequest;

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

	
}
