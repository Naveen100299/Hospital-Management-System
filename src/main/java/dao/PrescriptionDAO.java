package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.PrescriptionRequest;

public class PrescriptionDAO {
	
	
	
	public PrescriptionRequest PatientPrescriptionDetails(int user_id) {
		String query="Select diagnosis,medicines,doctor_advice FROM patient_history WHERE user_id=?";
		PrescriptionRequest prescription=new PrescriptionRequest();
		try {
			PreparedStatement statement=DButils.getConnection().prepareStatement(query);
		    statement.setInt(1, user_id);
		   ResultSet rs= statement.executeQuery();
		   if(rs.next()) {
			   
			   prescription.setUser_id(user_id);
			   prescription.setDiagnosis(rs.getString("diagnosis"));
			   prescription.setMedicines(rs.getString("medicines"));
			   prescription.setDoctor_advice(rs.getString("doctor_advice"));
		   }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return prescription;
		
	}
	
	public  void PatientPrescription(PrescriptionRequest request) {
		Connection con=DButils.getConnection();
		try {
	String query="insert into patient_history (user_id,diagnosis,medicines,doctor_advice) values(?,?,?,?)as new on duplicate key update user_id=new.user_id,diagnosis=new.diagnosis,medicines=new.medicines,doctor_advice=new.doctor_advice";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setInt(1,request.getUser_id());
			statement.setString(2,request.getDiagnosis());
			statement.setString(3, request.getMedicines());
			statement.setString(4, request.getDoctor_advice());
			
			statement.executeUpdate();
			
			String query1="update appointments set status='Completed' WHERE appointment_id=?";
			
			PreparedStatement ps=con.prepareStatement(query1);
			ps.setInt(1,request.getAppointment_id());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
