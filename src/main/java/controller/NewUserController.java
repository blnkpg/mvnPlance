package controller;

import service.event.ControllerListener;
import service.event.ControllerRegister;
import view.NewUserView;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;

public class NewUserController implements Controller {

	NewUserView gui = null;
	ControllerRegister newUserRegister = new ControllerRegister();

	public NewUserController(NewUserView newUser) {
		this.gui = newUser;
	}

	@Override
	public Component getView() {
		return this.gui;
	}

	public void addNewUserListener(ControllerListener listener) {
		this.newUserRegister.addListener(listener);
	}

	public void registerListeners() {
		this.gui.getButton().addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				System.out.println("fotze");
				if (!gui.emailMatches()) {

				} else if (!gui.passwordMatches()) {

				} else if (gui.getEmail().equals("")) {

				} else if (gui.getPassword().equals("")) {

				} else if (gui.getUsername().equals("")) {

				} else {
					newUserRegister.wakeUpListeners();
				}
			}
		});
	}

	public String getUsername() {
		return this.gui.getUsername();
	}

	public String getPassword() {
		return this.gui.getPassword();
	}

	public String getEmail() {
		return this.gui.getEmail();
	}

	public String getName() {
		return this.gui.getName();
	}

	public String getLastName() {
		return this.gui.getLastName();
	}
}
