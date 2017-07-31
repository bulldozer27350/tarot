package game.domain;

public class Card {
	
	public enum Color {PIQUE, COEUR, CARREAU, TREFLE, ATOUT, AUTRE}
	
	public enum Name { 
		AS(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), 
		NEUF(9), DIX(10), VALET(11), CAVALIER(12), DAME(13), ROI(14), 
		UN_ATOUT(15), DEUX_ATOUT(16), TROIS_ATOUT(17), QUATRE_ATOUT(18), CINQ_ATOUT(19), 
		SIX_ATOUT(20), SEPT_ATOUT(21), HUIT_ATOUT(22), NEUF_ATOUT(23), DIX_ATOUT(24), 
		ONZE_ATOUT(25), DOUZE_ATOUT(26), TREIZE_ATOUT(27), QUATORZE_ATOUT(28), QUINZE_ATOUT(29), 
		SEIZE_ATOUT(30), DIX_SEPT_ATOUT(31), DIX_HUIT_ATOUT(32), DIX_NEUF_ATOUT(33), VINGT_ATOUT(34), 
		VING_ET_UN_ATOUT(35), EXCUSE(0);
		
		int power;
		private Name(int powerArg){
			power = powerArg;
		}
		
		public int getPower(){
			return power;
		}
	
	}
	
	private Color color;
	private Name name;
	private double pointsValue;
	private Player owner;
	
	public Card() {
	}
	
	public Card(Color cardColor, Name cardName, double cardPointsValue, Player ownerArg){
		this.color = cardColor;
		this.name = cardName;
		this.pointsValue = cardPointsValue;
		this.owner = ownerArg;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

	public Name getName() {
		return this.name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public double getPointsValue() {
		return this.pointsValue;
	}

	public void setPointsValue(double pointsValue) {
		this.pointsValue = pointsValue;
	}
	
	public Player getOwner() {
		return this.owner;
	}
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		String cardName = "";
		switch(name){
		case AS:
			cardName = "A";
			break;
		case CAVALIER:
			cardName = "C";
			break;
		case CINQ:
			cardName = "5";
			break;
		case DAME:
			cardName = "D";
			break;
		case DEUX:
			cardName = "2";
			break;
		case DIX:
			cardName = "10";
			break;
		case DIX_HUIT_ATOUT:
			cardName = "18";
			break;
		case DIX_NEUF_ATOUT:
			cardName = "19";
			break;
		case DIX_SEPT_ATOUT:
			cardName = "17";
			break;
		case DOUZE_ATOUT:
			cardName = "12";
			break;
		case EXCUSE:
			cardName = "*";
			break;
		case HUIT:
			cardName = "8";
			break;
		case NEUF:
			cardName = "9";
			break;
		case ONZE_ATOUT:
			cardName = "11";
			break;
		case QUATORZE_ATOUT:
			cardName = "14";
			break;
		case QUATRE:
			cardName = "4";
			break;
		case QUINZE_ATOUT:
			cardName = "15";
			break;
		case ROI:
			cardName = "R";
			break;
		case SEIZE_ATOUT:
			cardName = "16";
			break;
		case SEPT:
			cardName = "7";
			break;
		case SIX:
			cardName = "6";
			break;
		case TREIZE_ATOUT:
			cardName = "13";
			break;
		case TROIS:
			cardName = "3";
			break;
		case UN_ATOUT:
			cardName = "1";
			break;
		case VALET:
			cardName = "V";
			break;
		case VINGT_ATOUT:
			cardName = "20";
			break;
		case VING_ET_UN_ATOUT:
			cardName = "21";
			break;
		case DEUX_ATOUT:
			cardName = "2";
			break;
		case TROIS_ATOUT:
			cardName = "3";
			break;
		case QUATRE_ATOUT:
			cardName = "4";
			break;
		case CINQ_ATOUT:
			cardName = "5";
			break;
		case SIX_ATOUT:
			cardName = "6";
			break;
		case SEPT_ATOUT:
			cardName = "7";
			break;
		case HUIT_ATOUT:
			cardName = "8";
			break;
		case NEUF_ATOUT:
			cardName = "9";
			break;
		case DIX_ATOUT:
			cardName = "10";
			break;
		default:
			break;
		}
		str.append(cardName).append(" ").append(this.color.name()).append(" (").append(pointsValue).append(" points) -> ");
		if (owner != null){
			str.append(owner.getName());
		}
		return str.toString();
	}

	
}
