package view.component;

import gui.ressource.RessourceHandler;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ContentContainer extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6596352254409483852L;
	public static final float WIDTH = (float) (100 * 1.7);
	public static final float IMG_STANDARDSIZE = 100.f;

	public ContentContainer(String shownText) {
		Image img = createMeImage(IMG_STANDARDSIZE, IMG_STANDARDSIZE, Unit.PIXELS);
		createContainer(shownText, IMG_STANDARDSIZE, IMG_STANDARDSIZE, Unit.PIXELS, img);
	}

	public ContentContainer(String shownText , float width , float height , Unit unit) {
		Image img = createMeImage(width, height, unit);
		createContainer(shownText, width, height, unit, img);
	}

	public ContentContainer(String shownText , float width , float height , Unit unit , Image img) {
		createContainer(shownText, width, height, unit, img);

	}

	public ContentContainer(String shownText , Image img) {
		img = createMeImage(IMG_STANDARDSIZE, IMG_STANDARDSIZE, Unit.PIXELS, img);
		createContainer(shownText, IMG_STANDARDSIZE, IMG_STANDARDSIZE, Unit.PIXELS, img);
	}

	private void createContainer(String shownText , float width , float height , Unit unit , Image img) {
		setWidth((float) (width * 1.5), unit);
		setHeight((float) (height * 1.5), unit);

		addComponent(img);
		setComponentAlignment(img, Alignment.MIDDLE_CENTER);

		Label lbl = new Label(shownText);
		lbl.setSizeUndefined();
		addComponent(lbl);
		this.setComponentAlignment(lbl, Alignment.MIDDLE_CENTER);
	}

	private Image createMeImage(float width , float height , Unit unit) {
		Image img = new Image();
		img.setSource(RessourceHandler.USER_DUMMY);
		return createMeImage(width, height, unit, img);

	}

	private Image createMeImage(float width , float height , Unit unit , Image img) {
		img.setWidth(width, unit);
		img.setHeight(height, unit);
		// img.setWidth(this.getWidth() - 10, Unit.PIXELS);
		// img.setHeight(img.getWidth(), Unit.PIXELS);
		img.setStyleName(RessourceHandler.ICON_BACKGROUD);
		return img;
	}

}
