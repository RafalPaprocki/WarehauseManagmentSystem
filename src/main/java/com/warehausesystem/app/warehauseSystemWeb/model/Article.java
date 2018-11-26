package com.warehausesystem.app.warehauseSystemWeb.model;

import javax.persistence.*;

@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String kind;
    private String color;
    private Double heigh;
    private Double width;
    private String name;
    private String descryption;
    private Long quantity;

    @ManyToOne
    @JoinColumn(name="compartment_id")
    private Compartment compartments;

    public Article(Long id, String kind, String kolor, Double heigh, Double width,
                   String name, String descryption, Long quantity, Compartment comp){
        this.id = id;
        this.kind = kind;
        this.color = kolor;
        this.heigh = heigh;
        this.width = width;
        this.name = name;
        this.descryption = descryption;
        this.quantity = quantity;
        this.compartments = comp;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getHeigh() {
        return heigh;
    }

    public void setHeigh(Double heigh) {
        this.heigh = heigh;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Compartment getCompartments() {
        return compartments;
    }

    public void setCompartments(Compartment compartments) {
        this.compartments = compartments;
    }
}
