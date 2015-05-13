package controller;

import service.event.ControllerListener;
import service.event.ControllerRegister;
import view.LoginView;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

public class LoginController implements Controller {

	LoginView view = null;
	ControllerRegister authentificationListeners = new ControllerRegister();
	ControllerRegister newUserListeners = new ControllerRegister();

	public LoginController(LoginView login) {
		// this.view = new LoginView();
		this.view = login;
		registerListener();

	}

	private void registerListener() {
		this.view.getButton().addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				authentificationListeners.wakeUpListeners();
			}
		});
	}

	public void addAuthentificationListener(ControllerListener listener) {
		this.authentificationListeners.addListener(listener);
	}

	public void removeAuthentificationListener(ControllerListener listener) {
		this.authentificationListeners.removeListener(listener);
	}

	public void addNewUserListener(ControllerListener listener) {
		this.newUserListeners.addListener(listener);
	}

	public void removeNewUserListener(ControllerListener listener) {
		this.newUserListeners.removeListener(listener);
	}

	public String getEnteredPassword() {
		return view.getPassword();
	}

	public String getEnteredUsername() {
		return view.getUsername();
	}

	@Override
	public Component getView() {
		return this.view;
	}

}
