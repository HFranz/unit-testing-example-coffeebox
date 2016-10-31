package example.coffe.entity;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

public class CoffeBill {

	private Map<UUID, BigDecimal> accountsDues = Maps.newHashMap();

	public CoffeBill(Map<UUID, BigDecimal> accountsDues) {
		this.accountsDues = accountsDues;
	}

	public BigDecimal getSum(Account account) {
		return accountsDues.get(account.getId());
	}
	
}
