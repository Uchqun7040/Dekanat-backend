package uz.edek.Dekanat.controller.withDTO;


import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.edek.Dekanat.dto.BaseDTO;
import uz.edek.Dekanat.entity.DistributedEntity;
import uz.edek.Dekanat.service.withDto.CommonServiceDto;

public abstract class AbstractDTOController<ENTITY extends DistributedEntity, DTO extends BaseDTO> {
    private final CommonServiceDto<ENTITY, DTO> service;

    public AbstractDTOController(CommonServiceDto<ENTITY, DTO> service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable));
    }

    @GetMapping("/full")
    public ResponseEntity<?> getAllList(){
        return ResponseEntity.ok(service.getAllList());
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ENTITY entity) throws Exception {
        return new ResponseEntity<>(service.create(entity), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody ENTITY entity) throws Exception {
        return new ResponseEntity<>(service.update(entity), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(service.delete(id)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.getById(id));
    }

}
