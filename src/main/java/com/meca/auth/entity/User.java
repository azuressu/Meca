package com.meca.auth.entity;

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
@Document(collection = "meca_user")
public class User extends AuditingDocument {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String nickname;

    @Field
    private String password;

}
