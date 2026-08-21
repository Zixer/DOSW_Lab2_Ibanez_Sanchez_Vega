package edu.eci.dosw.reto6;

/* Technician specialized in intermediate tickets
with a maximum priority of HIGH.*/
public class IntermediateTechnician
        extends Technician {

/* Creates an intermediate support technician.*
@param name technician name*/
public IntermediateTechnician(String name) {
        super(name,DifficultyLevel.INTERMEDIATE,Priority.HIGH);
    }
}