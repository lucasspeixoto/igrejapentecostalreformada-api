package com.platform.igrejapentecostalreformadaapi.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "user_process")
public class UserProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="has_contact", nullable = false)
    private boolean hasContact;

    @Column(name="has_address", nullable = false)
    private boolean hasAddress;

    @Column(name="has_document", nullable = false)
    private boolean hasDocument;

    @Column(name="has_family", nullable = false)
    private boolean hasFamily;

    @Column(name="has_education", nullable = false)
    private boolean hasEducation;

    @Column(name="has_member", nullable = false)
    @ColumnDefault("false")
    private boolean hasMember;

    @Column(name="has_baptism", nullable = false)
    private boolean hasBaptism;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name="updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @Column(name="user_id", nullable = false, unique = true)
    private Long userId;

    public UserProcess() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public boolean isHasContact() {
        return hasContact;
    }

    public void setHasContact(boolean hasContact) {
        this.hasContact = hasContact;
    }

    public boolean isHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(boolean hasAddress) {
        this.hasAddress = hasAddress;
    }

    public boolean isHasDocument() {
        return hasDocument;
    }

    public void setHasDocument(boolean hasDocument) {
        this.hasDocument = hasDocument;
    }

    public boolean isHasFamily() {
        return hasFamily;
    }

    public void setHasFamily(boolean hasFamily) {
        this.hasFamily = hasFamily;
    }

    public boolean isHasEducation() {
        return hasEducation;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public boolean isHasMember() {
        return hasMember;
    }

    public void setHasMember(boolean hasMember) {
        this.hasMember = hasMember;
    }

    public boolean isHasBaptism() {
        return hasBaptism;
    }

    public void setHasBaptism(boolean hasBaptism) {
        this.hasBaptism = hasBaptism;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserProcess contact)) return false;
        return Objects.equals(getId(), contact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}

