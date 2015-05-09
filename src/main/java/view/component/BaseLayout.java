package view.component;

import com.vaadin.ui.HorizontalSplitPanel;

public class BaseLayout extends HorizontalSplitPanel {

	/**
     *
     */
	private static final long serialVersionUID;
	static {
		serialVersionUID = -2265922916170768007L;
	}
	// initialize root & other components
	{
		initRoot();
		initComponents();
	}

	private void initRoot() {
		setStyleName("base-layout");
		/* set component style name */
		setSplitPosition(150, Unit.PIXELS, false);
		/*
		 * Moves the position of the splitter with given position and unit. Parameters: pos the new size of the first region. Fractions are only allowed when
		 * unit is percentage. unit the unit (from Sizeable) in which the size is given. reverse if set to true the split splitter position is measured by the
		 * second region else it is measured by the first region
		 */
	}

	private void initComponents() {
		meArea = new MeArea();
		setFirstComponent(meArea);

		// the content .

		setSecondComponent(new ContentHome());
	}

	private MeArea meArea;

}
