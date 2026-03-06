package frc.robot.subsystems.Vision;

import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import frc.robot.LimelightHelpers;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;


public class Position extends SubsystemBase {
    private final SwerveDrive swerveDrive;

    public static boolean doRejectUpdate = false;
    public static boolean twoTags = false;

    public Position(SwerveDrive swerveDrive) {
        this.swerveDrive = swerveDrive;
    }

public void updateOdometryWithVision() {
swerveDrive.updateOdometry();

    // Set heading in Limelight
    double yaw = swerveDrive.getYaw().getDegrees();
    LimelightHelpers.SetRobotOrientation("limelight", yaw, 0, 0, 0, 0, 0);

    // Log current yaw
    SmartDashboard.putNumber("Vision/Gyro Yaw", yaw);

    // Read MegaTag2
    LimelightHelpers.PoseEstimate mt2 =
        LimelightHelpers.getBotPoseEstimate_wpiBlue_MegaTag2("limelight");

    // Log Limelight data
    boolean hasValidTags = mt2 != null && mt2.tagCount > 0;
    SmartDashboard.putBoolean("Vision/Has Valid Tags", hasValidTags);
    SmartDashboard.putNumber("Vision/Tag Count", mt2 != null ? mt2.tagCount : 0);

    // Reject invalid measurements
    if (Math.abs(yaw) > 720) {
        DriverStation.reportWarning("[Vision] Measurement rejected – yaw out of range: " + yaw, false);
        SmartDashboard.putString("Vision/Reject Reason", "Yaw > 720°");
        SmartDashboard.putBoolean("Vision/Measurement Applied", false);
        return;
    }

    if (mt2 == null || mt2.tagCount == 0) {
        SmartDashboard.putString("Vision/Reject Reason", "No tags");
        SmartDashboard.putBoolean("Vision/Measurement Applied", false);
        return;
    }

    // Log pose before applying
    Pose2d estimatedPose = mt2.pose;
    SmartDashboard.putNumber("Vision/Pose X",        estimatedPose.getX());
    SmartDashboard.putNumber("Vision/Pose Y",        estimatedPose.getY());
    SmartDashboard.putNumber("Vision/Pose Rotation", estimatedPose.getRotation().getDegrees());
    SmartDashboard.putNumber("Vision/Timestamp",     mt2.timestampSeconds);
    SmartDashboard.putNumber("Vision/Avg Tag Dist",  mt2.avgTagDist);

    // Apply vision measurement
    swerveDrive.setVisionMeasurementStdDevs(VecBuilder.fill(.7, .7, 9999999));
    swerveDrive.addVisionMeasurement(estimatedPose, mt2.timestampSeconds);

    SmartDashboard.putString("Vision/Reject Reason",      "None");
    SmartDashboard.putBoolean("Vision/Measurement Applied", true);
    SmartDashboard.putNumber("Vision/Updates This Match",
        SmartDashboard.getNumber("Vision/Updates This Match", 0) + 1);
}

}

