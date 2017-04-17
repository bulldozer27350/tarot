package fr.tarot.counting.control.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.tarot.counting.control.BonusControlServices;
import fr.tarot.counting.model.Bonus;
import fr.tarot.counting.model.Done;

// TODO: Auto-generated Javadoc

/**
 * The Class BonusControlServicesImpl.
 */
public class BonusControlServicesImpl implements BonusControlServices {

    @Override
    public List<Bonus> getAllBonuses() {
        List<Bonus> bonuses = new ArrayList<>();

        bonuses.addAll(Arrays.asList(Bonus.values()));

        return bonuses;
    }

    @Override
    public void addBonus(Done done, Bonus bonus) {
        done.getBonuses().add(bonus);
    }

}
