package example.coffe.boundary.response;

import java.util.Objects;

public class RegisterationUserResponse {

	private final String message;
	
	public RegisterationUserResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || obj.getClass() != RegisterationUserResponse.class)
			return false;
		
		RegisterationUserResponse other = (RegisterationUserResponse) obj;
		
		return Objects.equals(message, other.message);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(message);
	}
}
