package com.warehausesystem.app.warehauseSystemWeb.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "gloveCompartment")
public class GloveCompartment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long number;
    private String location;
    private Long articleQuantity;
    @ManyToOne
    @JoinColumn(name="article_id")
    private Article article;
    //private Set<Article> articleSet;

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

//    public Set<Article> getArticleSet() {
//        return articleSet;
//    }
//
//    public void setArticleSet(Set<Article> articleSet) {
//        this.articleSet = articleSet;
//    }
}
