package view.component;

import java.util.ArrayList;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class VerticalMenu extends VerticalLayout {

	/**
     *
     */
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
		ArrayList<Button> buttons = new ArrayList<Button>();

		buttons.add(new Button("First"));
		buttons.add(new Button("Second"));
		buttons.add(new Button("Third"));

		for (final Button button : buttons) {
			button.addClickListener(new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					Notification.show("i'm " + button.getCaption() + " Button");
				}
			});

			switch (button.getCaption()) {
				case "First": {
					button.setIcon(FontAwesome.USER);
				}
					break;
				case "Second": {
					button.setIcon(FontAwesome.CLOUD);
				}
					break;
				case "Third": {
					button.setIcon(FontAwesome.MAGIC);
				}
					break;

			}
			addComponent(button);
		}

	}

	private ArrayList<Button> buttons;

}