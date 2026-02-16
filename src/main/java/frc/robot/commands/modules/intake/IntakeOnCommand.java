package frc.robot.commands.modules.intake;

import frc.robot.subsystems.intake.Intake;

import edu.wpi.first.wpilibj2.command.Command;

public class IntakeOnCommand extends Command {
    private final Intake intake;

    public IntakeOnCommand(Intake intake) {
        this.intake = intake;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
         intake.start();   
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    } 

    @Override
    public boolean isFinished() {
        return false;
    }

}