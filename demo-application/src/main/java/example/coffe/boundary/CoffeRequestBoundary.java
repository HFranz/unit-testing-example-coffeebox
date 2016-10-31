package example.coffe.boundary;

import example.coffe.boundary.request.BuyCoffeRequest;

public interface CoffeRequestBoundary {

	void handle(BuyCoffeRequest request);
	
}
