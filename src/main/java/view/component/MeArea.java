package view.component;

import gui.ressource.CPTPlaceholder;

import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.VerticalLayout;

public class MeArea extends VerticalLayout {

	private static final long serialVersionUID;
	static {
		serialVersionUID = -1508291768897794634L;
	}
	// initialize root & other components
	{
		initRoot();
		initComponents();
	}

	private void initRoot() {
		setStyleName("vertical-menu");
	}

	private void initComponents() {
		initButtons();
	}

	private void initButtons() {
		ContentContainer container = new ContentContainer("{Userid}");

		float placeholderHeight = ((Page.getCurrent().getBrowserWindowHeight() / 2) - (container.getHeight() / 2));
		// addComponent(new Label(String.valueOf(placeholderHeight)));

		CPTPlaceholder cptPlaceHolder = new CPTPlaceholder(70, placeholderHeight, Unit.PIXELS);
		addComponent(cptPlaceHolder);
		addComponent(container);
		this.setComponentAlignment(cptPlaceHolder, Alignment.MIDDLE_CENTER);
		this.setComponentAlignment(container, Alignment.MIDDLE_CENTER);

	}

}
