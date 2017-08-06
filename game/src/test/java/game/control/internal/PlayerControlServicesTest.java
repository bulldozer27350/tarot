package game.control.internal;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import game.domain.Card;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;
import game.domain.Player.Team;

public class PlayerControlServicesTest {

	
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
		
		PlayerControlServicesImpl playerControlServices = new PlayerControlServicesImpl(null);
		
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
		
		Fold fold1 = new Fold();
		fold1.getCards().addAll(Arrays.asList(unknownCard1));
		
		Fold fold2 = new Fold();
		fold2.getCards().addAll(Arrays.asList(unknownCard1, attackCard1));
		
		Fold fold3 = new Fold();
		fold3.getCards().addAll(Arrays.asList(unknownCard1, attackCard1, unknownCard2));
		
		Fold fold4 = new Fold();
		fold4.getCards().addAll(Arrays.asList(unknownCard1, attackCard1, unknownCard2, unknownCard3));

		Fold fold5 = new Fold();
		fold5.getCards().addAll(Arrays.asList(attackCard1));
		
		Fold fold6 = new Fold();
		fold6.getCards().addAll(Arrays.asList(attackCard1, defenseCard1));
		
		Fold fold7 = new Fold();
		fold7.getCards().addAll(Arrays.asList(attackCard1, defenseCard1, attackCard2));
		
		Fold fold8 = new Fold();
		fold8.getCards().addAll(Arrays.asList(attackCard1, defenseCard1, attackCard2, defenseCard2));
		
		
		// When
		boolean lastTeam = playerControlServices.isTeamPlayTheLast(done, new Fold(), unknownPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, fold1, attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, fold2, unknownPlayer2);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, fold3, unknownPlayer3);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done, fold4, unknownPlayer4);
		// Then
		Assert.assertTrue(lastTeam);
		
		// Done2 is for 5 players : 2 in attack, 3 in defense
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, new Fold(), attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, fold5, defensePlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, fold6, attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, fold7, defensePlayer2);
		// Then
		Assert.assertTrue(lastTeam);
		
		// When
		lastTeam = playerControlServices.isTeamPlayTheLast(done2, fold8, defensePlayer3);
		// Then
		Assert.assertTrue(lastTeam);
	}
	
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
