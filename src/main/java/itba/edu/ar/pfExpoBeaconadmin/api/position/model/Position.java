package itba.edu.ar.pfExpoBeaconadmin.api.position.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Position {

    @Id
    private int id;

    private double longitude;

    private double latitude;

    private boolean used = false;

    public Position() {
    }

    /**
     * Constructor. For test purposes
     *
     * @param id        id
     * @param longitude longitude
     * @param latitude  latitude
     */
    public Position(final int id, final double longitude, final double latitude) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public int getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isUsed() {
        return used;
    }

    private void setUsed(boolean used) {
        this.used = used;
    }

    public void used() {
        setUsed(true);
    }

    public void avaliable() {
        setUsed(false);
    }
}
