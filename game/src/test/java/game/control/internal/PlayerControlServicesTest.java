package game.control.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Card.Name;
import game.domain.Done;
import game.domain.Player;
import game.domain.Player.Team;

public class PlayerControlServicesTest {

	@Test
	public void testGetRemainingColorCards() {
		// Given
		Color carreau = Color.CARREAU;
		Color coeur = Color.COEUR;
		Color trefle = Color.TREFLE;

		List<Card> fold = new ArrayList<>();
		fold.add(TarotDeckControlServicesImpl.ROI_COEUR);
		fold.add(TarotDeckControlServicesImpl.ROI_CARREAU);
		fold.add(TarotDeckControlServicesImpl.DAME_COEUR);
		fold.add(TarotDeckControlServicesImpl.DIX_COEUR);
		fold.add(TarotDeckControlServicesImpl.CINQ_COEUR);
		fold.add(TarotDeckControlServicesImpl.TROIS_COEUR);
		fold.add(TarotDeckControlServicesImpl.DEUX_COEUR);
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl();

		// When
		List<Card.Name> remaingingCards = playerControlServices.getRemainingColorCards(fold, carreau);

		// Then
		Assert.assertEquals(13, remaingingCards.size());

		// When
		remaingingCards = playerControlServices.getRemainingColorCards(fold, coeur);

		// Then
		Assert.assertEquals(8, remaingingCards.size());
		Assert.assertFalse(remaingingCards.contains(Name.ROI));
		Assert.assertFalse(remaingingCards.contains(Name.DAME));
		Assert.assertFalse(remaingingCards.contains(Name.DIX));
		Assert.assertFalse(remaingingCards.contains(Name.CINQ));
		Assert.assertFalse(remaingingCards.contains(Name.TROIS));
		Assert.assertFalse(remaingingCards.contains(Name.DEUX));

		// When
		remaingingCards = playerControlServices.getRemainingColorCards(fold, trefle);

		// Then
		Assert.assertEquals(14, remaingingCards.size());
	}

	@Test
	public void testIsMasterCardWithOldFolds() {
		// Given
		List<Card> fold = new ArrayList<>();
		fold.add(TarotDeckControlServicesImpl.ROI_COEUR);
		fold.add(TarotDeckControlServicesImpl.ROI_CARREAU);
		fold.add(TarotDeckControlServicesImpl.DAME_COEUR);
		fold.add(TarotDeckControlServicesImpl.DIX_COEUR);
		fold.add(TarotDeckControlServicesImpl.CINQ_COEUR);
		fold.add(TarotDeckControlServicesImpl.TROIS_COEUR);
		fold.add(TarotDeckControlServicesImpl.DEUX_COEUR);
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl();
		Done done = new Done();

		// When
		boolean master = playerControlServices.isMasterCardWithOldFolds(TarotDeckControlServicesImpl.CAVALIER_COEUR,
				fold, done);

		// Then
		Assert.assertTrue(master);

		// When we want to check if the already passed card is stayed master
		master = playerControlServices.isMasterCardWithOldFolds(TarotDeckControlServicesImpl.ROI_COEUR, fold, done);

		// Then it is false
		Assert.assertFalse(master);

		// When
		master = playerControlServices.isMasterCardWithOldFolds(TarotDeckControlServicesImpl.VALET_COEUR, fold, done);

		// Then it is false (Cavalier is not yet passed
		Assert.assertFalse(master);
	}

	@Test
	public void testIsMasterCardWithCurrentFold() {
		// Given
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl();

		// When
		boolean winFold = playerControlServices.isMasterCardWithCurrentFold(TarotDeckControlServicesImpl.CINQ_PIQUE, Arrays.asList(TarotDeckControlServicesImpl.DEUX_PIQUE));
		
		// Then
		Assert.assertTrue(winFold);
		
		// When
		winFold = playerControlServices.isMasterCardWithCurrentFold(TarotDeckControlServicesImpl.CINQ_PIQUE, Arrays.asList(TarotDeckControlServicesImpl.SIX_PIQUE));
		
		// Then
		Assert.assertFalse(winFold);
		
		// When
		winFold = playerControlServices.isMasterCardWithCurrentFold(TarotDeckControlServicesImpl.CINQ_PIQUE, Arrays.asList(TarotDeckControlServicesImpl.DEUX_PIQUE, TarotDeckControlServicesImpl.SIX_PIQUE, TarotDeckControlServicesImpl.TROIS_PIQUE));
		
		// Then
		Assert.assertFalse(winFold);
		
		// When
		winFold = playerControlServices.isMasterCardWithCurrentFold(TarotDeckControlServicesImpl.CINQ_PIQUE, new ArrayList<>());
		
		// Then
		Assert.assertTrue(winFold);
	}
	
	@Test
	public void testIsTeamPlayTheLast(){
		// Given
		Done done = new Done();
		Done done2 = new Done();
		
		Player unknownPlayer1 = new Player();
		unknownPlayer1.setTeam(Team.UNKNOWN);
		Player unknownPlayer2 = new Player();
		unknownPlayer2.setTeam(Team.UNKNOWN);
		Player unknownPlayer3 = new Player();
		unknownPlayer3.setTeam(Team.UNKNOWN);
		Player unknownPlayer4 = new Player();
		unknownPlayer4.setTeam(Team.UNKNOWN);
		
		Player attackPlayer1 = new Player();
		attackPlayer1.setTeam(Team.ATTACK);
		Player attackPlayer2 = new Player();
		attackPlayer2.setTeam(Team.ATTACK);
		Player defensePlayer1 = new Player();
		defensePlayer1.setTeam(Team.DEFENSE);
		Player defensePlayer2 = new Player();
		defensePlayer2.setTeam(Team.DEFENSE);
		Player defensePlayer3 = new Player();
		defensePlayer3.setTeam(Team.DEFENSE);
		// No matters the player order
		done.getPlayers().addAll(Arrays.asList(attackPlayer2, unknownPlayer2, unknownPlayer1, unknownPlayer3, unknownPlayer4));
		done2.getPlayers().addAll(Arrays.asList(attackPlayer1, attackPlayer2, defensePlayer1, defensePlayer2, defensePlayer3));
		
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl();
		
		Card unknownCard1 = new Card();
		unknownCard1.setOwner(unknownPlayer1);
		Card unknownCard2 = new Card();
		unknownCard2.setOwner(unknownPlayer2);
		Card unknownCard3 = new Card();
		unknownCard3.setOwner(unknownPlayer3);
		
		Card attackCard1 = new Card();
		attackCard1.setOwner(attackPlayer1);
		Card attackCard2 = new Card();
		attackCard2.setOwner(attackPlayer2);
		
		Card defenseCard1 = new Card();
		defenseCard1.setOwner(defensePlayer1);
		Card defenseCard2 = new Card();
		defenseCard2.setOwner(defensePlayer2);
		Card defenseCard3 = new Card();
		defenseCard3.setOwner(defensePlayer3);
		
		// When
		boolean lastTeam = playerControlServices.isTeamPlayTheLast(done, new ArrayList<>(), unknownPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, Arrays.asList(unknownCard1), attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, Arrays.asList(unknownCard1, attackCard1), unknownPlayer2);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, Arrays.asList(unknownCard1, attackCard1, unknownCard2), unknownPlayer3);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, Arrays.asList(unknownCard1, attackCard1, unknownCard2, unknownCard3), unknownPlayer4);
		// Then
		Assert.assertTrue(lastTeam);
		
		// Done2 is for 5 players : 2 in attack, 3 in defense
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, new ArrayList<>(), attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, Arrays.asList(attackCard1), defensePlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, Arrays.asList(attackCard1, defenseCard1), attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, Arrays.asList(attackCard1, defenseCard1, attackCard2), defensePlayer2);
		// Then
		Assert.assertTrue(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, Arrays.asList(attackCard1, defenseCard1, attackCard2, defenseCard2), defensePlayer3);
		// Then
		Assert.assertTrue(lastTeam);
	}
	
	@Test
	public void testAddHand(){
		// Given
		Player player = new Player();
		Card card = new Card();
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl();
		
		// When
		playerControlServices.addHand(player, card);
		
		// Then
		Assert.assertEquals(1, player.getHand().size());
		Assert.assertEquals(card, player.getHand().get(0));
	}
	
	@Test
	public void addFold(){
		// Given
		List<Card> cards = new ArrayList<>();
		final Card card1 = new Card();
		final Card card2 = new Card();
		cards.add(card1);
		cards.add(card2);
		Player player = new Player();
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl();
		
		// When
		playerControlServices.addFold(player, cards);
		
		// Then
		Assert.assertEquals(1, player.getFolds().size());
		Assert.assertEquals(2, player.getFolds().get(0).size());
		Assert.assertTrue(player.getFolds().get(0).contains(card1));
		Assert.assertTrue(player.getFolds().get(0).contains(card2));
	}
}
