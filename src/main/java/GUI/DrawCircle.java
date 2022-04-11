package GUI;

import javafx.scene.shape.Circle;

public class DrawCircle {

	private Circle circle = new Circle();
	public DrawCircle(double x, double y, double radius) {
		this.circle.setCenterX(x);
		this.circle.setCenterY(y);
		this.circle.setRadius(radius);
	}
	public Circle getCircle() {
		return circle;
	}
}
