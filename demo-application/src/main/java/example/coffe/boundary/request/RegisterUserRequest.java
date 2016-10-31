package example.coffe.boundary.request;

public class RegisterUserRequest {
	
	private final String name;

	public RegisterUserRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
