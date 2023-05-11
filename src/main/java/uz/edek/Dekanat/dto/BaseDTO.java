package uz.edek.Dekanat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class BaseDTO {

    private Long id;

    private LocalDateTime created;

    public BaseDTO(Long id, LocalDateTime created){
        this.id = id;
        this.created = created;
    }

}
