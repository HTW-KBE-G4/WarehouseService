package de.htwkbeg4.WarehouseService.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PCComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String model;

    private String releaseDate;

    private String manufacturer;

    private String uvp;

    private String type;

    public PCComponent() {
    }

    public PCComponent(String model, String releaseDate, String manufacturer, String uvp, String type) {
        this.model = model;
        this.releaseDate = releaseDate;
        this.manufacturer = manufacturer;
        this.uvp = uvp;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PCComponent{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", uvp='" + uvp + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUvp() {
        return uvp;
    }

    public void setUvp(String uvp) {
        this.uvp = uvp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
