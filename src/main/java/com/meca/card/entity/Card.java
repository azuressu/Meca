package com.meca.card.entity;

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
@Document(collection = "meca_card")
public class Card extends AuditingDocument {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String front;

    @Field
    private String back;

    @Field
    private String folderId;

    @Field
    private Boolean memorised;

    public void updateCardInfo(String front, String back) {
        this.front = front;
        this.back = back;
    }

}
