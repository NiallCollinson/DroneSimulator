/**
 * 
 */


/**
 * @author niallcollinson
 *
 */
public class UserDrone extends Drone {

	/**
	 * Set up the paddle controlled by the user
	 */
	public UserDrone() {
		
	}

	/**Set paddle drone size ir at ix,iy
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public UserDrone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'b';
		
	}

	/* (non-Javadoc)
	 * checks drone every animation 
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		// nothing to do 
	}

	/* (non-Javadoc)
	 * adjusts drone every animation
	 */
	@Override
	protected void adjustDrone() {
		// nothing to do 
	}
	/**
	 *  return string description as paddle
	 */
	protected String getStrType() {
		return "User";
	}	
}
