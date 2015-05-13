package view;

import gui.ressource.RessourceHandler;
import view.component.HorizontalAccordion;

import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class StartView extends Window {

	private HorizontalAccordion horizon = new HorizontalAccordion();

	public StartView() {
		// this.setWidth(Page.getCurrent().getBrowserWindowWidth(), Unit.PIXELS);
		// this.setHeight((float) (Page.getCurrent().getBrowserWindowHeight() * 0.4), Unit.PIXELS);
		// this.setHeight(33, Unit.PERCENTAGE);
		this.setWidth(100, Unit.PERCENTAGE);
		this.setClosable(false);
		this.setResizable(false);
		this.setDraggable(false);
		this.center();
		this.setStyleName("transparentBG");

		// this.addStyleName(RessourceHandler.BACKGROUND_LOGIN);
		init();
	}

	public void init() {
		Image logo = new Image();
		logo.setSource(RessourceHandler.PLANCE);
		horizon.addSlot(logo, new Label(
				"<p>Plance is a fantastic tool</p> which offers you <b>every feature</b> you need to plan celebrations and your budget."));

		this.setContent(horizon);
	}

	public void addSlot(Slot slot) {
		this.horizon.addSlot(slot.getTab(), slot.getParagraph());
	}

	public void addSlot(Component tab , Component paragraph) {
		this.horizon.addSlot(tab, paragraph);
	}
}
