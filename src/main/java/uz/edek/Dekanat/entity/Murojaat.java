package uz.edek.Dekanat.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
}
