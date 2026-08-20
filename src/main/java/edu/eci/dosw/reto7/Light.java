package edu.eci.dosw.reto7;

/**
 * Represents a light controlled by the remote.
 */
public class Light {

    private boolean on;

    /**
     * Turns the light on.
     */
    public void turnOn() {
        on = true;
    }

    /**
     * Turns the light off.
     */
    public void turnOff() {
        on = false;
    }

    /**
     * Indicates whether the light is on.
     *
     * @return true if the light is on
     */
    public boolean isOn() {
        return on;
    }
}