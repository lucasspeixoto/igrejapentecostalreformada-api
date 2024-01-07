package com.platform.igrejapentecostalreformadaapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "has_contact", nullable = false)
    private boolean hasContact;

    @Column(name = "has_address", nullable = false)
    private boolean hasAddress;

    @Column(name = "has_document", nullable = false)
    private boolean hasDocument;

    @Column(name = "has_family", nullable = false)
    private boolean hasFamily;

    @Column(name = "has_education", nullable = false)
    private boolean hasEducation;

    @Column(name = "has_member", nullable = false)
    @ColumnDefault("false")
    private boolean hasMember;

    @Column(name = "has_baptism", nullable = false)
    private boolean hasBaptism;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public UserProcess() {
    }

    public UserProcess(Long id, boolean hasContact, boolean hasAddress, boolean hasDocument, boolean hasFamily, boolean hasEducation, boolean hasMember, boolean hasBaptism, User user) {
        this.id = id;
        this.hasContact = hasContact;
        this.hasAddress = hasAddress;
        this.hasDocument = hasDocument;
        this.hasFamily = hasFamily;
        this.hasEducation = hasEducation;
        this.hasMember = hasMember;
        this.hasBaptism = hasBaptism;
        this.user = user;
    }

    public UserProcess(boolean hasContact, boolean hasAddress, boolean hasDocument, boolean hasFamily, boolean hasEducation, boolean hasMember, boolean hasBaptism, User user) {
        this.hasContact = hasContact;
        this.hasAddress = hasAddress;
        this.hasDocument = hasDocument;
        this.hasFamily = hasFamily;
        this.hasEducation = hasEducation;
        this.hasMember = hasMember;
        this.hasBaptism = hasBaptism;
        this.user = user;
    }

    public Long getUserId() {
        return user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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


}

