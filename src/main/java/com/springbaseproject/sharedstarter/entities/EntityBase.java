package com.springbaseproject.sharedstarter.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@MappedSuperclass
public interface EntityBase<ID> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    ID getId();
    void setId(ID id);

    @Version
    Long getVersion();
    void setVersion(Long version);

    @Column(name = "created_at", nullable = false, updatable = false)
    LocalDateTime getCreatedAt();
    void setCreatedAt(LocalDateTime createdAt);

    @Column(name = "updated_at")
    LocalDateTime getUpdatedAt();
    void setUpdatedAt(LocalDateTime updatedAt);
}
