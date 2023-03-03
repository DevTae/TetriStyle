package tetristyle;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import javazoom.jl.player.Player;

public class Music extends Thread {

	private String path;
	private boolean isLoop;
	private Player player;
	
	public Music(String path, boolean isLoop) {
		try {
			this.path = path;
			this.isLoop = isLoop;
			FileInputStream is = new FileInputStream(new File(Main.class.getResource("../musics/" + path).toURI()));
			BufferedInputStream bis = new BufferedInputStream(is);
			player = new Player(bis);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			do {
				player.play();
				FileInputStream is = new FileInputStream(new File(Main.class.getResource("../musics/" + path).toURI()));
				BufferedInputStream bis = new BufferedInputStream(is);
				player = new Player(bis);	// 중요!!! 무한 반복이 되려면 player 변수를 계속해서 초기화 해줘야만 한다! 왜냐면 이미 다 읽었으니까.
			} while(isLoop);	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		isLoop = false;
		player.close();
		this.interrupt();
	}
}
