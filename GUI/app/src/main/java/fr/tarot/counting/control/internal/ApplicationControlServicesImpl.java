package fr.tarot.counting.control.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import fr.tarot.counting.control.ApplicationControlServices;
import fr.tarot.counting.model.Application;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

public class ApplicationControlServicesImpl implements ApplicationControlServices {

	@Override
	public void addPlayer(Application application, Player player) {
		application.getPlayers().add(player);
	}

	@Override
	public void removePlayer(Application application, Player player) {
		application.getPlayers().remove(player);
	}

	@Override
	public void addDone(Application application, Done done) {
		application.getDones().add(done);
	}

	@Override
	public String showResults(Application application) {
		Map<String, Integer> points = new HashMap<>();
		for (Player player : application.getPlayers()){
			for (Done done : application.getDones()){
				for (Player p : done.getPlayers()){
					if (p.getName().equals(player.getName())){
						int globalPoints = (points.get(p.getName()) == null ? 0 : points.get(p.getName())) + p.getPoints();
						points.put(player.getName(), globalPoints);
					}
				}
			}
		}
		StringBuilder builder = new StringBuilder();
		for (Entry<String, Integer> entry : points.entrySet()){
			builder.append(entry.getKey() + " : " + entry.getValue()).append("\n");
		}
		return builder.toString();
	}
	
}
