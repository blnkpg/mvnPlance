package controller;

import service.event.ControllerListener;
import service.event.ControllerRegister;
import view.NewUserView;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;

public class NewUserController implements Controller {

	NewUserView gui = null;
	ControllerRegister newUserRegister = new ControllerRegister();

	public NewUserController(NewUserView newUser) {
		this.gui = newUser;
		registerListeners();
	}

	@Override
	public Component getView() {
		return this.gui;

	}

	public void addNewUserListener(ControllerListener listener) {
		this.newUserRegister.addListener(listener);
	}

	public void registerListeners() {
		this.gui.getButton().addClickListener(new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				event.getButton().setCaption("Event Fired");
				event.getButton().requestRepaint();

				if (!gui.emailMatches()) {
					event.getButton().setCaption("Emailaddresses don#t match!");
					event.getButton().requestRepaint();
				} else if (!gui.passwordMatches()) {
					event.getButton().setCaption("Passwords donÄt match!");
					event.getButton().requestRepaint();
				} else if (gui.getEmail().equals("")) {
					event.getButton().setCaption("Emailaddresse is empty!");
					event.getButton().requestRepaint();
				} else if (gui.getPassword().equals("")) {
					event.getButton().setCaption("Password is empty!");
					event.getButton().requestRepaint();
				} else {
					event.getButton().setCaption("I register.... hopefully");
					event.getButton().requestRepaint();
					newUserRegister.wakeUpListeners();
					gui.close();
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
