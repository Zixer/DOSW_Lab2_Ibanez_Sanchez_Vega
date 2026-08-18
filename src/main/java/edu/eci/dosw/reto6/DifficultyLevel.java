package edu.eci.dosw.reto6;

/* Represents the difficulty levels available
for technical support tickets.
*/
public enum DifficultyLevel {

    BASIC(1),
    INTERMEDIATE(2),
    ADVANCED(3);

    private final int value;

/*
Creates a difficulty level with a numeric value.
@param value numeric level used for comparisons
*/
DifficultyLevel(int value) {this.value = value;}

/*   
Returns the numeric value of the difficulty level.
@return difficulty numeric value
*/
public int getValue() {return value;}
}