package GUI;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;

import converter.Converter;
/*
Sample tab
|-----------0-----|-0---------------|
|---------0---0---|-0---------------|
|-------1-------1-|-1---------------|
|-----2-----------|-2---------------|
|---2-------------|-2---------------|
|-0---------------|-0---------------|

*/
public class PreviewMXLController extends Application implements Initializable  {
	
	@FXML public Canvas canvas;
	@FXML TextField gotoMeasureField;
	@FXML Button gotoMeasureButton;
	@FXML Button savePDF;
	private GraphicsContext gc;
	public FXMLLoader loader;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        System.out.println("color set to black");
        gc.fillRect(50, 50, 100, 100);
        System.out.println("draw rectangle");
	}
	
	@FXML
	public void savePDF() {
	}
	@FXML
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
