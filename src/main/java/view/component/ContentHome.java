package view.component;

import gui.ressource.CPTPlaceholder;
import gui.ressource.RessourceHandler;

import java.util.ArrayList;

import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContentHome extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -493677085539318585L;
	private int pageWidth = 0;
	private int pageHeight = 0;

	public ContentHome() {
		this.pageWidth = Page.getCurrent().getBrowserWindowWidth();
		this.pageHeight = Page.getCurrent().getBrowserWindowHeight();
		this.setHeight(100, Unit.PERCENTAGE);

		Label titleInvites = new Label("Friends & Invites");
		titleInvites.setStyleName(RessourceHandler.TITEL_INVITATION);

		Label titleUpcoming = new Label("Upcoming");
		titleUpcoming.setStyleName(RessourceHandler.TITEL_UPCOMING);

		Image img = new Image();
		img.setSource(RessourceHandler.PLANCE);
		// HorizontalLayout secondRow = createRow(RessourceHandler.BACKGROUND_UPCOMING, createVerticalLayout(new Component[] { titleUpcoming,
		// createHorizontalLayout(createComponents(img)) }));
		// secondRow.setStyleName(RessourceHandler.BACKGROUND_UPCOMING);
		// addComponent(secondRow);
		MainScreenRow first = new MainScreenRow(createComponents(img), RessourceHandler.BACKGROUND_UPCOMING, 40);
		addComponent(first);

		// HorizontalLayout firstRow = createRow(RessourceHandler.BACKGROUND_INVITATION, createVerticalLayout(new Component[] { titleInvites,
		// createHorizontalLayout(createComponents(null)) }));
		// firstRow.setStyleName(RessourceHandler.BACKGROUND_INVITATION);
		// addComponent(firstRow);

		MainScreenRow second = new MainScreenRow(createComponents(null), RessourceHandler.BACKGROUND_INVITATION, 40);
		addComponent(second);

		// HorizontalLayout thirdMenu = createHorizontalLayout(createThirdRow());
		// VerticalLayout thirdRow = createVerticalLayout(new Component[] { new Label(""), thirdMenu });
		// thirdRow.setComponentAlignment(thirdMenu, Alignment.BOTTOM_CENTER);
		// addComponent(thirdRow);

	}

	private HorizontalLayout createRow(String cssClass , Component verticalLayout) {
		HorizontalLayout hLayout = new HorizontalLayout();
		hLayout.addComponent(createCanvas(cssClass));
		hLayout.addComponent(verticalLayout);
		return hLayout;
	}

	private VerticalLayout createVerticalLayout(Component[] components) {
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.addComponent(components[0]);
		vLayout.setComponentAlignment(components[0], Alignment.TOP_LEFT);
		// vLayout.addComponent(new CPTPlaceholder(0, 30, Unit.PIXELS));
		vLayout.addComponent(components[1]);
		vLayout.setComponentAlignment(components[0], Alignment.BOTTOM_LEFT);
		return vLayout;
	}

	private HorizontalLayout createHorizontalLayout(ArrayList<Component> components) {
		int i = (int) (Page.getCurrent().getBrowserWindowWidth() / ContentContainer.WIDTH);
		HorizontalLayout hLayout = new HorizontalLayout();
		for (Component component : components) {
			i--;
			if (i > 0) {
				hLayout.addComponent(component);
			} else {
				hLayout.addComponent(new ContentContainer("Add New"));
				break;
			}

		}
		return hLayout;
	}

	private ArrayList<Component> createComponents(Image img) {
		ArrayList<Component> componentList = new ArrayList<Component>();
		for (int i = 0; i < 20; i++) {
			if (img == null) {
				componentList.add(new ContentContainer("test " + i));
			} else {
				Image temp = new Image();
				temp.setSource(img.getSource());
				componentList.add(new ContentContainer("test " + i, temp));
			}

		}
		return componentList;
	}

	private ArrayList<Component> createThirdRow() {
		ArrayList<Component> componentList = new ArrayList<Component>();
		String[] strings = { "Statistics", "Achive", "Settings" };
		Image statistics = new Image();
		statistics.setSource(RessourceHandler.STATISTICS);
		statistics.setWidth(ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS);
		statistics.setHeight(ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS);

		Image history = new Image();
		history.setSource(RessourceHandler.HISTORY);
		history.setWidth(ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS);
		history.setHeight(ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS);

		Image settings = new Image();
		settings.setSource(RessourceHandler.SETTINGS);
		settings.setWidth(ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS);
		settings.setHeight(ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS);

		Image[] images = { statistics, history, settings };
		for (int i = 0; i < 3; i++) {

			float width = (float) ((pageWidth / 3) / 1.5);
			width -= ((width / 3) / 1.5);
			componentList.add(new ContentContainer(strings[i], width, ContentContainer.IMG_STANDARDSIZE, Unit.PIXELS, images[i]));
		}
		return componentList;
	}

	private CPTPlaceholder createCanvas(String cssClass) {
		CPTPlaceholder placeholder = new CPTPlaceholder(7, (int) (this.pageHeight * 0.4), Unit.PIXELS);
		placeholder.setStyleName(cssClass);
		return placeholder;
	}

}
