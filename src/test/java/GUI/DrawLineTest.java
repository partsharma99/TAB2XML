package GUI;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import javafx.scene.shape.Line;

class DrawLineTest {

	//Assert the getter method
	@Test
	void testGetLine() {
		DrawLine line = new DrawLine(5.0, 0.0, 10.0, 0.0);
		assertEquals(5.0, line.getLine().getStartX());
		assertEquals(0.0, line.getLine().getStartY());
		assertEquals(10.0, line.getLine().getEndX());
		assertEquals(0.0, line.getLine().getEndY());
	}
	
	//Assert the setter method
	@Test
	void testSetLine() {
		DrawLine l1 = new DrawLine(5.0, 0.0, 10.0, 0.0);
		Line l2 = new Line(10.0, 2.0, 15.0, 2.0);
		l1.setLine(l2);
		assertEquals(10.0, l1.getLine().getStartX());
		assertEquals(2.0, l1.getLine().getStartY());
		assertEquals(15.0, l1.getLine().getEndX());
		assertEquals(2.0, l1.getLine().getEndY());
	}
}
