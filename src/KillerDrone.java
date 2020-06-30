import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @author niallcollinson
 *
 */
public class KillerDrone extends Drone{
	
	DroneArena DroneArena = new DroneArena();
	double bAngle, bSpeed;				// angle and speed of travel
	
	/**
	 * 
	 */
	public KillerDrone() {
		
		// TODO Auto-generated constructor stub
	}

	/** Create game ball, size ir ay ix,iy, moving at angle ia and speed is
	 * @param ix
	 * @param iy
	 * @param ir
	 * @param ia
	 * @param is
	 */
	public KillerDrone(double ix, double iy, double ir, double ia, double is) {
		super(ix, iy, ir);
		
		col = 'g';
		bAngle = ia;
		bSpeed = is;
	}

	
	
	DroneArena dArena = new DroneArena();	
	
	
	/**
	 * checkball - change angle of travel if hitting wall or another ball
	 * @param b   ballArena
	 */
	@Override
	protected void checkDrone(DroneArena b) {
		bAngle = b. CheckDroneAngle(x, y, rad, bAngle, droneID);

		if(b.checkKill(this)) {

			
		}
		
		
	}
	
	
	
	

	
	
	

	/**
	 * adjustBall
	 * Here, move ball depending on speed and angle
	 */
	@Override
	protected void adjustDrone() {
		double radAngle = bAngle*Math.PI/180;		// put angle in radians
		x += bSpeed * Math.cos(radAngle);		// new X position
		y += bSpeed * Math.sin(radAngle);		// new Y position
	}
	
	
	
	/**
	 * return string defining ball type
	 */
	protected String getStrType() {
		return "Killer drone";
	}
	
	
	/**
	 * check if target is hitting the drones
	 * @param target 
	 * @return ans
	 */
	public boolean checkKillHit(Drone target) {
		boolean ans = false;
		for (Drone b : DroneArena.getDrones())
			if (b.hitting(target)) ans = true;
				// try all balls, if GameBall, check if hitting the target
		return ans;
	}
	

	
	
	
}
