package game.domain;

public class Card {
	
	public enum Color {SPADE, HEART, DIAMOND, CLUB, TRUMP, OTHER}
	
	public enum Name { 
		ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), 
		NINE(9), TEN(10), JACK(11), HORSEMAN(12), QUEEN(13), KING(14), 
		ONE_TRUMP(15), TWO_TRUMP(16), THREE_TRUMP(17), FOUR_TRUMP(18), FIVE_TRUMP(19), 
		SIX_TRUMP(20), SEVEN_TRUMP(21), EIGHT_TRUMP(22), NINE_TRUMP(23), TEN_TRUMP(24), 
		ELEVEN_TRUMP(25), TWELVE_TRUMP(26), THIRTEEN_TRUMP(27), FOURTEEN_TRUMP(28), FIVETEEN_TRUMP(29), 
		SIXTEEN_TRUMP(30), SEVENTEEN_TRUMP(31), EIGHTEEN_TRUMP(32), NINETEEN_TRUMP(33), TWENTY_TRUMP(34), 
		TWENTY_ONE_TRUMP(35), EXCUSE(0);
		
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
		case ACE:
			cardName = "A";
			break;
		case HORSEMAN:
			cardName = "C";
			break;
		case FIVE:
			cardName = "5";
			break;
		case QUEEN:
			cardName = "D";
			break;
		case TWO:
			cardName = "2";
			break;
		case TEN:
			cardName = "10";
			break;
		case EIGHTEEN_TRUMP:
			cardName = "18";
			break;
		case NINETEEN_TRUMP:
			cardName = "19";
			break;
		case SEVENTEEN_TRUMP:
			cardName = "17";
			break;
		case TWELVE_TRUMP:
			cardName = "12";
			break;
		case EXCUSE:
			cardName = "*";
			break;
		case EIGHT:
			cardName = "8";
			break;
		case NINE:
			cardName = "9";
			break;
		case ELEVEN_TRUMP:
			cardName = "11";
			break;
		case FOURTEEN_TRUMP:
			cardName = "14";
			break;
		case FOUR:
			cardName = "4";
			break;
		case FIVETEEN_TRUMP:
			cardName = "15";
			break;
		case KING:
			cardName = "R";
			break;
		case SIXTEEN_TRUMP:
			cardName = "16";
			break;
		case SEVEN:
			cardName = "7";
			break;
		case SIX:
			cardName = "6";
			break;
		case THIRTEEN_TRUMP:
			cardName = "13";
			break;
		case THREE:
			cardName = "3";
			break;
		case ONE_TRUMP:
			cardName = "1";
			break;
		case JACK:
			cardName = "V";
			break;
		case TWENTY_TRUMP:
			cardName = "20";
			break;
		case TWENTY_ONE_TRUMP:
			cardName = "21";
			break;
		case TWO_TRUMP:
			cardName = "2";
			break;
		case THREE_TRUMP:
			cardName = "3";
			break;
		case FOUR_TRUMP:
			cardName = "4";
			break;
		case FIVE_TRUMP:
			cardName = "5";
			break;
		case SIX_TRUMP:
			cardName = "6";
			break;
		case SEVEN_TRUMP:
			cardName = "7";
			break;
		case EIGHT_TRUMP:
			cardName = "8";
			break;
		case NINE_TRUMP:
			cardName = "9";
			break;
		case TEN_TRUMP:
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
