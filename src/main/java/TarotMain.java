import jeu.services.JeuDeTarot;

public class TarotMain {

	public static void main(String[] args){
		MyTarotGame game = new MyTarotGame();
		game.initialize(new JeuDeTarot());
	}
	
}
