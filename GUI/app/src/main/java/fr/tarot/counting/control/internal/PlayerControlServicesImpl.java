package fr.tarot.counting.control.internal;

import fr.tarot.counting.control.PlayerControlServices;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

/**
 * The Class PlayerControlServicesImpl.
 */
public class PlayerControlServicesImpl implements PlayerControlServices {

	@Override
	public void addPlayer(Done done, Player player) {
		done.getPlayers().add(player);
		
	}

	@Override
	public void removePlayer(Done done, Player player) {
		done.getPlayers().remove(player);
	}

	@Override
	public String showPlayer(Done done, Player player) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(player.getName()).append(" (").append(player.getPoints()).append(" points)");
		if (done.getDeads().contains(player)){
			stringBuilder.append("était mort à cette done");
		} else {
			stringBuilder.append(" n'était pas mort à cette done");
		}
		return stringBuilder.toString();
	}
	
}
