package dao;

import Exception.DataAccessException;
import Exception.ValidationException;
import models.SignupRequest;

public interface Patient {
	public int Sigup(SignupRequest user,String role) throws ValidationException, DataAccessException;

}
