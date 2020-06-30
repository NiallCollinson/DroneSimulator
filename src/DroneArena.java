/**
 * 
 */


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.geometry.*;



/**
 * @author niallcollinson
 * Class for Arena of drone
 */
public class DroneArena implements Serializable{	
	
	double xSize, ySize;						// size of arena
	public ArrayList<Drone> allDrones;			// array list of all drone in arena
	
	
	
	
	/**
	 * construct an arena
	 */
	DroneArena() {
		this(500, 400);			// default size
	}
	
	
	
	
	/**
	 * adds drones to an array list 
	 * @return array list
	 */
	public ArrayList<Drone> getDrones() {
		ArrayList<Drone> a = allDrones;
		return a;
	}
	
	
	
	
	/**
	 * construct arena of size xS by yS
	 * @param xS 
	 * @param yS
	 */
	DroneArena(double xS, double yS){
		xSize = xS;
		ySize = yS;
		allDrones = new ArrayList<Drone>();					// list of all drones, initially empty
		allDrones.add(new TargetDrone(200, 600, 15));		// adds target drone
		allDrones.add(new UserDrone(xS/2, yS-20, 20));	// adds user drone
		
		
		
	}
	
	/**
	 * creates user drone
	 */
	public void newUserDrone() {
		double x = getRandomIntegerBetween(0.0,xSize); 
		double y = getRandomIntegerBetween(0.0,ySize);
		
		allDrones.add(new UserDrone(x, y, 20));
	}
	
	
	
	
	
	
	
	
	/**
	 * return arena size in x direction
	 * @return 
	 */
	public double getXSize() {
		return xSize;
	}
	
	
	
	
	
	/**
	 * return arena size in y direction
	 * @return
	 */
	public double getYSize() {
		return ySize;
	}
	
	
	
	
	/**
	 * 
 	all drones in the arena into interface bi
	 * @param bi
	 */
	public void drawArena(MyCanvas mc) {
		for (Drone b : allDrones) b.drawDrone(mc);		// draw all droness
	}
	
	
	
	
	
	
	
	/**
	 * check all drones .. see if need to change angle of moving drones, etc 
	 */
	public void checkDrones() {
		for (Drone b : allDrones) b.checkDrone(this);	// check all drones
	}
	
	
	
	
	
	
	/**
	 * adjust all drones .. move any moving ones
	 */
	public void adjustDrones() {
		for (Drone b : allDrones) b.adjustDrone();
	}
	
	
	
	
	
	
	/** 
	 * set the user drone at x,y
	 * @param x
	 * @param y
	 */
	public void setUser(double x, double y) {
		for (Drone b : allDrones)
			if (b instanceof UserDrone) b.setXY(x, y);
	}
	
	
	
	
	
	/**
	 * reads keyboard input and moves user drone if needed 
	 * @param e keyboard event input
	 */
	public void setUserKeyboard(KeyEvent e){
		for (Drone b : allDrones)				// iterates through array
			if (b instanceof UserDrone) {		// checks for instance of user drone
				double x = b.getX();			// gets x coordinate 
				double y = b.getY();			// gets y coordinate 
		
		if(e.getCode()==KeyCode.A ) {			// checks if key input was letter A 	
	    	 
			x = x - 10;							// reduce x by 10 coordinates 
	    	 b.setXY(x, y);						// sets new x value in coordinates with original y value 
	         
	      }
	
	      if(e.getCode()==KeyCode.S ) {			// checks if key input was letter S
		    	 
	    	  y = y + 10;						// increase y coordinates by 10 points    	  
		    	 b.setXY(x, y);					// set coordinates with original x value and new y value
		    	 
		      }
		
	      
	      if(e.getCode()==KeyCode.D ) {			// checks if key input was letter D
		    	 
	    	  x = x + 10;						// adds 10 to x coordinates 
		    	 b.setXY(x, y);					// sets with original y value and new x value 
		         
		      }

  
	      if(e.getCode()==KeyCode.W ) {			// check if key input was letter w
		    	 
	    	  y = y - 10;						// subtract 10 from y 
		    	b.setXY(x, y);					// set original x and new y value 
		    	 
		      }
	      }
	}
	
	
	
	/**
	 * sets up target drone 
	 */
	public void setTarget() {
		
		double x = getRandomIntegerBetween(0.0,xSize); // gets random x coordinates within size of canvas
		double y = getRandomIntegerBetween(0.0,ySize); // gets random y coordinates within size of canvas
		
		for (Drone b : allDrones)
			if (b instanceof TargetDrone) b.setXY(x, y); // iterates through all drones and if target drone set x and y
		
		
	}
	
	
	
	/**
	 * function which moves the predator drone towards the user drone
	 * @param drone pass in drone to be moved
	 */
	public void movePredator(Drone drone) {
		
		double x1 = 0;							// initialises variables 
		double x2 = 0;
		double y1 = 0;
		double y2 = 0;
		
		for (Drone b : allDrones)				
			if (b instanceof UserDrone) {		// iterates through drones and if user drone
				x1 = b.getX();					// get X coordinate 
				y1 = b.getY();					// get Y coordinate 
				
				
			}
		
		
				x2 = drone.getX();				// gets X coordinate of drone passed in 
				y2 = drone.getY();				// gets Y coordinate of drone passed in 
			
				
				/*
				 * if X value of user drone higher than the value of predator drone 
				 * then add 1 to the predator drones X coordinate
				 * if X value of user drone lower than the value of predator drone
				 * then minus 1 to the predator drones X coordinate
				 * if Y value of user drone higher than the value of predator drone
				 * then minus 1 to the predator drones Y coordinate
				 * if Y value of user drone lower than the value of predator drone
				 * then minus 1 to the predator drones Y coordinate 
				 */
				
		if(x1 > x2) x2 = x2 + 1;				 
		if(x1 < x2) x2 = x2 - 1;
		if(y1 > y2) y2 = y2 + 1;
		if(y1 < y2) y2 = y2 - 1;
		
		
		drone.setXY(x2, y2);  // set the new value of x and y values 
		
		
	}	
	
	
	/**
	 * return list of strings defining each drone
	 * @return
	 */
	public ArrayList<String> describeAll() {
		ArrayList<String> ans = new ArrayList<String>();		// set up empty arraylist
		for (Drone b : allDrones) ans.add(b.toString());		// add string defining each drone
		return ans;												// return string list
	}
	
	
	
	
	
	
	/**
	 * removes all drones
	 */
	public void clearCanvas() {
		allDrones.removeAll(allDrones);
	}
	
	
	/** 
	 * Check angle of drone ... if hitting wall, rebound; if hitting drone, change angle
	 * @param x				drone x position
	 * @param y				y
	 * @param rad			radius
	 * @param ang			current angle
	 * @param notID			identify of drone not to be checked
	 * @return				new angle 
	 */
	public double CheckDroneAngle(double x, double y, double rad, double ang, int notID) {
		double ans = ang;
		if (x < rad || x > xSize - rad) ans = 180 - ans;
			// if drone hit (tried to go through) left or right walls, set mirror angle, being 180-angle
		if (y < rad || y > ySize - rad) ans = - ans;
			// if try to go off top or bottom, set mirror angle
		
		for (Drone b : allDrones) 
			if (b.getID() != notID && b.hitting(x, y, rad)) ans = 180*Math.atan2(y-b.getY(), x-b.getX())/Math.PI;
				// check all drones except one with given id
				// if hitting, return angle between the other drone and this one.
		
		return ans;		// return the angle
	}

	
	
	
	/**
	 * check if the target drone has been hit by another drone
	 * @param target	the target drone
	 * @return 	true if hit
	 */
	public boolean checkHit(Drone target) {
		boolean ans = false;				// intialise and declare variable
		for (Drone b : allDrones)
			if (b instanceof UserDrone && b.hitting(target)) {
				ans = true;					// if user drone and drone passed in hitting set answer to true 
				
				
			}
				// try all drones, if normal drone, check if hitting the target
		return ans;
	}
	
	
	
	/**
	 * checks if a killer drone has hit a target drone 
	 * @param target drone passed in 
	 * @return answer if hitting true 
	 */
	public boolean checkKill(Drone target) {
		boolean ans = false;		// initialise and declare variable 
		for (int i = 0; i < allDrones.size(); i++) // iterate through array containing all drones
			if (allDrones.get(i) instanceof Drone && allDrones.get(i).hitting(target) && target instanceof KillerDrone) {	//if hitting
				ans = true;  						// set answer to true
				if(allDrones.get(i) != target)		// check if hitting itself... if not 
					if(allDrones.get(i) instanceof TargetDrone && allDrones.get(i).hitting(target) && target instanceof KillerDrone) { 
						// if target drone and killer drone hit 
						break;	// do nothing 
					}else {
					allDrones.remove(i);	// remove drone
					}
					
			}
		
		
		return ans;
	}

	
	
	/**
	 * checks if a predator drone has hit the user drone 
	 * @param target passed in drone
	 */
	public void checkPredatorKill(Drone target) {
		for (int i = 0; i < allDrones.size(); i++)		// iterates through array containing drones
			if (allDrones.get(i) instanceof UserDrone && allDrones.get(i).hitting(target) && target instanceof PredatorDrone) {
				// if user drone and predator drone colliding 
				
				if(allDrones.get(i) != target) // if target isnt colliding with itself 
					
					allDrones.remove(i);		// remove drone in array
					
					
			}
	}
	
	double scannerX = 0; // declare and initialise values 
	double scannerY = 0;
	
	
	/**
	 * function to detect other drones and move away from it 
	 * @param target pass in drone
	 */
	public void fearfulDroneScanner(FearfulDrone target) {
		for (int i = 0; i < allDrones.size(); i++) { // iterate through an array 
			Point2D thisLoc = new Point2D(allDrones.get(i).getX(), allDrones.get(i).getY());
			// create 2d point and get x and y coordinates from array 
			Point2D otherLoc = new Point2D(target.getX(), target.getY());
			// create 2d point and get x and y coordinates from drone passed in 
			
			Point2D dif = otherLoc.subtract(thisLoc); // subtract coordinates of target location from i in arrays location 
			if(dif.magnitude() < 100) { // if difference is more than 100
				
				
				Random randomiser = new Random(); // get random value 
				int coinToss;
				coinToss = randomiser.nextInt(2); // between 1 and 2
				
				if(coinToss == 0) {				
				
				target.bAngle-=10;				// move left
				}else {
				target.bAngle+=10;				// move right
				}
				
			}
		}
	}
	
	
	
	
	
	
	/**
	 * adds a normal drone to the array 
	 */
	public void addDrone() {
		double point1 = getRandomIntegerBetween(0.0,xSize); 
		double point2 = getRandomIntegerBetween(0.0,ySize);
		double radiusRandom = getRandomIntegerBetween(0, 360);
		double speedRandom = getRandomIntegerBetween(1,15);
		allDrones.add(new GameDrone(point1, point2, 10, radiusRandom, speedRandom));
	}
	
	/**
	 * adds a fearful drone
	 */
	public void addFearfulDrone() {
		double point1 = getRandomIntegerBetween(0.0,xSize); 
		double point2 = getRandomIntegerBetween(0.0,ySize);
		double radiusRandom = getRandomIntegerBetween(0, 360);
		
		allDrones.add(new FearfulDrone(point1, point2, 5, radiusRandom, 2));
	}
	
	
	
	
	/**
	 * adds a blocker drone to the array list
	 */
	public void addBlockerDrone() {
		double point1 = getRandomIntegerBetween(0.0,xSize); 
		double point2 = getRandomIntegerBetween(0.0,ySize);
		allDrones.add(new BlockerDrone(point1, point2, 15));	
	}
	
	/**
	 * adds a predator drone to the array list
	 */
	public void addPredatorDrone() {
		double point1 = getRandomIntegerBetween(0.0,xSize); 
		double point2 = getRandomIntegerBetween(0.0,ySize);
		double radiusRandom = getRandomIntegerBetween(0, 360);
		double speed = 1;
		double size = 20;
		
		
		
		allDrones.add(new PredatorDrone(point1, point2, size, radiusRandom, speed));	
	}
	
	
	/**
	 * adds a killer drone to the array list
	 */
	public void addKillerDrone() {
		double point1 = getRandomIntegerBetween(0.0,xSize); 
		double point2 = getRandomIntegerBetween(0.0,ySize);
		double radiusRandom = getRandomIntegerBetween(0, 360);
		double speed = 1;
		double size = 10;
		
		allDrones.add(new KillerDrone(point1, point2, size, radiusRandom, speed));
	}
	
	
	/**
	 * adds a target drone 
	 */
	public void addTargetDrone() {
		
		double x = getRandomIntegerBetween(0.0,xSize); 
		double y = getRandomIntegerBetween(0.0,ySize);
		
		
		allDrones.add(new TargetDrone(x, y, 15));
	}
	
	
	
	
	/**
	 * gets a random integer
	 * @param a lower end of the range
	 * @param b upper end of the range
	 * @return result random number between the lower and the upper end of a range 
	 */
	public double getRandomIntegerBetween(double a, double b) {
		Random r = new Random();
		double low = a;
		double high = b;
		double result = low + (high - low) * r.nextDouble();
		return result;
	}
	
}
