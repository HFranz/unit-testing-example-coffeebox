package de.cas.qs.example.coffe.application;

import java.util.Objects;

public class UserRegisterationResponse {

	private final String message;
	
	public UserRegisterationResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || obj.getClass() != UserRegisterationResponse.class)
			return false;
		
		UserRegisterationResponse other = (UserRegisterationResponse) obj;
		
		return Objects.equals(message, other.message);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(message);
	}
}
