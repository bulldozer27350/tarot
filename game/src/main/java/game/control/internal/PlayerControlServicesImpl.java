package game.control.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import game.control.FoldControlServices;
import game.control.PlayerControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Done;
import game.domain.Fold;
import game.domain.Player;
import game.domain.Player.Team;

public class PlayerControlServicesImpl implements PlayerControlServices {

	private final FoldControlServices foldControlServices;

	public PlayerControlServicesImpl(final FoldControlServices foldControlServicesArg) {
		this.foldControlServices = foldControlServicesArg;
	}

	@Override
	public void addHand(final Player player, final Card card) {
		player.getHand().add(card);
	}

	@Override
	public void addFold(final Player player, final Fold cards) {
		player.getFolds().add(cards);
	}

	@Override
	public Card playCard(final Done done, final Player player, final Fold fold, final List<Fold> previousFolds) {
		Card card = null;
		final List<Card> playerHand = player.getHand();
		if (fold.getCards().isEmpty()) {
			fold.setPlayedColor(null);
		} else if (fold.getCards().get(0).getColor() == Color.OTHER) {
			if (fold.getCards().size() > 1) {
				fold.setPlayedColor(fold.getCards().get(1).getColor());
			} else {
				fold.setPlayedColor(Color.OTHER);
			}
		}
		// The player is the FIRST player, no restriction on his played card.
		if (fold.getCards().isEmpty()) {
			// Choose a random card
			card = this.selectFirstCardToPlay(done, player, playerHand, fold, previousFolds);
			fold.setPlayedColor(card.getColor());
		} else {
			Color foldColor = Color.DIAMOND;
			// The first card is the WildCard
			if (fold.getCards().get(0).getColor() == Color.OTHER) {
				// The player can choose the card color(Wild Card has been
				// played first, and the player has to play immediately later.
				// No restriction on the card color he wants to play
				if (fold.getCards().size() == 1) {
					card = this.selectColorCardToPlay(done, player, playerHand, fold, previousFolds);
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
				if (finalFoldColor != Color.TRUMP) {
					// trump is not fold color
					List<Card> goodColorCards = playerHand.stream().filter(c -> c.getColor() == finalFoldColor)
							.collect(Collectors.toList());
					// Player doesn't have the good color anymore, he has to
					// select an trump to cut
					if (goodColorCards.isEmpty()) {
						goodColorCards = playerHand.stream().filter(c -> c.getColor() == Color.TRUMP)
								.collect(Collectors.toList());
						// If he has no more trump, he has to select a random
						// card in his hand
						if (goodColorCards.isEmpty()) {
							goodColorCards = playerHand;
							card = this.selectColorCardToPlay(done, player, goodColorCards, fold, previousFolds);
						} else {
							card = this.selectRandomTrump(done, player, playerHand, fold, previousFolds);
						}
					} else {
						// he has the right color. Select a card in this color
						card = this.selectColorCardToPlay(done, player, goodColorCards, fold, previousFolds);
					}
				} else {
					List<Card> goodColorCards = playerHand.stream().filter(c -> c.getColor() == Color.TRUMP)
							.collect(Collectors.toList());
					if (goodColorCards.isEmpty()) {
						goodColorCards = playerHand;
						card = this.selectColorCardToPlay(done, player, goodColorCards, fold, previousFolds);
					} else {
						card = this.selectRandomTrump(done, player, playerHand, fold, previousFolds);
					}
				}
			}
		}
		// Add card to fold.
		fold.getCards().add(card);
		// remove card from player's hand
		playerHand.remove(card);
		return card;
	}

	private Card selectFirstCardToPlay(final Done done, final Player player, final List<Card> playerHand,
			final Fold fold, final List<Fold> previousFolds) {
		Card card = null;
		final List<Card> excuse = playerHand.stream().filter(c -> c.getColor() == Color.OTHER)
				.collect(Collectors.toList());

		// Particular case of Excuse
		if ((playerHand.size() == 2) && (excuse.size() == 1)) {
			card = excuse.get(0);
		} else if (playerHand.size() == 1) {
			card = playerHand.get(0);
		} else {
			final Map<Color, Integer> colorCardsNumber = new HashMap<>();
			colorCardsNumber.put(Color.TRUMP, 0);
			colorCardsNumber.put(Color.OTHER, 0);
			colorCardsNumber.put(Color.SPADE, 0);
			colorCardsNumber.put(Color.HEART, 0);
			colorCardsNumber.put(Color.DIAMOND, 0);
			colorCardsNumber.put(Color.CLUB, 0);
			playerHand.stream()
					.forEach(c -> colorCardsNumber.put(c.getColor(), colorCardsNumber.get(c.getColor()) + 1));
			final List<Player> others = this.foldControlServices.getPlayersOrder(done, player, fold);
			List<Card> interestingCards = new ArrayList<>();
			List<Color> interestingColors = new ArrayList<>();
			boolean opener = false;
			if (this.getTeam(player) == Team.DEFENSE) {
				// If the first (better) or second player after him is in other
				// team, we can try to open a newest color
				if ((this.getTeam(others.get(1)) == Team.ATTACK) || (this.getTeam(others.get(2)) == Team.ATTACK)) {
					interestingColors = this.getNotYetOpenedColors(done);
					// Sort color from their min cards number to their max
					colorCardsNumber.keySet().stream()
							.sorted((e1, e2) -> colorCardsNumber.get(e2) - colorCardsNumber.get(e1));
					opener = true;
				} else {
					interestingColors = this.getAlreadyOpenedColors(done);
				}
			} else {
				if ((this.getTeam(others.get(1)) == Team.ATTACK) || (this.getTeam(others.get(2)) == Team.ATTACK)) {
					interestingColors = this.getAlreadyOpenedColors(done);
				} else {
					interestingColors = this.getNotYetOpenedColors(done);
					// Sort color from their min cards number to their max
					colorCardsNumber.keySet().stream()
							.sorted((e1, e2) -> colorCardsNumber.get(e1) - colorCardsNumber.get(e2));
					opener = true;
				}
			}
			final List<Color> colorsOrder = new ArrayList<>(colorCardsNumber.keySet());
			colorsOrder.removeIf(e -> colorCardsNumber.get(e) == 0);
			for (final Color color : interestingColors) {
				final List<Card> colorCards = playerHand.stream().filter(c -> c.getColor() == color)
						.collect(Collectors.toList());
				for (final Card colorCard : colorCards) {
					if (colorCard.getColor() == colorsOrder.get(0)) {
						if (opener) {
							// If the player want to open, try to not give
							// points
							// FIXME: if the player's cut has already been
							// found, we
							// could perhaps play a king ...
							if (colorCard.getPointsValue() == 0.5) {
								interestingCards.add(colorCard);
							}
						} else {
							// We don't want to open, so play a little card or
							// the
							// masterCard if in hand
							if (this.foldControlServices.isCardMostPowerful(previousFolds, colorCard, done)
									|| (colorCard.getPointsValue() == 0.5)) {
								interestingCards.add(colorCard);
							}
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
			final int cardIndex = (int) (Math.random() * interestingCards.size());
			card = interestingCards.get(cardIndex);
		}
		return card;
	}

	List<Color> getAlreadyOpenedColors(final Done done) {
		final List<Color> allColors = new ArrayList<>();
		allColors.add(Color.SPADE);
		allColors.add(Color.HEART);
		allColors.add(Color.DIAMOND);
		allColors.add(Color.CLUB);
		allColors.removeAll(this.getNotYetOpenedColors(done));
		return allColors;
	}

	List<Color> getNotYetOpenedColors(final Done done) {
		final List<Color> allColors = new ArrayList<>();
		allColors.add(Color.SPADE);
		allColors.add(Color.HEART);
		allColors.add(Color.DIAMOND);
		allColors.add(Color.CLUB);
		for (final Player player : done.getPlayers()) {
			for (final Fold fold : player.getFolds()) {
				allColors.remove(fold.getPlayedColor());
			}
		}
		return allColors;
	}

	private boolean isTheTeamWinning(final Fold fold, final List<Fold> allFolds, final Player player, final Done done) {
		boolean winning = false;
		if (!fold.getCards().isEmpty()) {
			// Did the team are known ?
			final Team playerTeam = this.getTeam(player);
			if (playerTeam != Team.UNKNOWN) {
				// Did the player's team mate already played ?
				boolean alreadyPlayed = false;
				final List<Card> teammateCards = new ArrayList<>();
				// Check each card owner's team
				for (final Card playedCard : fold.getCards()) {
					final Team cardTeam = playedCard.getOwner().getTeam();
					if ((cardTeam == playerTeam) && (cardTeam != Team.UNKNOWN)) {
						alreadyPlayed = true;
						teammateCards.add(playedCard);
					}
				}
				if (alreadyPlayed) {
					for (final Card card : teammateCards) {
						if (this.foldControlServices.canCardWinFold(fold, card)
								&& this.foldControlServices.isCardMostPowerful(allFolds, card, done)) {
							winning = true;
						}
					}
				}
			}

		}
		return winning;
	}

	private Team getTeam(final Player player) {
		// FIXME: later, we could ask if the called card (obviously the called
		// king) is our hand in case of unknown team.
		// But for the moment, we can just give back the player's team.
		return player.getTeam();
	}

	Card selectRandomTrump(final Done done, final Player player, final List<Card> cards, final Fold fold,
			final List<Fold> previousFolds) {
		Card card = null;
		int mostPowerfullCard = 0;
		for (final Card foldCard : fold.getCards()) {
			if (foldCard.getName().getPower() > mostPowerfullCard) {
				mostPowerfullCard = foldCard.getName().getPower();
			}
		}
		final List<Card> trumps = cards.stream().filter(c -> c.getColor() == Color.TRUMP).collect(Collectors.toList());
		trumps.sort((c1, c2) -> c1.getName().getPower() - c2.getName().getPower());
		for (final Card trump : trumps) {
			if (trump.getName().getPower() > mostPowerfullCard) {
				card = trump;
				break;
			}
		}
		// No stronger trump has been found.
		if (card == null) {
			if (trumps.isEmpty()) {
				card = this.selectColorCardToPlay(done, player, cards, fold, previousFolds);
			} else {
				card = this.selectColorCardToPlay(done, player, trumps, fold, previousFolds);
			}
		}
		return card;
	}

	Card selectColorCardToPlay(final Done done, final Player player, final List<Card> cards, final Fold fold,
			final List<Fold> previousFolds) {
		Card randomCard = null;
		// Before the last turn, if the player has the excuse, play it to avoid
		// to loose it
		if ((cards.size() == 2) && (cards.stream().filter(c -> c.getColor() == Color.OTHER).count() == 1)) {
			randomCard = cards.stream().filter(c -> c.getColor() == Color.OTHER).collect(Collectors.toList()).get(0);
		} else if (fold.getCards().stream().filter(c -> c.getColor() == Color.TRUMP).count() != 0) {
			// There is a cut.
			if (this.foldControlServices.canGivePoints(fold, player, done)) {
				randomCard = cards.get(cards.size() - 1);
			} else {
				randomCard = cards.get(0);
			}
		}
		// If the player play at the last position, and there is no cut, he can
		// play his strongest card if he could win
		else if (foldControlServices.isTeamPlayTheLast(done, fold, player)) {
			// We would like to play the most powerful card if
			// - this card is the master card in the current fold
			// - the team is winning and the player can give some points (if
			// there is no points to give, it is better to keep "most powerful"
			// cards)
			if (this.foldControlServices.canCardWinFold(fold, cards.get(cards.size() - 1))
					|| (this.isTheTeamWinning(fold, previousFolds, player, done)
							&& (cards.get(cards.size() - 1).getPointsValue() > 0.5))) {
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
			if (this.foldControlServices.isCardMostPowerful(previousFolds, cards.get(cards.size() - 1), done)
					|| (this.isTheTeamWinning(fold, previousFolds, player, done)
							&& (cards.get(cards.size() - 1).getPointsValue() > 0.5))) {
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

}
