package itba.edu.ar.pfExpoBeaconadmin.api.stand.application;

import itba.edu.ar.pfExpoBeaconadmin.api.picture.model.Picture;
import itba.edu.ar.pfExpoBeaconadmin.api.position.model.Position;
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
     * Stand's position id.
     */
    @NotNull(message = "Position is mandatory")
    private Integer positionId;

    public StandDTO() {
    }

    StandDTO(final String id, final @NotBlank(message = "Title is mandatory") String title,
             final @NotBlank(message = "Short description is mandatory") String shortDescription,
             final String description, final @NotBlank(message = "Cover is mandatory") String cover,
             final double latitude, final double longitude) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
        this.cover = cover;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public Integer getPositionId() {
        return positionId;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public void setPosition(final Position position) {
        this.latitude = position.getLatitude();
        this.longitude = position.getLongitude();
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
}
