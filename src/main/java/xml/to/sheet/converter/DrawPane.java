package xml.to.sheet.converter;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JLabel;
import javax.swing.JPanel;

import GUI.InstrumentType;
import GUI.Lines;

public class DrawPane extends JPanel {
    public void paintComponent(Graphics g) {
        g.drawRect(20, 20, 250, 150); // Draw on g here e.g.
        
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin1 = new Line2D.Float(20, 50, 270, 50);
        Line2D lin2 = new Line2D.Float(20, 80, 270, 80);
        Line2D lin3 = new Line2D.Float(20, 110, 270, 110);
        Line2D lin4 = new Line2D.Float(20, 140, 270, 140);
       // Line2D lin5 = new Line2D.Float(20, 110, 220, 110);
        Line2D lin6 = new Line2D.Float(175, 20, 175 , 170);
        g2.draw(lin1);
        g2.draw(lin2);
        g2.draw(lin3);
        g2.draw(lin4);
       // g2.draw(lin5);
        g2.draw(lin6);
    }
}
