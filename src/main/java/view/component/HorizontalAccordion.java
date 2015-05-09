package view.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;

public class HorizontalAccordion extends Panel {

	private static final long serialVersionUID = 1L;

	private HorizontalLayout panel = new HorizontalLayout();

	public HorizontalAccordion() {
		this.setStyleName("container");
		panel.setStyleName("accordion");
		this.setContent(this.panel);
	}

	public void addSlot(Component tab , Component paragraph) {

		HorizontalLayout subTabPanel = new HorizontalLayout();
		HorizontalLayout subParaPanel = new HorizontalLayout();
		subTabPanel.setStyleName("tabs");
		// tab.setStyleName("tab");
		if (tab instanceof Label) {
			tab.addStyleName("textTabs");
		} else if (tab instanceof Image) {
			tab.setWidth(80.f, Unit.PIXELS);
			tab.setHeight(80.f, Unit.PIXELS);
		}

		subTabPanel.addComponent(tab);
		subTabPanel.setComponentAlignment(tab, Alignment.MIDDLE_CENTER);
		subParaPanel.addComponent(paragraph);
		subParaPanel.setComponentAlignment(paragraph, Alignment.MIDDLE_CENTER);

		// subParaPanel.setWidth((float) (subParaPanel.getWidth()), subParaPanel.getWidthUnits());
		subParaPanel.setStyleName("paragraph");
		subTabPanel.addComponent(subParaPanel);
		this.panel.addComponent(subTabPanel);
	}
}
