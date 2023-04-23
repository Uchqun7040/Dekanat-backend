package uz.edek.Dekanat.entity;

import javax.persistence.Entity;

@Entity
public class Talaba extends DistributedEntity{
    private String ism;
    private String familiya;
    private String sharif;
    private String hemisId;

    private String yonalish;

    private String talimShakl;

    private String talimTur;

    private String guruh;

    private String fakultet;

    private String bosqich;

    private String manzil;


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

    public String getSharif() {
        return sharif;
    }

    public void setSharif(String sharif) {
        this.sharif = sharif;
    }

    public String getHemisId() {
        return hemisId;
    }

    public void setHemisId(String hemisId) {
        this.hemisId = hemisId;
    }

    public String getYonalish() {
        return yonalish;
    }

    public void setYonalish(String yonalish) {
        this.yonalish = yonalish;
    }

    public String getTalimShakl() {
        return talimShakl;
    }

    public void setTalimShakl(String talimShakl) {
        this.talimShakl = talimShakl;
    }

    public String getTalimTur() {
        return talimTur;
    }

    public void setTalimTur(String talimTur) {
        this.talimTur = talimTur;
    }

    public String getGuruh() {
        return guruh;
    }

    public void setGuruh(String guruh) {
        this.guruh = guruh;
    }

    public String getFakultet() {
        return fakultet;
    }

    public void setFakultet(String fakultet) {
        this.fakultet = fakultet;
    }

    public String getBosqich() {
        return bosqich;
    }

    public void setBosqich(String bosqich) {
        this.bosqich = bosqich;
    }

    public String getManzil() {
        return manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }
}
