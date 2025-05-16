package com.meca.folder.repository;

import com.meca.folder.entity.Folder;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FolderRepository extends MongoRepository<Folder, String> {
    List<Folder> findByUsername(String username);
}
