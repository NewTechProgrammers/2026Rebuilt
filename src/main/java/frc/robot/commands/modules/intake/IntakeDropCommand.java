package frc.robot.commands.modules.intake;

import frc.robot.subsystems.intake.IntakeDrop;
import edu.wpi.first.wpilibj2.command.Command;

public class IntakeDropCommand extends Command {
    private final IntakeDrop intakeDrop;

    public IntakeDropCommand(IntakeDrop intakeDrop) {
        this.intakeDrop = intakeDrop;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
         intakeDrop.start();   
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