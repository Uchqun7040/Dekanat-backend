package uz.edek.Dekanat.service.withoutDto;


import uz.edek.Dekanat.entity.Talaba;

public interface TalabaService extends CommonService<Talaba>{

    public Talaba getByHemisId(String id);
}
