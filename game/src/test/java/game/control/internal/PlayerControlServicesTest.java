package game.control.internal;

import org.junit.Assert;
import org.junit.Test;

import game.domain.Card;
import game.domain.Fold;
import game.domain.Player;

public class PlayerControlServicesTest {

	
	@Test
	public void testAddHand(){
		// Given
		Player player = new Player();
		Card card = new Card();
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl(null);
		
		// When
		playerControlServices.addHand(player, card);
		
		// Then
		Assert.assertEquals(1, player.getHand().size());
		Assert.assertEquals(card, player.getHand().get(0));
	}
	
	@Test
	public void addFold(){
		// Given
		Fold fold = new Fold();
		final Card card1 = new Card();
		final Card card2 = new Card();
		fold.getCards().add(card1);
		fold.getCards().add(card2);
		Player player = new Player();
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl(null);
		
		// When
		playerControlServices.addFold(player, fold);
		
		// Then
		Assert.assertEquals(1, player.getFolds().size());
		Assert.assertEquals(2, player.getFolds().get(0).getCards().size());
		Assert.assertTrue(player.getFolds().get(0).getCards().contains(card1));
		Assert.assertTrue(player.getFolds().get(0).getCards().contains(card2));
	}
}
