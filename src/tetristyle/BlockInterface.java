package tetristyle;

import java.awt.Graphics;
import java.util.ArrayList;

/*
けけけけ - Block0 // mint.png

け
けけけ - Block1 // blue.png

     け
けけけ - Block2 // orange.png

けけ
けけ - Block3 // yellow.png

  けけ
けけ  - Block4 // green.png

   け
けけけ - Block5 // purple.png

けけ
   けけ - Block6 // red.png
*/

public interface BlockInterface {
	
	// points 痕呪, isLanded
	
	ArrayList<Point> points = new ArrayList<Point>();
	
	public void resetPosition();
	
	public void setRotation(); // 是 号狽徹
		// 戚凶 室稽 奄層 乞窮 鷺薫拭 呪送廃 乞窮 匝聖 伊紫馬惟 幻級畏柔艦陥. 
	
	public void directLand(); // 什凪戚什郊

	public void goRight(); // 神献楕 号狽徹

	public void goLeft(); // 図楕 号狽徹
	
	public void goDown(); // 焼掘 号狽徹
	
	public void fixLocation(); // 是帖 溌舛獣徹奄
	
	public void screenDraw(Graphics g); // 醗鯵帖壱 陥艦檎辞 益軒奄! せせ

	public void start();
	
	public void bonusAnimation();
	
	
}
