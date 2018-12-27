package com.warehausesystem.app.warehauseSystemWeb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Set;

//tylko jeden artykuł danego rodzaju
@Entity
@Table(name = "compartments")
public class Compartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private String location;
    private Long articleQuantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "article_id", nullable = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Article article;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Long getArticleQuantity() {
        return articleQuantity;
    }

    public void setArticleQuantity(Long articleQuantity) {
        this.articleQuantity = articleQuantity;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

}
