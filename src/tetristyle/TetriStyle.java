package tetristyle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TetriStyle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Image screenImage;
	private Graphics screenGraphics;
	private Image mainScreen = new ImageIcon(Main.class.getResource("../images/MainScreen.png")).getImage();
	private ImageIcon mainMenuBarImage = new ImageIcon(Main.class.getResource("../images/MainMenuBar.png"));
	
	private JLabel mainMenuBar = new JLabel(mainMenuBarImage);
	private JLabel menu = new JLabel(new ImageIcon(Main.class.getResource("../images/Menu(1).png")));
	
	private boolean[] isPressed = new boolean[3];
	private int mouseX, mouseY;
	private int nowSelected = 1;
	Music mainMusic;
	
	public TetriStyle() {
		setTitle("TetriStyle. Made_By_Ahdelron");
		setSize(Main.MAIN_SCREEN_WIDTH, Main.MAIN_SCREEN_HEIGHT);
		setUndecorated(true);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0)); // 투명 배경 // 깜박임 해결
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		isPressed[0] = false;
		isPressed[1] = false;
		isPressed[2] = false;
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP && isPressed[0] == false) {
					Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
					buttonEnteredMusic.start();
					isPressed[0] = true;
					menuUp();
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN && isPressed[1] == false) {
					isPressed[1] = true;
					Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3", false);
					buttonEnteredMusic.start();
					menuDown();
				} else if(e.getKeyCode() == KeyEvent.VK_ENTER && isPressed[2] == false) {
					isPressed[2] = true;
					Music buttonPressedMusic = new Music("buttonPressedMusic.mp3", false);
					buttonPressedMusic.start();
					menuSelect();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_UP) {
					isPressed[0] = false;
				} else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					isPressed[1] = false;
				} else if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					isPressed[2] = false;
				}
			}
		});
		
		mainMenuBar.setBounds(0, 0, 740, 32);
		mainMenuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		mainMenuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getXOnScreen() - mouseX, e.getYOnScreen() - mouseY);
			}
		});
		add(mainMenuBar);
		
		menu.setBounds(190, 400, 370, 100);
		add(menu);
		
		mainMusic = new Music("Main Music.mp3", true);
		mainMusic.start();
		
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g) {
		screenImage = createImage(Main.MAIN_SCREEN_WIDTH, Main.MAIN_SCREEN_HEIGHT);
		screenGraphics = screenImage.getGraphics();
		screenGraphics.drawImage(mainScreen, 0, 0, null);
		g.drawImage(screenImage, 0, 0, null);
		paintComponents(g);
		repaint();
	}
	
	public void menuUp() {
		if(nowSelected == 1) {
			nowSelected = 3;
		} else {
			nowSelected--;
		}
		menu.setIcon(new ImageIcon(Main.class.getResource("../images/Menu(" + Integer.toString(nowSelected) + ").png")));
	}
	
	public void menuDown() {
		if(nowSelected == 3) {
			nowSelected = 1;
		} else {
			nowSelected++;
		}
		menu.setIcon(new ImageIcon(Main.class.getResource("../images/Menu(" + Integer.toString(nowSelected) + ").png")));
	}
	
	public void menuSelect() {
		if(nowSelected == 1) {
			setVisible(false);
			mainMusic.close();
			Main.callGameScreen();
		} else if(nowSelected == 2) {
			
		} else if(nowSelected == 3) {
			try {
				Thread.sleep(300);
			} catch(Exception e) {
				e.printStackTrace();
			}
			System.exit(0);
		}
	}
}
