package uz.edek.Dekanat.entity;

import javax.persistence.Entity;

@Entity
public class Kafedra extends DistributedEntity{
    private String nom;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
