package com.airbus.hackathon.entity;


import com.airbus.hackathon.util.DateUtil;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@MappedSuperclass
public abstract class AbstractEntity<T> {

    T id;

    @Column(nullable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false, insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.updatedAt = this.createdAt = DateUtil.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public T getId() {
        return id;
    }

    public AbstractEntity<T> setId(T id) {
        this.id = id;
        return this;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = DateUtil.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public AbstractEntity<T> setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public AbstractEntity<T> setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public String formattedCreatedAt() {
        if (createdAt != null) {
            return DateUtil.toString(createdAt.truncatedTo(ChronoUnit.SECONDS), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return null;
    }

    public String formattedUpdatedAt() {
        if (updatedAt != null) {
            return DateUtil.toString(updatedAt.truncatedTo(ChronoUnit.SECONDS), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
        return null;
    }
}
