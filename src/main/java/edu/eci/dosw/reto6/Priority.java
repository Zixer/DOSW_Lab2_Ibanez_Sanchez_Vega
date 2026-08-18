package edu.eci.dosw.reto6;

/*
Represents the priority levels of a support ticket.
*/
public enum Priority {

    LOW(1),
    MEDIUM(2),
    HIGH(3);

    private final int value;

/*
Creates a priority with a numeric value.
@param value numeric priority value
*/
Priority(int value) {this.value = value;}

/*    
Returns the numeric value of the priority.
@return priority numeric value
*/
public int getValue() {return value;}}