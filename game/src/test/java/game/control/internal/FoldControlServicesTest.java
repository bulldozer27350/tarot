package game.control.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import game.domain.Card;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;
import game.domain.Card.Color;
import game.domain.Card.Name;
import game.domain.Player.Team;

public class FoldControlServicesTest {

	@Test
	public void testGetRemainingCardsInGivenColor() {
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
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		List<Card.Name> remaingingCards = foldControlServices.getRemainingCardsInGivenColor(fold, carreau);

		// Then
		Assert.assertEquals(13, remaingingCards.size());

		// When
		remaingingCards = foldControlServices.getRemainingCardsInGivenColor(fold, coeur);

		// Then
		Assert.assertEquals(8, remaingingCards.size());
		Assert.assertFalse(remaingingCards.contains(Name.ROI));
		Assert.assertFalse(remaingingCards.contains(Name.DAME));
		Assert.assertFalse(remaingingCards.contains(Name.DIX));
		Assert.assertFalse(remaingingCards.contains(Name.CINQ));
		Assert.assertFalse(remaingingCards.contains(Name.TROIS));
		Assert.assertFalse(remaingingCards.contains(Name.DEUX));

		// When
		remaingingCards = foldControlServices.getRemainingCardsInGivenColor(fold, trefle);

		// Then
		Assert.assertEquals(14, remaingingCards.size());
	}

	@Test
	public void testIsCardMostPowerful() {
		// Given
		Fold currentFold = new Fold();
		List<Card> fold = currentFold.getCards();
		fold.add(TarotDeckControlServicesImpl.ROI_COEUR);
		fold.add(TarotDeckControlServicesImpl.ROI_CARREAU);
		fold.add(TarotDeckControlServicesImpl.DAME_COEUR);
		fold.add(TarotDeckControlServicesImpl.DIX_COEUR);
		fold.add(TarotDeckControlServicesImpl.CINQ_COEUR);
		fold.add(TarotDeckControlServicesImpl.TROIS_COEUR);
		fold.add(TarotDeckControlServicesImpl.DEUX_COEUR);
		
		Done done = new Done();
		final Player playerA = new Player();
		final Fold cutFold = new Fold();
		cutFold.setPlayedColor(Color.COEUR);
		cutFold.getCards().add(TarotDeckControlServicesImpl.AS_COEUR);
		cutFold.getCards().add(TarotDeckControlServicesImpl.CINQ_ATOUT);
		playerA.getFolds().add(cutFold);
		Done cutDone = new Done();
		cutDone.getPlayers().add(playerA);
		FoldControlServicesImpl defaultFoldControlServices = new FoldControlServicesImpl();
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl(){
			@Override
			List<Name> getRemainingCardsInGivenColor(List<Card> foldedCards, Color selectedColor) {
				return new ArrayList<>();
			}
		};

		// When
		boolean master = defaultFoldControlServices.isCardMostPowerful(Arrays.asList(currentFold),
				TarotDeckControlServicesImpl.CAVALIER_COEUR, done);

		// Then
		Assert.assertTrue(master);

		// When we want to check if the already passed card is stayed master
		master = defaultFoldControlServices.isCardMostPowerful(Arrays.asList(currentFold),
				TarotDeckControlServicesImpl.ROI_COEUR, done);

		// Then it is false
		Assert.assertFalse(master);

		// When
		master = defaultFoldControlServices.isCardMostPowerful(Arrays.asList(currentFold),
				TarotDeckControlServicesImpl.VALET_COEUR, done);

		// Then it is false (Cavalier is not yet passed)
		Assert.assertFalse(master);
		
		// When we want to know if a card could win when all the colors's cards are passed (foldControlServices is overridden)
		master = foldControlServices.isCardMostPowerful(Arrays.asList(currentFold),
				TarotDeckControlServicesImpl.VALET_COEUR, done);
		
		// Then it is mandatory true (no cards left)
		Assert.assertTrue(master);
		
		// When 
		master = defaultFoldControlServices.isCardMostPowerful(Arrays.asList(currentFold),
				TarotDeckControlServicesImpl.CAVALIER_COEUR, cutDone);
		
		// Then 
		Assert.assertFalse(master);
	}

	@Test
	public void testIsMasterCardWithCurrentFold() {
		// Given
		Fold fold1 = new Fold();
		fold1.getCards().addAll(Arrays.asList(TarotDeckControlServicesImpl.DEUX_PIQUE));
		fold1.setPlayedColor(Color.PIQUE);
		Fold fold2 = new Fold();
		fold2.setPlayedColor(Color.PIQUE);
		fold2.getCards().addAll(Arrays.asList(TarotDeckControlServicesImpl.SIX_PIQUE));
		Fold fold3 = new Fold();
		fold3.setPlayedColor(Color.PIQUE);
		fold3.getCards().addAll(Arrays.asList(TarotDeckControlServicesImpl.DEUX_PIQUE,
				TarotDeckControlServicesImpl.SIX_PIQUE, TarotDeckControlServicesImpl.TROIS_PIQUE));
		// empty fold
		Fold fold4 = new Fold();
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		boolean winFold = foldControlServices.canCardWinFold(fold1, TarotDeckControlServicesImpl.CINQ_PIQUE);

		// Then
		Assert.assertTrue(winFold);

		// When
		winFold = foldControlServices.canCardWinFold(fold2, TarotDeckControlServicesImpl.CINQ_PIQUE);

		// Then
		Assert.assertFalse(winFold);

		// When
		winFold = foldControlServices.canCardWinFold(fold3, TarotDeckControlServicesImpl.CINQ_PIQUE);

		// Then
		Assert.assertFalse(winFold);

		// When
		winFold = foldControlServices.canCardWinFold(fold4, TarotDeckControlServicesImpl.CINQ_PIQUE);

		// Then
		Assert.assertTrue(winFold);
	}

	@Test
	public void testComputeWinner() {
		// Given
		Fold fold = new Fold();
		Fold fold2 = new Fold();
		Fold fold3 = new Fold();
		final Card card1 = new Card();
		final Card card2 = new Card();
		final Card card3 = new Card();
		final Card card4 = new Card();
		final Player player1 = new Player("P1");
		final Player player2 = new Player("P2");
		final Player player3 = new Player("P3");
		final Player player4 = new Player("P4");

		card1.setOwner(player1);
		card2.setOwner(player2);
		card3.setOwner(player3);
		card4.setOwner(player4);

		card1.setName(Name.SEPT);
		card1.setColor(Color.CARREAU);
		card2.setName(Name.DIX);
		card2.setColor(Color.CARREAU);
		card3.setName(Name.ROI);
		card3.setColor(Color.COEUR);
		card4.setName(Name.CINQ_ATOUT);
		card4.setColor(Color.ATOUT);

		fold.setPlayedColor(card1.getColor());
		fold.getCards().add(card1);
		fold.getCards().add(card2);

		fold2.setPlayedColor(card1.getColor());
		fold2.getCards().add(card1);
		fold2.getCards().add(card3);

		fold3.setPlayedColor(card1.getColor());
		fold3.getCards().add(card1);
		fold3.getCards().add(card4);

		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		foldControlServices.computeWinner(fold);

		// Then
		Assert.assertEquals(player2, fold.getWinner());

		// When
		foldControlServices.computeWinner(fold2);

		// Then
		Assert.assertEquals(player1, fold2.getWinner());

		// When
		foldControlServices.computeWinner(fold3);

		// Then
		Assert.assertEquals(player4, fold3.getWinner());

	}

	@Test
	public void testComputeColor() {
		// Given
		Fold fold = new Fold();
		fold.getCards().add(TarotDeckControlServicesImpl.CAVALIER_CARREAU);
		fold.getCards().add(TarotDeckControlServicesImpl.CAVALIER_PIQUE);
		fold.getCards().add(TarotDeckControlServicesImpl.DEUX_CARREAU);

		Fold fold2 = new Fold();
		fold2.getCards().add(TarotDeckControlServicesImpl.EXCUSE);
		fold2.getCards().add(TarotDeckControlServicesImpl.CAVALIER_PIQUE);
		fold2.getCards().add(TarotDeckControlServicesImpl.DEUX_CARREAU);

		Fold fold3 = new Fold();
		fold3.getCards().add(TarotDeckControlServicesImpl.CINQ_ATOUT);
		fold3.getCards().add(TarotDeckControlServicesImpl.CAVALIER_PIQUE);
		fold3.getCards().add(TarotDeckControlServicesImpl.DEUX_CARREAU);

		Fold fold4 = new Fold();
		fold4.getCards().add(TarotDeckControlServicesImpl.EXCUSE);

		Fold fold5 = new Fold();

		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		foldControlServices.computeColor(fold);

		// Then
		Assert.assertEquals(Color.CARREAU, fold.getPlayedColor());

		// When
		foldControlServices.computeColor(fold2);

		// Then
		Assert.assertEquals(Color.PIQUE, fold2.getPlayedColor());

		// When
		foldControlServices.computeColor(fold3);

		// Then
		Assert.assertEquals(Color.ATOUT, fold3.getPlayedColor());

		// When
		foldControlServices.computeColor(fold4);

		// Then
		Assert.assertNull(fold4.getPlayedColor());

		// When
		foldControlServices.computeColor(fold5);

		// Then
		Assert.assertNull(fold5.getPlayedColor());
	}

	@Test
	public void testCanCardWinFold() {
		// Given
		Fold fold = new Fold();
		fold.setPlayedColor(Color.TREFLE);
		fold.getCards().add(TarotDeckControlServicesImpl.CINQ_TREFLE);
		fold.getCards().add(TarotDeckControlServicesImpl.VALET_TREFLE);
		fold.getCards().add(TarotDeckControlServicesImpl.HUIT_TREFLE);

		Fold fold2 = new Fold();
		fold2.setPlayedColor(Color.TREFLE);
		fold2.getCards().add(TarotDeckControlServicesImpl.CINQ_TREFLE);
		fold2.getCards().add(TarotDeckControlServicesImpl.VALET_TREFLE);
		fold2.getCards().add(TarotDeckControlServicesImpl.HUIT_ATOUT);
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		boolean canWin = foldControlServices.canCardWinFold(fold, TarotDeckControlServicesImpl.CAVALIER_PIQUE);

		// Then
		Assert.assertFalse(canWin);

		// When
		canWin = foldControlServices.canCardWinFold(fold, TarotDeckControlServicesImpl.CAVALIER_TREFLE);

		// Then
		Assert.assertTrue(canWin);

		// When
		canWin = foldControlServices.canCardWinFold(fold, TarotDeckControlServicesImpl.EXCUSE);

		// Then
		Assert.assertFalse(canWin);

		// When
		canWin = foldControlServices.canCardWinFold(fold, TarotDeckControlServicesImpl.UN_ATOUT);

		// Then
		Assert.assertTrue(canWin);

		// When
		canWin = foldControlServices.canCardWinFold(fold2, TarotDeckControlServicesImpl.UN_ATOUT);

		// Then
		Assert.assertFalse(canWin);
	}

	@Test
	public void testGetPlayersOrder(){
		// Given
		Done done = new Done();
		final Player playerA = new Player();
		final Player playerB = new Player();
		final Player playerC = new Player();
		final Player playerD = new Player();
		playerA.setName("A");
		playerB.setName("B");
		playerC.setName("C");
		playerD.setName("D");
		done.getPlayers().add(playerA);
		done.getPlayers().add(playerB);
		done.getPlayers().add(playerC);
		done.getPlayers().add(playerD);
		final Card cardA = TarotDeckControlServicesImpl.AS_CARREAU;
		cardA.setOwner(playerA);
		final Card cardB = TarotDeckControlServicesImpl.AS_PIQUE;
		cardB.setOwner(playerB);
		final Card cardC = TarotDeckControlServicesImpl.AS_COEUR;
		cardC.setOwner(playerC);
		final Card cardD = TarotDeckControlServicesImpl.AS_TREFLE;
		cardD.setOwner(playerD);
		
		Fold emptyFold = new Fold();
		Fold onePlayerFold = new Fold();
		onePlayerFold.getCards().add(cardA);
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();
		
		// When
		List<Player> playersOrder = foldControlServices.getPlayersOrder(done, playerA, emptyFold);
		
		// Then
		Assert.assertEquals(Arrays.asList(playerA, playerB, playerC, playerD), playersOrder);
		
		// When
		playersOrder = foldControlServices.getPlayersOrder(done, playerC, emptyFold);
		
		// Then
		Assert.assertEquals(Arrays.asList(playerC, playerD, playerA, playerB), playersOrder);
		
		// When
		playersOrder = foldControlServices.getPlayersOrder(done, playerB, onePlayerFold);
		
		// Then
		Assert.assertEquals(Arrays.asList(playerB, playerC, playerD), playersOrder);
		
	}

	@Test
	public void testIsTeamPlayTheLast() {
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
		done.getPlayers()
				.addAll(Arrays.asList(attackPlayer2, unknownPlayer2, unknownPlayer1, unknownPlayer3, unknownPlayer4));
		done2.getPlayers()
				.addAll(Arrays.asList(attackPlayer1, attackPlayer2, defensePlayer1, defensePlayer2, defensePlayer3));

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

		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		boolean lastTeam = foldControlServices.isTeamPlayTheLast(done, new Fold(), unknownPlayer1);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done, fold1, attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done, fold2, unknownPlayer2);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done, fold3, unknownPlayer3);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done, fold4, unknownPlayer4);
		// Then
		Assert.assertTrue(lastTeam);

		// Done2 is for 5 players : 2 in attack, 3 in defense

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done2, new Fold(), attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done2, fold5, defensePlayer1);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done2, fold6, attackPlayer1);
		// Then
		Assert.assertFalse(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done2, fold7, defensePlayer2);
		// Then
		Assert.assertTrue(lastTeam);

		// When
		lastTeam = foldControlServices.isTeamPlayTheLast(done2, fold8, defensePlayer3);
		// Then
		Assert.assertTrue(lastTeam);
	}

	@Test
	public void testCanGivePoints() {
		// Given
		Player player = new Player();
		player.setTeam(Team.DEFENSE);
		
		Player sameTeamPlayer = new Player();
		sameTeamPlayer.setTeam(Team.DEFENSE);
		
		Player otherTeamPlayer = new Player();
		otherTeamPlayer.setTeam(Team.ATTACK);
		
		TarotDeckControlServicesImpl.CINQ_ATOUT.setOwner(otherTeamPlayer);
		TarotDeckControlServicesImpl.SIX_ATOUT.setOwner(otherTeamPlayer);
		TarotDeckControlServicesImpl.SEPT_ATOUT.setOwner(sameTeamPlayer);

		Fold emptyFold = new Fold();
		
		Fold foldWithoutAtout = new Fold();
		foldWithoutAtout.getCards().add(TarotDeckControlServicesImpl.CAVALIER_TREFLE);
		foldWithoutAtout.getCards().add(TarotDeckControlServicesImpl.CINQ_TREFLE);
		foldWithoutAtout.getCards().add(TarotDeckControlServicesImpl.DAME_TREFLE);
		foldWithoutAtout.getCards().add(TarotDeckControlServicesImpl.AS_TREFLE);
		
		Fold foldWithLosingAtout = new Fold();
		foldWithLosingAtout.getCards().add(TarotDeckControlServicesImpl.CAVALIER_TREFLE);
		foldWithLosingAtout.getCards().add(TarotDeckControlServicesImpl.CINQ_ATOUT);
		foldWithLosingAtout.getCards().add(TarotDeckControlServicesImpl.DAME_TREFLE);
		foldWithLosingAtout.getCards().add(TarotDeckControlServicesImpl.SIX_ATOUT);

		Fold foldWithWinningAtout = new Fold();
		foldWithWinningAtout.getCards().add(TarotDeckControlServicesImpl.CAVALIER_TREFLE);
		foldWithWinningAtout.getCards().add(TarotDeckControlServicesImpl.CINQ_ATOUT);
		foldWithLosingAtout.getCards().add(TarotDeckControlServicesImpl.DAME_TREFLE);
		foldWithWinningAtout.getCards().add(TarotDeckControlServicesImpl.SEPT_ATOUT);
		
		final boolean teamPlayTheLast = true;
		final boolean teamDoNotPlayTheLast = false;
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl() {
			@Override
			public boolean isTeamPlayTheLast(Done done, Fold fold, Player player) {
				return teamPlayTheLast;
			}
		};

		FoldControlServicesImpl foldControlServices2 = new FoldControlServicesImpl() {
			@Override
			public boolean isTeamPlayTheLast(Done done, Fold fold, Player player) {
				return teamDoNotPlayTheLast;
			}
		};

		// When
		try {
			foldControlServices.canGivePoints(emptyFold, null, null);
			Assert.fail();
		} catch (UnsupportedOperationException e) {
			// Then an exception should occurs when there is no card in the
			// given fold
		}

		// When
		boolean canGivePoints = foldControlServices.canGivePoints(foldWithoutAtout, player, null);
		
		// Then
		Assert.assertFalse(canGivePoints);
		
		// When
		canGivePoints = foldControlServices.canGivePoints(foldWithoutAtout, player, null);
		
		// Then
		Assert.assertFalse(canGivePoints);
		
		// When
		canGivePoints = foldControlServices.canGivePoints(foldWithLosingAtout, player, null);
		
		// Then
		Assert.assertFalse(canGivePoints);
		
		// When
		canGivePoints = foldControlServices.canGivePoints(foldWithWinningAtout, player, null);
		
		// Then
		Assert.assertEquals(teamPlayTheLast, canGivePoints);
		
		// When
		canGivePoints = foldControlServices2.canGivePoints(foldWithWinningAtout, player, null);
		
		// Then
		Assert.assertEquals(teamDoNotPlayTheLast, canGivePoints);
	}

}
