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
	public void showPlayer(Done done, Player player) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(player.getName()).append(" (").append(player.getPoints()).append(" points)");
		if (done.getDeads().contains(player)){
			stringBuilder.append(" �tait mort � cette done");
		} else {
			stringBuilder.append(" n'�tait pas mort � cette done");
		}
		System.out.println(stringBuilder.toString());
	}
	
}
