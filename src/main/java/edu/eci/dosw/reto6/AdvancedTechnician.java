package edu.eci.dosw.reto6;
/* Technician specialized in advanced tickets
with a maximum priority of MEDIUM.*
Advanced tickets with HIGH priority therefore
remain pending escalation.*/
public class AdvancedTechnician
        extends Technician {

/*Creates an advanced support technician.*
@param name technician name*/
public AdvancedTechnician(String name) {
        super(name,DifficultyLevel.ADVANCED,Priority.MEDIUM);
    }
}