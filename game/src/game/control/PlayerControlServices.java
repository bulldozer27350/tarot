package game.control;

import java.util.List;

import game.domain.Card;
import game.domain.Done;
import game.domain.Player;

public interface PlayerControlServices {

	public void addHand(Player player, Card card);
	public void addFold(Player player, List<Card> cards);
	public Card playCard(Done done, Player player, List<Card> fold, List<Card> previousFolds);
	
}
