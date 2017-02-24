package control.internal;

import control.PlayerControlServices;
import model.Done;
import model.Player;

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
			stringBuilder.append(" était mort à cette done");
		} else {
			stringBuilder.append(" n'était pas mort à cette done");
		}
		System.out.println(stringBuilder.toString());
	}
	
	@Override
	public void addDead(Done done, Player player) {
		done.getDeads().add(player);
	}

}
