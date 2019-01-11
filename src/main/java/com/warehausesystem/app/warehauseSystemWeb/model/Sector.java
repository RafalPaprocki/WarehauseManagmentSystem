package com.warehausesystem.app.warehauseSystemWeb.model;



import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sectors")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String sector;

    @NotNull
    private Long width;

    @NotNull
    private Long heigh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Long getWidth() {
        return width;
    }

    public void setWidth(Long width) {
        this.width = width;
    }

    public Long getHeigh() {
        return heigh;
    }

    public void setHeigh(Long heigh) {
        this.heigh = heigh;
    }
}
