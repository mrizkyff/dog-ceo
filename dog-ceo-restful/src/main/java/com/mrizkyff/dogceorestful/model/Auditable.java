package com.mrizkyff.dogceorestful.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat (shape = JsonFormat.Shape.STRING)
    private UUID id;

    @CreatedDate
    @Column ( name = "created_date" )
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Instant createdDate;

    @LastModifiedDate
    @Column ( name = "last_modified_date" )
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Jakarta")
    private Instant lastModifiedDate;

}