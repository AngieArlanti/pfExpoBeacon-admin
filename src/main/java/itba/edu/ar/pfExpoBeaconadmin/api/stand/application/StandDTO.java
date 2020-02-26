package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.beacon.model.Beacon;
import itba.edu.ar.pfExpoBeaconadmin.api.picture.model.Picture;
import org.springframework.web.multipart.MultipartFile;

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
    private String description;

    /**
     * The Stand's cover icon.
     */
    @NotBlank(message = "Cover is mandatory")
    private String cover;

    private List<MultipartFile> uploadedFiles;

    private List<Picture> pictures;


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
             final String description, final @NotBlank(message = "Cover is mandatory") String cover,
             final double latitude, final double longitude,
             final @NotNull(message = "Picture is mandatory") List<Picture> pictures) {
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
     * @param id               id
     * @param title            title
     * @param shortDescription short description
     * @param description      description
     * @param cover            cover
     */
    public StandDTO(final String id, final String title,
                    final String shortDescription, final String description,
                    final String cover) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cover = cover;
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

    public List<MultipartFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public void setPictures(final List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setUploadedFiles(final List<MultipartFile> uploadedFile) {
        this.uploadedFiles = uploadedFile;
    }

    public void setBeacon(final Beacon beacon) {
        this.id = beacon.getId();
        this.latitude = beacon.getLatitude();
        this.longitude = beacon.getLongitude();
    }
}
