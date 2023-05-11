package uz.edek.Dekanat.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
public class FileStorage extends DistributedEntity {
    private String name;
    private String extension;
    private Long fileSize;
    private String hashId;
    private String contentType;
    private String uploadPath;
    @Enumerated(EnumType.STRING)
    private FileStatus status;
}