package tetristyle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;

public class Score extends Thread {
	
	private boolean levelUpVisible = false;
	private boolean levelUpBuffer = false;
	private Image levelUpImage = new ImageIcon(Main.class.getResource("../images/levelUp.gif")).getImage();
	
	public static int BLOCK_SPEED = 1300; // 1300 1000 700
	public static int result = 0;
	public static int display = 0;
	int level = 1;
	
	public Score() {
		
	}
	
	public void screenDraw(Graphics2D g) {
		
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.setColor(Color.DARK_GRAY);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.drawString(String.format("%08d", display), 460, 252);
		
		g.drawString("Level : " + Integer.toString(level), 515, 315);
		g.setFont(new Font("Elephant", Font.ITALIC, 15));
		g.drawString("1 ~ 7 is available.", 520, 340);
		
		if(result >= 60000) {
			levelManage(7);
		} else if(result >= 50000) {
			levelManage(6);
		} else if(result >= 40000) {
			levelManage(5);
		} else if(result >= 30000) {
			levelManage(4);
		} else if(result >= 20000) {
			levelManage(3);
		} else if(result >= 10000) {
			levelManage(2);
		} else {
			levelManage(1);
		}
		
		if(levelUpVisible) {
			if(!levelUpBuffer) { // false 일때 levelUpAnimation 호출.
				levelUpAnimation();
			}
			g.drawImage(levelUpImage, 0, 180, null);
		}
	}
	
	public void levelManage(int destinationLevel) {
		switch(destinationLevel) {
		case 1:
			BLOCK_SPEED = 1000;
			break;
		case 2:
			if(level == destinationLevel - 1) {
				level = destinationLevel;
				//Board.resetBoard();
				levelUpVisible = true;
			}
			BLOCK_SPEED = 700;
			break;
		case 3:
			if(level == destinationLevel - 1) {
				level = destinationLevel;
				//Board.resetBoard();
				levelUpVisible = true;
			}
			BLOCK_SPEED = 400;
			break;
		case 4:
			if(level == destinationLevel - 1) {
				level = destinationLevel;
				//Board.resetBoard();
				levelUpVisible = true;
			}
			BLOCK_SPEED = 300;
			break;
		case 5:
			if(level == destinationLevel - 1) {
				level = destinationLevel;
				//Board.resetBoard();
				levelUpVisible = true;
			}
			BLOCK_SPEED = 200;
			break;
		case 6:
			if(level == destinationLevel - 1) {
				level = destinationLevel;
				//Board.resetBoard();
				levelUpVisible = true;
			}
			BLOCK_SPEED = 100;
			break;
		case 7:
			if(level == destinationLevel - 1) {
				level = destinationLevel;
				//Board.resetBoard();
				levelUpVisible = true;
			}
			BLOCK_SPEED = 50;
			break;
		}
	}
	
	public void levelUpAnimation() {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				Music music = new Music("bonusMusic.mp3", false);
				music.start();
				levelUpBuffer = true;
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				levelUpBuffer = false;
				levelUpVisible = false;
			}
		};
		GameScreen.threadPool.execute(thread);
	}
	
	public void scoreAnimation() {
		Runnable thread = new Runnable() {
			@Override
			public void run() {
				try {
					while(true) {
						if(GameScreen.isStarted == false) break;
						int gap = result - display;
						for(int i = 0; i < 10; i++) {
							Thread.sleep(20);
							display += (gap / 10);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		GameScreen.threadPool.execute(thread);
	}
	
	public void start() {
		Runnable thread = new Runnable() {
			public void run() {
				try {
					while(true) {
						if(GameScreen.isStarted == true) {
							Thread.sleep(BLOCK_SPEED);
							if(result >= 100000000)
								result = 99999999;
							result += (int)(Math.random() * 100) + BLOCK_SPEED / 2;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		GameScreen.threadPool.execute(thread);
	}
}
