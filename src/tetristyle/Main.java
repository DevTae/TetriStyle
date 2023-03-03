package tetristyle;

public class Main {

	public static final int MAIN_SCREEN_WIDTH = 740;
	public static final int MAIN_SCREEN_HEIGHT = 550;
	public static final int GAME_SCREEN_WIDTH = 704;
	public static final int GAME_SCREEN_HEIGHT = 736;
	
	public static void main(String[] args) {
		new TetriStyle();
		//new GameScreen();
	}
	
	public static void callGameScreen() {
		new GameScreen();
	}
}
