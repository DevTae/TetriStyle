package tetristyle;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Block3 implements BlockInterface {
	
	private Image block = new ImageIcon(Main.class.getResource("../images/blocks/3.png")).getImage();
	
	ArrayList<Point> points = new ArrayList<Point>();
	private boolean isLanded = false;
	private int bonus = 10000;
	
	public Block3() {
		points.add(new Point(1, 5)); // 블록마다 다름
		points.add(new Point(1, 6)); // 블록마다 다름
		points.add(new Point(2, 5)); // 블록마다 다름
		points.add(new Point(2, 6)); // 블록마다 다름
		boolean ending = false;
		for(int i = 0; i < points.size(); i++) {
			if(Board.position[points.get(i).getX()][points.get(i).getY()][0] == 1)
				ending = true;
		}
		if(ending) {
			GameScreen.board = null;
			isLanded = true;
			GameScreen.isStarted = false;
			GameScreen.gameOverDraw();
			return;
		}
	}
	
	public void resetPosition() {
		points.add(new Point(1, 5)); // 블록마다 다름
		points.add(new Point(1, 6)); // 블록마다 다름
		points.add(new Point(2, 5)); // 블록마다 다름
		points.add(new Point(2, 6)); // 블록마다 다름
		for(int i = 0; i < 4; i++)
			points.remove(0);
	}

	public void setRotation() {
		Music music = new Music("blockChange.mp3", false);
		music.start();
	}
	
	public void goRight() {
		Music music = new Music("blockChange.mp3", false);
		music.start();
		
		boolean isPossible = true;
		for(int i = 0; i < points.size(); i++) {
			if(Board.position[points.get(i).getX()][points.get(i).getY() + 1][0] == 1)
				isPossible = false;
		}
		if(isPossible) {
			for(int i = 0; i < points.size(); i++) {
				points.get(i).setY(points.get(i).getY() + 1);
			}
		}
	}

	public void goLeft() {
		Music music = new Music("blockChange.mp3", false);
		music.start();
		
		boolean isPossible = true;
		for(int i = 0; i < points.size(); i++) {
			if(Board.position[points.get(i).getX()][points.get(i).getY() - 1][0] == 1)
				isPossible = false;
		}
		if(isPossible) {
			for(int i = 0; i < points.size(); i++) {
				points.get(i).setY(points.get(i).getY() - 1);
			}
		}
	}
	
	public boolean isDown() {
		boolean isPossible = true;
		for(int i = 0; i < points.size(); i++) {
			if(Board.position[points.get(i).getX() + 1][points.get(i).getY()][0] == 1) {		
				isPossible = false;
				isLanded = true;
				break;
			}
		}
		if(isPossible) {
			for(int i = 0; i < points.size(); i++) {
				points.get(i).setX(points.get(i).getX() + 1);
			}
			return true;
		} else {
			fixLocation();
			return false;
		}
	}

	public void goDown() {
		if (isLanded)
			return;
		Music music = new Music("blockChange.mp3", false);
		music.start();
		
		bonus = (bonus - 1000 >= 0) ? bonus -= 1000 : bonus;
		isDown();
	}
	
	public void directLand() { // 뭔가 꼬인 것 같습니다.. 나중에.. 개선하도록..
		Music music = new Music("blockChange.mp3", false);
		music.start();
		
		long startTime = System.currentTimeMillis();
		while(isDown()) {
			if(System.currentTimeMillis() - startTime >= 1) {
				System.out.println("1");
			} else {
				System.out.println("2");
			}
		}
		
		for (int i = 10; i > 0; i--) {
			if (bonus == 1000 * i) {
				Music bonusMusic = new Music("bonusMusic.mp3", false);
				bonusMusic.start();
				GameScreen.scoreImage = new ImageIcon(
						Main.class.getResource("../images/score/score(" + Integer.toString(bonus) + ").png"))
								.getImage();
				GameScreen.isBonus = true;
				bonusAnimation();
				break;
			}
		}
		
		Score.result += bonus;
	}
	
	@Override
	public void fixLocation() {
		Music music = new Music("blockLand.mp3", false);
		music.start();
		
		for(int i = 0; i < points.size(); i++) {
			Board.position[points.get(i).getX()][points.get(i).getY()][0] = 1;
			Board.position[points.get(i).getX()][points.get(i).getY()][1] = Board.COLOR_YELLOW; // 각각 다름
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 이때 세로 기준 모든 블럭에 수직한 모든 줄을 검사하게 만들겠습니다. 아마 함수명 researchLine()
		researchLine();
		
		Board.randomBlock();
		
		Board.newBlocks.remove(Block3.this);// 각각 다름.
		
		if(Board.newBlocks.get(0) != null) {
			//Board.newBlocks.get(0).start();
		}
	}

	public void researchLine() {
		int[] numArr = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		
		for(int i = 0; i < points.size(); i++) {
			numArr[points.get(i).getX()]++;
		}
		
		for(int i = 1; i <= 18; i++) {
			if(numArr[i] != 0) {
				boolean isLine = true;
				for(int j = 1; j <= 9; j++) {
					if(Board.position[i][j][0] == 0) isLine = false;
				}
				if(isLine) {
					for(int j = i; j > 1; j--) {
						for(int k = 1; k <= 9; k++) {
							Board.position[j][k][0] = Board.position[j - 1][k][0];
							Board.position[j][k][1] = Board.position[j - 1][k][1];
						}
					}
					Music music = new Music("blockDelete.mp3", false);
					music.start();
				}
			}
		}
	}
	
	@Override
	public void screenDraw(Graphics g) {
		for(int i = 0; i < points.size(); i++) {
			g.drawImage(block, 32 * points.get(i).getY() + 32 * 1, 32 * points.get(i).getX() + 32 * 2, null);
		}
	}
	
	private Runnable goDownThread = new Runnable() {
		@Override
		public void run() {
			try {
				while (true) {
					if (isLanded)
						break;
					int temp = 0;
					for (int i = 0; i < 50; i++) {
						if (GameKeyListener.isDowned == true) {
							GameKeyListener.isDowned = false;
							temp = 1;
							break;
						}
						Thread.sleep(Score.BLOCK_SPEED / 50);
					}
					if (temp == 0)
						goDown();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	@Override
	public void start() {
		GameScreen.threadPool.execute(goDownThread);
	}

	private Runnable bonusAnimationThread = new Runnable() {
		@Override
		public void run() {
			try {
				Thread.sleep(500);
				GameScreen.isBonus = false;
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	@Override
	public void bonusAnimation() {
		GameScreen.threadPool.execute(bonusAnimationThread);
	}
}
