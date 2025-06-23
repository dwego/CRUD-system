package com.dweg0.crud.crudsystem.adapter.outbound.persistence.entity;

import com.dweg0.crud.crudsystem.core.domain.Tag;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tags")
@Getter
@Setter
@NoArgsConstructor
public class TagEntity {

    @Id
    private String tag;

    public TagEntity(String tag) {
        this.tag = tag;
    }

    public static TagEntity fromDomain(Tag domainTag) {
        return new TagEntity(domainTag.getTag());
    }

    public Tag toDomain() {
        return new Tag(this.tag);
    }
}
