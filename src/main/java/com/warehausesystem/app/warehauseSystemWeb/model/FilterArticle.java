package com.warehausesystem.app.warehauseSystemWeb.model;

public class FilterArticle {
    public String kind;
    public String color;
    public String id;
    public String name;

    public String getKind() {
        if(kind == null)
            return "";
        else return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getColor() {
        if(color == null)
            return "";
        else return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        if(id == null)
            return "";
        else return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        if(name == null)
            return "";
        else return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
