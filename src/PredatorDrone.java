import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.Node;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.FileInputStream;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.util.Duration;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.animation.*;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.*;
import javafx.scene.*;
import javafx.scene.Group;


/**
 * 
 * @author niallcollinson
 *
 */
public class PredatorDrone extends Drone{

	double bAngle, bSpeed;			// angle and speed of travel
	/**
	 * 
	 */
	public PredatorDrone() {
		// TODO Auto-generated constructor stub
	}

	/** Create game drone, size ir ay ix,iy, moving at angle ia and speed is
	 * @param ix
	 * @param iy
	 * @param ir
	 * @param ia
	 * @param is
	 */
	public PredatorDrone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		
		col = 'e';
		
		bAngle = ia;
		bSpeed = is;
	}

	
	/**
	 * checkdrone - change angle of travel if hitting wall or another drone
	 * check if its hit user drone 
	 * move drone towards predator drone 
	 * @param b   droneArena
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		bAngle = b.CheckDroneAngle(x, y, rad, bAngle, droneID);	
		
		b.checkPredatorKill(this);
		b.movePredator(this);
		
	}

	
	
	
	/**
	 * adjustdrone
	 * Here, move drone depending on speed and angle
	 */
	@Override
	protected void adjustDrone() {
		double radAngle = bAngle*Math.PI/180;		// put angle in radians
		x += bSpeed * Math.cos(radAngle);		// new X position
		y += bSpeed * Math.sin(radAngle);		// new Y position
	}


	
	
	/**
	 * return string defining drone type
	 */
	protected String getStrType() {
		return "Predator drone";
	}
	
}
