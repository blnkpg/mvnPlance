package view;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;

public class LoginView extends Window implements Slot {

	private static final long serialVersionUID = 1L;
	private TextField tf_username = null;
	private PasswordField tf_password = null;
	private String welcome = "Please enter your usercredentials to use this fancy tool.";

	private FormLayout formLayout = new FormLayout();

	private Button btn_login = null;

	public LoginView() {

		formLayout.setStyleName("loginform");

		this.tf_username = new TextField("username", "username");
		this.tf_username.setIcon(FontAwesome.USER);

		this.tf_password = new PasswordField("password");
		this.tf_password.setIcon(FontAwesome.LOCK);

		this.btn_login = new Button("Login", FontAwesome.HEART);
		this.init();
	}

	private void init() {
		formLayout.addComponent(new Label(this.welcome));
		formLayout.addComponent(this.tf_username);
		formLayout.addComponent(this.tf_password);
		formLayout.addComponent(this.btn_login);

		this.setContent(formLayout);
	}

	public String getUsername() {
		return this.tf_username.getValue();
	}

	public String getPassword() {
		return this.tf_password.getValue();
	}

	public Button getButton() {
		return this.btn_login;
	}

	@Override
	public Component getTab() {
		return new Label("Login");
	}

	@Override
	public Component getParagraph() {
		return this.formLayout;
	}

}
