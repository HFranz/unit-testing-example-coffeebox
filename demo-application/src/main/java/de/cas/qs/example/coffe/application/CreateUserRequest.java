package de.cas.qs.example.coffe.application;

public class CreateUserRequest {
	
	private final String name;

	public CreateUserRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
