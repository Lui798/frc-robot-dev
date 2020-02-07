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
    private DifferentialDrive wheelsMotor1; //The left... or right motor? At this point, I don't even know.
    private DifferentialDrive wheelsMotor2; //The opposite of the wheelsMotor1.

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

        //read values periodically
        xDistFromCH = tx.getDouble(0.0);
        yDistFromCH = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
        validTarget = tv.getDouble(0.0);

        //post to smart dashboard periodically
        Debug.printOnce("LimelightX" + xDistFromCH + "\n LimelightY" + yDistFromCH + "\n LimelightArea" + area + " \n ValidTarget"+ validTarget);
        
        if (validTarget==1.0) {
            System.out.println("Found target, Value: " + validTarget);
        }

        if (xDistFromCH >= -5 && xDistFromCH  <= 5 && 
            yDistFromCH >= -5 && yDistFromCH<= 5 && validTarget == 1.0) {
            System.out.println("Target within bounds.\n");
            wheelsMotor2.tankDrive(.8,.8);
            wheelsMotor1.tankDrive(.8,.8);
        } else {
            System.out.println("Target outside of bounds.\n");
            wheelsMotor2.stopMotor();
            wheelsMotor1.stopMotor();
        }

        // These two lines move robot forward.
        
    }
}
