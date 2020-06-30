/**
 * 
 */

/**
 * @author niallcollinson
 *
 */
public abstract class Drone {
	
	
	
	protected double x, y, rad;						// position and size of drone
	protected char col;								// used to set colour
	static int droneCounter = 0;						// used to give each drone a unique identifier
	protected int droneID;							// unique identifier for item


	Drone() {
		this(100, 100, 10);
	}
	
	
	/**
	 * construct a drone of radius ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	Drone (double ix, double iy, double ir) {
		x = ix;
		y = iy;
		rad = ir;
		droneID = droneCounter++;			// set the identifier and increment class static
		col = 'r';
	}
	
	
	
	
	/**
	 * return x position
	 * @return
	 */
	public double getX() { return x; }
	
	
	
	
	/**
	 * return y position
	 * @return
	 */
	public double getY() { return y; }
	
	
	
	
	/**
	 * return radius of drone
	 * @return
	 */
	public double getRad() { return rad; }
	
	
	
	
	
	/** 
	 * set the drone at position nx,ny
	 * @param nx
	 * @param ny
	 */
	public void setXY(double nx, double ny) {
		x = nx;
		y = ny;
	}
	
	
	
	
	
	/**
	 * return the identity of drone
	 * @return
	 */
	public int getID() {return droneID; }
	
	
	
	
	/**
	 * draw a drone into the interface bi
	 * @param bi
	 */
	public void drawDrone(MyCanvas mc) {
		mc.showCircle(x, y, rad, col);
	}
	
	
	
	
	
	protected String getStrType() {
		return "Drone";
	}
	
	
	
	
	/** 
	 * return string describing drone
	 */
	public String toString() {
		return getStrType()+" at "+Math.round(x)+", "+Math.round(y);
	}
	
	
	
	
	/**
	 * abstract method for checking a drone in arena b
	 * @param b
	 */
	protected abstract void checkDrone(DroneArena b);
	
	
	
	
	/**
	 * abstract method for adjusting a drone (?moving it)
	 */
	protected abstract void adjustDrone();
	
	
	
	
	
	/**
	 * is drone at ox,oy size or hitting this drone
	 * @param ox
	 * @param oy
	 * @param or
	 * @return true if hitting
	 */
	public boolean hitting(double ox, double oy, double or) {
		return (ox-x)*(ox-x) + (oy-y)*(oy-y) < (or+rad)*(or+rad);
	}		// hitting if dist between drone and ox,oy < ist rad + or
	
	
	
	
	
	
	/** is drone hitting the other drone
	 * 
	 * @param oDrone - the other drone
	 * @return true if hitting
	 */
	public boolean hitting (Drone oDrone) {
		return hitting(oDrone.getX(), oDrone.getY(), oDrone.getRad());
	}
	


	
}
