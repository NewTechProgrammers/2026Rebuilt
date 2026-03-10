// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Preferences;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static double MAX_SPEED = Units.feetToMeters(4.5);

  private static String key(String name) {
    return "Constants/" + name;
  }

  private static double prefDouble(String name, double defaultValue) {
    String key = key(name);
    Preferences.initDouble(key, defaultValue);
    return Preferences.getDouble(key, defaultValue);
  }

  private static int prefInt(String name, int defaultValue) {
    String key = key(name);
    Preferences.initInt(key, defaultValue);
    return Preferences.getInt(key, defaultValue);
  }

  public static void refresh() {
    MAX_SPEED = prefDouble("MAX_SPEED", Units.feetToMeters(4.5));

    DrivebaseConstants.WHEEL_LOCK_TIME = prefDouble("Drivebase/WheelLockTime", 10);

    OperatorConstants.kDriverControllerPort = prefInt("Operator/DriverControllerPort", 0);
    OperatorConstants.kSupportControllerPort = prefInt("Operator/SupportControllerPort", 1);
    OperatorConstants.DEADBAND = prefDouble("Operator/Deadband", 0.1);
    OperatorConstants.LEFT_Y_DEADBAND = prefDouble("Operator/LeftYDeadband", 0.1);
    OperatorConstants.RIGHT_X_DEADBAND = prefDouble("Operator/RightXDeadband", 0.1);
    OperatorConstants.TURN_CONSTANT = prefDouble("Operator/TurnConstant", 6);

    IntakeConstants.kIntakeSparkMaxPort = prefInt("Intake/SparkMaxPort", 10);
    IntakeConstants.kPercentOutputIntake = prefDouble("Intake/PercentOutput", 0.50);
    IntakeConstants.kIntakeAccel = prefDouble("Intake/Accel", 4.0);

    IntakeDropConstants.kIntakeDropSparkMaxPort = prefInt("IntakeDrop/SparkMaxPort", 13);
    IntakeDropConstants.kPercentOutputIntakeDrop = prefDouble("IntakeDrop/PercentOutput", 0.30);

    ShootingConstants.kSortingSparkMaxPort = prefInt("Shooting/SortingSparkMaxPort", 12);
    ShootingConstants.kPassthroughSparkMaxPort = prefInt("Shooting/PassthroughSparkMaxPort", 11);
    ShootingConstants.kShooterSparkMaxPort = prefInt("Shooting/ShooterSparkMaxPort", 9);
    ShootingConstants.kShooterAccel = prefDouble("Shooting/ShooterAccel", 4.0);
    ShootingConstants.kPercentOutputSorting = prefDouble("Shooting/PercentOutputSorting", -0.20);
    ShootingConstants.kPercentOutputPassthrough = prefDouble("Shooting/PercentOutputPassthrough", -0.45);
    ShootingConstants.kPercentOutputShooter = prefDouble("Shooting/PercentOutputShooter", -0.65);
    ShootingConstants.kShooterReferenceDistanceMeters =
        prefDouble("Shooting/ReferenceDistanceMeters", 2.0);
    ShootingConstants.kShooterPercentPerMeter =
        prefDouble("Shooting/PercentPerMeter", 0.25);

    AutoConstants.kMaxSpeedMetersPerSecond =
        prefDouble("Auto/MaxSpeedMetersPerSecond", 3);
    AutoConstants.kMaxAccelerationMetersPerSecondSquared =
        prefDouble("Auto/MaxAccelerationMetersPerSecondSquared", 3);
    AutoConstants.kMaxAngularSpeedRadiansPerSecond =
        prefDouble("Auto/MaxAngularSpeedRadiansPerSecond", Math.PI);
    AutoConstants.kMaxAngularSpeedRadiansPerSecondSquared =
        prefDouble("Auto/MaxAngularSpeedRadiansPerSecondSquared", Math.PI);
    AutoConstants.kPXController = prefDouble("Auto/PXController", 1);
    AutoConstants.kPYController = prefDouble("Auto/PYController", 1);
    AutoConstants.kPThetaController = prefDouble("Auto/PThetaController", 1);
    AutoConstants.kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            AutoConstants.kMaxAngularSpeedRadiansPerSecond,
            AutoConstants.kMaxAngularSpeedRadiansPerSecondSquared);
  }

  public static final class DrivebaseConstants {
    public static double WHEEL_LOCK_TIME = 10; // seconds
  }

  public static final class OperatorConstants {
    public static int kDriverControllerPort = 0;
    public static int kSupportControllerPort = 1;

    // Joystick Deadband
    public static double DEADBAND        = 0.1;
    public static double LEFT_Y_DEADBAND = 0.1;
    public static double RIGHT_X_DEADBAND = 0.1;
    public static double TURN_CONSTANT    = 6;
  }


  public static final class IntakeConstants {
    // We will have to change these later
    public static int kIntakeSparkMaxPort = 10;
    public static double kPercentOutputIntake = 0.50;

    public static double kIntakeAccel = 4.0;
  }

  public static final class IntakeDropConstants {
    // We will have to change these later
    public static int kIntakeDropSparkMaxPort = 13;
    public static double kPercentOutputIntakeDrop = 0.30;

  }
  public static final class ShootingConstants {
    // We will need to change these later
    public static int kSortingSparkMaxPort = 12;
    public static int kPassthroughSparkMaxPort = 11;
    public static int kShooterSparkMaxPort = 9;

    public static double kShooterAccel = 4.0;
    
    // We will need to change these later
    public static double kPercentOutputSorting = -0.20;
    public static double kPercentOutputPassthrough = -0.45;
    public static double kPercentOutputShooter = -0.65;
    // Distance (meters) where shooter uses exactly kPercentOutputShooter.
    public static double kShooterReferenceDistanceMeters = 2.0;
    // Additional shooter output applied per meter away from reference distance.
    public static double kShooterPercentPerMeter = 0.25;
  }

  public static final class AutoConstants {
    public static double kMaxSpeedMetersPerSecond = 3;
    public static double kMaxAccelerationMetersPerSecondSquared = 3;
    public static double kMaxAngularSpeedRadiansPerSecond = Math.PI;
    public static double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;

    public static double kPXController = 1;
    public static double kPYController = 1;
    public static double kPThetaController = 1;

    // Constraint for the motion profiled robot angle controller
    public static TrapezoidProfile.Constraints kThetaControllerConstraints =
        new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
  }
}
