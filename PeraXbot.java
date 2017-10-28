import java.awt.Color;

import robocode.BulletMissedEvent;
import robocode.CharlieBot;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.WinEvent;

import static robocode.util.Utils.normalRelativeAngleDegrees;
public class PeraXbot extends CharlieBot {

	boolean peek; 
	double moveAmount; 
	boolean stopWhenSeeRobot = false;
	
	int count = 0; 

	double gunTurnAmt; 
	String trackName; 

	public void run() {
		setBodyColor(Color.ORANGE);
		setGunColor(Color.RED);
		setRadarColor(Color.orange);
		setBulletColor(Color.green);
		setScanColor(Color.green);

		
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		
		peek = false;

		turnLeft(getHeading());
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

		if (e.getBearing() > -120 && e.getBearing() < 120) {
			fire(3);
			back(40);
			
			
		}
		else {
			ahead(100);
		
			
		}
		
	}

	public void onRobotDetected(ScannedRobotEvent e) {
		// If the other robot is close by, and we have plenty of life,
		// fire hard!
		if (e.getDistance() < 50 && getEnergy() > 50) {
			fire(3);
		} // otherwise, fire 1.
		else {
			fire(1);
		}
		// Call scan again, before we turn the gun
		scan();
	}

	public void onHitByBullet(HitByBulletEvent e) {
		turnRight(normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));

		ahead(100);
		
		scan();
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
		
		public void onWin(WinEvent e) {
			for (int i = 0; i < 50; i++) {
				turnRight(30);
				turnLeft(30);
			}
		}
		
		
}