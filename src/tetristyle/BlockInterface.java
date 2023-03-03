package tetristyle;

import java.awt.Graphics;
import java.util.ArrayList;

/*
ㅁㅁㅁㅁ - Block0 // mint.png

ㅁ
ㅁㅁㅁ - Block1 // blue.png

     ㅁ
ㅁㅁㅁ - Block2 // orange.png

ㅁㅁ
ㅁㅁ - Block3 // yellow.png

  ㅁㅁ
ㅁㅁ  - Block4 // green.png

   ㅁ
ㅁㅁㅁ - Block5 // purple.png

ㅁㅁ
   ㅁㅁ - Block6 // red.png
*/

public interface BlockInterface {
	
	// points 변수, isLanded
	
	ArrayList<Point> points = new ArrayList<Point>();
	
	public void resetPosition();
	
	public void setRotation(); // 위 방향키
		// 이때 세로 기준 모든 블럭에 수직한 모든 줄을 검사하게 만들겠습니다. 
	
	public void directLand(); // 스페이스바

	public void goRight(); // 오른쪽 방향키

	public void goLeft(); // 왼쪽 방향키
	
	public void goDown(); // 아래 방향키
	
	public void fixLocation(); // 위치 확정시키기
	
	public void screenDraw(Graphics g); // 활개치고 다니면서 그리기! ㅋㅋ

	public void start();
	
	public void bonusAnimation();
	
	
}
