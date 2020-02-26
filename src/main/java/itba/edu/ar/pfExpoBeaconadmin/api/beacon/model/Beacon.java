package itba.edu.ar.pfExpoBeaconadmin.api.beacon.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Beacon {

    @Id
    private String id;

    private boolean used = false;

    private double longitude;

    private double latitude;

    public Beacon() {
    }

    public Beacon(final String id, final double longitude, final double latitude) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public boolean isUsed() {
        return used;
    }

    private void setUsed(final boolean used) {
        this.used = used;
    }

    public void used() {
        setUsed(true);
    }

    public void avaliable() {
        setUsed(false);
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}
