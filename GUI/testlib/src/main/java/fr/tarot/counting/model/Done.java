package fr.tarot.counting.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Done.
 */
public class Done implements Serializable {

    /**
     * The players.
     */
    private List<Player> players = new ArrayList<>();

    /**
     * The deads.
     */
    private List<Player> deads = new ArrayList<>();

    /**
     * The giver.
     */
    private Player giver;

    /**
     * The taker.
     */
    private Player taker;

    /**
     * The called.
     */
    private Player called;

    /**
     * The type.
     */
    private DoneType type;

    /**
     * The called color.
     */
    private KingColor calledColor;

    /**
     * The bonuses.
     */
    private List<Bonus> bonuses = new ArrayList<>();

    /**
     * The little for taker.
     */
    private boolean littleForTaker;

    /**
     * The twenty one for player.
     */
    private boolean twentyOneForPlayer;

    /**
     * The excuse for player.
     */
    private boolean excuseForPlayer;

    /**
     * The points for taker team.
     */
    private int pointsForTakerTeam;

    /**
     * Checks if is little for taker.
     *
     * @return true, if is little for taker
     */
    public boolean isLittleForTaker() {
        return littleForTaker;
    }

    /**
     * Sets the little for taker.
     *
     * @param littleForTaker the new little for taker
     */
    public void setLittleForTaker(boolean littleForTaker) {
        this.littleForTaker = littleForTaker;
    }

    /**
     * Checks if is twenty one for player.
     *
     * @return true, if is twenty one for player
     */
    public boolean isTwentyOneForPlayer() {
        return twentyOneForPlayer;
    }

    /**
     * Sets the twenty one for player.
     *
     * @param twentyOneForPlayer the new twenty one for player
     */
    public void setTwentyOneForPlayer(boolean twentyOneForPlayer) {
        this.twentyOneForPlayer = twentyOneForPlayer;
    }

    /**
     * Checks if is excuse for player.
     *
     * @return true, if is excuse for player
     */
    public boolean isExcuseForPlayer() {
        return excuseForPlayer;
    }

    /**
     * Sets the excuse for player.
     *
     * @param excuseForPlayer the new excuse for player
     */
    public void setExcuseForPlayer(boolean excuseForPlayer) {
        this.excuseForPlayer = excuseForPlayer;
    }

    /**
     * Gets the players.
     *
     * @return the players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the players.
     *
     * @param players the new players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Gets the giver.
     *
     * @return the giver
     */
    public Player getGiver() {
        return giver;
    }

    /**
     * Sets the giver.
     *
     * @param giver the new giver
     */
    public void setGiver(Player giver) {
        this.giver = giver;
    }

    /**
     * Gets the taker.
     *
     * @return the taker
     */
    public Player getTaker() {
        return taker;
    }

    /**
     * Sets the taker.
     *
     * @param taker the new taker
     */
    public void setTaker(Player taker) {
        this.taker = taker;
    }

    /**
     * Gets the called.
     *
     * @return the called
     */
    public Player getCalled() {
        return called;
    }

    /**
     * Sets the called.
     *
     * @param called the new called
     */
    public void setCalled(Player called) {
        this.called = called;
    }

    /**
     * Gets the type.
     *
     * @return the type
     */
    public DoneType getType() {
        return type;
    }

    /**
     * Sets the type.
     *
     * @param type the new type
     */
    public void setType(DoneType type) {
        this.type = type;
    }

    /**
     * Gets the called color.
     *
     * @return the called color
     */
    public KingColor getCalledColor() {
        return calledColor;
    }

    /**
     * Sets the called color.
     *
     * @param calledColor the new called color
     */
    public void setCalledColor(KingColor calledColor) {
        this.calledColor = calledColor;
    }

    /**
     * Gets the bonuses.
     *
     * @return the bonuses
     */
    public List<Bonus> getBonuses() {
        return bonuses;
    }

    /**
     * Sets the bonuses.
     *
     * @param bonuses the new bonuses
     */
    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }

    /**
     * Gets the deads.
     *
     * @return the deads
     */
    public List<Player> getDeads() {
        return deads;
    }

    /**
     * Sets the deads.
     *
     * @param deads the new deads
     */
    public void setDeads(List<Player> deads) {
        this.deads = deads;
    }

    /**
     * Gets the points for taker team.
     *
     * @return the points for taker team
     */
    public int getPointsForTakerTeam() {
        return pointsForTakerTeam;
    }

    /**
     * Sets the points for taker team.
     *
     * @param pointsForTakerTeam the new points for taker team
     */
    public void setPointsForTakerTeam(int pointsForTakerTeam) {
        this.pointsForTakerTeam = pointsForTakerTeam;
    }
}
