package example.coffe.boundary.response;

import java.util.Objects;
import java.util.Optional;

import example.coffe.entity.Account;

public class RegisterationUserResponse {

	private final Optional<Account> account;
	private final boolean successful;
	
	public RegisterationUserResponse(boolean successful, Account account) {
		this.successful = successful;
		this.account = Optional.of(account);
	}
	
	public RegisterationUserResponse(boolean successful) {
		this.successful = successful;
		this.account = Optional.empty();
	}

	public Optional<Account> getAccount() {
		return account;
	}

	public boolean isSuccessful() {
		return successful;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || obj.getClass() != RegisterationUserResponse.class)
			return false;
		
		RegisterationUserResponse other = (RegisterationUserResponse) obj;
		
		return Objects.equals(successful, other.successful) && Objects.equals(account, other.account);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(successful, account);
	}

	@Override
	public String toString() {
		return "RegisterationUserResponse [account=" + account + ", successful=" + successful + "]";
	}
}
