import java.awt.Color;

import robocode.AlphaBot;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
public class PeraXbot extends AlphaBot {

	boolean peek; 
	double moveAmount; 
	boolean stopWhenSeeRobot = false;
	
	
	public void run() {
		
		
		setBodyColor(Color.red);
		setGunColor(Color.blue);
		setRadarColor(Color.orange);
		setBulletColor(Color.cyan);
		setScanColor(Color.green);

		
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		
		peek = false;

		turnLeft(getHeading() % 90);
		ahead(moveAmount);
		peek = true;
		turnGunRight(90);
		turnRight(90);

		while (true) {

			peek = true;
			ahead(moveAmount);
			peek = false;
			turnRight(90);
		}
	}

	public void onHitRobot(HitRobotEvent e) {
		
		double turnGunAmt = normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());

		turnGunRight(turnGunAmt);
		if (e.getEnergy() > 16) {
			fire(3);
		} else if (e.getEnergy() > 10) {
			fire(2);
		} else if (e.getEnergy() > 4) {
			fire(1);
		} else if (e.getEnergy() > 2) {
			fire(.5);
		} else if (e.getEnergy() > .4) {
			fire(.1);
		}
		ahead(40);

		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		}
		else {
			ahead(100);
		}
		
	}

		public void onScannedRobot(ScannedRobotEvent e) {
		
			if (stopWhenSeeRobot) {
				stop();
				smartFire(e.getDistance());
				scan();
				resume();
			} else {
				smartFire(e.getDistance());
			}	
		}
	

		public void smartFire(double robotDistance) {
			if (robotDistance > 200 || getEnergy() < 15) {
				fire(1);
			} else if (robotDistance > 50) {
				fire(2);
			} else {
				fire(3);
			}
		}
		
		
		
		
}