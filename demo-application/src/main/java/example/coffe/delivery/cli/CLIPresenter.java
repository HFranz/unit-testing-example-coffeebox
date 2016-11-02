package example.coffe.delivery.cli;

import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.response.RegisterationUserResponse;

public class CLIPresenter implements OutputBoundary<RegisterationUserResponse> {

	private String viewModel;
	
	@Override
	public void output(RegisterationUserResponse response) {
		viewModel = "Response: " + response.getMessage();
	}
	
	public String getViewModel() {
		return viewModel;
	}

}
