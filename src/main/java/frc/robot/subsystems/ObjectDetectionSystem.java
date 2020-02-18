package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.util.Debug;

public class ObjectDetectionSystem {

    double xDistFromCH;
    double yDistFromCH;
    double area;
    double validTarget;
    double tShort;
    double tLong;
    private double turnDifference = 7;
    private String ballPosition = "none";
    private double SPEED = 0.6; // negative numbers move robot where limelight is looking

    public ObjectDetectionSystem() {
    }

    public void update() {

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");
        NetworkTableEntry tv = table.getEntry("tv");
        NetworkTableEntry tshort = table.getEntry("tshort");
        NetworkTableEntry tlong = table.getEntry("tlong");

        //read values periodically
        xDistFromCH = tx.getDouble(0.0);
        yDistFromCH = ty.getDouble(0.0);
        area = ta.getDouble(0.0);           // Target Area (0%-100% of image)
        validTarget = tv.getDouble(0.0);
        tShort = tshort.getDouble(0.0);
        tLong = tlong.getDouble(0.0);

        //post to smart dashboard periodically
        Debug.printOnce("LimelightX: " + xDistFromCH +
                     "\n LimelightY: " + yDistFromCH + 
                     "\n Target Area %: " + area +
                     "\n ValidTarget: " + validTarget +
                     "\n Short Side of Yellow Box: " + 
                     "\n");

        // Uncomment to make robot move while seeing ball.
        if (xDistFromCH >= -12 && xDistFromCH  <= 12 &&
            area <= 3 && area >= 0.03 &&          
            validTarget == 1.0) {
            System.out.println("Target within bounds.\n");
            DriveSystem.moveWheels(SPEED, SPEED);
        } else {
            System.out.println("Target outside of bounds.\n");
            DriveSystem.stopWheels();
        }

        if (xDistFromCH > turnDifference && validTarget == 1.0) {
            ballPosition = "Right";
            System.out.println("Ball's Position: " + ballPosition);
            DriveSystem.moveWheels(SPEED * .66, -SPEED * .66);
        }
        if (xDistFromCH < -turnDifference && validTarget == 1.0) {
            ballPosition = "Left";
            System.out.println("Ball's Position: " + ballPosition);
            DriveSystem.moveWheels(-SPEED * .66, SPEED * .66);
        } 
        if (xDistFromCH < -turnDifference &&
            xDistFromCH > turnDifference && 
            validTarget == 1.0) {
            ballPosition = "Center";
            System.out.println("Ball's Position: " + ballPosition);
            DriveSystem.stopWheels();
        }
        
    }
}