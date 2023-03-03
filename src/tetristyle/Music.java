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
				player = new Player(bis);	// �߿�!!! ���� �ݺ��� �Ƿ��� player ������ ����ؼ� �ʱ�ȭ ����߸� �Ѵ�! �ֳĸ� �̹� �� �о����ϱ�.
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
