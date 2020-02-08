package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.util.Debug;

import frc.robot.controls.OI;

public class ObjectDetectionSystem {

    double xDistFromCH;
    double yDistFromCH;
    double area;
    double validTarget;
    double tShort;
    double tLong;
    private double turnDifference = 7;
    private String ballPosition = "center";
    private DifferentialDrive wheelsMotor1; //The left... or right motor? At this point, I don't even know.
    private DifferentialDrive wheelsMotor2; //The opposite of the wheelsMotor1.
    private double SPEED = -0.5; // negative numbers move robot where limelight is looking

    public ObjectDetectionSystem() {
        wheelsMotor1 = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(2));
        wheelsMotor2 = new DifferentialDrive(new PWMVictorSPX(1), new PWMVictorSPX(3));
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
        
        if (validTarget==1.0) {
            System.out.println("Found target, Value: " + validTarget);
            ballPosition = "None";
        } else {
            System.out.println("\nNo target, value: " + validTarget);
        }

        // Debug.printOnce("\n Target Area %: " + area);

        // Uncomment to make robot move while seeing ball.
        if (xDistFromCH >= -12 && xDistFromCH  <= 12 &&
            area <= 6 && area >= 0.03 && 
            validTarget == 1.0) {
            System.out.println("Target within bounds.\n");
            wheelsMotor2.tankDrive(SPEED * 1.16, SPEED);
            wheelsMotor1.tankDrive(SPEED * 1.16, SPEED);
        } else {
            System.out.println("Target outside of bounds.\n");
            wheelsMotor2.stopMotor();
            wheelsMotor1.stopMotor();
        }

        if (xDistFromCH > turnDifference && validTarget == 1.0) {
            ballPosition = "Right";
            System.out.println("Ball's Position: " + ballPosition);
            wheelsMotor1.tankDrive(SPEED * .5, SPEED);
            wheelsMotor2.tankDrive(SPEED * .5, SPEED);
        }
        if (xDistFromCH < -turnDifference && validTarget == 1.0) {
            ballPosition = "Left";
            System.out.println("Ball's Position: " + ballPosition);
            wheelsMotor1.tankDrive(SPEED, SPEED * .66);
            wheelsMotor2.tankDrive(SPEED, SPEED * .66);
        } 
        if (xDistFromCH < -turnDifference &&
            xDistFromCH > turnDifference && 
            validTarget == 1.0) {
            ballPosition = "Center";
            System.out.println("Ball's Position: " + ballPosition);
            wheelsMotor1.stopMotor();
            wheelsMotor2.stopMotor();
        }

        // These two lines move robot forward.
        
    }
}