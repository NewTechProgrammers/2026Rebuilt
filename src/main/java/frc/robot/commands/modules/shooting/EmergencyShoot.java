package frc.robot.commands.modules.shooting;

import frc.robot.subsystems.Shooting;
import edu.wpi.first.wpilibj2.command.Command;

public class EmergencyShoot extends Command {
    private final Shooting shooting;

    public EmergencyShoot(Shooting shooting) {
        this.shooting = shooting;
        addRequirements(shooting);
    }

    @Override
    public void initialize() {
        shooting.setEmergencyOverride(true);
    }

    @Override
    public void execute() {
        shooting.runEmergencyRaw();   
    }

    @Override
    public void end(boolean interrupted) {
        shooting.setEmergencyOverride(false);
        shooting.stop();
    } 

    @Override
    public boolean isFinished() {
        return false;
    }
}
