package game.control.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import game.control.PlayerControlServices;
import game.domain.Card;
import game.domain.Card.Color;
import game.domain.Card.Name;
import game.domain.Done;
import game.domain.Player;
import game.domain.Player.Team;

public class PlayerControlServicesImpl implements PlayerControlServices {

	@Override
	public void addHand(Player player, Card card) {
		player.getHand().add(card);
	}

	@Override
	public void addFold(Player player, List<Card> cards) {
		player.getFolds().add(cards);
	}

	@Override
	public Card playCard(Done done, Player player, List<Card> fold, List<Card> previousFolds) {
		Card card = null;
		List<Card> playerHand = player.getHand();
		// The player is the FIRST player, no restriction on his played card.
		if (fold.isEmpty()){
			// Choose a random card
			card = selectCardToPlay(done, player, playerHand, fold, previousFolds);
		}
		else {
			Color foldColor = Color.CARREAU;
			// The first card is the WildCard
			if (fold.get(0).getColor() == Color.AUTRE){
				// The player can choose the card color(Wild Card has been played first, and the player has to play immediately later.
				// No restriction on the card color he wants to play
				if (fold.size() == 1){
					card = selectCardToPlay(done, player, playerHand, fold, previousFolds);
				}
				else {
					foldColor = fold.get(1).getColor();
				}
			} else {
				foldColor = fold.get(0).getColor();
			}
			if (card == null){
				// select a random card from the correct color.
				final Color finalFoldColor = foldColor;
				if (finalFoldColor != Color.ATOUT) {
					List<Card> goodColorCards = playerHand.stream().filter(c->c.getColor() == finalFoldColor).collect(Collectors.toList());
					// Player doesn't have the good color anymore, he has to select an atout to cut
					if (goodColorCards.isEmpty()){
						goodColorCards = playerHand.stream().filter(c->c.getColor() == Color.ATOUT).collect(Collectors.toList());
					}
					// If he has no more atout, he has to select a random card in his hand
					if (goodColorCards.isEmpty()){
						goodColorCards = playerHand;
					}
					card = selectCardToPlay(done, player, goodColorCards, fold, previousFolds);
				} else {
					card = selectRandomAtout(done, player, playerHand, fold,previousFolds);
				}
			}
		}
		// Add card to fold.
		fold.add(card);
		// remove card from player's hand
		playerHand.remove(card);
		return card;
	}
	
	private boolean isTheTeamWinning(List<Card> fold, List<Card> allFolds, Player player){
		boolean winning = false;
		if (!fold.isEmpty()){
			// Did the team are known ?
			Team playerTeam = getTeam(player);
			if (playerTeam != Team.UNKNOWN){
				// Did the player's team mate already played ?
				boolean alreadyPlayed = false;
				List<Card> teammateCards = new ArrayList<>();
				// Check each card owner's team
				for (Card playedCard : fold){
					Team cardTeam = playedCard.getOwner().getTeam();
					if (cardTeam == playerTeam && cardTeam != Team.UNKNOWN){
						alreadyPlayed = true;
						teammateCards.add(playedCard);
					}
				}
				if (alreadyPlayed) {
					for (Card card : teammateCards){
						if (isMasterCardWithCurrentFold(card, fold) && isMasterCardWithOldFolds(card, allFolds)){
							winning = true;
						}
					}
				}
			}
			
		}
		return winning;
	}
		
	private Team getTeam(Player player){
		// FIXME: later, we could ask if the called card (obviously the called king) is our hand in case of unknown team.
		// But for the moment, we can just give back the player's team.
		return player.getTeam();
	}
	
	private Card selectRandomAtout(Done done, Player player, List<Card> cards, List<Card> fold,List<Card> previousFolds) {
		Card card = null;
		int mostPowerfullCard = 0;
		for (Card foldCard : fold){
			if (foldCard.getName().getPower() > mostPowerfullCard){
				mostPowerfullCard = foldCard.getName().getPower();
			}
		}
		List<Card> atouts = cards.stream().filter(c -> c.getColor() == Color.ATOUT).collect(Collectors.toList());
		atouts.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		for (Card atout : atouts){
			if (atout.getName().getPower() > mostPowerfullCard){
				card = atout;
				break;
			}
		}
		// No stronger atout has been found.
		if (card == null){
			if (atouts.isEmpty()){
				card = discardCard(cards);
			} else {
				card = selectCardToPlay(done, player, atouts, fold, previousFolds);
			}
		}
		return card;
	}

	private Card discardCard(List<Card> cards){
		Card randomCard = null;
		if (cards.size() == 1){
			randomCard = cards.get(0);
		} else {
			Random r = new Random();
			int index = r.nextInt(cards.size());
			randomCard = cards.get(index);
		}
		return randomCard;
	}
	
	private Card selectCardToPlay(Done done, Player player, List<Card> cards, List<Card> fold, List<Card> previousFolds){
		Card randomCard = null;
		// If the player play at the last position, and there is no cut, he can play his strongest card if he could win
		if (isTeamPlayTheLast(done, fold, player) && fold.stream().filter(c->c.getColor() == Color.ATOUT).count() == 0){
			// We would like to play the most powerful card if 
			// - this card is the master card in the current fold
			// - the team is winning and the player can give some points (if there is no points to give, it is better to keep "most powerful" cards)
			if (isMasterCardWithCurrentFold(cards.get(cards.size() - 1), fold) || (isTheTeamWinning(fold, previousFolds, player) && cards.get(cards.size() - 1).getPointsValue() > 0.5)) {
				randomCard = cards.get(cards.size() - 1);
			}
			else {
				// Otherwise, it is better to select the less powerful card ...
				randomCard = cards.get(0);
			}
		} 
		// the player is not the last player
		else {
			// Is the powerful card in the hand is a good card to play ? Yes if
			// - the card is master in his color (a King, or a Queen if the King is already passed, ...)
			// - the team is winning the fold and the player has points to give
			if (isMasterCardWithOldFolds(cards.get(cards.size() - 1), previousFolds) || (isTheTeamWinning(fold, previousFolds, player) && cards.get(cards.size() - 1).getPointsValue() > 0.5)) {
				// If the player plays at the first position, it could be interesting not to try to but the powerful card in his hand, but to try each color 
				// in order to know if the player has a mastercard in a color 
				randomCard = cards.get(cards.size() - 1);
			} else {
				randomCard = cards.get(0);
			}
		}
		// Nothing seems to be a good choice, so we can try to select lowest card in order to lose the less points ...
		cards.sort((c1,c2)->c1.getName().getPower() - c2.getName().getPower());
		if (randomCard == null){
			randomCard = cards.get(0);
		}
		return randomCard;
	}

	private boolean isTeamPlayTheLast(Done done, List<Card> fold, Player player) {
		boolean theLast = false;
		// We can't take a risk if we don't play at the last position so we have to be sure of the scenario.
		
		// We estimate than the player team doesn't play the last if
		// - The team are unknown, and the player is the taker (so in attack, but alone for the moment)
		// - The team are unknown and the player doesn't know who are his team mates. (later, getTeam couldn't return unknown anymore, we always know if we are in a defense team or attack team)
		// FIXME: delete test on player team == unknown.
		if (getTeam(player) == Team.UNKNOWN || (done.getPlayers().stream().filter(p->p.getTeam() == Team.UNKNOWN).count() != 0 && getTeam(player) == Team.ATTACK)) {
			theLast = fold.size() == done.getPlayers().size() - 1;
		} else {
			// The team are known
			int otherTeamMatesNumber = (int) done.getPlayers().stream().filter(e->e.getTeam() != getTeam(player)).count();
			int otherTeamMatesCard = 0;
			for (Card card : fold){
				if (card.getOwner().getTeam() != getTeam(player)) {
					otherTeamMatesCard++;
				}
			}
			// If all other team mates have played, the player's team play at end ...
			theLast = otherTeamMatesCard == otherTeamMatesNumber;
		}
		return theLast;
	}
	
	private boolean isMasterCardWithCurrentFold(Card wantedCard, List<Card> fold) {
		boolean good = false;
		int highestValue = 0;
		Card strongestCard = null;
		for (Card card : fold){
			if (card.getName().getPower() > highestValue){
				highestValue = card.getName().getPower();
				strongestCard = card;
			}
		}
		if (strongestCard == null){
			good = true;
		} else {
			good = wantedCard.getName().getPower()>=strongestCard.getName().getPower();
		}
		return good;
	}

	private List<Name> getRemainingColorCards(List<Card> foldedCards, Color selectedColor){
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
		foldedCards.stream().filter(e->e.getColor() == selectedColor).forEach(e->remainingCards.add(e.getName()));
		values.removeIf(e->remainingCards.contains(e));
		return values;
	}
	
	private boolean isMasterCardWithOldFolds(Card wantedCard, List<Card> foldedCards){
		boolean good = false;
		List<Name> remainingNames = getRemainingColorCards(foldedCards, wantedCard.getColor());
		good = remainingNames.get(remainingNames.size()-1).getPower() == wantedCard.getName().getPower();
		return good;
	}

}
