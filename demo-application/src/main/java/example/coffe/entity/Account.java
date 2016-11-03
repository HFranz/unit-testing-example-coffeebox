package example.coffe.entity;

import java.util.Objects;
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		
		if (obj == null || obj.getClass() != Account.class)
			return false;
		
		Account other = (Account) obj;
		
		return Objects.equals(other.id, id) && Objects.equals(other.name, name);
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", id=" + id + "]";
	}
	
	
}
