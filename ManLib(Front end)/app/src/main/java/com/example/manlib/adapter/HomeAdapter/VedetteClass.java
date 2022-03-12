package com.example.manlib.adapter.HomeAdapter;

public class VedetteClass {

    int image;
    float rate;

    String titre, description;



    public VedetteClass(int image, String titre, String description, float rate) {
        this.image = image;
        this.titre = titre;
        this.description = description;
        this.rate = rate;
    }

    public int getImage() {
        return image;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public float getRate() {
        return rate;
    }
}
