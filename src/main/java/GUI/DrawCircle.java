package GUI;

import javafx.scene.shape.Circle;

public class DrawCircle {

	private Circle circle = new Circle();
	public DrawCircle(double x, double y) {
		this.circle.setCenterX(x);
		this.circle.setCenterY(y);
		this.circle.setRadius(5);
	}
	public Circle getCircle() {
		return circle;
	}
}
