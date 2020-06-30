/**
 * 
 */


/**
 * @author niallcollinson
 * The Target drone which you are aiming at
 */
public class TargetDrone extends Drone {
	private int score;
	/**
	 * 
	 */
	public TargetDrone() {
		
	}
	
	

	/**
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public TargetDrone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		score = 0;
		col = 'g';
	}

	
	
	
	/** 
	 * checkdrone in arena 
	 * set in another location once hit
	 * @param b droneArena
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		if (b.checkHit(this)) { score++;			// if been hit, then increase score
		b.setTarget();
		}
		
	}
	
	
	
	/**
	 * draw drone and display score
	 */
	public void drawDrone(MyCanvas mc) {
		super.drawDrone(mc);
		mc.showInt(x, y, score);
	}
	
	
	

	/**
	 * adjustdrone
	 * for moving the drone - not needed here
	 */
	@Override
	protected void adjustDrone() {
			
	}
	
	
	
	/**
	 * return string defining drone ... here as target
	 */
	protected String getStrType() {
		return "Target";
	}	
}
