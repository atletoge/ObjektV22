package of6.observable.lf;

import java.util.ArrayList;
import java.util.List;

public class App {

	private List<AppListener> clients = new ArrayList<AppListener>();
	private List<String> sentPushNotifications = new ArrayList<>();

	public void sendPushNotification(String notification) {
		sentPushNotifications.add(notification);
		firePushNotification(notification);
	}

	// Observatør-observert-teknikken:

	// Metoder for å legge til lyttere etc...
	public void subscribe(AppListener listener) {
		if (!clients.contains(listener)) {
			clients.add(listener);
		}
	}

	// Metoder for å legge til lyttere etc...
	public void unsubscribe(AppListener listener) {
		if (clients.contains(listener)) {
			clients.remove(listener);
		}
	}

	// Metode for å varsle om endringer
	private void firePushNotification(String newDeadline) {
		for (AppListener client : clients) {
			client.receivePushNotification(newDeadline);
		}

	}
}
