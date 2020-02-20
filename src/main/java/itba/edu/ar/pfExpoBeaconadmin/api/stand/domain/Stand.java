package itba.edu.ar.pfExpoBeaconadmin.api.stand.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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
    private String description;

    /**
     * The Stand's cover icon.
     */
    @NotBlank(message = "Cover is mandatory")
    private String cover;

    //TODO: (ma 2020-2-19) add pictures

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
}
