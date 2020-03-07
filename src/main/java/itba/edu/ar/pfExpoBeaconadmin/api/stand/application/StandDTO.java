package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class StandDTO {

    /**
     * Stand's Id. Reference to Beacon Id
     */
    private String id;

    /**
     * The Stand's title.
     */
    @NotBlank(message = "Title is mandatory")
    private String title;

    /**
     * The Stand's short description.
     */
    @NotBlank(message = "Short description is mandatory")
    private String shortDescription;

    /**
     * The Stand's description.
     */
    @NotBlank(message = "Description is mandatory")
    private String description;

    /**
     * The Stand's cover icon.
     */
    @NotBlank(message = "Cover is mandatory")
    private String cover;

    @NotNull(message = "Picture is mandatory")
    private List<String> pictures;


    /**
     * Stand's latitude position.
     */
    private double latitude;

    /**
     * Stand's longitude position.
     */
    private double longitude;

    /**
     * Stand's beacon id.
     */
    @NotNull(message = "Beacon is mandatory")
    private String beaconId;

    public StandDTO() {
    }

    StandDTO(final String id, final @NotBlank(message = "Title is mandatory") String title,
             final @NotBlank(message = "Short description is mandatory") String shortDescription,
             final @NotBlank(message = "Description is mandatory") String description,
             final @NotBlank(message = "Cover is mandatory") String cover,
             final double latitude, final double longitude,
             final @NotNull(message = "Picture is mandatory") List<String> pictures) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cover = cover;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pictures = pictures;
    }

    /**
     * Constructor. For test purposes
     *
     * @param title            title
     * @param shortDescription short description
     * @param description      description
     * @param cover            cover
     * @param beaconId         beaconId
     */
    public StandDTO(final String title,
                    final String shortDescription, final String description,
                    final String cover, final String beaconId) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cover = cover;
        this.beaconId = beaconId;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCover() {
        return cover;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getTitle() {
        return title;
    }

    public String getBeaconId() {
        return beaconId;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setBeacon(final Beacon beacon) {
        this.id = beacon.getId();
        this.latitude = beacon.getLatitude();
        this.longitude = beacon.getLongitude();
    }
}
