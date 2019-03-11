/*
 *	Michael Buffone 
 * 	3/06/2019
 * 	COSC1047W19
 * 	Assignment 6 Question A2 (16.13 pg 685)
 * 	This program displays a group of fans which can be controlled
 */

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PartB1 extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		
		GridPane fanPane = new GridPane();
		fanPane.setHgap(20);
		fanPane.setPadding(new Insets(20,20,20,20));
		Fan blueFan = new Fan(Color.BLUE);
		fanPane.add(blueFan, 0, 0);
		Fan redFan = new Fan(Color.RED);
		fanPane.add(redFan, 1, 0);
		Fan yellowFan = new Fan(Color.YELLOW);
		fanPane.add(yellowFan, 2, 0);
		
		// Force actions
		GridPane forceButtons = new GridPane();
		forceButtons.setPadding(new Insets(5,5,5,5));
		fanPane.add(forceButtons, 1, 1);
		GridPane.setHalignment(forceButtons, HPos.CENTER);
		
		forceButtons.setAlignment(Pos.BOTTOM_CENTER);
		Button forceStart = new Button("Start All");
		Button forceStop = new Button("Stop All");
		forceButtons.setHgap(20);
		forceButtons.add(forceStart, 0, 0);
		forceButtons.add(forceStop, 1, 0);
		
		forceStart.setOnAction(e -> {
			blueFan.forceStart();
			redFan.forceStart();
			yellowFan.forceStart();
		});
		
		forceStop.setOnAction(e -> {
			blueFan.forceStop();
			redFan.forceStop();
			yellowFan.forceStop();
		});
		
		Scene mainScene = new Scene(fanPane, 620, 250);
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Fans");
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		Application.launch();
	}
	
	private class Fan extends BorderPane {
		
		private boolean isMirrored = false;
		private Paint paint;
	    Timeline rotationAnimation = new Timeline();

		Fan(Paint paint) {
			
			this.paint = paint;
			
			/*--------------------Create the fan pane & main objects--------------------*/
			
			// Controls and buttons
			GridPane buttonPane = new GridPane();
			buttonPane.setPadding(new Insets(5,5,5,5));
			buttonPane.setHgap(3);
			buttonPane.setVgap(3);
			Button btPause = new Button("Pause");
			Button btResume = new Button("Resume");
			Button btReverse = new Button("Reverse");
			buttonPane.add(btPause, 0, 0);
			buttonPane.add(btResume, 1, 0);
			buttonPane.add(btReverse, 2, 0);
			
			
			// Fan object creation
			Pane fanPieces = new Pane();
			Circle fanBorder = new Circle(90, 60, 50);
			fanBorder.setFill(Color.WHITE);
			fanBorder.setStroke(Color.BLACK);
			Arc fanBlade1 = new Arc(fanBorder.getCenterX(), fanBorder.getCenterY(), 45, 45, 0, 35);
			fanBlade1.setType(ArcType.ROUND);
			fanBlade1.setFill(paint);
			Arc fanBlade2 = new Arc(fanBorder.getCenterX(), fanBorder.getCenterY(), 45, 45, 90, 35);
			fanBlade2.setType(ArcType.ROUND);
			fanBlade2.setFill(paint);
			Arc fanBlade3 = new Arc(fanBorder.getCenterX(), fanBorder.getCenterY(), 45, 45, 180, 35);
			fanBlade3.setType(ArcType.ROUND);
			fanBlade3.setFill(paint);
			Arc fanBlade4 = new Arc(fanBorder.getCenterX(), fanBorder.getCenterY(), 45, 45, 270, 35);
			fanBlade4.setType(ArcType.ROUND);
			fanBlade4.setFill(paint);
			
			fanPieces.getChildren().addAll(fanBorder, fanBlade1, fanBlade2, fanBlade3, fanBlade4);
			setCenter(fanPieces);
			
			/*--------------------Fan animation--------------------*/
			
			// Adding the fan pane and making sure that it rotates only around the center
			Rotate rotation = new Rotate(0, fanBorder.getCenterX(), fanBorder.getCenterY());
			fanPieces.getTransforms().add(rotation);

		    rotationAnimation.getKeyFrames().add(new KeyFrame(Duration.seconds(5), new KeyValue(rotation.angleProperty(), 360)));
		    rotationAnimation.setCycleCount(Animation.INDEFINITE);
		    rotationAnimation.play();
			
			/*--------------------Button listeners and the slider control--------------------*/
			
			Slider slSpeed = new Slider();
			slSpeed.setMin(0);
			slSpeed.setMax(10);
			rotationAnimation.rateProperty().bind(slSpeed.valueProperty());
			
			btPause.setOnAction(e -> {
				if(rotationAnimation.getStatus() == Transition.Status.RUNNING) {
					rotationAnimation.pause();
				}
			});
			btResume.setOnAction(e -> {
				if(rotationAnimation.getStatus() == Transition.Status.PAUSED) {
					rotationAnimation.play();
				}
			});
			btReverse.setOnAction(e -> {
				if(isMirrored != true) {
					isMirrored = true;
					slSpeed.setMin(-10);
					slSpeed.setMax(0);
					rotationAnimation.isAutoReverse();
				}
				else {
					isMirrored = false;
					slSpeed.setMin(0);
					slSpeed.setMax(10);
				}
			});
			
			setBottom(slSpeed);
			setTop(buttonPane);
			setStyle("-fx-border-color: black");
		}
		
		private void forceStop() {
			rotationAnimation.pause();
		}
		private void forceStart() {
			rotationAnimation.play();
		}
	}

}

