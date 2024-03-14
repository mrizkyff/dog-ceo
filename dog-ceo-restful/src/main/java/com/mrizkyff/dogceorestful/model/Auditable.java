package com.mrizkyff.dogceorestful.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Setter
@Getter
@SuperBuilder
@EntityListeners ( {AuditingEntityListener.class} )
public abstract class Auditable {
    public Auditable() {
    }

    public Auditable(UUID id , Instant createdDate , Instant lastModifiedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    @Id
    @GeneratedValue ( strategy = GenerationType.UUID )
    private UUID id;

    @CreatedDate
    @Column ( name = "created_date" )
    private Instant createdDate;

    @LastModifiedDate
    @Column ( name = "last_modified_date" )
    private Instant lastModifiedDate;

}