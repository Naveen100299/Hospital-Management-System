package dao;

import Exception.DataAccessException;
import Exception.ValidationException;
import models.SignupRequest;

public interface Patient {
	public int Signup(SignupRequest user) throws ValidationException, DataAccessException;

}
