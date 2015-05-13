package com.example.mvnplance;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

import controller.MainController;

@SuppressWarnings("serial" )
@Theme("mvnplance" )
public class MvnplanceUI extends UI {

	@Override
	protected void init(VaadinRequest request) {
		MainController mainControl = new MainController(this);
		System.out.println("überlebt");

		// Button button = new Button("Click Me");
		// button.addClickListener(new Button.ClickListener() {
		// public void buttonClick(ClickEvent event) {
		// layout.addComponent(new Label("Thank you for clicking"));
		// }
		// });
		// layout.addComponent(button);
	}

}
