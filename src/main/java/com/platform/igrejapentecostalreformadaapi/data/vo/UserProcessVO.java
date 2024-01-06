package com.platform.igrejapentecostalreformadaapi.data.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.platform.igrejapentecostalreformadaapi.entities.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class UserProcessVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private boolean hasContact;

    private boolean hasAddress;

    private boolean hasDocument;

    private boolean hasFamily;

    private boolean hasEducation;

    private boolean hasMember;

    private boolean hasBaptism;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "America/Sao_Paulo")
    private Date updatedAt;

    @JsonIgnore
    private User user;

    public UserProcessVO() {
    }

    public UserProcessVO(boolean hasContact, boolean hasAddress, boolean hasDocument, boolean hasFamily, boolean hasEducation, boolean hasMember, boolean hasBaptism, Date createdAt, Date updatedAt) {
        this.hasContact = hasContact;
        this.hasAddress = hasAddress;
        this.hasDocument = hasDocument;
        this.hasFamily = hasFamily;
        this.hasEducation = hasEducation;
        this.hasMember = hasMember;
        this.hasBaptism = hasBaptism;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getUserId() {
        return this.user.getId();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getHasContact() {
        return hasContact;
    }

    public void setHasContact(boolean hasContact) {
        this.hasContact = hasContact;
    }

    public boolean getHasAddress() {
        return hasAddress;
    }

    public void setHasAddress(boolean hasAddress) {
        this.hasAddress = hasAddress;
    }

    public boolean getHasDocument() {
        return hasDocument;
    }

    public void setHasDocument(boolean hasDocument) {
        this.hasDocument = hasDocument;
    }

    public boolean getHasFamily() {
        return hasFamily;
    }

    public void setHasFamily(boolean hasFamily) {
        this.hasFamily = hasFamily;
    }

    public boolean getHasEducation() {
        return hasEducation;
    }

    public void setHasEducation(boolean hasEducation) {
        this.hasEducation = hasEducation;
    }

    public boolean getHasMember() {
        return hasMember;
    }

    public void setHasMember(boolean hasMember) {
        this.hasMember = hasMember;
    }

    public boolean getHasBaptism() {
        return hasBaptism;
    }

    public void setHasBaptism(boolean hasBaptism) {
        this.hasBaptism = hasBaptism;
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
        if (!(o instanceof UserProcessVO that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

