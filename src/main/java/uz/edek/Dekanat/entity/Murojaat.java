package uz.edek.Dekanat.entity;

import javax.persistence.*;

@Entity
public class Murojaat extends DistributedEntity{
    @ManyToOne
    private Talaba talaba;
    @ManyToOne
    private Oqituvchi oqituvchi;
    private String mavzu;
    @Lob
    private String matn;
    private String fayl;
    private String spravka;
    @Enumerated(EnumType.STRING)
    private MurojaatHolat holat;
    @Lob
    private String xulosa;

    public Talaba getTalaba() {
        return talaba;
    }

    public void setTalaba(Talaba talaba) {
        this.talaba = talaba;
    }

    public String getMavzu() {
        return mavzu;
    }

    public void setMavzu(String mavzu) {
        this.mavzu = mavzu;
    }

    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public String getFayl() {
        return fayl;
    }

    public void setFayl(String fayl) {
        this.fayl = fayl;
    }

    public MurojaatHolat getHolat() {
        return holat;
    }

    public void setHolat(MurojaatHolat holat) {
        this.holat = holat;
    }

    public Oqituvchi getOqituvchi() {
        return oqituvchi;
    }

    public void setOqituvchi(Oqituvchi oqituvchi) {
        this.oqituvchi = oqituvchi;
    }

    public String getXulosa() {
        return xulosa;
    }

    public void setXulosa(String xulosa) {
        this.xulosa = xulosa;
    }

    public String getSpravka() {
        return spravka;
    }

    public void setSpravka(String spravka) {
        this.spravka = spravka;
    }
}
