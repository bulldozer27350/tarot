package fr.tarot.counting.control.internal;

import java.util.ArrayList;
import java.util.List;

import fr.tarot.counting.control.DoneControlServices;
import fr.tarot.counting.control.PlayerControlServices;
import fr.tarot.counting.model.Bonus;
import fr.tarot.counting.model.Done;
import fr.tarot.counting.model.Player;

/**
 * The Class DoneControlServicesImpl.
 */
public class DoneControlServicesImpl implements DoneControlServices {

	private PlayerControlServices playerControlServices;
	
	public DoneControlServicesImpl(PlayerControlServices playerControlServicesArg) {
		playerControlServices = playerControlServicesArg;
	}

	@Override
	public void computePoints(Done done) {
		
		// Visit http://fftarot.fr/index.php/Decouvrir/Le-Tarot-a-5-joueurs.html to have more examples
		boolean isVictory = false;
		List<Player> attackTeam = getAttackTeam(done);
		List<Player> defenseTeam = getDefenseTeam(done);
		List<Player> playersTeam = new ArrayList<>();
		playersTeam.addAll(attackTeam);
		playersTeam.addAll(defenseTeam);

		List<Player> winners = new ArrayList<>();
		if (isVictory) {
			winners.addAll(attackTeam);
		} else {
			winners.addAll(defenseTeam);
		}

		int nbPointsToDo = getNbPointsToDo(done);
		isVictory = done.getPointsForTakerTeam() >= nbPointsToDo;
		int diff = getRound(Math.abs(done.getPointsForTakerTeam() - nbPointsToDo));
		int littleAtEnd = getLittleAtEndBonusPoints(done);
		int hands = getHandBonusPoints(done, isVictory);
		int contrat = 25;
		int chelem = 0;
		int total1 = isVictory ? contrat + diff : 0 - (contrat + diff);
		System.out.println("total 1 : " + total1);
		int total2 = total1 + littleAtEnd;
		System.out.println("total 2 : " + total2);
		int total3 = total2 * done.getType().getCoefficient();
		System.out.println("total 3 : " + total3);
		int total = total3 + hands + chelem;
		System.out.println("total : " + total);
		computeAttackScore(done, isVictory, total);
		computeDefenseScore(done, isVictory, total, defenseTeam);
	}

	/**
	 * Gets the hand bonus points.
	 *
	 * @param done the done
	 * @param victory the victory
	 * @return the hand bonus points
	 */
	private int getHandBonusPoints(Done done, boolean victory) {
		int bonusPoints = 0;
		for (Bonus bonus : done.getBonuses()) {
			if (bonus.ordinal() == Bonus.SIMPLE_HAND.ordinal() || bonus.ordinal() == Bonus.DOUBLE_HAND.ordinal()
					|| bonus.ordinal() == Bonus.TRIPLE_HAND.ordinal()) {
				// doesn't matter who has a hand, if the taker wins, he wins the
				// points, if he looses, he looses the points.
				if (victory) {
					bonusPoints = bonus.getPoints();
				} else {
					bonusPoints = 0 - bonus.getPoints();
				}
			}
		}
		return bonusPoints;
	}

	/**
	 * Gets the defense team.
	 *
	 * @param done the done
	 * @return the defense team
	 */
	private List<Player> getDefenseTeam(Done done) {
		List<Player> defenseTeam = new ArrayList<>();
		for (Player player : done.getPlayers()) {
			if (!done.getDeads().contains(player)) {
				if (!player.equals(done.getTaker()) && !player.equals(done.getCalled())) {
					defenseTeam.add(player);
				}
			}
		}
		return defenseTeam;
	}

	/**
	 * Gets the attack team.
	 *
	 * @param done the done
	 * @return the attack team
	 */
	private List<Player> getAttackTeam(Done done) {
		List<Player> attack = new ArrayList<>();
		attack.add(done.getTaker());
		Player called = done.getCalled();
		if (!attack.contains(called)) {
			attack.add(called);
		}
		return attack;
	}

	/**
	 * Compute defense score.
	 *
	 * @param done the done
	 * @param isVictory the is victory
	 * @param singlePoints the single points
	 * @param defenseTeam the defense team
	 */
	private void computeDefenseScore(Done done, boolean isVictory, int singlePoints, List<Player> defenseTeam) {
		for (Player player : defenseTeam) {
			player.setPoints(player.getPoints() - singlePoints);
		}
	}

	/**
	 * Compute attack score.
	 *
	 * @param done the done
	 * @param victory the victory
	 * @param singlePoints the single points
	 */
	private void computeAttackScore(Done done, boolean victory, int singlePoints) {
		// Play alone (1 versus 4)
		if (done.getTaker().equals(done.getCalled())) {
			int score = done.getTaker().getPoints();
			score += singlePoints * 4;
			done.getTaker().setPoints(score);
			// Play 2 versus 3
		} else {
			done.getTaker().setPoints(done.getTaker().getPoints() + (singlePoints * 2));
			done.getCalled().setPoints(done.getCalled().getPoints() + (singlePoints));
		}
	}

	/**
	 * Gets the little at end bonus points.
	 *
	 * @param done the done
	 * @return the little at end bonus points
	 */
	private int getLittleAtEndBonusPoints(Done done) {
		int bonusPoints = 0;
		for (Bonus bonus : done.getBonuses()) {
			if (bonus.ordinal() == Bonus.LITTLE_AT_END.ordinal()) {
				// If attack
				if (bonus.getWho().size() == 1 || bonus.getWho().size() == 2) {
					bonusPoints += bonus.getPoints();
				} else {
					bonusPoints -= bonus.getPoints();
				}
			}
		}
		return bonusPoints;
	}

	/**
	 * Gets the nb points to do.
	 *
	 * @param done the done
	 * @return the nb points to do
	 */
	private int getNbPointsToDo(Done done) {
		int nbOudlers = 0;
		if (done.isExcuseForPlayer()) {
			nbOudlers++;
		}
		if (done.isLittleForTaker()) {
			nbOudlers++;
		}
		if (done.isTwentyOneForPlayer()) {
			nbOudlers++;
		}
		int nbPointsToDo = 56;
		if (nbOudlers == 1) {
			nbPointsToDo = 51;
		} else if (nbOudlers == 2) {
			nbPointsToDo = 41;
		} else if (nbOudlers == 3) {
			nbPointsToDo = 36;
		}
		return nbPointsToDo;
	}

	/**
	 * Gets the round.
	 *
	 * @param nombre the nombre
	 * @return the round
	 */
	public int getRound(int nombre) {
		int round = 0;
		int roundAt = 5;
		round = (int) (roundAt * (Math.ceil((nombre + roundAt / 2) / roundAt)));
		// if (nombre > roundAt) {
		// if (nombre % roundAt > roundAt / 2) {
		// round = (int) (nombre + Math.ceil(roundAt / 2));
		// } else {
		// round = (int) (nombre - Math.ceil(roundAt / 2));
		// }
		// } else {
		// if (nombre > roundAt / 2) {
		// round = (int) (nombre + Math.ceil(roundAt / 2));
		// } else {
		// round = (int) (nombre - Math.ceil(roundAt / 2));
		// }
		// }
		System.out.println("Arrondi de " + nombre + " = " + round);
		return nombre;
//		return round;
	}

	@Override
	public void addPlayer(Done done, Player player) {
		done.getPlayers().add(player);
	}

	public void addDead(Done done, Player player){
		done.getDeads().add(player);
	}
	
	@Override
	public String showResults(Done done) {
		StringBuilder builder = new StringBuilder();
		int total = 0;
		
		for (Player player : done.getPlayers()){
			builder.append(playerControlServices.showPlayer(done, player)).append("\n");
			if (!done.getDeads().contains(player)){
				total += player.getPoints();
			}
		}
		builder.append("Total : " + total);

		return builder.toString();
	}

}
