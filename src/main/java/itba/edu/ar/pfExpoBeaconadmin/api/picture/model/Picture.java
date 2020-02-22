package itba.edu.ar.pfExpoBeaconadmin.api.picture.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Picture {

    @Id
    private int id;

    private String fileName;

    private String contentType;

    @Lob
    private byte[] data;

    public Picture() {
    }

    public Picture(final String fileName, final String contentType, final byte[] data) {
        this.fileName = fileName;
        this.contentType = contentType;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getData() {
        return data;
    }

    public String getContentType() {
        return contentType;
    }
}
