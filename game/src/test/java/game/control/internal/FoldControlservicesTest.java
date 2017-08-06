package game.control.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import game.domain.Card;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Card.Color;
import game.domain.Card.Name;

public class FoldControlservicesTest {

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
		FoldControlServicesImpl foldControlServices = new FoldControlServicesImpl();

		// When
		boolean master = foldControlServices.isCardMostPowerful(Arrays.asList(currentFold),
				TarotDeckControlServicesImpl.CAVALIER_COEUR, done);

		// Then
		Assert.assertTrue(master);

		// When we want to check if the already passed card is stayed master
		master = foldControlServices.isCardMostPowerful(Arrays.asList(currentFold), TarotDeckControlServicesImpl.ROI_COEUR, done);

		// Then it is false
		Assert.assertFalse(master);

		// When
		master = foldControlServices.isCardMostPowerful(Arrays.asList(currentFold), TarotDeckControlServicesImpl.VALET_COEUR, done);

		// Then it is false (Cavalier is not yet passed
		Assert.assertFalse(master);
	}
	
	@Test
	public void testIsMasterCardWithCurrentFold() {
		// Given
		Fold fold1 = new Fold();
		fold1.getCards().addAll(Arrays.asList(TarotDeckControlServicesImpl.DEUX_PIQUE));
		Fold fold2 = new Fold();
		fold2.getCards().addAll(Arrays.asList(TarotDeckControlServicesImpl.SIX_PIQUE));
		Fold fold3 = new Fold();
		fold3.getCards().addAll(Arrays.asList(TarotDeckControlServicesImpl.DEUX_PIQUE, TarotDeckControlServicesImpl.SIX_PIQUE, TarotDeckControlServicesImpl.TROIS_PIQUE));
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
	
}
