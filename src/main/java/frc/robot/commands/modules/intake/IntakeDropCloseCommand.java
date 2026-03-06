package frc.robot.commands.modules.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.IntakeDrop;

public class IntakeDropCloseCommand extends Command {
     private final IntakeDrop intakeDrop;

    public IntakeDropCloseCommand(IntakeDrop intakeDrop) {
        this.intakeDrop = intakeDrop;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
         intakeDrop.close();   
    }

    @Override
    public void end(boolean interrupted) {
        intakeDrop.stop();
    } 

    @Override
    public boolean isFinished() {
        return false;
    }   
}
