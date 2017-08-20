package game.control.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import game.control.FoldControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Card.Name;
import game.domain.Player.Team;
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

	/**
	 * Gets the other players order. The first player is the given player, and
	 * the others are added in the correct order.
	 *
	 * @param done
	 *            the done
	 * @param player
	 *            the player
	 * @param currentFold
	 *            the current fold
	 * @return the other players order
	 */
	@Override
	public List<Player> getPlayersOrder(Done done, Player player, Fold currentFold) {
		List<Player> players = new ArrayList<>();
		final List<Player> donePlayers = done.getPlayers();
		int playerIndex = 0;
		// Look for player index
		for (int i = 0; i < donePlayers.size(); i++) {
			Player p = donePlayers.get(i);
			if (p.getName().equals(player.getName())) {
				playerIndex = i;
			}
		}
		for (int i = 0; i < donePlayers.size(); i++) {
			players.add(donePlayers.get((i + playerIndex) % donePlayers.size()));
		}
		// Remove already played players
		currentFold.getCards().stream().forEach(e -> players.remove(e));
		return players;
	}

	@Override
	public boolean canGivePoints(Fold fold, Player player, Done done) {
		boolean givePoints = false;
		if (fold.getCards().isEmpty()) {
			throw new UnsupportedOperationException("To know if we can give points, we must have a not empty fold !");
		}
		if (fold.getCards().stream().filter(card -> card.getColor() == Color.ATOUT).count() != 0) {
			List<Card> foldCards = new ArrayList<>(fold.getCards());
			foldCards.sort((e1, e2) -> e2.getName().getPower() - e1.getName().getPower());
			// There is a cut. Is it possible to give points ?

			// Who has the highest atout on the fold ?
			if (foldCards.get(0).getOwner().getTeam() == player.getTeam()) {
				if (isTeamPlayTheLast(done, fold, player)) {
					givePoints = true;
				}
			}
		}
		return givePoints;
	}

	boolean isTeamPlayTheLast(Done done, Fold fold, Player player) {
		boolean theLast = false;
		// We can't take a risk if we don't play at the last position so we have
		// to be sure of the scenario.

		// We estimate than the player team doesn't play the last if
		// - The team are unknown, and the player is the taker (so in attack,
		// but alone for the moment)
		// - The team are unknown and the player doesn't know who are his team
		// mates. (later, getTeam couldn't return unknown anymore, we always
		// know if we are in a defense team or attack team)
		// FIXME: delete test on player team == unknown.
		if (getTeam(player) == Team.UNKNOWN
				|| (done.getPlayers().stream().filter(p -> p.getTeam() == Team.UNKNOWN).count() != 0
						&& getTeam(player) == Team.ATTACK)) {
			theLast = fold.getCards().size() == done.getPlayers().size() - 1;
		} else {
			// The team are known
			int otherTeamMatesNumber = (int) done.getPlayers().stream().filter(e -> e.getTeam() != getTeam(player))
					.count();
			int otherTeamMatesCard = 0;
			for (Card card : fold.getCards()) {
				if (card.getOwner().getTeam() != getTeam(player)) {
					otherTeamMatesCard++;
				}
			}
			// If all other team mates have played, the player's team play at
			// end ...
			theLast = otherTeamMatesCard == otherTeamMatesNumber;
		}
		return theLast;
	}

	private Team getTeam(Player player) {
		// FIXME: later, we could ask if the called card (obviously the called
		// king) is our hand in case of unknown team.
		// But for the moment, we can just give back the player's team.
		return player.getTeam();
	}

}
