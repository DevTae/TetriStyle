package tetristyle;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameKeyListener extends KeyAdapter {

	long delay = 200;
	long time = System.currentTimeMillis();
	
	boolean[] isClicked = new boolean[] { false, false, false, false, false, false, false };
	
	public static boolean isDowned = false;
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			if(isClicked[0] == false) {
				isClicked[0] = true;
				if(!Board.newBlocks.isEmpty()) {
					Board.newBlocks.get(0).setRotation();// 눌렀을때의 이벤트
				}
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					if(!Board.newBlocks.isEmpty()) {
						Board.newBlocks.get(0).setRotation();// 눌렀을때의 이벤트
					}
				}
			}
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			if(isClicked[1] == false) {
				isClicked[1] = true;
				if(!Board.newBlocks.isEmpty()) { // 원래는 !Board.newBlocks.isEmpty()
					isDowned = true;	// 아래 움직일시 쓰레드 초기화
					Board.newBlocks.get(0).goDown();// 눌렀을때의 이벤트
				}
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					if(!Board.newBlocks.isEmpty()) {
						isDowned = true;	// 아래 움직일시 쓰레드 초기화
						Board.newBlocks.get(0).goDown();// 눌렀을때의 이벤트
					}
				}
			}
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(isClicked[2] == false) {
				isClicked[2] = true;
				if(!Board.newBlocks.isEmpty())
					Board.newBlocks.get(0).goRight();// 눌렀을때의 이벤트
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					if(!Board.newBlocks.isEmpty())
						Board.newBlocks.get(0).goRight();// 눌렀을때의 이벤트
				}
			}
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(isClicked[3] == false) {
				isClicked[3] = true;
				if(!Board.newBlocks.isEmpty())
					Board.newBlocks.get(0).goLeft();// 눌렀을때의 이벤트
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					if(!Board.newBlocks.isEmpty())
						Board.newBlocks.get(0).goLeft();// 눌렀을때의 이벤트
				}
			}
		} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			if(isClicked[4] == false) {
				isClicked[4] = true;
				if(!Board.newBlocks.isEmpty()) {
					//Board.newBlocks.get(0).directLand();// 눌렀을때의 이벤트
					Board.newBlocks.get(0).goDown();
				}
					
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					if(!Board.newBlocks.isEmpty()) {
						//Board.newBlocks.get(0).directLand();// 눌렀을때의 이벤트
						Board.newBlocks.get(0).goDown();
					}
				}
			}
		} else if(e.getKeyCode() == KeyEvent.VK_Q) {
			if(isClicked[5] == false) {
				isClicked[5] = true;
				System.exit(0); // 눌렀을때의 이벤트
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					System.exit(0); // 눌렀을때의 이벤트
				}
			}
		} else if(e.getKeyCode() == KeyEvent.VK_R) {
			if(isClicked[6] == false) {
				isClicked[6] = true;
				//GameScreen.board = new Board();
				//GameScreen.gameOverLabel.setVisible(false);
				System.exit(0); // 눌렀을때의 이벤트
			} else {
				if(System.currentTimeMillis() - time >= delay) { // delay 만큼 딜레이를 줄 것인데 오래 누를 수록 빨리 가지도록 할 것이다.
					time = System.currentTimeMillis();
					if(delay - 50 > 0) delay -= 50;
					//GameScreen.board = new Board();
					//GameScreen.gameOverLabel.setVisible(false);
					System.exit(0); // 눌렀을때의 이벤트
				}
			}
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			delay = 200;
			isClicked[0] = false;
		} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			delay = 200;
			isClicked[1] = false;
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			delay = 200;
			isClicked[2] = false;
		} else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			delay = 200;
			isClicked[3] = false;
		} else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			delay = 200;
			isClicked[4] = false;
		} else if(e.getKeyCode() == KeyEvent.VK_Q) {
			delay = 200;
			isClicked[5] = false;
		} else if(e.getKeyCode() == KeyEvent.VK_R) {
			delay = 200;
			isClicked[6] = false;
		}
	}
}
