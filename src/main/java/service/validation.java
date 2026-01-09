package service;

import Exception.ValidationException;

public class validation {
	
	public void validationPhone(String phone) throws ValidationException {

	    if (phone == null || phone.isBlank()) {
	        throw new ValidationException("Phone number cannot be null or empty");
	    }

	   
	    if (!phone.matches("^[6-9][0-9]{9}$")) {
	        throw new ValidationException("Invalid phone number");
	    }
	}

	
	public void validationEmail(String email) throws ValidationException {
		if(email==null ||email.isBlank()||!email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")){
			throw new ValidationException("Invaild Email Id");
		}
	}
	public void validationPassword(String password) throws ValidationException {

	    if (password == null || password.isBlank()) {
	        throw new ValidationException("Password cannot be empty");
	    }

	    int length = password.length();

	    if (length < 8 || length > 12) {
	        throw new ValidationException(
	            "Password must be minimum 8 and maximum 12 characters"
	        );
	    }
	}

}
