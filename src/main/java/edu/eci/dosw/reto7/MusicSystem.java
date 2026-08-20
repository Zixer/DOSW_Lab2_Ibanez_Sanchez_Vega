package reto7;

/**
 * Represents a music system controlled by the remote
 */
public class MusicSystem {
    private int volume = 20;

    /**
     * Changes the current volume
     * @param volume new volume value
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * Returns the current volume
     * @return current volume
     */
    public int getVolume() {
        return volume;
    }
}