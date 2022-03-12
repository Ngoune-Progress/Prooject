package com.example.manlib.adapter.CategoriesAdapter;

public class CategoryAdapter {
    int img;
    String nom_categorie;

    public int getImg() {
        return img;
    }

    public String getNom_categorie() {
        return nom_categorie;
    }

    public CategoryAdapter(int img, String nom_categorie) {
        this.img = img;
        this.nom_categorie = nom_categorie;
    }
}
