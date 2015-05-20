package controller;

import gui.ressource.RessourceHandler;
import service.MainService;
import service.event.ControllerListener;
import view.LoginView;
import view.NewUserView;
import view.StartView;
import view.component.BaseLayout;

import com.vaadin.server.Page;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Video;
import com.vaadin.ui.Window;

public class MainController implements Controller {

	private UI gui = null;
	private MainService mainService = null;
	private LoginController login = null;
	private NewUserController newUserController = null;
	private BaseLayout baseLayout = null;
	private StartView startview = new StartView();

	public MainController(UI mvnplanceUI) {
		this.gui = mvnplanceUI;
		this.mainService = new MainService();
		final Video v = new Video("video");
		v.setStyleName("video_background");
		v.setSource(RessourceHandler.VIDEO);

		v.setSizeFull();
		v.setAutoplay(true);
		final Embedded bgVideo = new Embedded();
		bgVideo.setSource(RessourceHandler.BACKGROUNDVID);
		bgVideo.setType(Embedded.TYPE_BROWSER);
		bgVideo.setWidth(100, Unit.PERCENTAGE);
		bgVideo.setHeight(100, Unit.PERCENTAGE);

		// this.gui.setContent(v);
		this.gui.setContent(bgVideo);
		openStartview();
	}

	@Override
	public Component getView() {
		return this.gui;
	}

	private void registerLoginListener() {
		this.login.addAuthentificationListener(new ControllerListener() {

			@Override
			public void wakeUP() {
				if (mainService.authenticate(login.getEnteredUsername(), login.getEnteredPassword())) {
					closeWindows();
					gui.setContent(baseLayout = new BaseLayout());
				}
			}
		});

	}

	private void registerNewUserlistener() {
		this.newUserController.addNewUserListener(new ControllerListener() {

			@Override
			public void wakeUP() {
				closeWindows();
				Window registerPopUp = new Window();
				registerPopUp.center();
				registerPopUp.setClosable(false);
				registerPopUp.setResizable(false);

				VerticalLayout informations = new VerticalLayout();
				informations.addComponent(new Label("Register the following usercredentials:"));
				informations.addComponent(new Label("Username: " + newUserController.getUsername()));
				informations.addComponent(new Label("Email: " + newUserController.getEmail()));
				informations.addComponent(new Label("Password: ;) <3"));
				informations.addComponent(new Label("Name: " + newUserController.getName() + " " + newUserController.getLastName()));

				registerPopUp.setWidth((float) (Page.getCurrent().getBrowserWindowWidth() / 3), Unit.PIXELS);
				registerPopUp.setHeight((float) (Page.getCurrent().getBrowserWindowHeight() / 3), Unit.PIXELS);
				registerPopUp.setContent(informations);
				gui.addWindow(registerPopUp);
				if (mainService.signUp(newUserController.getUsername(), newUserController.getName(), newUserController.getLastName(),
						newUserController.getEmail(), newUserController.getPassword())) {
					registerPopUp.close();
					gui.setContent(baseLayout = new BaseLayout());
				} else {
					openStartview();
				}
			}
		});

	}

	private void openStartview() {
		LoginView loginView = new LoginView();
		this.login = new LoginController(loginView);

		NewUserView newUser = new NewUserView();
		this.newUserController = new NewUserController(newUser);

		registerLoginListener();
		registerNewUserlistener();

		startview.addSlot(newUser);
		startview.addSlot(loginView);

		this.gui.addWindow((Window) startview);

	}

	private void closeWindows() {
		for (Window win : gui.getWindows()) {
			win.close();
		}

	}
}
