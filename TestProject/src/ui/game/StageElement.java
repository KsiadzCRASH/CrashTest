package ui.game;

import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;

public class StageElement {

	public Color color;
	public int xPos = 0; //Default initialization prevents render errors
	public int yPos = 0;
	public Rectangle2D size;

	public StageElement(Color color, int xPos, int yPos, Rectangle2D size) {
		super();
		this.color = color;
		this.xPos = xPos;
		this.yPos = yPos;
		this.size = size;
	}
}
