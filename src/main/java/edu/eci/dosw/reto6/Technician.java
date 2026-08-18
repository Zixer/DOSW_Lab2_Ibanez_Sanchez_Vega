package edu.eci.dosw.reto6;

/**
 * Base handler of the Chain of Responsibility
 * Each technician decides whether they can resolvea ticket. If not, the ticket is passed to the next technician in the chain.
 */
public abstract class Technician {

    protected final String name;
    protected final DifficultyLevel specialty;
    protected final Priority maximumPriority;

    protected Technician nextTechnician;

    /**
     * Creates a technician with a specialty
     * and maximum supported priority
     * @param name technician name
     * @param specialty maximum difficulty handled
     * @param maximumPriority maximum priority handled
     */
    protected Technician(String name,DifficultyLevel specialty,Priority maximumPriority) {

        this.name = name;
        this.specialty = specialty;
        this.maximumPriority = maximumPriority;
    }

    /**
     * Defines the next technician in the chain
     * @param nextTechnician next handler
     * @return the next technician to allow chained configuration
     */
    public Technician setNext(Technician nextTechnician) {
        this.nextTechnician = nextTechnician;
        return nextTechnician;
    }

    /**
     * Processes a support ticket
     * If this technician can resolve the ticket, the ticket is marked as resolved
     * Otherwise, the ticket is passed to the next technician in the chain
     * If no technician can resolve it, the ticket remains pending escalation
     * @param ticket ticket to process
     */
    public void handleTicket(Ticket ticket) {
        ticket.addTechnicianToPath(name);
        System.out.println(name + " is reviewing Ticket #" + ticket.getId());
        if (canHandle(ticket)) {ticket.resolve(name);
            System.out.println("Ticket #" + ticket.getId() + " resolved by " + name);
            return;
        }
        System.out.println(name + " cannot resolve Ticket #" + ticket.getId());
        if (nextTechnician != null) {System.out.println("Passing ticket to " + nextTechnician.name);
            nextTechnician.handleTicket(ticket);
        } else {
            System.out.println("Ticket #" + ticket.getId() + " remains PENDING ESCALATION.");
        }
    }

    /**
     * Determines whether this technician can
     * resolve the specified ticket
     * @param ticket ticket to evaluate
     * @return true if the technician can handle it
     */
    protected boolean canHandle(Ticket ticket) {
        boolean validDifficulty = ticket.getDifficultyLevel().getValue() <= specialty.getValue();
        boolean validPriority =ticket.getPriority().getValue() <= maximumPriority.getValue();
        return validDifficulty && validPriority;
    }
}




