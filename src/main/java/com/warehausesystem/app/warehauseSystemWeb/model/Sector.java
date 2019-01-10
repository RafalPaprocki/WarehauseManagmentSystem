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
    private Integer width;

    @NotNull
    private Integer heigh;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "sector")
    private Compartment compartment;


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
}
