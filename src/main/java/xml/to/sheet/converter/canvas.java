package xml.to.sheet.converter;

//Java Program to create a to create
//a canvas and paint the canvas
import java.awt.*;
import javax.swing.*;
class canvas extends JFrame {

	// constructor
	canvas()
	{
		super("canvas");

		// create a empty canvas
		Canvas c = new Canvas() {

			// paint the canvas
			public void paint(Graphics g)
			{
				// set color to red
				g.setColor(Color.red);

				// set Font
				g.setFont(new Font("Bold", 1, 20));

				// draw a COMPONENT
				g.drawLine(10, 20, 100, 300);
			
			int notespacing =100;
			Graphics2D g2 = (Graphics2D) g;
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		    Font font = new Font("Bravura", Font.PLAIN, 32);
		    g2.setFont(font);
		    g2.drawString("\uD834\uDD1A", notespacing-2, 30);
		    notespacing = notespacing + 16;
			}
			};

		// set background
		c.setBackground(Color.white);

		add(c);
		setSize(400, 300);
	}

	// Main Method
	public static void main(String args[])
	{
		canvas c = new canvas();
	}
}
