package edu.eci.dosw.reto6;

import java.util.ArrayList;
import java.util.List;

/*
Represents a technical support ticket.
Stores the ticket information, its resolution
status and the technicians that reviewed it.
*/
public class Ticket {

    private final int id;
    private final String description;
    private final DifficultyLevel difficultyLevel;
    private final Priority priority;

    private boolean resolved;
    private String resolvedBy;

    private final List<String> technicianPath = new ArrayList<>();

/* 
Creates a new support ticket.
@param id unique ticket identifier
@param description description of the problem
@param difficultyLevel difficulty of the ticket
@param priority priority of the ticket
*/
public Ticket(
     int id,
     String description,
     DifficultyLevel difficultyLevel,
     Priority priority) {

        this.id = id;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.priority = priority;

        this.resolved = false;
        this.resolvedBy = null;
    }

/*
Returns the ticket identifier.
@return ticket id
*/
public int getId() {return id;}

/*
Returns the ticket description.
@return ticket description
*/
public String getDescription() {return description;}

/*
Returns the difficulty level.
@return ticket difficulty
*/
public DifficultyLevel getDifficultyLevel() {return difficultyLevel;}

/*
Returns the ticket priority.
@return ticket priority
*/
public Priority getPriority() {return priority;}

/*
Indicates whether the ticket was resolved.
@return true if resolved, otherwise false
*/
public boolean isResolved() {return resolved;}

/*
Returns the technician who resolved the ticket.
@return technician name or null if unresolved
*/
public String getResolvedBy() {return resolvedBy;}

/*
Returns the complete path of technicians
that reviewed the ticket.
@return list of technician names
*/
public List<String> getTechnicianPath() {return technicianPath;}

/*
Registers that a technician reviewed the ticket.
@param technicianName technician name
*/
public void addTechnicianToPath(String technicianName) {
        technicianPath.add(technicianName);
    }

/*     
Marks the ticket as resolved and stores
the responsible technician.
@param technicianName technician who resolved it
*/
public void resolve(String technicianName) {
        this.resolved = true;
        this.resolvedBy = technicianName;
    }
}