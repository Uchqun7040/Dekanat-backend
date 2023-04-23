package uz.edek.Dekanat.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Oqituvchi extends DistributedEntity{
    private String ism;
    private String familiya;
    private String tel;
    private Lavozim lavozim;
    @ManyToOne
    private Kafedra kafedra;
    private String ilmiyDaraja;
    private String mutaxasislik;

    public String getIsm() {
        return ism;
    }

    public void setIsm(String ism) {
        this.ism = ism;
    }

    public String getFamiliya() {
        return familiya;
    }

    public void setFamiliya(String familiya) {
        this.familiya = familiya;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Lavozim getLavozim() {
        return lavozim;
    }

    public void setLavozim(Lavozim lavozim) {
        this.lavozim = lavozim;
    }

    public Kafedra getKafedra() {
        return kafedra;
    }

    public void setKafedra(Kafedra kafedra) {
        this.kafedra = kafedra;
    }

    public String getMutaxasislik() {
        return mutaxasislik;
    }

    public void setMutaxasislik(String mutaxasislik) {
        this.mutaxasislik = mutaxasislik;
    }

    public String getIlmiyDaraja() {
        return ilmiyDaraja;
    }

    public void setIlmiyDaraja(String ilmiyDaraja) {
        this.ilmiyDaraja = ilmiyDaraja;
    }
}
