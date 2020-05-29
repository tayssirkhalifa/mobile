/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionproduit;

import java.util.Date;

/**
 *
 * @author asus
 */
public class product {
      private int id;
    private String nom;
    private String description;
    private double prix;
    private int quantite;
    private String image;
   private String type;
   private String taille;
   private int views;
    private int nbrAchat;
    private int categorie_id;
    private String marque;
    private Date date;
    private String sexe;

    public product(int id, String nom, String description, double prix, int quantite, String image, String type, String taille, int views, int nbrAchat, int categorie_id, String marque, String sexe) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.type = type;
        this.taille = taille;
        this.views = views;
        this.nbrAchat = nbrAchat;
        this.categorie_id = categorie_id;
        this.marque = marque;
        this.sexe = sexe;
    }
       public product(int id, String nom, String description, double prix, int quantite, String image, String type, String taille, int views, int nbrAchat, String marque, String sexe) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.type = type;
        this.taille = taille;
        this.views = views;
        this.nbrAchat = nbrAchat;
        this.marque = marque;
        this.sexe = sexe;
    }

    public product(int id, String nom, double prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public product(String nom, double prix) {
        this.nom = nom;
        this.prix = prix;
    }

    public product(int id, String nom, String description, double prix, int quantite, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
    }

    public product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTaille() {
        return taille;
    }

    public void setTaille(String taille) {
        this.taille = taille;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getNbrAchat() {
        return nbrAchat;
    }

    public void setNbrAchat(int nbrAchat) {
        this.nbrAchat = nbrAchat;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public product(int id, String nom, String description, double prix, int quantite, String image, String type, String taille, int views, int nbrAchat, String marque, Date date, String sexe) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
        this.type = type;
        this.taille = taille;
        this.views = views;
        this.nbrAchat = nbrAchat;
        this.marque = marque;
        this.date = date;
        this.sexe = sexe;
    }

   


    
}
