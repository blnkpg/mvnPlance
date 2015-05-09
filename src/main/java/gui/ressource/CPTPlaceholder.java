package gui.ressource;

import com.vaadin.ui.Label;

public class CPTPlaceholder extends Label {

	/**
	 * 
	 */
	private static final long serialVersionUID = -538234578928566793L;

	public CPTPlaceholder(float width , float height , Unit unit) {
		super(" ");
		setHeight(height, unit);
		setWidth(width, unit);
	}
}
