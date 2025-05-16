package com.meca.folder.entity;

import com.meca.common.entity.AuditingDocument;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "meca_folder")
public class Folder extends AuditingDocument {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String name;

}
