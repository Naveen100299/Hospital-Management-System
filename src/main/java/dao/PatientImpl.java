package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import javax.management.relation.Role;

import Exception.DataAccessException;
import Exception.ValidationException;
import models.SignupRequest;
import service.validation;

public class PatientImpl implements Patient {

	@Override
	public int Sigup(SignupRequest user, String role)
	        throws ValidationException, DataAccessException {

	    Connection con = DButils.getConnection();

	    try {
	        System.out.println(role + ":dao");

	        validation val = new validation();

	        
	        if ("RECEPTIONIST".equals(role)) {

	            if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
	                val.validationEmail(user.getEmail());
	            }

	        } else {
	            val.validationEmail(user.getEmail());
	            val.validationPassword(user.getPassword());
	        }
	        val.validationPhone(user.getPhone());

	        String query =
	            "INSERT INTO users (role,name,dob,gender,phone,email,password,address) " +
	            "VALUES (?,?,?,?,?,?,?,?)";

	        PreparedStatement statement = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

	       
	        statement.setString(1,"PATIENT");

	      
	        statement.setString(2, user.getName());
	        statement.setString(3, user.getDob());
	        statement.setString(4, user.getGender());
	        statement.setString(5, user.getPhone());

	       
	        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
	            statement.setNull(6, java.sql.Types.VARCHAR);
	        } else {
	            statement.setString(6, user.getEmail());
	        }

	        
	        if ("RECEPTIONIST".equals(role)) {
	            statement.setNull(7, java.sql.Types.VARCHAR);
	        } else {
	            statement.setString(7, user.getPassword());
	        }

	        
	        statement.setString(8, user.getAddress());

	        statement.executeUpdate();
	        ResultSet rs = statement.getGeneratedKeys();
	        if (rs.next()) {
	            int userId = rs.getInt(1);
	            return userId;
	        }

	    } catch (SQLIntegrityConstraintViolationException e) {
	        throw new DataAccessException("Email already exists");
	    } catch (SQLException e) {
	        throw new DataAccessException("Database error");
	    }
		return 0;
	}

	
	
	

}
