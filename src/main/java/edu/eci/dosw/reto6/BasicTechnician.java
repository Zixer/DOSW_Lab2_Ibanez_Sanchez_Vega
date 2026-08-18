package reto6;

/**
 * Technician specialized in basic tickets with a maximum priority of MEDIUM
 */
public class BasicTechnician extends Technician {

    /**
     * Creates a basic support technician
     * @param name technician name
     */
    public BasicTechnician(String name) {
        super(name,DifficultyLevel.BASIC,Priority.MEDIUM);
    }
}
