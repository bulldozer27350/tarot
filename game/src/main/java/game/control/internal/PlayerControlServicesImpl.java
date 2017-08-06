package game.control.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import game.control.FoldControlServices;
import game.control.PlayerControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Card.Name;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;
import game.domain.Player.Team;

public class PlayerControlServicesImpl implements PlayerControlServices {

	private final FoldControlServices foldControlServices;
	
	public PlayerControlServicesImpl(FoldControlServices foldControlServicesArg) {
		this.foldControlServices = foldControlServicesArg;
	}
	
	@Override
	public void addHand(Player player, Card card) {
		player.getHand().add(card);
	}

	@Override
	public void addFold(Player player, Fold cards) {
		player.getFolds().add(cards);
	}

	@Override
	public Card playCard(Done done, Player player, Fold fold, List<Fold> previousFolds) {
		Card card = null;
		List<Card> playerHand = player.getHand();
		if (fold.getCards().isEmpty()) {
			fold.setPlayedColor(null);
		} else if (fold.getCards().get(0).getColor() == Color.AUTRE) {
			if (fold.getCards().size() > 1) {
				fold.setPlayedColor(fold.getCards().get(1).getColor());
			} else {
				fold.setPlayedColor(Color.AUTRE);
			}
		}
		// The player is the FIRST player, no restriction on his played card.
		if (fold.getCards().isEmpty()) {
			// Choose a random card
			card = selectFirstCardToPlay(done, player, playerHand, fold, previousFolds);
			fold.setPlayedColor(card.getColor());
		} else {
			Color foldColor = Color.CARREAU;
			// The first card is the WildCard
			if (fold.getCards().get(0).getColor() == Color.AUTRE) {
				// The player can choose the card color(Wild Card has been
				// played first, and the player has to play immediately later.
				// No restriction on the card color he wants to play
				if (fold.getCards().size() == 1) {
					card = selectColorCardToPlay(done, player, playerHand, fold, previousFolds);
					fold.setPlayedColor(card.getColor());
				} else {
					foldColor = fold.getCards().get(1).getColor();
					fold.setPlayedColor(foldColor);
				}
			} else {
				foldColor = fold.getCards().get(0).getColor();
			}
			if (card == null) {
				// select a random card from the correct color.
				final Color finalFoldColor = foldColor;
				if (finalFoldColor != Color.ATOUT) {
					List<Card> goodColorCards = playerHand.stream().filter(c -> c.getColor() == finalFoldColor)
							.collect(Collectors.toList());
					// Player doesn't have the good color anymore, he has to
					// select an atout to cut
					if (goodColorCards.isEmpty()) {
						goodColorCards = playerHand.stream().filter(c -> c.getColor() == Color.ATOUT)
								.collect(Collectors.toList());
					}
					// If he has no more atout, he has to select a random card
					// in his hand
					if (goodColorCards.isEmpty()) {
						goodColorCards = playerHand;
					}
					card = selectColorCardToPlay(done, player, goodColorCards, fold, previousFolds);
				} else {
					card = selectRandomAtout(done, player, playerHand, fold, previousFolds);
				}
			}
		}
		// Add card to fold.
		fold.getCards().add(card);
		// remove card from player's hand
		playerHand.remove(card);
		return card;
	}

	private Card selectFirstCardToPlay(Done done, Player player, List<Card> playerHand, Fold fold,
			List<Fold> previousFolds) {
		Card card = null;
		List<Card> excuse = playerHand.stream().filter(c -> c.getColor() == Color.AUTRE).collect(Collectors.toList());

		// Particular case of Excuse
		if (playerHand.size() == 2 && excuse.size() == 1) {
			card = excuse.get(0);
		} else if (playerHand.size() == 1) {
			card = playerHand.get(0);
		} else {
			List<Player> others = getPlayersOrder(done, player, fold);
			List<Card> interestingCards = new ArrayList<>();
			List<Color> interestingColors = new ArrayList<>();
			boolean opener = false;
			if (getTeam(player) == Team.DEFENSE) {
				// If the first (better) or second player after him is in other
				// team, we can try to open a newest color
				if (getTeam(others.get(1)) == Team.ATTACK || getTeam(others.get(2)) == Team.ATTACK) {
					interestingColors = getNotYetOpenedColors(done);
					opener = true;
				} else {
					interestingColors = getAlreadyOpenedColors(done);
				}
			} else {
				if (getTeam(others.get(1)) == Team.ATTACK || getTeam(others.get(2)) == Team.ATTACK) {
					interestingColors = getAlreadyOpenedColors(done);
				} else {
					interestingColors = getNotYetOpenedColors(done);
					opener = true;
				}
			}
			for (Color color : interestingColors) {
				List<Card> colorCards = playerHand.stream().filter(c -> c.getColor() == color)
						.collect(Collectors.toList());
				for (Card colorCard : colorCards) {
					if (opener) {
						// If the player want to open, try to not give points
						// FIXME: if the player's cut has already been found, we
						// could perhaps play a king ...
						if (colorCard.getPointsValue() == 0.5) {
							interestingCards.add(colorCard);
						}
					} else {
						// We don't want to open, so play a little card or the
						// masterCard if in hand
						if (foldControlServices.isCardMostPowerful(previousFolds, colorCard, done)
								|| colorCard.getPointsValue() == 0.5) {
							interestingCards.add(colorCard);
						}
					}
				}
			}
			if (interestingCards.isEmpty()) {
				// Quite bad but nothing is interesting to play ... so select a
				// random Card in no values cards (if exists ...)
				interestingCards = playerHand.stream().filter(c -> c.getPointsValue() == 0.5)
						.collect(Collectors.toList());
				if (interestingCards.isEmpty()) {
					interestingCards = playerHand;
				}
			}
			int cardIndex = (int) (Math.random() * interestingCards.size());
			card = interestingCards.get(cardIndex);
		}
		return card;
	}

	private List<Color> getAlreadyOpenedColors(Done done) {
		List<Color> allColors = new ArrayList<>();
		allColors.add(Color.PIQUE);
		allColors.add(Color.COEUR);
		allColors.add(Color.CARREAU);
		allColors.add(Color.TREFLE);
		allColors.removeAll(getNotYetOpenedColors(done));
		return allColors;
	}

	private List<Color> getNotYetOpenedColors(Done done) {
		List<Color> allColors = new ArrayList<>();
		allColors.add(Color.PIQUE);
		allColors.add(Color.COEUR);
		allColors.add(Color.CARREAU);
		allColors.add(Color.TREFLE);
		for (Player player : done.getPlayers()) {
			for (Fold fold : player.getFolds()) {
				allColors.remove(fold.getPlayedColor());
			}
		}
		return allColors;
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
	private List<Player> getPlayersOrder(Done done, Player player, Fold currentFold) {
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

	private boolean isTheTeamWinning(Fold fold, List<Fold> allFolds, Player player, Done done) {
		boolean winning = false;
		if (!fold.getCards().isEmpty()) {
			// Did the team are known ?
			Team playerTeam = getTeam(player);
			if (playerTeam != Team.UNKNOWN) {
				// Did the player's team mate already played ?
				boolean alreadyPlayed = false;
				List<Card> teammateCards = new ArrayList<>();
				// Check each card owner's team
				for (Card playedCard : fold.getCards()) {
					Team cardTeam = playedCard.getOwner().getTeam();
					if (cardTeam == playerTeam && cardTeam != Team.UNKNOWN) {
						alreadyPlayed = true;
						teammateCards.add(playedCard);
					}
				}
				if (alreadyPlayed) {
					for (Card card : teammateCards) {
						if (foldControlServices.canCardWinFold(fold, card) && foldControlServices.isCardMostPowerful(allFolds, card, done)) {
							winning = true;
						}
					}
				}
			}

		}
		return winning;
	}

	private Team getTeam(Player player) {
		// FIXME: later, we could ask if the called card (obviously the called
		// king) is our hand in case of unknown team.
		// But for the moment, we can just give back the player's team.
		return player.getTeam();
	}

	private Card selectRandomAtout(Done done, Player player, List<Card> cards, Fold fold, List<Fold> previousFolds) {
		Card card = null;
		int mostPowerfullCard = 0;
		for (Card foldCard : fold.getCards()) {
			if (foldCard.getName().getPower() > mostPowerfullCard) {
				mostPowerfullCard = foldCard.getName().getPower();
			}
		}
		List<Card> atouts = cards.stream().filter(c -> c.getColor() == Color.ATOUT).collect(Collectors.toList());
		atouts.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		for (Card atout : atouts) {
			if (atout.getName().getPower() > mostPowerfullCard) {
				card = atout;
				break;
			}
		}
		// No stronger atout has been found.
		if (card == null) {
			if (atouts.isEmpty()) {
				card = discardCard(cards);
			} else {
				card = selectColorCardToPlay(done, player, atouts, fold, previousFolds);
			}
		}
		return card;
	}

	private Card discardCard(List<Card> cards) {
		Card randomCard = null;
		if (cards.size() == 1) {
			randomCard = cards.get(0);
		} else {
			Random r = new Random();
			int index = r.nextInt(cards.size());
			randomCard = cards.get(index);
		}
		return randomCard;
	}

	private Card selectColorCardToPlay(Done done, Player player, List<Card> cards, Fold fold,
			List<Fold> previousFolds) {
		Card randomCard = null;
		// Before the last turn, if the player has the excuse, play it to avoid
		// to loose it
		if (cards.size() == 2 && cards.stream().filter(c -> c.getColor() == Color.AUTRE).count() == 1) {
			randomCard = cards.stream().filter(c -> c.getColor() == Color.AUTRE).collect(Collectors.toList()).get(0);
		}
		// If the player play at the last position, and there is no cut, he can
		// play his strongest card if he could win
		else if (isTeamPlayTheLast(done, fold, player)
				&& fold.getCards().stream().filter(c -> c.getColor() == Color.ATOUT).count() == 0) {
			// We would like to play the most powerful card if
			// - this card is the master card in the current fold
			// - the team is winning and the player can give some points (if
			// there is no points to give, it is better to keep "most powerful"
			// cards)
			if (foldControlServices.canCardWinFold(fold, cards.get(cards.size() - 1))
					|| (isTheTeamWinning(fold, previousFolds, player, done)
							&& cards.get(cards.size() - 1).getPointsValue() > 0.5)) {
				randomCard = cards.get(cards.size() - 1);
			} else {
				// Otherwise, it is better to select the less powerful card ...
				randomCard = cards.get(0);
			}
		}
		// the player is not the last player
		else {
			// Is the powerful card in the hand is a good card to play ? Yes if
			// - the card is master in his color (a King, or a Queen if the King
			// is already passed, ...)
			// - the team is winning the fold and the player has points to give
			if (foldControlServices.isCardMostPowerful(previousFolds, cards.get(cards.size() - 1), done)
					|| (isTheTeamWinning(fold, previousFolds, player, done)
							&& cards.get(cards.size() - 1).getPointsValue() > 0.5)) {
				// If the player plays at the first position, it could be
				// interesting not to try to but the powerful card in his hand,
				// but to try each color
				// in order to know if the player has a master card in a color
				randomCard = cards.get(cards.size() - 1);
			} else {
				randomCard = cards.get(0);
			}
		}
		// Nothing seems to be a good choice, so we can try to select lowest
		// card in order to lose the less points ...
		cards.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		if (randomCard == null) {
			randomCard = cards.get(0);
		}
		return randomCard;
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

}
