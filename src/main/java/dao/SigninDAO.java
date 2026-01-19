package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Exception.DataAccessException;
import models.SigninRequest;
import models.SignupRequest;
import models.SignupRequest.Department;
import models.SignupRequest.Role;
import service.validation;

public class SigninDAO {

    public SignupRequest signin(SigninRequest signinRequest) throws DataAccessException {

        validation validation = new validation();

        try {
            validation.validationEmail(signinRequest.getEmail());
            validation.validationPassword(signinRequest.getPassword());

            Connection con = DButils.getConnection();

            String query = "SELECT user_id, name, role, department " +
                           "FROM users WHERE email=? AND password=?";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, signinRequest.getEmail());
            statement.setString(2, signinRequest.getPassword());

            ResultSet rs = statement.executeQuery();

            
            if (!rs.next()) {
                throw new DataAccessException("Invalid email or password");
            }

            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setUser_id(rs.getInt("user_id"));
            signupRequest.setName(rs.getString("name"));

            String roleStr = rs.getString("role");
            if (roleStr != null) {
                signupRequest.setRole(Role.valueOf(roleStr));
            }

            String deptStr = rs.getString("department");
            if (deptStr != null) {
            	
                signupRequest.setDepartment(Department.valueOf(deptStr));
            }
         
            return signupRequest;

        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw new DataAccessException(e.getMessage());
        }
    }
}
