package de.cas.qs.example.coffe;

import java.util.UUID;

public class Account {

	private String name;
	private UUID id;
	
	public Account(UUID id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public UUID getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
