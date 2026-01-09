package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Exception.DataAccessException;
import models.AppoinmentRequest;

public class BookAppoinmentDAO {
	
	public void BookAppoinment(AppoinmentRequest appoinment) {
		Connection con=DButils.getConnection();
		try {
			String quires="INSERT INTO appointments (user_id,reason,department,preferred_date) VALUES(?,?,?,?)";
			PreparedStatement statement=con.prepareStatement(quires);
			statement.setInt(1,appoinment.getUser_id());
			statement.setString(2, appoinment.getReason());
			statement.setString(3, appoinment.getDepartment());
			statement.setTimestamp(4, appoinment.getAppointmentDate());
			int row=statement.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	 public String getPatientNameById(int userId) throws DataAccessException {

	        String sql = "SELECT name FROM users WHERE user_id = ? AND role = 'PATIENT'";

	        try {
	             PreparedStatement ps = DButils.getConnection().prepareStatement(sql); 

	            ps.setInt(1, userId);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    return rs.getString("name");
	                }
	            }

	        } catch (SQLException e) {
	            throw new DataAccessException("Error fetching patient name");
	        }

	        return null;
	    }

}
