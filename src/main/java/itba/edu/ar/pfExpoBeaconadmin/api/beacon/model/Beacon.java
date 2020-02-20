package itba.edu.ar.pfExpoBeaconadmin.api.beacon.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Beacon {

    @Id
    private String id;

    private boolean used = false;

    public Beacon() {
    }

    public Beacon(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(final boolean used) {
        this.used = used;
    }
}
