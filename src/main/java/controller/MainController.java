package controller;

import service.MainService;
import service.event.ControllerListener;
import view.LoginView;
import view.NewUserView;
import view.StartView;

import com.vaadin.ui.Component;
import com.vaadin.ui.UI;

public class MainController implements Controller {

	private UI gui = null;
	private MainService mainService = null;
	private LoginController login = null;
	private NewUserController newUserController = null;

	public MainController(UI mvnplanceUI) {
		this.gui = mvnplanceUI;
		this.mainService = new MainService();
		doLogin();
	}

	private boolean doLogin() {
		LoginView loginView = new LoginView();
		this.login = new LoginController(loginView);

		NewUserView newUser = new NewUserView();
		this.newUserController = new NewUserController(newUser);

		StartView startview = new StartView();
		startview.addSlot(loginView);
		startview.addSlot(newUser);

		// this.gui.addWindow((Window) startview);

		registerLoginListener();
		registerNewUserlistener();
		// this.gui.setContent(this.login.getView());

		// final BaseLayout layout = new BaseLayout();
		// this.gui.setContent(layout);
		return false;
	}

	@Override
	public Component getView() {
		// TODO Auto-generated method stub
		return this.gui;
	}

	private void registerLoginListener() {
		this.login.addAuthentificationListener(new ControllerListener() {

			@Override
			public void wakeUP() {
				mainService.authenticate(login.getEnteredUsername(), login.getEnteredPassword());
			}
		});

		this.login.addNewUserListener(new ControllerListener() {

			@Override
			public void wakeUP() {
				// TODO Auto-generated method stub

			}
		});
	}

	private void registerNewUserlistener() {
		this.newUserController.addNewUserListener(new ControllerListener() {

			@Override
			public void wakeUP() {
				mainService.signUp(newUserController.getUsername(), newUserController.getName(), newUserController.getLastName(), newUserController.getEmail(),
						newUserController.getPassword());
			}
		});

	}

	public void signUPTest() {
		mainService.signUp("Testuser", "testvorname", "testnachname", "testemail", "testpasswort");
	}
}
