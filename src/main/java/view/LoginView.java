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
	private Button newAccount = null;

	public LoginView() {

		formLayout.setStyleName("loginform");

		this.tf_username = new TextField("username", "username");
		// this.tf_username.setValue("username");
		this.tf_username.setIcon(FontAwesome.USER);

		this.tf_password = new PasswordField("password");
		this.tf_password.setIcon(FontAwesome.LOCK);

		this.btn_login = new Button("Login", FontAwesome.HEART);
		this.newAccount = new Button();
		this.newAccount.setStyleName("v-button-link");
		this.newAccount.setCaption("Create an account ");
		this.newAccount.setIcon(FontAwesome.HEART_O);
		this.init();
	}

	private void init() {
		formLayout.addComponent(new Label(this.welcome));
		formLayout.addComponent(this.tf_username);
		formLayout.addComponent(this.tf_password);
		formLayout.addComponent(this.btn_login);
		formLayout.addComponent(this.newAccount);

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

	public Button getNewAccount() {
		return this.newAccount;
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
