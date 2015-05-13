package view;

import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class NewUserView extends Window implements Slot {

	private TextField tf_name = null;
	private TextField tf_lastname = null;
	private TextField tf_username = null;
	private TextField tf_email = null;
	private TextField tf_email_repeat = null;
	private PasswordField tf_password = null;
	private PasswordField tf_password_repeat = null;
	private Button bt_register = null;
	private FormLayout formLayout = new FormLayout();

	public NewUserView() {
		formLayout.setStyleName("loginform");

		createElements();
		addElements();
	}

	private void createElements() {
		this.tf_username = new TextField("Benutzername");
		this.tf_name = new TextField("Vorname");
		this.tf_lastname = new TextField("Nachname");
		this.tf_email = new TextField("E-Mailadresse");
		this.tf_email_repeat = new TextField("Wiederholung Email-Adresse");
		this.tf_password = new PasswordField("Passwort");
		this.tf_password_repeat = new PasswordField("Wiederholung Passwort");
		this.bt_register = new Button("Sign up");
	}

	private void addElements() {
		this.formLayout.addComponent(this.tf_username);
		this.formLayout.addComponent(this.tf_name);
		this.formLayout.addComponent(this.tf_lastname);
		this.formLayout.addComponent(this.tf_email);
		this.formLayout.addComponent(this.tf_email_repeat);
		this.formLayout.addComponent(this.tf_password);
		this.formLayout.addComponent(this.tf_password_repeat);
		this.formLayout.addComponent(this.bt_register);
	}

	@Override
	public Component getTab() {
		return new Label("Sign up");
	}

	@Override
	public Component getParagraph() {
		return formLayout;
	}

	public Button getButton() {
		return this.bt_register;
	}

	public boolean passwordMatches() {
		return matches(this.tf_password.getValue(), this.tf_password_repeat.getValue());
	}

	public boolean emailMatches() {
		return matches(this.tf_email.getValue(), this.tf_email_repeat.getValue());
	}

	private boolean matches(String first , String second) {
		return first.equals(second);
	}

	public String getUsername() {
		return this.tf_username.getValue();
	}

	public String getPassword() {
		return this.tf_password.getValue();
	}

	public String getEmail() {
		return this.tf_email.getValue();
	}

	public String getName() {
		return this.tf_name.getValue();
	}

	public String getLastName() {
		return this.tf_lastname.getValue();
	}

}
