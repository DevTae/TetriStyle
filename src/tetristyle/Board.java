package tetristyle;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class Board {
	
	public static final int COLOR_MINT = 0;
	public static final int COLOR_BLUE = 1;
	public static final int COLOR_ORANGE = 2;
	public static final int COLOR_YELLOW = 3;
	public static final int COLOR_GREEN = 4;
	public static final int COLOR_PURPLE = 5;
	public static final int COLOR_RED = 6;
	
	private Image[] images = new Image[] {
			new ImageIcon(Main.class.getResource("../images/blocks/0.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/1.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/2.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/3.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/4.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/5.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/6.png")).getImage()
	};
	private Image[] blockImages = new Image[] {
			new ImageIcon(Main.class.getResource("../images/blocks/block0.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/block1.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/block2.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/block3.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/block4.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/block5.png")).getImage(),
			new ImageIcon(Main.class.getResource("../images/blocks/block6.png")).getImage()
	};
	
	
	public static ArrayList<BlockInterface> newBlocks = new ArrayList<BlockInterface>();
	public static int[][][] position = new int[20][11][2];

	public Board() {
		resetBoard();
		randomBlock();
		randomBlock();
		randomBlock();
	}
	
	public static void resetBoard() {
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 11; j++) {
				Arrays.fill(position[i][j], (int)0);
			}
		}
		for(int i = 0; i < 20; i++) {
			Arrays.fill(position[i][0], (int)1);
			Arrays.fill(position[i][0], (int)1);
			Arrays.fill(position[i][10], (int)1);
		}
		for(int i = 0; i < 11; i++) {
			Arrays.fill(position[0][i], (int)1);
			Arrays.fill(position[19][i], (int)1);
		}
		
		if(!newBlocks.isEmpty()) {
			Music music = new Music("bonusMusic.mp3", false);
			music.start();
			newBlocks.get(0).resetPosition();
		}	// 맨 처음 호출한 것이면.. newBlocks 에 array range out 오류가 뜨므로 이렇게 처리를 해줘야한다.
		
	}
	
	public void screenDraw(Graphics g) {
		
		if(newBlocks.isEmpty()) return; // 에러 처리해둠.
		
		newBlocks.get(0).screenDraw(g);
		
		if(newBlocks.get(1) instanceof Block0) {
			g.drawImage(blockImages[0], 32 * 13, 32 * 3, null);
		} else if(newBlocks.get(1) instanceof Block1) {
			g.drawImage(blockImages[1], 32 * 13, 32 * 3, null);
		} else if(newBlocks.get(1) instanceof Block2) {
			g.drawImage(blockImages[2], 32 * 13, 32 * 3, null);
		} else if(newBlocks.get(1) instanceof Block3) {
			g.drawImage(blockImages[3], 32 * 13, 32 * 3, null);
		} else if(newBlocks.get(1) instanceof Block4) {
			g.drawImage(blockImages[4], 32 * 13, 32 * 3, null);
		} else if(newBlocks.get(1) instanceof Block5) {
			g.drawImage(blockImages[5], 32 * 13, 32 * 3, null);
		} else if(newBlocks.get(1) instanceof Block6) {
			g.drawImage(blockImages[6], 32 * 13, 32 * 3, null);
		}
		
		if(newBlocks.get(2) instanceof Block0) {
			g.drawImage(blockImages[0], 32 * 17, 32 * 3, null);
		} else if(newBlocks.get(2) instanceof Block1) {
			g.drawImage(blockImages[1], 32 * 17, 32 * 3, null);
		} else if(newBlocks.get(2) instanceof Block2) {
			g.drawImage(blockImages[2], 32 * 17, 32 * 3, null);
		} else if(newBlocks.get(2) instanceof Block3) {
			g.drawImage(blockImages[3], 32 * 17, 32 * 3, null);
		} else if(newBlocks.get(2) instanceof Block4) {
			g.drawImage(blockImages[4], 32 * 17, 32 * 3, null);
		} else if(newBlocks.get(2) instanceof Block5) {
			g.drawImage(blockImages[5], 32 * 17, 32 * 3, null);
		} else if(newBlocks.get(2) instanceof Block6) {
			g.drawImage(blockImages[6], 32 * 17, 32 * 3, null);
		}
		
		for(int i = 1; i < 19; i++) {
			for(int j = 1; j < 10; j++) {
				if(position[i][j][0] == 1) {
					g.drawImage(images[position[i][j][1]], j * 32 + 32 * 1, i * 32 + 32 * 2, null);
				}
			}
		}
	}
	
	public static void randomBlock() {
		// 블럭 종류는 총 7개입니다.
		// newBlocks 에 추가해주도록 합시다.
		
		int result = (int)(Math.random() * 1000) % 7; // 0~6
		
		switch(result) {
		case COLOR_MINT:
			newBlocks.add(new Block0());
			break;
		case COLOR_BLUE:
			newBlocks.add(new Block1());
			break;
		case COLOR_ORANGE:
			newBlocks.add(new Block2());
			break;
		case COLOR_YELLOW:
			newBlocks.add(new Block3());
			break;
		case COLOR_GREEN:
			newBlocks.add(new Block4());
			break;
		case COLOR_PURPLE:
			newBlocks.add(new Block5());
			break;
		case COLOR_RED:
			newBlocks.add(new Block6());
			break;
		default:
			System.err.println(result);
			break;
		}
	}
}
