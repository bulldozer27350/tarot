package game.control.internal;

import java.util.List;

import game.control.DoneControlServices;
import game.control.PlayerControlServices;
import game.domain.Card;
import game.domain.Done;
import game.domain.Player;

public class DoneControlServicesImpl implements DoneControlServices {

	private PlayerControlServices playerControlServices;

	public DoneControlServicesImpl(PlayerControlServices playerControlServicesArg) {
		this.playerControlServices = playerControlServicesArg;
	}
	
	@Override
	public void distribute(Done done) {
		List<Player> players = done.getPlayers();
		int cardsSize = done.getCards().size();
		for (int i = 0 ; i < cardsSize; i = i + 3){
			Player player = players.get(i%players.size());
			Card removedCard = done.getCards().remove(0);
			removedCard.setOwner(player);
			this.playerControlServices.addHand(player, removedCard);
			removedCard = done.getCards().remove(0);
			removedCard.setOwner(player);
			this.playerControlServices.addHand(player, removedCard);
			removedCard = done.getCards().remove(0);
			removedCard.setOwner(player);
			this.playerControlServices.addHand(player, removedCard);
		}
	}

	@Override
	public void addCard(Done done, Card card) {
		done.getCards().add(card);
	}

	@Override
	public void addPlayer(Done done, Player player) {
		done.getPlayers().add(player);
	}

}
