package tetristyle;

import java.awt.Graphics;
import java.util.ArrayList;

/*
�������� - Block0 // mint.png

��
������ - Block1 // blue.png

     ��
������ - Block2 // orange.png

����
���� - Block3 // yellow.png

  ����
����  - Block4 // green.png

   ��
������ - Block5 // purple.png

����
   ���� - Block6 // red.png
*/

public interface BlockInterface {
	
	// points ����, isLanded
	
	ArrayList<Point> points = new ArrayList<Point>();
	
	public void resetPosition();
	
	public void setRotation(); // �� ����Ű
		// �̶� ���� ���� ��� ���� ������ ��� ���� �˻��ϰ� ����ڽ��ϴ�. 
	
	public void directLand(); // �����̽���

	public void goRight(); // ������ ����Ű

	public void goLeft(); // ���� ����Ű
	
	public void goDown(); // �Ʒ� ����Ű
	
	public void fixLocation(); // ��ġ Ȯ����Ű��
	
	public void screenDraw(Graphics g); // Ȱ��ġ�� �ٴϸ鼭 �׸���! ����

	public void start();
	
	public void bonusAnimation();
	
	
}
