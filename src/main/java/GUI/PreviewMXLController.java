package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.scene.canvas.*;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import org.fxmisc.flowless.VirtualizedScrollPane;
/*
Sample tab
|-----------0-----|-0---------------|
|---------0---0---|-0---------------|
|-------1-------1-|-1---------------|
|-----2-----------|-2---------------|
|---2-------------|-2---------------|
|-0---------------|-0---------------|

*/
public class PreviewMXLController extends Application {
	
	@FXML public Canvas canvas;
	public GraphicsContext gc;
	public Parent root;
	
	public void setRoot(Parent root) {
		this.root =root;
	}
	public void savePDF() {
	}
	
	public void handleGotoMeasure() {
	}
	// paint the canvas
	public void paint(GraphicsContext g) {
		// set color to red
		//g.setColor(Color.BLACK);
		g.setFill(Color.ALICEBLUE);

		// set Font
		g.setFont(new Font("Bold", 1));
		g.lineTo(100, 100);
		/* draw a COMPONENT
		g.drawLine(10, 100, 200, 100);
		g.drawLine(10, 110, 200, 110);
		g.drawLine(10, 120, 200, 120);
		g.drawLine(10, 130, 200, 130);
		g.drawLine(10, 140, 200, 140);
		update(g);*/

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	}
}
