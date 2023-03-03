package tetristyle;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Block2 implements BlockInterface {
	
	private Image block = new ImageIcon(Main.class.getResource("../images/blocks/2.png")).getImage();
	
	ArrayList<Point> points = new ArrayList<Point>();
	private boolean isLanded = false;
	private int bonus = 10000;
	private int[] axisPos = new int[] { 1, 4 };
	private int[][] shape = new int[][] {
		{ 0, 0, 1 },
		{ 1, 1, 1 },
		{ 0, 0, 0 }
	};
			
	
	public Block2() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(shape[i][j] == 1)
					points.add(new Point(axisPos[0] + i, axisPos[1] + j));
			}
		}
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
		axisPos[0] = 1;
		axisPos[1] = 4;
		points.add(new Point(1, 6)); // ��ϸ��� �ٸ�
		points.add(new Point(2, 4)); // ��ϸ��� �ٸ�
		points.add(new Point(2, 5)); // ��ϸ��� �ٸ�
		points.add(new Point(2, 6)); // ��ϸ��� �ٸ�
		for(int i = 0; i < 4; i++)
			points.remove(0);
	}
	
	public void setRotation() {
		Music music = new Music("blockChange.mp3", false);
		music.start();
		
		int[][] temp = new int[][] {
			{ 0, 0, 0 },
			{ 0, 0, 0 },
			{ 0, 0, 0 }
		};
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				temp[j][2 - i] = shape[i][j];
			}
		} // �ð�������� 90�� ȸ��
		
		boolean isPossible = true;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(temp[i][j] == 1)
					if(Board.position[axisPos[0] + i][axisPos[1] + j][0] == 1
							|| axisPos[0] + i < 1 || axisPos[0] + i > 18
							|| axisPos[1] + j < 1 || axisPos[1] + j > 9)
						isPossible = false;
			}
		} // 90�� ȸ���� ������ �� ���� �ø� �� �ִ��� �Ǵ�
		
		if(isPossible) {
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					if(temp[i][j] == 1)
						points.add(new Point(axisPos[0] + i, axisPos[1] + j));
				}
			} // 90�� ���� ������ ��ǥ 4���� �Է�
			
			for(int i = 0; i < 4; i++) {
				points.remove(0);
			} // ������ ��ǥ 4���� ����
			
			for(int i = 0; i < 3; i++) {
				for(int j = 0; j < 3; j++) {
					shape[i][j] = temp[i][j];
				}
			}
		}
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
			axisPos[1] += 1; // �߽����� �̵�
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
			axisPos[1] -= 1; 
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
			axisPos[0] += 1; 
			return true;
		} else {
			fixLocation();
			return false;
		}
	}

	public void goDown() {
		Music music = new Music("blockChange.mp3", false);
		music.start();
		
		bonus = (bonus - 1000 >= 0) ? bonus -= 1000 : bonus;
		isDown();
	}
	
	public void directLand() { // ���� ���� �� �����ϴ�.. ���߿�.. �����ϵ���..
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
			Board.position[points.get(i).getX()][points.get(i).getY()][1] = Board.COLOR_ORANGE; // ���� �ٸ�
		}
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// �̶� ���� ���� ��� ���� ������ ��� ���� �˻��ϰ� ����ڽ��ϴ�. �Ƹ� �Լ��� researchLine()
		researchLine();
		
		Board.randomBlock();
		
		Board.newBlocks.remove(Block2.this);// ���� �ٸ�.
		
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
