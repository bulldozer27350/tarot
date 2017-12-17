package game.control.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Done;
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
	
	@Test
	public void testGetNotYetOpenedColors(){
		// Given
		Done emptyDone = new Done();
		Done done = new Done();
		final Fold foldCoeur = new Fold();
		foldCoeur.setPlayedColor(Color.HEART);
		Player player = new Player();
		player.getFolds().add(foldCoeur);
		done.getPlayers().add(player);
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl(null);
		
		// When
		List<Color> notYetOpenedColors = playerControlServices.getNotYetOpenedColors(emptyDone);
		
		// Then
		Assert.assertEquals(Arrays.asList(Color.SPADE, Color.HEART, Color.DIAMOND, Color.CLUB),  notYetOpenedColors);
		
		// When
		notYetOpenedColors = playerControlServices.getNotYetOpenedColors(done);
		
		// Then
		Assert.assertEquals(Arrays.asList(Color.SPADE, Color.DIAMOND, Color.CLUB),  notYetOpenedColors);
	}
	
	@Test
	public void testGetAlreadyOpenedColors(){
		// Given
		Done emptyDone = new Done();
		Done done = new Done();
		final Fold foldCoeur = new Fold();
		foldCoeur.setPlayedColor(Color.HEART);
		Player player = new Player();
		player.getFolds().add(foldCoeur);
		done.getPlayers().add(player);
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl(null);
		
		// When
		List<Color> notYetOpenedColors = playerControlServices.getAlreadyOpenedColors(emptyDone);
		
		// Then
		Assert.assertEquals(Arrays.asList(),  notYetOpenedColors);
		
		// When
		notYetOpenedColors = playerControlServices.getAlreadyOpenedColors(done);
		
		// Then
		Assert.assertEquals(Arrays.asList(Color.HEART),  notYetOpenedColors);
	}
	
	@Test
	public void testSelectRandomTrump(){
		// Given
		Done done = new Done();
		Card randomCard= new Card();
		List<Card> cards = new ArrayList<>();
		List<Fold> previousFolds = new ArrayList<>();
		final Fold fold = new Fold();
		Player player = new Player();
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl(null){
			@Override
			Card selectColorCardToPlay(Done done, Player player, List<Card> cards, Fold fold,
					List<Fold> previousFolds) {
				return randomCard;
			}
		};
		
		// When
		Card card = playerControlServices.selectRandomTrump(done, player, cards, fold, previousFolds);
		
		// Then
		Assert.assertEquals(randomCard, card);
	}
	
	@Test
	public void testSelectColorCardToPlay() {
		// should be implemented because it has been overiden in another test
		Assert.fail();
	}
}
