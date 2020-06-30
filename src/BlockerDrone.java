/**
 * 
 */


/**
 * @author niallcollinson
 * Drone which gets in way of game drone
 */
public class BlockerDrone extends Drone {

	/**
	 * 
	 */
	public BlockerDrone() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * defines parameters for when being called
	 * @param ix
	 * @param iy
	 * @param ir
	 */
	public BlockerDrone(double ix, double iy, double ir) {
		super(ix, iy, ir);
		col = 'o';											//colour of ball
	}

	/* (non-Javadoc)
	 * nothing to do 
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		// null

	}

	/* (non-Javadoc)
	 * nothing to do because does not move
	 */
	@Override
	protected void adjustDrone() {
		// nowt to do

	}
	protected String getStrType() {
		return "Blocker";
	}	

}
