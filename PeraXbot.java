import java.awt.Color;

import javax.swing.text.StyleContext.SmallAttributeSet;

import robocode.BravoBot;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;

public class PeraXbot extends BravoBot {

	boolean peek; 
	double moveAmount; 

	
	
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

		if (e.getBearing() > -90 && e.getBearing() < 90) {
			back(100);
		}
		else {
			ahead(100);
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
		if (peek) {
			scan();
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