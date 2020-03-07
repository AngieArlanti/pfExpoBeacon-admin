package itba.edu.ar.pfExpoBeaconadmin.api.stand.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * A Stand.
 */
@Entity
public class Stand {

    /**
     * Stand's Id. Reference to Beacon Id
     */
    @Id
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

    /**
     * The Stand's picture list.
     */
    @Fetch(FetchMode.JOIN)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name="stand_pictures", joinColumns=@JoinColumn(name="stand_id"))
    @Column(name = "picture", nullable = false)
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
     * Empty constructor. For Hibernate purposes.
     */
    public Stand() {
    }

    /**
     * Constructor. For test purposes
     *
     * @param id               id
     * @param title            title
     * @param shortDescription short description
     */
    public Stand(final String id, final String title, final String shortDescription) {
        this.id = id;
        this.title = title;
        this.shortDescription = shortDescription;
    }

    /**
     * Constructor. For mapper purposes
     *
     * @param id
     * @param title
     * @param shortDescription
     * @param description
     * @param cover
     * @param latitude
     * @param longitude
     */
    public Stand(final String id, final @NotBlank(message = "Title is mandatory") String title,
                 final @NotBlank(message = "Short description is mandatory") String shortDescription,
                 final String description, final @NotBlank(message = "Cover is mandatory") String cover,
                 final double latitude, final double longitude, final List<String> pictures) {
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
     * Returns the Stand's id.
     *
     * @return the Stand's id.
     */
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    /**
     * Returns the Stand's title.
     *
     * @return the Stand's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the Stand's description.
     *
     * @return the Stand's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the Stand's shortDescription.
     *
     * @return the Stand's shortDescription.
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Returns the Stand's cover.
     *
     * @return the Stand's cover.
     */
    public String getCover() {
        return cover;
    }

    /**
     * Returns the Stand's latitude position.
     *
     * @return the Stand's latitude position.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the Stand's longitude position.
     *
     * @return the Stand's longitude position.
     */
    public double getLongitude() {
        return longitude;
    }

    public List<String> getPictures() {
        return pictures;
    }
}
