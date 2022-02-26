package xml.to.sheet.converter;

//Java Program to create a to create
//a canvas and paint the canvas
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.*;

class MyCanvas extends JFrame {

	// constructor
	public MyCanvas(){
		super("canvas");
	}

	// paint the canvas
	public void paint(Graphics g) {
		// set color to red
		g.setColor(Color.black);

		// set Font
		g.setFont(new Font("Bold", 1, 20));
		// draw a COMPONENT
		g.drawLine(10, 100, 200, 100);
		g.drawLine(10, 110, 200, 110);
		g.drawLine(10, 120, 200, 120);
		g.drawLine(10, 130, 200, 130);
		g.drawLine(10, 140, 200, 140);
		update(g);
		
	}
	// Main Method
	public static void main(String args[]){
		Canvas c = new Canvas();
		//c.add(c);
		c.setBackground(Color.white);
		c.setSize(400, 300);
		c.setVisible(true);

		while (c.isVisible()) {
			// set background
			

			//c.add();
			
			
		}
	}
}