package com.meca.card.repository;

import com.meca.card.entity.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findByFolderId(String folderId);

    List<Card> findByFolderIdAndIsDeleted(String folderId, boolean isDeleted);
}
