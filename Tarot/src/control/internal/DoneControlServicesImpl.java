package control.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import control.DoneControlServices;
import model.Bonus;
import model.Done;
import model.Player;

public class DoneControlServicesImpl implements DoneControlServices {

	@Override
	public void computePoints(Done done) {
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
		int diff = getArrondi(Math.abs(done.getPointsForTakerTeam() - nbPointsToDo));
		int littleAtEnd = getLittleAtEndBonusPoints(done);
		int hands = getHandBonusPoints(done, isVictory);
		int contrat = 10;
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

	private List<Player> getAttackTeam(Done done) {
		List<Player> attack = new ArrayList<>();
		attack.add(done.getTaker());
		Player called = done.getCalled();
		if (!attack.contains(called)) {
			attack.add(called);
		}
		return attack;
	}

	private void computeDefenseScore(Done done, boolean isVictory, int singlePoints, List<Player> defenseTeam) {
		for (Player player : defenseTeam) {
			player.setPoints(player.getPoints() - singlePoints);
		}
	}

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

	public int getArrondi(int nombre) {
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
		return round;
	}

}
