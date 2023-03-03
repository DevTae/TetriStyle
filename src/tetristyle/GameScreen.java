package tetristyle;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameScreen extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static ExecutorService threadPool;
	
	private Image screenImage;
	private Graphics screenGraphics;
	private Image gameScreen = new ImageIcon(Main.class.getResource("../images/GameScreen.png")).getImage();
	private Image judgeLineImage = new ImageIcon(Main.class.getResource("../images/judgeLine.png")).getImage();
	private Image informationImage = new ImageIcon(Main.class.getResource("../images/Information.png")).getImage();
	private JLabel gameMenuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/GameMenuBar.png")));
	private JLabel countDownImage = new JLabel(new ImageIcon(Main.class.getResource("../images/Number(5).png")));
	public static Image gameOverImage = new ImageIcon(Main.class.getResource("../images/GameOver.png")).getImage();
	public static JLabel gameOverLabel = new JLabel(new ImageIcon(gameOverImage));
	
	public static Image scoreImage = new ImageIcon(Main.class.getResource("../images/score/score(0).png")).getImage();
	
	private Music gameMusic, countDownMusic;
	private int mouseX, mouseY;
	
	public static boolean isStarted = false;
	public static boolean isBonus = false;
	
	public static Board board;
	public static Score score;
	
	public GameScreen() {
		setTitle("TetriStyle. Made_By_Ahdelron");
		setSize(Main.GAME_SCREEN_WIDTH, Main.GAME_SCREEN_HEIGHT);
		setLayout(null);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(new Color(0, 0, 0, 0));
		setVisible(true);
		
		gameMenuBar.setBounds(0, 0, 704, 32);
		gameMenuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		gameMenuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
			}
		});
		add(gameMenuBar);
		
		countDownImage.setVisible(true);
		countDownImage.setBounds(280, 250, 178, 300);
		add(countDownImage);
		
		gameOverLabel.setBounds(2, 220, 700, 300);
		gameOverLabel.setVisible(false);
		add(gameOverLabel);
		
		gameMusic = new Music("Game Music.mp3", true);
		countDownMusic = new Music("CountDownMusic.mp3", false);
		
		board = new Board(); // 블럭 갖다놓기
		score = new Score(); // 점수 갖다놓기

		Runnable countDown = new Runnable() {
			@Override
			public void run() {
				try {
					countDownMusic.start();
					gameMusic.start();
					for(int i = 4; i > 0; i--) {
						Thread.sleep(1000);
						countDownImage.setIcon(new ImageIcon(Main.class.getResource("../images/Number(" + Integer.toString(i) + ").png")));
					}
					Thread.sleep(500);
					countDownImage.setVisible(false);
					addKeyListener(new GameKeyListener());
					setFocusable(true);
					isStarted = true;
					if(Board.newBlocks.get(0) != null) {
						Board.newBlocks.get(0).start();
					}
					score.start();
					score.scoreAnimation();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		};
		threadPool = Executors.newCachedThreadPool();
		threadPool.execute(countDown);
	}
	
	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.GAME_SCREEN_WIDTH, Main.GAME_SCREEN_HEIGHT);
		screenGraphics = screenImage.getGraphics();
		screenGraphics.drawImage(gameScreen, 0, 0, null);
		screenGraphics.drawImage(judgeLineImage, 543, 96, null);
		screenGraphics.drawImage(informationImage, 420, 400, null);
		
		if(board != null)
			board.screenDraw(screenGraphics);
		if(score != null)
			score.screenDraw((Graphics2D) screenGraphics);
		if(isBonus)
			screenGraphics.drawImage(scoreImage, 500, 230, null);
		
		paintComponents(screenGraphics);
		if(gameOverLabel.isVisible())
			gameOverScoreDraw((Graphics2D) screenGraphics);
		
		g.drawImage(screenImage, 0, 0, null);
		repaint();
	}
	
	public static void gameOverDraw() {
		gameOverLabel.setVisible(true);
	}
	
	public void gameOverScoreDraw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Elephant", Font.BOLD, 30));
		g.setColor(Color.ORANGE);
		g.drawString("SCORE : " + String.format("%08d", Score.display), 170, 280);
	}
}
