package uz.edek.Dekanat.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
public class Oqituvchi extends DistributedEntity{
    private String ism;

    private String familiya;

    private String tel;

    @ElementCollection(targetClass = Lavozim.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "oqituvchi_lavozim",
            joinColumns = @JoinColumn(name = "oqituvchi_id"))
    @Column(name = "lavozim_id")
    private Set<Lavozim> lavozim;

    @ManyToOne
    private Kafedra kafedra;

    private String ilmiyDaraja;

    private String mutaxasislik;

    @Size(max = 30, min = 6)
    @Column(unique = true, nullable = false)
    private String login;

    @Size(max = 60, min = 60)
    @Column(nullable = false)
    private String parol;
}
