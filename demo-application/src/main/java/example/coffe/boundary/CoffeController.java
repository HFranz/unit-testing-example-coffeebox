package example.coffe.boundary;

import example.coffe.boundary.request.BuyCoffeRequest;

public interface CoffeController {

	void handle(BuyCoffeRequest request);
	
}
