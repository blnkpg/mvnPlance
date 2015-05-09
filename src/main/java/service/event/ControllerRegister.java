package service.event;

import java.util.HashSet;
import java.util.Set;

public class ControllerRegister {

	private Set<ControllerListener> listeners = new HashSet<ControllerListener>();

	public void addListener(ControllerListener listener) {
		this.listeners.add(listener);
	}

	public void removeListener(ControllerListener listener) {
		this.listeners.remove(listener);
	}

	public void wakeUpListeners() {
		for (ControllerListener controllerListener : listeners) {
			controllerListener.wakeUP();
		}
	}

}
