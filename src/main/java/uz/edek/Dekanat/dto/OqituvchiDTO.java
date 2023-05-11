package uz.edek.Dekanat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import uz.edek.Dekanat.entity.Kafedra;
import uz.edek.Dekanat.entity.Lavozim;
import uz.edek.Dekanat.entity.Oqituvchi;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
public class OqituvchiDTO extends BaseDTO{
    private String ism;

    private String familiya;

    private String tel;

    private Lavozim lavozim;

    private Kafedra kafedra;

    private String ilmiyDaraja;

    private String mutaxasislik;

    private String login;

    public OqituvchiDTO(Oqituvchi oqituvchi){
        super(oqituvchi.getId(), oqituvchi.getCreated());
        this.ism = oqituvchi.getIsm();
        this.familiya = oqituvchi.getFamiliya();
        this.tel = oqituvchi.getTel();
        this.kafedra = oqituvchi.getKafedra();
        this.ilmiyDaraja = oqituvchi.getIlmiyDaraja();
        this.mutaxasislik = oqituvchi.getMutaxasislik();
        this.login = oqituvchi.getLogin();
    }
}
