import robocode.AlphaBot;
import robocode.ScannedRobotEvent;

public class PeraXbot1 extends AlphaBot {
	
		public void run() {
	        while (true) {
	            back(50);
	            turnGunRight(360);
				ahead(25);
				turnGunLeft(180);
	            turnLeft(90);
	            turnGunRight(90);
				back(50);
				turnGunLeft(180);
				back(50);
	        }
	    }
	 
	    public void onScannedRobot(ScannedRobotEvent e) {
	        fire(10);
	    }
	}

