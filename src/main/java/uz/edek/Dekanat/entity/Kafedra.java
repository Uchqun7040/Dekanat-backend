package uz.edek.Dekanat.entity;

import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
public class Kafedra extends DistributedEntity{
    private String nom;
}
