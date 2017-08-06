package game.control.internal;

import java.util.ArrayList;
import java.util.List;

import game.control.FoldControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Card.Name;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;

public class FoldControlServicesImpl implements FoldControlServices {

	@Override
	public void computeWinner(Fold fold) {
		int highestValue = 0;
		Card strongestCard = null;
		for (Card card : fold.getCards()) {
			if (card.getName().getPower() > highestValue
					&& (card.getColor() == fold.getPlayedColor() || card.getColor() == Color.ATOUT)) {
				highestValue = card.getName().getPower();
				strongestCard = card;
			}
		}
		Player owner = strongestCard.getOwner();
		owner.getFolds().add(fold);
		fold.setWinner(owner);
	}

	@Override
	public Color getColor(Fold fold) {
		return null;
	}

	@Override
	public boolean canCardWinFold(Fold fold, Card wantedCard) {
		boolean good = false;
		int highestValue = 0;
		Card strongestCard = null;
		for (Card card : fold.getCards()) {
			if (card.getName().getPower() > highestValue) {
				highestValue = card.getName().getPower();
				strongestCard = card;
			}
		}
		if (strongestCard == null) {
			good = true;
		} else {
			good = wantedCard.getName().getPower() >= strongestCard.getName().getPower();
		}
		return good;
	}

	@Override
	public boolean isCardMostPowerful(List<Fold> foldedCards, Card wantedCard, Done done) {
		List<Card> allFoldedCards = new ArrayList<>();
		foldedCards.stream().forEach(f -> allFoldedCards.addAll(f.getCards()));
		List<Name> remainingNames = getRemainingCardsInGivenColor(allFoldedCards, wantedCard.getColor());
		boolean good = false;
		if (remainingNames.isEmpty()) {
			good = true;
		} else {
			boolean powerful = remainingNames.get(remainingNames.size() - 1).getPower() == wantedCard.getName()
					.getPower();
			boolean isCuts = false;
			for (Player player : done.getPlayers()) {
				for (Fold fold : player.getFolds()) {
					if (fold.getPlayedColor() == wantedCard.getColor()
							&& fold.getCards().stream().filter(c -> c.getColor() == Color.ATOUT).count() != 0) {
						isCuts = true;
					}
				}
			}
			good = powerful && !isCuts;
		}
		return good;
	}
	
	/**
	 * Gets the remaining color cards. Will use the previous folds to remove
	 * passed cards. Could be very useful to know what is already passed in this
	 * color, and what is not.
	 *
	 * @param foldedCards
	 *            the folded cards
	 * @param selectedColor
	 *            the selected color
	 * @return the remaining color cards
	 */
	List<Name> getRemainingCardsInGivenColor(List<Card> foldedCards, Color selectedColor) {
		List<Name> values = new ArrayList<>();
		values.add(Name.AS);
		values.add(Name.DEUX);
		values.add(Name.TROIS);
		values.add(Name.QUATRE);
		values.add(Name.CINQ);
		values.add(Name.SIX);
		values.add(Name.SEPT);
		values.add(Name.HUIT);
		values.add(Name.NEUF);
		values.add(Name.DIX);
		values.add(Name.VALET);
		values.add(Name.CAVALIER);
		values.add(Name.DAME);
		values.add(Name.ROI);
		List<Name> remainingCards = new ArrayList<>();
		foldedCards.stream().filter(e -> e.getColor() == selectedColor).forEach(e -> remainingCards.add(e.getName()));
		values.removeIf(e -> remainingCards.contains(e));
		return values;
	}

}
