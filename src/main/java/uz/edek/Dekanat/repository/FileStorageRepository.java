package uz.edek.Dekanat.repository;



import org.springframework.stereotype.Repository;
import uz.edek.Dekanat.entity.FileStatus;
import uz.edek.Dekanat.entity.FileStorage;

import java.util.List;

@Repository
public interface FileStorageRepository extends DistributedRepository<FileStorage>{
    FileStorage findByHashId(String hashId);
    List<FileStorage> findAllByStatus(FileStatus status);

}
