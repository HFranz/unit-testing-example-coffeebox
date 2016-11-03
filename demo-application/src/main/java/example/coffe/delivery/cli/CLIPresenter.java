package example.coffe.delivery.cli;

import example.coffe.boundary.OutputBoundary;
import example.coffe.boundary.response.RegisterationUserResponse;

public class CLIPresenter implements OutputBoundary<RegisterationUserResponse> {

	private String viewModel;
	
	@Override
	public void output(RegisterationUserResponse response) {
		if (response.isSuccessful() && response.getAccount().isPresent())
			viewModel = "Successfully created account with id " + response.getAccount().get().getId();
		else
			viewModel = "Failed to create account";
	}
	
	public String getViewModel() {
		return viewModel;
	}

}
